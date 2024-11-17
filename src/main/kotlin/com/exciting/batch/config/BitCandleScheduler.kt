package com.exciting.batch.config

import com.exciting.batch.application.BitCandleService
import com.exciting.batch.domain.BitCandleDocument
import com.exciting.batch.repository.BitCandleRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BitCandleScheduler(
    private val bitCandleService: BitCandleService,
    private val bitCandleRepository: BitCandleRepository
) {

    @Scheduled(fixedRate = 300000)
    fun fetchAndProcessCandles() {
        bitCandleService.getCandles("KRW-BTC", 5, 10)
            .subscribe { candle ->
                val candleDocument = BitCandleDocument(
                    market = candle.market,
                    candleDateTimeUtc = candle.candle_date_time_utc,
                    candleDateTimeKst = candle.candle_date_time_kst,
                    openingPrice = candle.opening_price,
                    highPrice = candle.high_price,
                    lowPrice = candle.low_price,
                    tradePrice = candle.trade_price,
                    timestamp = candle.timestamp,
                    candleAccTradePrice = candle.candle_acc_trade_price,
                    candleAccTradeVolume = candle.candle_acc_trade_volume,
                    unit = candle.unit
                )
                bitCandleRepository.save(candleDocument)
            }
    }
}
