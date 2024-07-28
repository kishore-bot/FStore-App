package com.example.fstore.domain.model

import com.example.fstore.domain.model.rec_sub.Bag

data class BagModel(
    val bags: List<Bag>,
    val total: Int
)