package com.khinthirisoe.lomotif.core.rx

import io.reactivex.Scheduler

/**
 * Rx Scheduler Provider
 */
interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}