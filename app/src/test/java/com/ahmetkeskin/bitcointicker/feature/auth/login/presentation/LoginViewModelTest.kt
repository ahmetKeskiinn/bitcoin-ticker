package com.ahmetkeskin.bitcointicker.feature.auth.login.presentation

import com.ahmetkeskin.bitcointicker.feature.auth.login.domain.SaveUserSettings
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import com.ahmetkeskin.bitcointicker.feature.auth.splash.domain.Authentication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {
    @Mock
    lateinit var authentication: Authentication

    @Mock
    lateinit var saveUserSettings: SaveUserSettings

    @InjectMocks
    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel(authentication, saveUserSettings)
    }

    @Test
    fun authenticationTest() {
        // GIVEN
        val data = UserModel("test", "test")
        // WHEN
        loginViewModel.authentication(data)
        // THEN
        Mockito.verify(authentication).execute(loginViewModel, Authentication.Params(data))
    }

    @Test
    fun saveUserSettingsTest() {
        // GIVEN
        val data = UserModel("test", "test")
        // WHEN
        loginViewModel.saveUserSettings(data)
        // THEN
        Mockito.verify(saveUserSettings).execute(loginViewModel, SaveUserSettings.Params(data))
    }
}
