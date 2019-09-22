package com.khinthirisoe.lomotif.ui

open class ViewModelState

/**
 * Generic Loading State
 */
object Loading : ViewModelState()

/**
 * Generic Error state
 */
data class Failed(val error: Throwable) : ViewModelState()