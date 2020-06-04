package de.trbnb.mvvmbase.rxjava3

import de.trbnb.mvvmbase.ViewModel
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.kotlin.plusAssign

/**
 * Read-only bindable property delegate that has last emitted value from a [Maybe] or `defaultValue` if no value has been emitted.
 */
class MaybeBindableProperty<T> internal constructor(
    viewModel: ViewModel,
    defaultValue: T,
    fieldId: Int?,
    maybe: Maybe<out T>,
    onError: (Throwable) -> Unit,
    onComplete: () -> Unit
) : RxBindablePropertyBase<T>(viewModel, defaultValue, fieldId) {
    init {
        viewModel.compositeDisposable += maybe.subscribe({ value = it }, onError, onComplete)
    }
}
