package com.ahmetkeskin.bitcointicker.feature.home

import com.ahmetkeskin.bitcointicker.base.common.domain.firebase.MergeFavoriteFBAndDB
import com.ahmetkeskin.bitcointicker.feature.home.domain.GetCryptos
import com.ahmetkeskin.bitcointicker.feature.home.presentation.HomeViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @Mock
    lateinit var getCryptos: GetCryptos

    @Mock
    lateinit var mergeFavoriteFBAndDB: MergeFavoriteFBAndDB

    @InjectMocks
    lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        homeViewModel = HomeViewModel(getCryptos, mergeFavoriteFBAndDB)
    }

    @Test
    fun getCryptosTest() {
        homeViewModel.getCrypto()
        verify(getCryptos).execute(homeViewModel, Unit)
    }
}
