package com.dlgsoft.pathapp

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.dlgsoft.pathapp.SectionsManager.Companion.BASE
import com.dlgsoft.pathapp.SectionsManager.Companion.FORK_4
import com.dlgsoft.pathapp.SectionsManager.Companion.FORK_7
import com.dlgsoft.pathapp.SectionsManager.Companion.FORK_9
import kotlin.math.roundToInt

class MainActivity: AppCompatActivity(), OnClickListener {

    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }
    private val progress by lazy { findViewById<TextView>(R.id.progress) }

    private val pathProvider by lazy { PathManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToNextFragment(BASE)
    }

    private fun navigateToFragment(section: Section, tag: String) {
        // Get fragment and index
        val pair = pathProvider.getFragmentAndIndex(tag)
        val fragmentData = pair.first
        val index = pair.second

        // Add tag to breadcrumb
        pathProvider.addElement(tag)

        // Calculate and show progress
        val fragmentWeight = section.calculateFragmentWeight(fragmentData.isFork())
        val percentage =
            fragmentData.getPercentage(
                pathProvider.sections.size,
                pathProvider.getSectionById(section.id).id,
                index,
                fragmentWeight
            )
        progress.text = getString(R.string.percentage, percentage)
        animateProgress(percentage)

        // Navigate to fragment
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = fragmentData.fragment.value
        fragmentTransaction.replace(
            R.id.container,
            fragment,
            fragmentData.tag
        ).commit()
    }

    private fun navigateToNextFragment(forkTag: String) {
        val nextTag = pathProvider.getNextFragmentTag(forkTag)
        navigateToFragment(pathProvider.getSectionByFragmentTag(nextTag), nextTag)
    }

    override fun navigateToNextFragmentBase() {
        navigateToNextFragment(BASE)
    }

    override fun navigateToNextFragmentFork4() {
        navigateToNextFragment(FORK_4)
    }

    override fun navigateToNextFragmentFork7() {
        navigateToNextFragment(FORK_7)
    }

    override fun navigateToNextFragmentFork9() {
        navigateToNextFragment(FORK_9)
    }

    override fun finishSection() {
        val currentSection = pathProvider.getCurrentSection()
        val nextSection = pathProvider.getNextSection(currentSection.id)
        navigateToFragment(
            nextSection,
            pathProvider.getFirstFragmentTagBySectionId(nextSection.id)
        )
    }

    override fun cancelSection() {
        val currentSection = pathProvider.getCurrentSection()
        val previous = pathProvider.getPreviousSection(currentSection.id)
        navigateToFragment(
            previous,
            pathProvider.getLastFragmentTagBySectionId(previous.id)
        )
    }

    override fun navigateToSection(id: Int) {
        navigateToFragment(
            pathProvider.getSectionById(id),
            pathProvider.getFirstFragmentTagBySectionId(id)
        )
    }

    private fun animateProgress(progressValue: Double) {
        ObjectAnimator.ofInt(progressBar, "progress", progressValue.roundToInt())
            .setDuration(500)
            .start()
    }

    override fun onBackPressed() {
        pathProvider.navigateBack({ tag ->
            navigateToFragment(pathProvider.getSectionByFragmentTag(tag), tag)
        }, {
            super.onBackPressed()
        })
    }
}

interface OnClickListener {
    fun navigateToNextFragmentBase()
    fun navigateToNextFragmentFork4()
    fun navigateToNextFragmentFork7()
    fun navigateToNextFragmentFork9()
    fun navigateToSection(id: Int)
    fun finishSection()
    fun cancelSection()
}