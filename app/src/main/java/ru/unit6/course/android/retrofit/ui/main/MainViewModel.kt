package ru.unit6.course.android.retrofit.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import ru.unit6.course.android.retrofit.data.model.User
import ru.unit6.course.android.retrofit.ui.main.MainAdapter.UserItems
import ru.unit6.course.android.retrofit.usecases.GetUsersFromApiUseCase
import ru.unit6.course.android.retrofit.usecases.GetUsersFromDatabaseUseCase
import ru.unit6.course.android.retrofit.usecases.SetUsersToDatabaseUseCase
import ru.unit6.course.android.retrofit.utils.Resource
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getUsersFromApiUseCase: GetUsersFromApiUseCase,
    private val getUsersFromDatabaseUseCase: GetUsersFromDatabaseUseCase,
    private val setUsersToDatabaseUseCase: SetUsersToDatabaseUseCase,
) : ViewModel() {

    companion object {
        private const val SEARCH_DELAY_MILLIS = 300L
    }

    private var _allUsers: List<User> = listOf()

    private val _users = MutableLiveData<Resource<List<UserItems>>>()
    val users: LiveData<Resource<List<UserItems>>>
        get() = _users

    private val searchQuery: BehaviorSubject<String> = BehaviorSubject.createDefault("")

    init {
        getUsers()
        observerSearchQuery()
    }

    private fun observerSearchQuery() = searchQuery
        .debounce(SEARCH_DELAY_MILLIS, TimeUnit.MILLISECONDS)
        .subscribe { query ->
            val filteredUsers = _allUsers.filter { it.name.contains(query, ignoreCase = true) }

            _users.postValue(
                Resource.success(data = filteredUsers.map { UserItems.UserItemApi(it) })
            )
        }

    private fun getUsersFromDatabase() = getUsersFromDatabaseUseCase.invoke()
        .observeOn(AndroidSchedulers.mainThread())

    fun setSearchQuery(text: String) {
        searchQuery.onNext(text)
    }

    fun setAllUsersToDatabase(users: List<User>) = setUsersToDatabaseUseCase.invoke(users)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { getUsersFromDatabase() }

    private fun getRestUsers() = getUsersFromApiUseCase.invoke()
        .subscribeOn(AndroidSchedulers.mainThread())

    private fun getUsers(): Disposable = Single.zip(
        getRestUsers(),
        getUsersFromDatabase()
    ) { restUsers, localUsers ->
        mutableListOf<User>().apply {
            addAll(restUsers)
            addAll(localUsers)
        }
    }
        .doOnSubscribe {
            _users.postValue(Resource.loading(data = null))
        }
        .doOnError { error ->
            _users.postValue(Resource.error(data = null, message = error.message ?: "Error Occurred!"))
        }
        .subscribe { userList ->
            _allUsers = userList
            _users.postValue(Resource.success(data = userList.map { UserItems.UserItemApi(it) }))
        }

}