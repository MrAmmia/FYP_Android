package net.thebookofcode.www.fyp.model

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.thebookofcode.www.fyp.entity.ServerImages
import net.thebookofcode.www.fyp.entity.ServerMedia
import net.thebookofcode.www.fyp.entity.ServerVideos
import net.thebookofcode.www.fyp.repository.MainRepository
import net.thebookofcode.www.fyp.room.entity.Notification
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    private val _myImages = MutableLiveData<ServerImages>()
    val myImages: LiveData<ServerImages> = _myImages

    private val _myVideos = MutableLiveData<ServerVideos>()
    val myVideos: LiveData<ServerVideos> = _myVideos

    private val _myFiles = MutableLiveData<ServerMedia>()
    val myFiles: LiveData<ServerMedia> = _myFiles

    val getAllNotifications:LiveData<List<Notification>> = repository.getAllNotifications.asLiveData()

    fun deleteAllNotification() = viewModelScope.launch {
        repository.deleteAllNotifications()
    }

    fun deleteNotification(notification: Notification) = viewModelScope.launch {
        repository.deleteNotifications(notification)
    }

    fun insertNotification(notification: Notification) = viewModelScope.launch {
        repository.insertNotification(notification)
    }

    fun getOnlinePhotos() = viewModelScope.launch {
        _myImages.value = repository.getOnlinePhotos()
    }

    fun getOnlineVideos() = viewModelScope.launch {
        _myVideos.value = repository.getOnlineVideos()
    }

    fun getOnlineFiles() = viewModelScope.launch {
        _myFiles.value = repository.getOnlineFiles()
    }
}
