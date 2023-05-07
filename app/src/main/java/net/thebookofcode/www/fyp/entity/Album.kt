package net.thebookofcode.www.fyp.entity

data class Album(
    val thumbnailUrls:ArrayList<String>?,
    val albumName: String,
    val quantity:Int = 0,
    val type:String
)
