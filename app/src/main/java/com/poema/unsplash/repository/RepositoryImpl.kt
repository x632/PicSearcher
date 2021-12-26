package com.poema.unsplash.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.poema.unsplash.data.UnSplashApi
import com.poema.unsplash.data.UnsplashPagingSource
import com.poema.unsplash.data.repository.Repository
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val api: UnSplashApi
) : Repository {

    override fun getSearchResults(query: String, color: String?) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(api, query,color) }
        ).flow


}
