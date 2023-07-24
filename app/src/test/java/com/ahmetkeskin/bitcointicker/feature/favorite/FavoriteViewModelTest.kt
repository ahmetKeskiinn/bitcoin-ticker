package com.ahmetkeskin.bitcointicker.feature.favorite

import com.ahmetkeskin.bitcointicker.feature.favorite.domain.GetFavorites
import com.ahmetkeskin.bitcointicker.feature.favorite.domain.GetSearchedFavorite
import com.ahmetkeskin.bitcointicker.feature.favorite.presentation.FavoriteViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    @Mock
    lateinit var getFavorites: GetFavorites

    @Mock
    lateinit var getSearchedFavorite: GetSearchedFavorite

    @InjectMocks
    lateinit var favoriteViewModel: FavoriteViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favoriteViewModel = FavoriteViewModel(getFavorites, getSearchedFavorite)
    }

    @Test
    fun getFavoritesTest() {
        // GIVEN

        // WHEN
        favoriteViewModel.getFavorites()
        // THEN
        Mockito.verify(getFavorites).execute(favoriteViewModel, Unit)
    }

    @Test
    fun authenticationTest() {
        // GIVEN

// WHEN
        favoriteViewModel.searchCurrency("test")
        // THEN
        Mockito.verify(getSearchedFavorite).execute(favoriteViewModel, GetSearchedFavorite.Params("test"))
    }
}
