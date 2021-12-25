package com.poema.unsplash.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.poema.unsplash.data.UnSplashApi.Companion.AUTH
import com.poema.unsplash.data.model.SearchResponseDto
import com.poema.unsplash.ui.uimodel.Photo
import okio.IOException
import retrofit2.HttpException

private const val UNSPLASH_STARTING_PAGE_INDEX = 1

class UnsplashPagingSource(
    private val unsplashApi: UnSplashApi,
    private val query: String,
    private val color: String?
) : PagingSource<Int, Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        println("!!! BEEN HERE IN PAGING SOURCE")
        return try {

            val response = unsplashApi.searchPhotos(AUTH, query, position, params.loadSize,color)

            val photos = mutableListOf<Photo>()
            for (element in response.results) {
                photos.add(element.toPhoto())
            }

            LoadResult.Page(
                data = photos,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }
}