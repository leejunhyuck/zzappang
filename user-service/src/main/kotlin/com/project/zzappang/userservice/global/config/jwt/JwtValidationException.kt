package com.project.zzappang.userservice.global.config.jwt

import java.lang.RuntimeException

class JwtValidationException(override val message: String?) : RuntimeException()