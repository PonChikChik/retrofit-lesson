package ru.unit6.course.android.retrofit.usecases

import ru.unit6.course.android.retrofit.data.repository.LocalUsersRepository
import javax.inject.Inject

class GetUsersFromDatabaseUseCase @Inject constructor(
    private val localUsersRepository: LocalUsersRepository
) {

    operator fun invoke() = localUsersRepository.getAllLocalUsers()
}