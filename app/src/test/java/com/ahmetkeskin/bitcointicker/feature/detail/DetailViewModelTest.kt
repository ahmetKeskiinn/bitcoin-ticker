package com.ahmetkeskin.bitcointicker.feature.detail

import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import com.ahmetkeskin.bitcointicker.base.common.domain.firebase.AddFavoriteOnFB
import com.ahmetkeskin.bitcointicker.base.common.domain.firebase.RemoveFavoriteOnFB
import com.ahmetkeskin.bitcointicker.base.common.domain.room.CheckFavoriteOnDB
import com.ahmetkeskin.bitcointicker.feature.detail.domain.GetDetail
import com.ahmetkeskin.bitcointicker.feature.detail.presentation.DetailViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @Mock
    lateinit var getDetail: GetDetail

    @Mock
    lateinit var addFavoriteOnFB: AddFavoriteOnFB

    @Mock
    lateinit var checkFavoriteOnDB: CheckFavoriteOnDB

    @Mock
    lateinit var removeFavoriteOnFB: RemoveFavoriteOnFB

    @InjectMocks
    lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        detailViewModel =
            DetailViewModel(getDetail, addFavoriteOnFB, checkFavoriteOnDB, removeFavoriteOnFB)
    }

    @Test
    fun getDetailTest() {
        // GIVEN
        val data = GetDetail.Params("test")
        // WHEN
        detailViewModel.getDetail(data)
        // THEN
        Mockito.verify(getDetail).execute(detailViewModel, data)
    }

    @Test
    fun removeFavoriteTest() {
        // GIVEN
        val data = RemoveFavoriteOnFB.Params(FavoriteModel("test", "test", "test", "test"))
        // WHEN
        detailViewModel.removeFavorite(data)
        // THEN
        Mockito.verify(removeFavoriteOnFB).execute(detailViewModel, data)
    }

    @Test
    fun addFavoriteOnFBTest() {
        // GIVEN
        val data = AddFavoriteOnFB.Params(FavoriteModel("test", "test", "test", "test"))
        // WHEN
        detailViewModel.addFavorite(data)
        // THEN
        Mockito.verify(addFavoriteOnFB).execute(detailViewModel, data)
    }

    @Test
    fun checkFollowingTest() {
        // GIVEN
        val data = CheckFavoriteOnDB.Params("test")
        // WHEN
        detailViewModel.checkFollowing(data)
        // THEN
        Mockito.verify(checkFavoriteOnDB).execute(detailViewModel, data)
    }
}
