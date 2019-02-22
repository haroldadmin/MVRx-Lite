package com.kshitijchauhan.haroldadmin.mvrx_lite

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

val Any?.safe: Unit
    get() = Unit

fun Disposable.disposeWith(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

fun Any.log(message: String, type: Char = 'd') {
    val tag = this::class.java.simpleName
    when (type) {
        'd' -> Log.d(tag, message)
        'e' -> Log.e(tag, message)
        'v' -> Log.v(tag, message)
    }
}