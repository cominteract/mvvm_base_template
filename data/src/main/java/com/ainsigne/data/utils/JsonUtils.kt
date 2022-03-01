package com.ainsigne.data.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
fun json(): Gson = GsonBuilder().apply {
    setLenient()
}.create()
