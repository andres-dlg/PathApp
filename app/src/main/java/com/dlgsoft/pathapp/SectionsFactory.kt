package com.dlgsoft.pathapp

import com.dlgsoft.pathapp.fragments.Fragment1
import com.dlgsoft.pathapp.fragments.Fragment10
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
import com.dlgsoft.pathapp.fragments.Fragment9
import com.dlgsoft.pathapp.fragments.Fragment9A
import com.dlgsoft.pathapp.fragments.Fragment9B

class SectionsFactory {

    fun generateSections() = listOf(
        Section(
            id = SECTION_1,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment1.newInstance() },
                    Fragment1.TAG,
                    SectionsManager.BASE
                ),
            ),
        ),
        Section(
            id = SECTION_2,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment2.newInstance() },
                    Fragment2.TAG,
                    SectionsManager.BASE
                ),
            ),
        ),
        Section(
            id = SECTION_3,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment3.newInstance() },
                    Fragment3.TAG,
                    SectionsManager.BASE
                ),
            ),
        ),
        Section(
            id = SECTION_4,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment4.newInstance() },
                    Fragment4.TAG,
                    SectionsManager.BASE
                ),
                FragmentData(
                    lazy { Fragment4A.newInstance() },
                    Fragment4A.TAG,
                    SectionsManager.FORK_4
                ),
                FragmentData(
                    lazy { Fragment4B.newInstance() },
                    Fragment4B.TAG,
                    SectionsManager.FORK_4
                ),
            ),
        ),
        Section(
            id = SECTION_5,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment5.newInstance() },
                    Fragment5.TAG,
                    SectionsManager.BASE
                ),
            ),
        ),
        Section(
            id = SECTION_6,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment6.newInstance() },
                    Fragment6.TAG,
                    SectionsManager.BASE
                ),
            ),
        ),
        Section(
            id = SECTION_7,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment7.newInstance() },
                    Fragment7.TAG,
                    SectionsManager.BASE
                ),
                FragmentData(
                    lazy { Fragment7A.newInstance() },
                    Fragment7A.TAG,
                    SectionsManager.FORK_7
                ),
                FragmentData(
                    lazy { Fragment7B.newInstance() },
                    Fragment7B.TAG,
                    SectionsManager.FORK_7
                ),
            ),
        ),
        Section(
            id = SECTION_8,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment8.newInstance() },
                    Fragment8.TAG,
                    SectionsManager.BASE
                ),
            ),
        ),
        Section(
            id = SECTION_9,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment9.newInstance() },
                    Fragment9.TAG,
                    SectionsManager.BASE
                ),
                FragmentData(
                    lazy { Fragment9A.newInstance() },
                    Fragment9A.TAG,
                    SectionsManager.FORK_9
                ),
                FragmentData(
                    lazy { Fragment9B.newInstance() },
                    Fragment9B.TAG,
                    SectionsManager.FORK_9_NOT_PROGRESS,
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
                    SectionsManager.BASE
                ),
            ),
        ),
        Section(
            id = SECTION_11,
            fragments = listOf(
                FragmentData(
                    lazy { Fragment11.newInstance() },
                    Fragment11.TAG,
                    SectionsManager.BASE
                ),
            ),
        ),
    )

    companion object {
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