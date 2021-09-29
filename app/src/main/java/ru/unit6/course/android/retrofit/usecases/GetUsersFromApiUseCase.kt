package ru.unit6.course.android.retrofit.usecases

import io.reactivex.schedulers.Schedulers
import ru.unit6.course.android.retrofit.data.repository.MainRepository
import javax.inject.Inject

class GetUsersFromApiUseCase @Inject constructor(
    private val mainRepository: MainRepository,
) {

    operator fun invoke() = mainRepository.getUsers().subscribeOn(Schedulers.io())
}