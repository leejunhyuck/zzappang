package com.project.zzappang.productservice.repository

import com.project.zzappang.productservice.domain.Coupon
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CouponRepository : ReactiveCrudRepository<Coupon, String>