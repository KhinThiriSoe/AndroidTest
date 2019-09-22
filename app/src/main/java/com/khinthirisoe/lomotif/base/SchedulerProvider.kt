package com.khinthirisoe.lomotif.base

import io.reactivex.Scheduler

/**
 * Rx Scheduler Provider
 */
interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}