package com.project.zzappang.productservice.exception

open class NotFoundException(override val message: String): RuntimeException()

class ProductNotFoundException(override val message: String): NotFoundException(message)

class CouponNotFoundException(override val message: String): NotFoundException(message)

class CategoryNotFoundException(override val message: String): NotFoundException(message)