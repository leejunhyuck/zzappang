package com.project.userorderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserOrderServiceApplication

fun main(args: Array<String>) {
	runApplication<UserOrderServiceApplication>(*args)
}
