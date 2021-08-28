package com.dlgsoft.pathapp

import androidx.fragment.app.Fragment

data class FragmentData(
        val fragment: Lazy<Fragment>,
        val tag: String,
        val pathType: PathType,
        private val isNotProgress: Boolean = false
) {
    fun isFork(): Boolean {
        return pathType != PathType.BASE
    }

    fun calculateFragmentWeight(fragmentsInFork: Int): Double {
        if (isNotProgress) return 0.0
        if (!isFork()) return 1.0
        return 1.0 / fragmentsInFork
    }

    fun getPercentage(
            sectionsQty: Int,
            sectionNumber: Int,
            fragmentPositionInSection: Int,
            fragmentWeight: Double
    ): Double {
        var basePercentage = sectionNumber / sectionsQty.toDouble() * 100
        val comparator = fragmentWeight * fragmentPositionInSection
        if (comparator < 1.0) {
            basePercentage -= comparator * 10
        }
        return basePercentage
    }

    fun initFragment() = fragment.value
}