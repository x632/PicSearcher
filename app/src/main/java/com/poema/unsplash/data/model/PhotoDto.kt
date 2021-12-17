package com.poema.unsplash.data.model

import com.google.gson.annotations.SerializedName


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
    fun toPhoto(): Photo{
        return if (description==null) Photo(id=id,description="by " + user.name, url=urls.regular)
        else Photo(id=id,description=description + " by ${user.name}" , url=urls.regular)
    }
}

class User (
    val name: String
)
