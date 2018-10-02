package kz.rakymzhan.flickkotlinapp.data.network.model

import kz.rakymzhan.flickkotlinapp.domain.entity.PhotoEntity

data class GalleryResponse(
        val photos: Response
)

data class Response(
        val page: Int,
        val pages: Int,
        val perpage: Int,
        val total: Int,
        val photo: List<PhotoEntity>
)