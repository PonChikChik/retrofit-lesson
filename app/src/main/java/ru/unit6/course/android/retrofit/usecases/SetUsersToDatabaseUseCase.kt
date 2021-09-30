package ru.unit6.course.android.retrofit.usecases

import ru.unit6.course.android.retrofit.data.model.User
import ru.unit6.course.android.retrofit.data.model.UserDB
import ru.unit6.course.android.retrofit.data.repository.LocalUsersRepository
import javax.inject.Inject

class SetUsersToDatabaseUseCase @Inject constructor(
    private val localUsersRepository: LocalUsersRepository
) {

    operator fun invoke(users: List<User>) = localUsersRepository.setUsersToDatabase(
        users = users.map { user ->
            UserDB(
                id = user.id,
                name = user.name,
                avatar = user.avatar,
                email = user.email,
            )
        })
}