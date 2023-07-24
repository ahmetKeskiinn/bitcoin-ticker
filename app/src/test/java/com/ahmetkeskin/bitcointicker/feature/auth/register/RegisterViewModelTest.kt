package com.ahmetkeskin.bitcointicker.feature.auth.register

import com.ahmetkeskin.bitcointicker.feature.auth.register.domain.SetRegisterOnFB
import com.ahmetkeskin.bitcointicker.feature.auth.register.presentation.RegisterViewModel
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegisterViewModelTest {
    @Mock
    lateinit var setRegisterOnFB: SetRegisterOnFB

    @InjectMocks
    lateinit var registerViewModel: RegisterViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        registerViewModel = RegisterViewModel(setRegisterOnFB)
    }

    @Test
    fun getCryptosTest() {
        // GIVEN
        val data = UserModel("test", "test")
        // WHEN
        registerViewModel.register(data)
        // THEN
        Mockito.verify(setRegisterOnFB).execute(registerViewModel, SetRegisterOnFB.Params(data))
    }
}