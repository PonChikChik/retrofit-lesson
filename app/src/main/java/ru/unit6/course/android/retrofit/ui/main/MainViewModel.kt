package ru.unit6.course.android.retrofit.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import ru.unit6.course.android.retrofit.data.model.User
import ru.unit6.course.android.retrofit.ui.main.MainAdapter.UserItems
import ru.unit6.course.android.retrofit.usecases.GetUsersFromApiUseCase
import ru.unit6.course.android.retrofit.usecases.GetUsersFromDatabaseUseCase
import ru.unit6.course.android.retrofit.usecases.SetUsersToDatabaseUseCase
import ru.unit6.course.android.retrofit.utils.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getUsersFromApiUseCase: GetUsersFromApiUseCase,
    private val getUsersFromDatabaseUseCase: GetUsersFromDatabaseUseCase,
    private val setUsersToDatabaseUseCase: SetUsersToDatabaseUseCase,
) : ViewModel() {

    private val _allUsers = MutableLiveData<Resource<List<UserItems>>>()

    private val _users = MutableLiveData<Resource<List<UserItems>>>()
    val users: LiveData<Resource<List<UserItems>>>
        get() = _allUsers

    init {
        getUsers()
    }

    fun getUsersFromDatabase() = getUsersFromDatabaseUseCase.invoke()
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
            _users.postValue(Resource.loading(data = null))
        }
        .doOnError { error ->
            _users.postValue(Resource.error(data = null, message = error.message ?: "Error Occurred!"))
        }
        .subscribe { local ->
            _allUsers.postValue(Resource.success(data = local.map { UserItems.UserItemDB(it) }))
            _users.postValue(Resource.success(data = local.map { UserItems.UserItemDB(it) }))
        }

    fun setAllUsersToDatabase(users: List<User>) = setUsersToDatabaseUseCase.invoke(users)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { getUsersFromDatabase() }

    fun getUsers(): Disposable = getUsersFromApiUseCase.invoke()
        .subscribeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
            _users.postValue(Resource.loading(data = null))
        }
        .doOnError { error ->
            _users.postValue(Resource.error(data = null, message = error.message ?: "Error Occurred!"))
        }
        .subscribe { userList ->
            setAllUsersToDatabase(userList)
            _allUsers.postValue(Resource.success(data = userList.map { UserItems.UserItemApi(it) }))
            _users.postValue(Resource.success(data = userList.map { UserItems.UserItemApi(it) }))
        }

}