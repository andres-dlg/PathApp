package com.dlgsoft.pathapp

interface OnClickListener {
    fun navigateToNextFragmentBase()
    fun navigateToNextFragmentFork4()
    fun navigateToNextFragmentFork7()
    fun navigateToNextFragmentFork9()
    fun navigateToNextFragmentFork9NoProgress()
    fun navigateToSection(id: Int)
    fun finishSection()
    fun cancelSection()
    fun returnToBasePath()
}