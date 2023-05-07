package net.thebookofcode.www.fyp.entity

import com.google.gson.annotations.SerializedName

data class ServerImages(
    @SerializedName("images")
    val images:List<String>
)
