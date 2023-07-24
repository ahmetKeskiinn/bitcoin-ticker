package com.ahmetkeskin.bitcointicker.feature.history

import com.ahmetkeskin.bitcointicker.feature.history.domain.GetHistory
import com.ahmetkeskin.bitcointicker.feature.history.presentation.HistoryViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HistoryViewModelTest {
    @Mock
    lateinit var getHistory: GetHistory

    @InjectMocks
    lateinit var historyViewModel: HistoryViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        historyViewModel = HistoryViewModel(getHistory)
    }

    @Test
    fun getFavoritesTest() {
        // GIVEN
        val data = GetHistory.Params("test", "test", "test", "test", "test")
        // WHEN
        historyViewModel.getHistory(data)
        // THEN
        Mockito.verify(getHistory).execute(historyViewModel, data)
    }
}
