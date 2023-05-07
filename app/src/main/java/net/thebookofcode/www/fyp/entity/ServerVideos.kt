package net.thebookofcode.www.fyp.entity

import com.google.gson.annotations.SerializedName

data class ServerVideos(
    @SerializedName("videos")
    val videos:List<String>
)
