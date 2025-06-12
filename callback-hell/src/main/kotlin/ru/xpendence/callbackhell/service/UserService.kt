package ru.xpendence.callbackhell.service

import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.User

interface UserService {

    fun get(id: String): Mono<User>
}