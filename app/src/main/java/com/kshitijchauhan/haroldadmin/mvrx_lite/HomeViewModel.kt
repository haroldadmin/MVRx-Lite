package com.kshitijchauhan.haroldadmin.mvrx_lite

import com.kshitijchauhan.haroldadmin.mvrxlite.base.MVRxLiteViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    initialState: State.HomeState,
    private val numbersRepository: NumbersRepository
): MVRxLiteViewModel<State.HomeState>(initialState) {

    private val listOfNumbers = mutableListOf<Long>()
    private val compositeDisposable = CompositeDisposable()

    fun getNumbers() {
        numbersRepository.numbers
            .observeOn(Schedulers.io())
            .doOnNext {  newNumber ->
                listOfNumbers.add(newNumber)
                log("Added new number to list: $listOfNumbers")
            }
            .subscribe {
                log("Modifying state")
                setState {
                    copy(numbers = Result.Success(listOfNumbers))
                        .also {
                            log("New state: $it")
                        }
                }
            }
            .disposeWith(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
