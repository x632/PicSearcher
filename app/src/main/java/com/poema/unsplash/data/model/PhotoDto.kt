package com.poema.unsplash.data.model

import androidx.core.text.trimmedLength
import com.google.gson.annotations.SerializedName
import com.poema.unsplash.ui.uimodel.Photo


data class PhotoDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    var description: String?,
    @SerializedName("urls")
    val urls: UrlsDto,
    @SerializedName("user")
    val user: User,

    ) {
    fun toPhoto(): Photo {

        return Photo(
            id = id,
            description = description ?: "no description",
            url = urls.regular,
            bestUrl = urls.regular,
            name = "by " + user.name
        )
    }
}

class User(
    val name: String?
)
