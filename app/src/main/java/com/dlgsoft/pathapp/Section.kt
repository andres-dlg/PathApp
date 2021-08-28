package com.dlgsoft.pathapp

data class Section(
        val id: Int,
        private val fragments: List<FragmentData>
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
        val fragmentsInFork = fragments.count { it.forkTag == fragmentData.forkTag }
        return fragmentData.calculateFragmentWeight(fragmentsInFork)
    }

    fun hasFragmentByTag(fragmentTag: String) = fragments.any { it.tag == fragmentTag }

    fun getFragmentByTag(fragmentTag: String): Pair<FragmentData, Int>? {
        fragments.forEachIndexed { index, fragmentData ->
            if (fragmentData.tag == fragmentTag) {
                return Pair(fragmentData, index)
            }
        }
        return null
    }
}