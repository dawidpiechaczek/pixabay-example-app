package com.appsirise.pixabayexampleapp.auth.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.pixabayexampleapp.auth.data.model.SearchedPhotosResponse
import com.appsirise.pixabayexampleapp.auth.ui.usecase.SearchPhotosUseCase
import com.appsirise.sharedtest.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class AuthViewModelTest {

    @get:Rule
    val coroutineRule = TestCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getDogBreedsUseCase: SearchPhotosUseCase

    @RelaxedMockK
    lateinit var dogBreedsObserver: Observer<ViewState<List<SearchedPhotosResponse>>>

    private lateinit var authViewModel: PhotosListViewModel

    @Before
    fun before() {
        MockKAnnotations.init(this)
        authViewModel = PhotosListViewModel(getDogBreedsUseCase)
    }

    @Test
    fun `Should emit dog breeds list if succeeded`() = coroutineRule.runTest {
        // Given
        val dogBreeds = listOf(DogBreed(1L, "Akita"))

        coEvery { getDogBreedsUseCase.execute(Unit) } returns dogBreeds
        // When
        authViewModel.getBreedsLiveData.observeForever(dogBreedsObserver)
        authViewModel.getBreeds()

        // Then
        verify { dogBreedsObserver.onChanged(ViewState.Success(dogBreeds)) }
        confirmVerified(dogBreedsObserver)
    }
}
