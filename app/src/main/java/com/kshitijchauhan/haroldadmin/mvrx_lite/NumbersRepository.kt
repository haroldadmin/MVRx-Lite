package com.kshitijchauhan.haroldadmin.mvrx_lite

import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class NumbersRepository {

    val numbers: Flowable<Long> = Flowable.interval(1000, TimeUnit.MILLISECONDS)
}