package com.appsirise.core.ui.extensions

import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

fun Completable.subscribeTo(compositeDisposable: CompositeDisposable) =
    this.onErrorComplete()
        .subscribeOn(Schedulers.io())
        .subscribeBy()
        .addTo(compositeDisposable)

fun Disposable.disposeWith(compositeDisposable: DisposableContainer): Disposable =
    apply { compositeDisposable.add(this) }
