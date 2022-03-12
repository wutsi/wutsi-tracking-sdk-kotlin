package com.wutsi.analytics.tracking

import com.wutsi.analytics.tracking.dto.PushTrackRequest
import com.wutsi.analytics.tracking.dto.PushTrackResponse
import feign.Headers
import feign.RequestLine

public interface WutsiTrackingApi {
  @RequestLine("POST /v1/tracks")
  @Headers(value=["Content-Type: application/json"])
  public fun push(request: PushTrackRequest): PushTrackResponse
}
