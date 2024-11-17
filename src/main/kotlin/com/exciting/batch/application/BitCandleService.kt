package com.exciting.batch.application

import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Slf4j
@Service
class BitCandleService(private val webClientBuilder: WebClient.Builder) {

    private val logger = KotlinLogging.logger {}

    private val webClient: WebClient = webClientBuilder
        .baseUrl("https://api.upbit.com/v1")
        .build()

    fun getCandles(market: String, unit: Int, count: Int): Flux<BitCandleDto> {
        return webClient.get()
            .uri("/candles/minutes/$unit?market=$market&count=$count")
            .retrieve()
            .bodyToFlux(BitCandleDto::class.java)
            .doOnError {e ->
                logger.error("Error fetching candles for market: $market and unit: $unit", e)
            }
            .retry(3)
    }
}