package com.study.spotilist.viewmodel

import com.nhaarman.mockitokotlin2.whenever
import com.study.spotilist.model.Playlist
import com.study.spotilist.repository.PlaylistRepository
import com.study.spotilist.utils.BaseUnitTest
import com.study.spotilist.utils.getValueForTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.*
import java.lang.RuntimeException

class PlaylistViewModelTest : BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists: List<Playlist> = mock()

    @Test
    fun fetchPlaylistsShouldCallFetchAllPlaylistsFromRepository() {
        runBlocking {
            val sut = PlaylistViewModel(repository)
            sut.fetchPlaylists()
            verify(repository, times(1)).fetchAllPlaylist()
        }
    }

    @Test
    fun fetchAllPlaylistsShouldReturnSuccessInCaseOfSuccess() {
        runBlocking {
            val sut = mockSuccess()
            sut.fetchPlaylists()
            val valueForTest = sut.playlists.getValueForTest()
            Assert.assertEquals(playlists, valueForTest)
        }
    }

    @Test
    fun fetchAllPlaylistsShouldReturnNullInCaseOfFailure() {
        runBlocking {
            val sut = mockFailure()
            sut.fetchPlaylists()
            val valueForTest = sut.playlists.getValueForTest()
            Assert.assertEquals(null, valueForTest)
        }
    }

    private suspend fun mockFailure(): PlaylistViewModel {
        whenever(repository.fetchAllPlaylist()).thenThrow(
            RuntimeException("Something went wrong")
        )
        return PlaylistViewModel(repository)
    }

    private suspend fun mockSuccess(): PlaylistViewModel {
        whenever(repository.fetchAllPlaylist()).then {
            flow {
                emit(playlists)
            }
        }

        return PlaylistViewModel(repository)
    }
}