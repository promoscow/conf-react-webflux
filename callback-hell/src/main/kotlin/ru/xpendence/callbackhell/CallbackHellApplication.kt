package ru.xpendence.callbackhell

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CallbackHellApplication

fun main(args: Array<String>) {
    runApplication<CallbackHellApplication>(*args)
}
