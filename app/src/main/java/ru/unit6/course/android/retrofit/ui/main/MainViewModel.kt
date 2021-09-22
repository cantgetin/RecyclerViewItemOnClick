package ru.unit6.course.android.retrofit.ui.main

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.unit6.course.android.retrofit.data.api.ApiHelper
import ru.unit6.course.android.retrofit.data.api.RetrofitBuilder
import ru.unit6.course.android.retrofit.data.database.AppDatabase
import ru.unit6.course.android.retrofit.data.model.User
import ru.unit6.course.android.retrofit.data.model.UserDB
import ru.unit6.course.android.retrofit.data.repository.MainRepository
import ru.unit6.course.android.retrofit.utils.Resource

class MainViewModel : ViewModel() {

    private val apiHelper = ApiHelper(RetrofitBuilder.apiService)
    private val mainRepository: MainRepository = MainRepository(apiHelper)
    private val appDatabase = AppDatabase.getDatabase()

    private val _localUsers = MutableLiveData<List<UserDB>>()
    val localUsers: LiveData<List<UserDB>>
        get() = _localUsers

    fun getUsersFromDatabase() =
        viewModelScope.launch {
            try {
                _localUsers.postValue(appDatabase.userDao().getAllUsers())
            } catch (exception: Exception) {
                throw exception
            }
        }

    fun setAllUsersToDatabase(users: List<UserDB>) =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                appDatabase.userDao().insertAllUsers(users)
                getUsersFromDatabase()
            }
        }

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}