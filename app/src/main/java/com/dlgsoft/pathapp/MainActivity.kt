package com.dlgsoft.pathapp

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.dlgsoft.pathapp.SectionsManager.Companion.BASE
import com.dlgsoft.pathapp.SectionsManager.Companion.FORK_4
import com.dlgsoft.pathapp.SectionsManager.Companion.FORK_7
import com.dlgsoft.pathapp.SectionsManager.Companion.FORK_9
import com.dlgsoft.pathapp.SectionsManager.Companion.FORK_9_NOT_PROGRESS
import kotlin.math.roundToInt

class MainActivity: AppCompatActivity(), OnClickListener {

    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }
    private val progress by lazy { findViewById<TextView>(R.id.progress) }
    private val path by lazy { findViewById<TextView>(R.id.path) }

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
        val fragmentWeight = section.calculateFragmentWeight(fragmentData)
        val percentage =
            fragmentData.getPercentage(
                pathProvider.sections.size,
                pathProvider.getSectionById(section.id).id,
                index,
                fragmentWeight
            )
        progress.text = "${String.format("%.2f", percentage)}%"
        animateProgress(percentage)

        // Show current path
        path.text = getPath(fragmentData.forkTag)

        // Navigate to fragment
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = fragmentData.fragment.value
        fragmentTransaction.replace(
            R.id.container,
            fragment,
            fragmentData.tag
        )
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .commit()
    }

    private fun navigateToNextFragment(forkTag: String, nextIsNotProgress: Boolean = false) {
        val nextTag = pathProvider.getNextFragmentTag(forkTag, nextIsNotProgress)
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

    override fun navigateToNextFragmentFork9NoProgress() {
        navigateToNextFragment(FORK_9_NOT_PROGRESS, true)
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
        val lastFragmentTagFromPreviousSection =
            pathProvider.getLastFragmentTagBySectionId(previous.id)
        pathProvider.clearBreadcrumbFrom(lastFragmentTagFromPreviousSection)
        navigateToFragment(
            previous,
            lastFragmentTagFromPreviousSection
        )
    }

    override fun navigateToSection(id: Int) {
        navigateToFragment(
            pathProvider.getSectionById(id),
            pathProvider.getFirstFragmentTagBySectionId(id)
        )
    }

    override fun returnToBasePath() {
        onBackPressed()
    }

    private fun animateProgress(progressValue: Double) {
        ObjectAnimator.ofInt(progressBar, "progress", progressValue.roundToInt())
            .setDuration(500)
            .start()
    }

    private fun getPath(forkTag: String?): String {
        return when (forkTag) {
            BASE -> "CAMINO BASE"
            FORK_4 -> "FORK 4"
            FORK_7 -> "FORK 7"
            FORK_9 -> "FORK 9"
            FORK_9_NOT_PROGRESS -> "FORK 9 - NO SUMA PROGRESO"
            else -> ""
        }
    }

    override fun onBackPressed() {
        pathProvider.navigateBack({ tag ->
            navigateToFragment(pathProvider.getSectionByFragmentTag(tag), tag)
        }, {
            super.onBackPressed()
        })
    }
}