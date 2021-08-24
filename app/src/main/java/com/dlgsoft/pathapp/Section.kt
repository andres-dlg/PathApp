package com.dlgsoft.pathapp

data class Section(
    val id: Int,
    val fragments: List<FragmentData>
) {
    private fun getFirstFragmentData(forkTag: String) = fragments.first { forkTag == it.forkTag }

    private fun getLastFragmentData(forkTag: String) = fragments.last { forkTag == it.forkTag }

    fun getFirstFragmentDataTag(forkTag: String) = getFirstFragmentData(forkTag).tag

    fun getLastFragmentDataTag(forkTag: String) = getLastFragmentData(forkTag).tag

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

    fun calculateFragmentWeight(fragmentData: FragmentData): Double {
        if (fragmentData.isNotProgress) return 0.0
        if (!fragmentData.isFork()) return 1.0
        val fragmentsInFork = fragments.count { it.forkTag == fragmentData.forkTag }
        return 1.0 / fragmentsInFork
    }
}