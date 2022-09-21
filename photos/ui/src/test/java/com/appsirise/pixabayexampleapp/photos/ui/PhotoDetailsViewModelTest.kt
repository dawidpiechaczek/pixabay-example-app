package com.appsirise.pixabayexampleapp.photos.ui

import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import com.appsirise.pixabayexampleapp.photos.ui.model.details.PhotoDetailsAction
import com.appsirise.pixabayexampleapp.photos.ui.model.details.PhotoDetailsEffect
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosSource
import com.appsirise.pixabayexampleapp.photos.ui.view.details.PhotosDetailsViewModel
import com.appsirise.pixabayexampleapp.photos.ui.view.details.PhotosDetailsViewModelImpl
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PhotoDetailsViewModelTest {

    @Mock
    private lateinit var searchedPhotosSource: SearchedPhotosSource

    private fun createViewModel(
        effect: PublishSubject<PhotoDetailsEffect>,
    ): PhotosDetailsViewModel =
        PhotosDetailsViewModelImpl(searchedPhotosSource, effect)

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `Should get photo details when photoId is passed`() {
        val effect = PublishSubject.create<PhotoDetailsEffect>()
        val photoId = 12L

        val viewModel = createViewModel(effect)
        val photoDetails = SearchedPhoto(
            id = photoId,
            previewUrl = "http://www.previewurl.com",
            largeImageUrl = "http://www.largeimageurl.com",
            userName = "SweetDave19",
            tags = "banana, apple",
            likes = 12,
            comments = 16,
            downloads = 0
        )

        val testObserver = viewModel.observeEffect().test()

        Mockito.`when`(searchedPhotosSource.getById(anyLong()))
            .thenReturn(Single.just(photoDetails))

        viewModel.onAction(PhotoDetailsAction.GetPhotoDetails(photoId))
            .test()
            .assertComplete()

        testObserver.assertValues(PhotoDetailsEffect.PhotoDetails(photoDetails))
    }
}
