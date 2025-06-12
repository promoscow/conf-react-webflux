package ru.xpendence.callbackhell

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.scheduler.Schedulers
import ru.xpendence.callbackhell.service.OrderService
import kotlin.test.assertNotNull

@SpringBootTest
class CallbackHellApplicationTests
