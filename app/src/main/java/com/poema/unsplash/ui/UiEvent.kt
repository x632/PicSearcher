package com.poema.unsplash.ui

sealed class UiEvent {
    data class ColorFilter(val col: String): UiEvent()
    data class SearchQuery(val query: String) : UiEvent()
}
