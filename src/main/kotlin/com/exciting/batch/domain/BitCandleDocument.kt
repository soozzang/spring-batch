package com.exciting.batch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bitcoin_candles")
data class BitCandleDocument(
    @Id val id: String? = null,
    val market: String,
    val candleDateTimeUtc: String,
    val candleDateTimeKst: String,
    val openingPrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val tradePrice: Double,
    val timestamp: Long,
    val candleAccTradePrice: Double,
    val candleAccTradeVolume: Double,
    val unit: Int
)