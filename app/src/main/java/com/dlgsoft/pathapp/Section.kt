package com.dlgsoft.pathapp

data class Section(
    val id: Int,
    val fragments: List<FragmentData>
) {
    private fun getFirstFragmentData(forkTag: String) = fragments.first { forkTag == it.forkTag }

    fun getFirstFragmentDataTag(forkTag: String) = getFirstFragmentData(forkTag).tag

    fun isLastFragmentInFork(forkTag: String, forkedFragmentTag: String): Boolean {
        val forkedFragments = fragments.filter { it.forkTag == forkTag }
        val forkedFragmentIndex = forkedFragments.indexOfFirst { it.tag == forkedFragmentTag }
        return forkedFragmentIndex == forkedFragments.lastIndex
    }

    fun getNextFragmentInFork(forkTag: String?, forkedFragmentTag: String): FragmentData {
        val forkedFragments = fragments.filter { it.forkTag == forkTag }
        val forkedFragmentIndex = forkedFragments.indexOfFirst { it.tag == forkedFragmentTag }
        return forkedFragments[forkedFragmentIndex.plus(1)]
    }

    fun calculateFragmentWeight(isFork: Boolean): Double {
        return if (isFork) {
            val counter = fragments.count { it.isFork() }
            return 1.0 / counter
        } else {
            1.0
        }
    }
}