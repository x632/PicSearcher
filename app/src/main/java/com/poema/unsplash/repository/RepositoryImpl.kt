package com.poema.unsplash.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.poema.unsplash.data.UnSplashApi
import com.poema.unsplash.data.UnSplashApi.Companion.AUTH
import com.poema.unsplash.data.UnsplashPagingSource
import com.poema.unsplash.data.model.SearchResponseDto
import com.poema.unsplash.data.repository.Repository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val api: UnSplashApi
) : Repository {

    override fun getSearchResults(query: String) =

        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(api, query) }
        ).flow

    override suspend fun searchPhotos(query: String): SearchResponseDto {
        try {
            return api.searchPhotos(AUTH, query, 4, 15)

        } catch (e: HttpException) {
            println("!!! HTTP EXCEPTION ${e.message}")

        } catch (e: IOException) {
            println("!!! IOException ${e.message}")
        }
        return SearchResponseDto(emptyList())
    }
}
