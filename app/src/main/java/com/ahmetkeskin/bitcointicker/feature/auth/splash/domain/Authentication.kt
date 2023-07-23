package com.ahmetkeskin.bitcointicker.feature.auth.splash.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.EMPTY
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

class Authentication @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : BaseUseCase<MutableLiveData<Boolean?>, Authentication.Params>() {

    data class Params(
        val userModel: UserModel
    )

    override fun execute(
        viewModel: BaseViewModel,
        input: Params?
    ): MutableLiveData<Boolean?> {
        return MutableLiveData<Boolean?>().apply {
            input?.let {
                viewModel.viewModelScope.launch {
                    firebaseAuth.signInWithEmailAndPassword(
                        it.userModel.email ?: EMPTY,
                        it.userModel.password ?: EMPTY
                    ).addOnCompleteListener {
                        value = it.isSuccessful
                    }
                }
            }
        }
    }
}