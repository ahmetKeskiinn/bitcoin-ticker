package com.ahmetkeskin.bitcointicker.feature.auth.splash

import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import com.ahmetkeskin.bitcointicker.feature.auth.splash.domain.Authentication
import com.ahmetkeskin.bitcointicker.feature.auth.splash.domain.GetLocalSettings
import com.ahmetkeskin.bitcointicker.feature.auth.splash.presentation.SplashViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {
    @Mock
    lateinit var authentication: Authentication

    @Mock
    lateinit var getLocalSettings: GetLocalSettings

    @InjectMocks
    lateinit var splashViewModel: SplashViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        splashViewModel = SplashViewModel(getLocalSettings, authentication)
    }

    @Test
    fun getLocalSettingsTest() {
        // GIVEN

        // WHEN
        splashViewModel.getUserSettings()
        // THEN
        Mockito.verify(getLocalSettings).execute(splashViewModel, Unit)
    }

    @Test
    fun authenticationTest() {
        // GIVEN
        val data = UserModel("test", "test")
        // WHEN
        splashViewModel.auth(data)
        // THEN
        Mockito.verify(authentication).execute(splashViewModel, Authentication.Params(data))
    }
}