package com.example.testapi.domain.usecase

import com.example.testapi.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservePingUseCase @Inject constructor(
    private val repository: ChatRepository
) {

    operator fun invoke() = repository.observePing()

}