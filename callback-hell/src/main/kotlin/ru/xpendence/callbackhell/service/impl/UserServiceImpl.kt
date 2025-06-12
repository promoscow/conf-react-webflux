package ru.xpendence.callbackhell.service.impl

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.xpendence.callbackhell.domain.User
import ru.xpendence.callbackhell.service.UserService
import java.time.Duration

@Service
class UserServiceImpl : UserService {

    override fun get(id: String): Mono<User> = Mono.just(
        User(
            id = id,
            name = "John Doe",
            email = "john@example.com",
            loyaltyLevel = 3
        )
    ).delayElement(Duration.ofMillis(150))
}