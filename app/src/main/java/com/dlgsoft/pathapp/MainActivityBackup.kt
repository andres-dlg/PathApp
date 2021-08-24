package com.dlgsoft.pathapp

/*
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity: AppCompatActivity(), OnClickListener {

    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }
    private val progress by lazy { findViewById<TextView>(R.id.progress) }

    private var sections = createSections()

    private var breadcrumb = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToNextFragment(false)
        /*supportFragmentManager.addOnBackStackChangedListener {
            supportFragmentManager.fragments.getOrNull(0)?.let { currentFragment ->
                val progress = fragments.first { it.tag == currentFragment.tag }.percentage
                progressBar.progress = progress
            }
        }*/
    }

    private fun navigateToFragment(section: Section, tag: String) {
        // Get fragment and index
        val pair = getFragmentDataByTag(tag)
        val fragmentData = pair.first
        val index = pair.second

        // Add tag to breadcrumb
        breadcrumb.add(0, tag)

        // Calculate and show progress
        val percentage = fragmentData.getPercentage(section.number, index)
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


        /*if (fragmentData.shouldAddToBackStack) {
            fragmentTransaction.replace(
                R.id.container,
                fragment,
                fragmentData.tag
            ).addToBackStack(fragmentData.tag).commit()
        } else {
            fragmentTransaction.replace(
                R.id.container,
                fragment,
                fragmentData.tag
            ).commit()
        }*/
    }

    private fun createSections(): List<Section> =
        listOf(
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment1.newInstance() },
                        Fragment1.TAG,
                        1.0
                    ),
                ),
                number = 1
            ),
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment2.newInstance() },
                        Fragment2.TAG,
                        1.0
                    ),
                ),
                number = 2
            ),
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment3.newInstance() },
                        Fragment3.TAG,
                        1.0
                    ),
                ),
                number = 3
            ),
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment4.newInstance() },
                        Fragment4.TAG,
                        1.0
                    ),
                    FragmentData(
                        lazy { Fragment4A.newInstance() },
                        Fragment4A.TAG,
                        0.5
                    ),
                    FragmentData(
                        lazy { Fragment4B.newInstance() },
                        Fragment4B.TAG,
                        0.5
                    ),
                ),
                number = 4
            ),
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment5.newInstance() },
                        Fragment5.TAG,
                        1.0
                    ),
                ),
                number = 5
            ),
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment6.newInstance() },
                        Fragment6.TAG,
                        1.0
                    ),
                ),
                number = 6
            ),
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment7.newInstance() },
                        Fragment7.TAG,
                        1.0
                    ),
                    FragmentData(
                        lazy { Fragment7A.newInstance() },
                        Fragment7A.TAG,
                        0.5
                    ),
                    FragmentData(
                        lazy { Fragment7B.newInstance() },
                        Fragment7B.TAG,
                        0.5
                    ),
                ),
                number = 7
            ),
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment8.newInstance() },
                        Fragment8.TAG,
                        1.0
                    ),
                ),
                number = 8
            ),
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment9.newInstance() },
                        Fragment9.TAG,
                        1.0
                    ),
                ),
                number = 9
            ),
            Section(
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment10.newInstance() },
                        Fragment10.TAG,
                        1.0
                    ),
                ),
                number = 10
            ),
        )

    override fun navigateToNextFragment(isForked: Boolean) {
        val nextTag = if (isForked) {
            getNextForkedTag()
        } else {
            getNextTag()
        }
        navigateToFragment(getSectionByTag(nextTag), nextTag)
    }

    private fun getNextTag(): String {
        return if (breadcrumb.isEmpty()) {
            Fragment1.TAG
        } else {
            when (breadcrumb[0]) {
                Fragment1.TAG -> Fragment2.TAG
                Fragment2.TAG -> Fragment3.TAG
                Fragment3.TAG -> Fragment4.TAG
                Fragment4.TAG -> Fragment5.TAG
                Fragment4A.TAG -> Fragment4B.TAG
                Fragment4B.TAG -> Fragment5.TAG
                Fragment5.TAG -> Fragment6.TAG
                Fragment6.TAG -> Fragment7.TAG
                Fragment7.TAG -> Fragment8.TAG
                Fragment7A.TAG -> Fragment7B.TAG
                Fragment7B.TAG -> Fragment8.TAG
                Fragment8.TAG -> Fragment9.TAG
                Fragment9.TAG -> Fragment10.TAG
                else -> Fragment1.TAG
            }
        }
    }

    private fun getNextForkedTag(): String {
        return when (breadcrumb[0]) {
            Fragment3.TAG -> Fragment4A.TAG
            Fragment6.TAG -> Fragment7A.TAG
            else -> ""
        }
    }

    private fun getSectionByTag(tag: String) = sections.first { section ->
        section.fragments.any { it.tag == tag }
    }

    private fun getFragmentDataByTag(tag: String): Pair<FragmentData, Int> {
        lateinit var pair: Pair<FragmentData, Int>
        sections.forEach { section ->
            section.fragments.forEachIndexed { index, fragmentData ->
                if (fragmentData.tag == tag) {
                    pair = Pair(fragmentData, index)
                }
            }
        }
        return pair
    }

    private fun animateProgress(progressValue: Int) {
        ObjectAnimator.ofInt(progressBar, "progress", progressValue)
            .setDuration(500)
            .start()
    }

    override fun onBackPressed() {
        val currentFragment = breadcrumb[0]
        if (currentFragment == Fragment8.TAG && breadcrumb[1] == Fragment7.TAG) {
            breadcrumb.remove(Fragment7.TAG)
            breadcrumb.remove(Fragment6.TAG)
            Toast.makeText(this, "Back to 5", Toast.LENGTH_SHORT).show()
        } else if (currentFragment == Fragment7A.TAG && breadcrumb[1] == Fragment6.TAG) {
            breadcrumb.remove(Fragment6.TAG)
            Toast.makeText(this, "Back to 5", Toast.LENGTH_SHORT).show()
        }
        breadcrumb.removeAt(0)
        if (breadcrumb.isEmpty()) {
            super.onBackPressed()
        } else {
            if (breadcrumb[0] != currentFragment) {
                navigateToFragment(getSectionByTag(breadcrumb[0]), breadcrumb[0])
                breadcrumb.removeAt(0)
            }
        }
    }
}

data class Section(
    val fragments: List<FragmentData>,
    val number: Int
)

data class FragmentData(
    val fragment: Lazy<Fragment>,
    val tag: String,
    val weight: Double,
) {
    fun getPercentage(sectionNumber: Int, fragmentPositionInSection: Int): Int {
        var basePercentage = sectionNumber * 10
        val comparator = weight * fragmentPositionInSection
        if (comparator < 1.0) {
            basePercentage -= (comparator * 10).toInt()
        }
        return basePercentage
    }
}

interface OnClickListener {
    fun navigateToNextFragment(isForked: Boolean)
}*/