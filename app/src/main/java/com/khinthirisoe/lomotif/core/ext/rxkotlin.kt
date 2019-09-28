package com.khinthirisoe.lomotif.core.ext

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Completable.initSchedulers(
        observeOn: Scheduler = AndroidSchedulers.mainThread(),
        subscribeOn: Scheduler = Schedulers.io()
): Completable = this.observeOn(observeOn).subscribeOn(subscribeOn)

fun <T> Single<T>.initSchedulers(
        observeOn: Scheduler = AndroidSchedulers.mainThread(),
        subscribeOn: Scheduler = Schedulers.io()
): Single<T> = this.observeOn(observeOn).subscribeOn(subscribeOn)