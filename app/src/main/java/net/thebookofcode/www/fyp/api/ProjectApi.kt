package net.thebookofcode.www.fyp.api

import net.thebookofcode.www.fyp.entity.ServerImages
import net.thebookofcode.www.fyp.entity.ServerMedia
import net.thebookofcode.www.fyp.entity.ServerVideos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ProjectApi {
    // can't reaaly work with tis till URLs have been sorted

    @POST()
    suspend fun isValidUser(username: String, password: String): Response<Boolean>

    @POST
    suspend fun isValidAdmin(username: String, password: String): Response<Boolean>

    @GET("uploaded-images/")
    suspend fun getOnlinePhotos():ServerImages

    @GET("uploaded-files/")
    suspend fun getOnlineFiles():ServerMedia

    @GET("uploaded-videos/")
    suspend fun getOnlineVideos():ServerVideos

}