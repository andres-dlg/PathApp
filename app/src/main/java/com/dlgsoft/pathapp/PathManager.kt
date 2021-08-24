package com.dlgsoft.pathapp

import com.dlgsoft.pathapp.SectionsManager.Companion.BASE
import com.dlgsoft.pathapp.fragments.Fragment6
import com.dlgsoft.pathapp.fragments.Fragment7
import com.dlgsoft.pathapp.fragments.Fragment7A
import com.dlgsoft.pathapp.fragments.Fragment8

class PathManager {

    private val sectionsManager by lazy { SectionsManager() }

    private var breadcrumb = arrayListOf<String>()

    fun addElement(tag: String) {
        breadcrumb.add(0, tag)
    }

    /**
     * Gets first element from breadcrumb. If the list is empty it returns an empty String
     */
    private fun getCurrentFragmentTag(): String = if (breadcrumb.isEmpty()) BASE else breadcrumb[0]

    /**
     * Gets next fragment tag taking into account the [forkTag] parameter. Some scenarios:
     */
    private fun getNextTag(forkTag: String, nextIsNotProgress: Boolean): String {
        val currentFragmentTag = getCurrentFragmentTag()
        var currentSection: Section? = null
        var fragmentData: FragmentData? = null
        if (currentFragmentTag.isNotBlank()) {
            currentSection = getSectionByFragmentTag(currentFragmentTag)
            fragmentData = getFragmentAndIndex(currentFragmentTag).first
        }
        return when {
            // 1) First scenario. Returns first fragment tag from first section
            currentFragmentTag.isEmpty() -> sectionsManager.getFirstFragmentDataTag(forkTag)

            // 2) Second scenario. If nextIsNotProgress is true returns first fragment with no
            // progress in current section
            nextIsNotProgress -> currentSection!!.getFirstFragmentDataTag(forkTag)

            // 3) Third scenario. If current fragment is not a fork returns first fragment of
            // next section
            !fragmentData!!.isFork() && !nextIsNotProgress -> getNextSection(currentSection!!.id).getFirstFragmentDataTag(
                forkTag
            )

            // 4) Fourth scenario. If current fragment is a fork but it is the last fragment
            // of the fork, return first fragment of next section
            currentSection!!.isLastFragmentInFork(forkTag, currentFragmentTag) -> getNextSection(
                currentSection.id
            ).getFirstFragmentDataTag(BASE)

            // 5) Fifth scenario. If current fragment is a fork but it is not the last fragment
            // of the fork, return next fragment in the fork
            else -> {
                val nextFragmentData =
                    currentSection.getNextFragmentInFork(forkTag, currentFragmentTag)
                nextFragmentData.tag
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
    fun getSectionByFragmentTag(fragmentTag: String) =
        sectionsManager.getSectionByFragmentTag(fragmentTag)

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
    fun getSectionById(sectionId: Int) = sectionsManager.getSectionById(sectionId)

    /**
     * Returns next fragment based on [forkTag]
     */
    fun getNextFragmentTag(forkTag: String, nextIsNotProgress: Boolean): String {
        return getNextTag(forkTag, nextIsNotProgress)
    }

    /**
     * Returns a Pair containing a fragment and its index within its section
     */
    fun getFragmentAndIndex(tag: String): Pair<FragmentData, Int> =
        sectionsManager.getFragmentAndIndex(tag)

    /**
     * Returns current section based on current fragment tag
     */
    fun getCurrentSection(): Section {
        val currentFragmentTag = getCurrentFragmentTag()
        return getSectionByFragmentTag(currentFragmentTag)
    }

    fun clearBreadcrumbFrom(tag: String) {
        val indexOfCurrentTag = breadcrumb.indexOf(tag)
        breadcrumb.subList(0, indexOfCurrentTag + 1).clear()
    }

    fun getNextNextSectionId(): Int {
        val currentSectionId = getCurrentSection().id
        return sectionsManager.getNextNextSectionId(currentSectionId)
    }

    fun getTotalSections() = sectionsManager.getTotalSections()
}