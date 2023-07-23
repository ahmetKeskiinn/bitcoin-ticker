package com.ahmetkeskin.bitcointicker.feature.auth.register.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.EMPTY
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

class SetRegisterOnFB @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : BaseUseCase<MutableLiveData<Boolean?>, SetRegisterOnFB.Params>() {

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
                    firebaseAuth.createUserWithEmailAndPassword(
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