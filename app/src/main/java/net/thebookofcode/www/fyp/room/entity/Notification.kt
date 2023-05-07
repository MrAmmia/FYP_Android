package net.thebookofcode.www.fyp.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notification(
    val title:String,
    val subject:String,
    val content:String,

    val date:Long,

    @ColumnInfo(name = "image_url")
    val imageUrl:String?,

    @ColumnInfo(name = "time")
    var time: String
){
    @PrimaryKey(autoGenerate = true)
    var uid: Int? = null
}
