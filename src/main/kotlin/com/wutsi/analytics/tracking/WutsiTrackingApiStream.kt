package com.wutsi.analytics.tracking

import com.wutsi.analytics.tracking.dto.PushTrackRequest
import com.wutsi.analytics.tracking.dto.PushTrackResponse
import com.wutsi.analytics.tracking.event.EventURN
import com.wutsi.analytics.tracking.event.TrackPushedPayload
import com.wutsi.platform.core.stream.EventStream
import java.util.UUID

class WutsiTrackingApiStream(
  private val eventStream: EventStream
): WutsiTrackingApi {
  override fun push(request: PushTrackRequest): PushTrackResponse {
    eventStream.publish(EventURN.TRACK_PUSHED.urn, TrackPushedPayload(request.track))
    return PushTrackResponse(transactionId = UUID.randomUUID().toString())
  }
}
