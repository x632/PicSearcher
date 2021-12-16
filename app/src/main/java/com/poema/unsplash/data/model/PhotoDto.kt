package com.poema.unsplash.data.model

import com.google.gson.annotations.SerializedName


data class PhotoDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("urls")
    val urls: UrlsDto,
    ){
    fun toPhoto(): Photo{
        return Photo(id=id,description=description, url=urls.regular)
    }
}