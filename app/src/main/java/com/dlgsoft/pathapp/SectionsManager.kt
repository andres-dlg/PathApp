package com.dlgsoft.pathapp

import com.dlgsoft.pathapp.fragments.Fragment1
import com.dlgsoft.pathapp.fragments.Fragment11
import com.dlgsoft.pathapp.fragments.Fragment2
import com.dlgsoft.pathapp.fragments.Fragment3
import com.dlgsoft.pathapp.fragments.Fragment4
import com.dlgsoft.pathapp.fragments.Fragment4A
import com.dlgsoft.pathapp.fragments.Fragment4B
import com.dlgsoft.pathapp.fragments.Fragment5
import com.dlgsoft.pathapp.fragments.Fragment6
import com.dlgsoft.pathapp.fragments.Fragment7
import com.dlgsoft.pathapp.fragments.Fragment7A
import com.dlgsoft.pathapp.fragments.Fragment7B
import com.dlgsoft.pathapp.fragments.Fragment8
import com.dlgsoft.pathapp.fragments.Fragment10
import com.dlgsoft.pathapp.fragments.Fragment9
import com.dlgsoft.pathapp.fragments.Fragment9A
import com.dlgsoft.pathapp.fragments.Fragment9B

class SectionsManager {

    val sections =
        listOf(
            Section(
                id = SECTION_1,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment1.newInstance() },
                        Fragment1.TAG,
                        BASE
                    ),
                ),
            ),
            Section(
                id = SECTION_2,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment2.newInstance() },
                        Fragment2.TAG,
                        BASE
                    ),
                ),
            ),
            Section(
                id = SECTION_3,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment3.newInstance() },
                        Fragment3.TAG,
                        BASE
                    ),
                ),
            ),
            Section(
                id = SECTION_4,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment4.newInstance() },
                        Fragment4.TAG,
                        BASE
                    ),
                    FragmentData(
                        lazy { Fragment4A.newInstance() },
                        Fragment4A.TAG,
                        FORK_4
                    ),
                    FragmentData(
                        lazy { Fragment4B.newInstance() },
                        Fragment4B.TAG,
                        FORK_4
                    ),
                ),
            ),
            Section(
                id = SECTION_5,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment5.newInstance() },
                        Fragment5.TAG,
                        BASE
                    ),
                ),
            ),
            Section(
                id = SECTION_6,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment6.newInstance() },
                        Fragment6.TAG,
                        BASE
                    ),
                ),
            ),
            Section(
                id = SECTION_7,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment7.newInstance() },
                        Fragment7.TAG,
                        BASE
                    ),
                    FragmentData(
                        lazy { Fragment7A.newInstance() },
                        Fragment7A.TAG,
                        FORK_7
                    ),
                    FragmentData(
                        lazy { Fragment7B.newInstance() },
                        Fragment7B.TAG,
                        FORK_7
                    ),
                ),
            ),
            Section(
                id = SECTION_8,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment8.newInstance() },
                        Fragment8.TAG,
                        BASE
                    ),
                ),
            ),
            Section(
                id = SECTION_9,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment9.newInstance() },
                        Fragment9.TAG,
                        BASE
                    ),
                    FragmentData(
                        lazy { Fragment9A.newInstance() },
                        Fragment9A.TAG,
                        FORK_9
                    ),
                    FragmentData(
                        lazy { Fragment9B.newInstance() },
                        Fragment9B.TAG,
                        FORK_9_NOT_PROGRESS,
                        true
                    ),
                ),
            ),
            Section(
                id = SECTION_10,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment10.newInstance() },
                        Fragment10.TAG,
                        BASE
                    ),
                ),
            ),
            Section(
                id = SECTION_11,
                fragments = listOf(
                    FragmentData(
                        lazy { Fragment11.newInstance() },
                        Fragment11.TAG,
                        BASE
                    ),
                ),
            ),
        )

    fun findNextSectionFromSectionId(sectionId: Int): Section {
        val lastSectionId = sections.last().id
        val currentSection = sections.first { it.id == sectionId }
        return if (sectionId == lastSectionId) {
            currentSection
        } else {
            val currentSectionIndex = sections.indexOf(currentSection)
            return sections[currentSectionIndex + 1]
        }
    }

    fun findPreviousSectionFromSectionId(sectionId: Int): Section {
        val firstSectionId = sections.first().id
        val currentSection = sections.first { it.id == sectionId }
        return if (sectionId == firstSectionId) {
            currentSection
        } else {
            val currentSectionIndex = sections.indexOf(currentSection)
            return sections[currentSectionIndex - 1]
        }
    }

    fun getNextNextSectionId(currentSectionId: Int): Int {
        val currentSectionIndex = sections.indexOfFirst { it.id == currentSectionId }
        return sections[currentSectionIndex + 2].id
    }

    companion object {

        // Forks
        const val BASE = ""
        const val FORK_4 = "Fork 4"
        const val FORK_7 = "Fork 7"
        const val FORK_9 = "Fork 9"
        const val FORK_9_NOT_PROGRESS = "Fork 9 Not Progress"

        // Sections
        private const val SECTION_1 = 1
        private const val SECTION_2 = 2
        private const val SECTION_3 = 3
        private const val SECTION_4 = 4
        private const val SECTION_5 = 5
        private const val SECTION_6 = 6
        private const val SECTION_7 = 7
        private const val SECTION_8 = 8
        private const val SECTION_9 = 9
        private const val SECTION_10 = 10
        private const val SECTION_11 = 11
    }
}