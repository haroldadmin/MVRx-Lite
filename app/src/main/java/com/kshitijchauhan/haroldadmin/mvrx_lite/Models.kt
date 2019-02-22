package com.kshitijchauhan.haroldadmin.mvrx_lite

import com.kshitijchauhan.haroldadmin.mvrxlite.base.MVRxLiteState


sealed class State: MVRxLiteState {
    data class HomeState(val numbers: Result<List<Long>>) : State()
}

sealed class Result<T> {
    class Loading<T> : Result<T>()
    data class Success<T> (val data: T) : Result<T>()
    data class Error<T> (val errorMessage: String) : Result<T>()
}

