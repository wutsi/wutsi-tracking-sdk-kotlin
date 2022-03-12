package com.wutsi.analytics.tracking

import com.fasterxml.jackson.databind.ObjectMapper
import feign.RequestInterceptor
import feign.codec.ErrorDecoder
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.collections.List

public class WutsiTrackingApiBuilder {
  public fun build(
    env: Environment,
    mapper: ObjectMapper,
    interceptors: List<RequestInterceptor> = emptyList(),
    errorDecoder: ErrorDecoder = ErrorDecoder.Default(),
    retryPeriodMillis: Long = 100L,
    retryMaxPeriodSeconds: Long = 3,
    retryMaxAttempts: Int = 5,
    connectTimeoutMillis: Long = 15000,
    readTimeoutMillis: Long = 15000,
    followRedirects: Boolean = true
  ) = feign.Feign.builder()
    .client(feign.okhttp.OkHttpClient())
    .encoder(feign.jackson.JacksonEncoder(mapper))
    .decoder(feign.jackson.JacksonDecoder(mapper))
    .logger(feign.slf4j.Slf4jLogger(WutsiTrackingApi::class.java))
    .logLevel(feign.Logger.Level.BASIC)
    .requestInterceptors(interceptors)
    .errorDecoder(errorDecoder)
    .retryer(feign.Retryer.Default(retryPeriodMillis,
      java.util.concurrent.TimeUnit.SECONDS.toMillis(retryMaxPeriodSeconds), retryMaxAttempts))
    .options(feign.Request.Options(connectTimeoutMillis, java.util.concurrent.TimeUnit.MILLISECONDS,
      readTimeoutMillis, java.util.concurrent.TimeUnit.MILLISECONDS, followRedirects))
    .target(WutsiTrackingApi::class.java, env.url)
}
