package com.appsirise.pixabayexampleapp.photos.ui

import com.appsirise.pixabayexampleapp.photos.ui.model.SearchedPhoto
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosRepository
import com.appsirise.pixabayexampleapp.photos.ui.repository.SearchedPhotosSource
import org.junit.Before
import org.junit.Test

class SearchedPhotoRepositoryTest {

    private lateinit var searchedPhotosSource: SearchedPhotosSource

    @Before
    fun init() {
        searchedPhotosSource = SearchedPhotosRepository()
    }

    @Test
    fun `Should return all data when new photo inserted`() {
        val photoId = 12L

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
        val photosToInsert = listOf(photoDetails)

        val testObserver = searchedPhotosSource.get().test()

        searchedPhotosSource.insert(photosToInsert)
            .test()
            .assertComplete()

        testObserver.assertValues(photosToInsert)
    }
}
