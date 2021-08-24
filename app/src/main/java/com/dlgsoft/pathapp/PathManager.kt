package com.dlgsoft.pathapp

import com.dlgsoft.pathapp.SectionsManager.Companion.BASE
import com.dlgsoft.pathapp.fragments.Fragment6
import com.dlgsoft.pathapp.fragments.Fragment7
import com.dlgsoft.pathapp.fragments.Fragment7A
import com.dlgsoft.pathapp.fragments.Fragment8

class PathManager {

    private val sectionsManager by lazy { SectionsManager() }

    val sections by lazy { sectionsManager.sections }

    private var breadcrumb = arrayListOf<String>()

    fun addElement(tag: String) {
        breadcrumb.add(0, tag)
    }

    /**
     * Gets first element from breadcrumb. If the list is empty it returns an empty String
     */
    private fun getCurrentFragmentTag(): String = if (breadcrumb.isEmpty()) "" else breadcrumb[0]

    /**
     * Gets next fragment tag taking into account the [forkTag] parameter. Some scenarios:
     */
    private fun getNextTag(forkTag: String): String {
        val currentFragmentTag = getCurrentFragmentTag()
        return if (currentFragmentTag.isEmpty()) {
            // 1) First scenario. Returns first fragment tag from first section
            sections.first().getFirstFragmentDataTag(forkTag)
        } else {
            val currentSection = getSectionByFragmentTag(currentFragmentTag)
            val fragmentData = getFragmentAndIndex(currentFragmentTag).first
            return if (!fragmentData.isFork()) {
                // 2) Second scenario. If current fragment is not a fork returns first fragment of
                // next section
                getNextSection(currentSection.id).getFirstFragmentDataTag(forkTag)
            } else {
                if (currentSection.isLastFragmentInFork(forkTag, currentFragmentTag)) {
                    // 3) Third scenario. If current fragment is a fork but it is the last fragment
                    // of the fork, return first fragment of next section
                    getNextSection(currentSection.id).getFirstFragmentDataTag(BASE)
                } else {
                    // 4) Forth scenario. If current fragment is a fork but it is not the last fragment
                    // of the fork, return next fragment in the fork
                    val nextFragmentData =
                        currentSection.getNextFragmentInFork(forkTag, currentFragmentTag)
                    nextFragmentData.tag
                }
            }
        }
    }


    /**
     * Checks current fragment and navigate back based on restrictions. TODO: Improve
     * */
    fun navigateBack(navigateBackCallback: (tag: String) -> Unit, isEmptyCallback: () -> Unit) {
        val currentFragment = breadcrumb[0]
        if (currentFragment == Fragment8.TAG && breadcrumb[1] == Fragment7.TAG) {
            breadcrumb.remove(Fragment7.TAG)
            breadcrumb.remove(Fragment6.TAG)
        } else if (currentFragment == Fragment7A.TAG && breadcrumb[1] == Fragment6.TAG) {
            breadcrumb.remove(Fragment6.TAG)
        }
        breadcrumb.removeAt(0)
        if (breadcrumb.isEmpty()) {
            isEmptyCallback()
        } else {
            if (breadcrumb[0] != currentFragment) {
                navigateBackCallback(breadcrumb[0])
                breadcrumb.removeAt(0)
            }
        }
    }


    /**
     * Returns next section based on [sectionId]
     */
    fun getNextSection(sectionId: Int) =
        sectionsManager.findNextSectionFromSectionId(sectionId)

    /**
     * Returns previous section based on [sectionId]
     */
    fun getPreviousSection(sectionId: Int) =
        sectionsManager.findPreviousSectionFromSectionId(sectionId)

    /**
     * Returns a section based on [fragmentTag]. For example: If the fragment with id = 5 is in the
     * section = 2, this method will return the tag corresponding to section = 2
     */
    fun getSectionByFragmentTag(fragmentTag: String) = sections.first { section ->
        section.fragments.any { it.tag == fragmentTag }
    }

    /**
     * Returns the first fragment of the section with id = [sectionId]
     */
    fun getFirstFragmentTagBySectionId(sectionId: Int) =
        getSectionById(sectionId).getFirstFragmentDataTag(BASE)

    /**
     * Returns the last fragment of the section with id = [sectionId]
     */
    fun getLastFragmentTagBySectionId(sectionId: Int) =
        getSectionById(sectionId).getLastFragmentDataTag(BASE)

    /**
     * Returns the section with id = [sectionId]
     */
    fun getSectionById(sectionId: Int) = sections.first { it.id == sectionId }

    /**
     * Returns next fragment based on [forkTag]
     */
    fun getNextFragmentTag(forkTag: String): String {
        return getNextTag(forkTag)
    }

    /**
     * Returns a Pair containing a fragment and its index within its section
     */
    fun getFragmentAndIndex(tag: String): Pair<FragmentData, Int> {
        lateinit var pair: Pair<FragmentData, Int>
        sections.forEach { section ->
            section.fragments.forEachIndexed { index, fragmentData ->
                if (fragmentData.tag == tag) {
                    pair = Pair(fragmentData, index)
                    return@forEach
                }
            }
        }
        return pair
    }

    /**
     * Returns current section based on current fragment tag
     */
    fun getCurrentSection(): Section {
        val currentFragmentTag = getCurrentFragmentTag()
        return getSectionByFragmentTag(currentFragmentTag)
    }
}