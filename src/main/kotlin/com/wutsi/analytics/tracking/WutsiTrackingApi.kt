package com.wutsi.analytics.tracking

import com.wutsi.analytics.tracking.dto.PushTrackRequest
import com.wutsi.analytics.tracking.dto.PushTrackResponse
import feign.Headers
import feign.Param
import feign.RequestLine
import java.time.LocalDate

public interface WutsiTrackingApi {
    @RequestLine("POST /v1/tracks")
    @Headers(value = ["Content-Type: application/json"])
    public fun push(request: PushTrackRequest): PushTrackResponse

    @RequestLine("GET /v1/tracks/aggregate?start-date={start-date}")
    @Headers(value = ["Content-Type: application/json"])
    public fun aggregate(@Param("start-date") startDate: LocalDate): Unit
}
