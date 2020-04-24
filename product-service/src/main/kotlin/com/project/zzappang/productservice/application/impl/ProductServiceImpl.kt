package com.project.zzappang.productservice.application.impl

import com.project.zzappang.productservice.application.ProductService
import com.project.zzappang.productservice.domain.Product
import com.project.zzappang.productservice.dto.ProductDto
import com.project.zzappang.productservice.exception.CouponNotFoundException
import com.project.zzappang.productservice.exception.ProductNotFoundException
import com.project.zzappang.productservice.repository.ProductRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class ProductServiceImpl(
    @Autowired private val productRepository: ProductRepository
): ProductService {
    override fun getProducts(): Flux<Product> {
        return productRepository.findAll()
    }

    override fun getProduct(productId: String): Mono<Product> {
        return productRepository.findById(productId).switchIfEmpty { throw ProductNotFoundException("product not found") }
    }

    override fun recommendProduct(): Flux<Product> {
        return Flux.empty()
    }

    override fun updateProduct(product: Mono<ProductDto.UpdateProductReq>): Mono<Product> {
        return product.flatMap { productDto ->
            getProduct(productDto._id).flatMap { productRepository.save(productDto.toEntity()) }
        }
    }

    override fun deleteProduct(productId: String): Mono<Void> {
        return getProduct(productId).flatMap {
            productRepository.deleteById(productId)
        }
    }

    override fun createProduct(product: Mono<Product>): Mono<Product> {
        return product.flatMap { productRepository.save(it) }
    }

}