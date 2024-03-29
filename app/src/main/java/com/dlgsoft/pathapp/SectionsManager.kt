package com.dlgsoft.pathapp

class SectionsManager {

    private val sections by lazy { SectionsFactory().generateSections() }

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

    fun getAfterNextSectionId(currentSectionId: Int): Int {
        val currentSectionIndex = sections.indexOfFirst { it.id == currentSectionId }
        return sections[currentSectionIndex + 2].id
    }

    fun getFragmentAndIndex(fragmentTag: String): Pair<FragmentData, Int> {
        lateinit var pair: Pair<FragmentData, Int>
        sections.forEach { section ->
            val tempPair = section.getFragmentByTag(fragmentTag)
            if (tempPair != null) {
                pair = tempPair
                return@forEach
            }
        }
        return pair
    }

    fun getTotalSections() = sections.size

    fun getFirstFragmentDataTag(pathType: PathType) = sections.first().getFirstFragmentDataTag(pathType)

    fun getSectionByFragmentTag(fragmentTag: String) = sections.first { section ->
        section.hasFragmentByTag(fragmentTag)
    }

    fun getSectionById(sectionId: Int) = sections.first { it.id == sectionId }
}