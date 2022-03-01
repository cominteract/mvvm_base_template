package com.ainsigne.data.utils

import android.util.Log
import com.ainsigne.common.UNKNOWN_NETWORK_EXCEPTION
import com.ainsigne.common.utils.network.NetworkStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import timber.log.Timber

@ExperimentalCoroutinesApi
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<Pair<ResultType, Int>>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline clearData: suspend () -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline shouldClear: (RequestType, ResultType) -> Boolean = { requestType: RequestType, resultType: ResultType -> false }
) = flow<NetworkStatus<ResultType>> {
    val dbData = query().first()

    emit(NetworkStatus.Loading(null))

    if (dbData.second > 0) {
        emitAll(query().map { result -> NetworkStatus.DBSuccess(result.first) })
    }
    if (shouldFetch(dbData.first)) {
        try {
            val fetchData = fetch()
            if (fetchData is NetworkStatus.Error<*> && dbData.second < 1) {
                emitAll(query().map { NetworkStatus.Error(fetchData.errorMessage, it.first) })
            } else {
                if (shouldClear(fetchData, dbData.first)) {
                    clearData()
                }
                saveFetchResult(fetchData)

                emitAll(query().map { result ->
                    NetworkStatus.Success(result.first)
                })
            }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            emitAll(query().map { NetworkStatus.Error(throwable.message, it.first) })
        }

    } else {
        emitAll(query().map { result -> NetworkStatus.Success(result.first) })
    }


}