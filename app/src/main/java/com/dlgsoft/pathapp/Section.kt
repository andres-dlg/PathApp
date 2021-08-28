package com.dlgsoft.pathapp

data class Section(
        val id: Int,
        private val fragments: List<FragmentData>
) {
    private fun getFirstFragmentData(pathType: PathType) = fragments.first { pathType == it.pathType }

    private fun getLastFragmentData(pathType: PathType) = fragments.last { pathType == it.pathType }

    fun getFirstFragmentDataTag(pathType: PathType) = getFirstFragmentData(pathType).tag

    fun getLastFragmentDataTag(pathType: PathType) = getLastFragmentData(pathType).tag

    fun isLastFragmentInFork(pathType: PathType, forkedFragmentTag: String): Boolean {
        val forkedFragments = fragments.filter { it.pathType == pathType }
        val forkedFragmentIndex = forkedFragments.indexOfFirst { it.tag == forkedFragmentTag }
        return forkedFragmentIndex == forkedFragments.lastIndex
    }

    fun getNextFragmentInFork(pathType: PathType, forkedFragmentTag: String): FragmentData {
        val forkedFragments = fragments.filter { it.pathType == pathType }
        val forkedFragmentIndex = forkedFragments.indexOfFirst { it.tag == forkedFragmentTag }
        return forkedFragments[forkedFragmentIndex.plus(1)]
    }

    fun calculateFragmentWeight(fragmentData: FragmentData): Double {
        val fragmentsInFork = fragments.count { it.pathType == fragmentData.pathType }
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