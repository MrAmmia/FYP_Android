package net.thebookofcode.www.fyp.entity

import com.google.gson.annotations.SerializedName

data class ServerMedia(
    @SerializedName("images")
    val images:List<String>,

    @SerializedName("videos")
    val videos:List<String>
) {
}