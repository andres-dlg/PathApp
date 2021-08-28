package com.dlgsoft.pathapp.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.dlgsoft.pathapp.*
import kotlin.math.roundToInt

class PathActivity : AppCompatActivity(), OnClickListener {

    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }
    private val progress by lazy { findViewById<TextView>(R.id.progress) }
    private val path by lazy { findViewById<TextView>(R.id.path) }
    private val sectionText by lazy { findViewById<TextView>(R.id.section) }

    private val pathProvider by lazy { PathManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToNextFragment(PathType.BASE)
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
                        pathProvider.getTotalSections(),
                        pathProvider.getSectionById(section.id).id,
                        index,
                        fragmentWeight
                )
        progress.text = getString(R.string.percentage, percentage)
        animateProgress(percentage)

        // Show current path
        path.text = fragmentData.pathType.pathName
        sectionText.text = getString(R.string.section, section.id)

        // Navigate to fragment
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment = fragmentData.initFragment()
        fragmentTransaction.replace(
                R.id.container,
                fragment,
                fragmentData.tag
        )
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit()
    }

    private fun navigateToNextFragment(pathType: PathType, nextIsNotProgress: Boolean = false) {
        val nextTag = pathProvider.getNextFragmentTag(pathType, nextIsNotProgress)
        navigateToFragment(pathProvider.getSectionByFragmentTag(nextTag), nextTag)
    }

    override fun navigateToNextFragmentBase() {
        navigateToNextFragment(PathType.BASE)
    }

    override fun navigateToNextFragmentFork4() {
        navigateToNextFragment(PathType.FORK_4)
    }

    override fun navigateToNextFragmentFork7() {
        navigateToNextFragment(PathType.FORK_7)
    }

    override fun navigateToNextFragmentFork9() {
        navigateToNextFragment(PathType.FORK_9)
    }

    override fun navigateToNextFragmentFork9NoProgress() {
        navigateToNextFragment(PathType.FORK_9_NOT_PROGRESS, true)
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

    override fun skipNextSection() {
        val nextNextSectionId = pathProvider.getNextNextSectionId()
        navigateToFragment(
                pathProvider.getSectionById(nextNextSectionId),
                pathProvider.getFirstFragmentTagBySectionId(nextNextSectionId)
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

    override fun onBackPressed() {
        pathProvider.navigateBack({ tag ->
            navigateToFragment(pathProvider.getSectionByFragmentTag(tag), tag)
        }, {
            super.onBackPressed()
        })
    }
}