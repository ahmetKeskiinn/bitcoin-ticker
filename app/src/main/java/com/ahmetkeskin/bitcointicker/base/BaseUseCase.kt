package com.ahmetkeskin.bitcointicker.base

abstract class BaseUseCase<ReturnType, Params> {
    abstract fun execute(viewModel: BaseViewModel, input: Params?): ReturnType?
}