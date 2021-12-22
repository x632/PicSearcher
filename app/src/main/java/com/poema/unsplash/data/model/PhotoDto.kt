package com.poema.unsplash.data.model

import com.google.gson.annotations.SerializedName
import com.poema.unsplash.ui.uimodel.Photo


data class PhotoDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("urls")
    val urls: UrlsDto,
    @SerializedName("user")
    val user: User,

    ){
    fun toPhoto(): Photo {
        return Photo(id=id,description=description?: "No description", url=urls.regular, bestUrl=urls.full,name = "by " + user.name)
        //else Photo(id=id,description=description + " by ${user.name}" , url=urls.regular)
    }
}

class User (
    val name: String?
)
