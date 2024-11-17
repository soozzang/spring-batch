package com.exciting.batch.repository

import com.exciting.batch.domain.BitCandleDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface BitCandleRepository : MongoRepository<BitCandleDocument, String>