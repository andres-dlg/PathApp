package com.dlgsoft.pathapp

import androidx.fragment.app.Fragment

data class FragmentData(
    val fragment: Lazy<Fragment>,
    val tag: String,
    val forkTag: String?,
    val isNotProgress: Boolean = false
) {
    fun isFork(): Boolean {
        return !forkTag.isNullOrEmpty()
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
}