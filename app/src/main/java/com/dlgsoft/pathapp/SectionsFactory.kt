package com.dlgsoft.pathapp

import com.dlgsoft.pathapp.fragments.Fragment1
import com.dlgsoft.pathapp.fragments.Fragment10
import com.dlgsoft.pathapp.fragments.Fragment2
import com.dlgsoft.pathapp.fragments.Fragment2A
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

class SectionsFactory {
    companion object {

        // Forks
        const val BASE = ""
        const val FORK_4 = "Fork 4"
        const val FORK_7 = "Fork 7"

        // Sections
        const val SECTION_1 = 1
        const val SECTION_2 = 2
        const val SECTION_3 = 3
        const val SECTION_4 = 4
        const val SECTION_5 = 5
        const val SECTION_6 = 6
        const val SECTION_7 = 7
        const val SECTION_8 = 8
        const val SECTION_9 = 9
        const val SECTION_10 = 10

        fun createSections(): List<Section> =
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
                /*Section(
                    fragments = listOf(
                        FragmentData(
                            lazy { Fragment2A.newInstance() },
                            Fragment2A.TAG,
                            false
                        ),
                    ),
                ),*/
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
            )
    }
}