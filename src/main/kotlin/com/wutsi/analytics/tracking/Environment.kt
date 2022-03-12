package com.wutsi.analytics.tracking

import kotlin.String

public enum class Environment(
  public val url: String
) {
  SANDBOX("https://wutsi-tracking-server-test.herokuapp.com"),
  PRODUCTION("https://wutsi-tracking-server-prod.herokuapp.com"),
  ;
}
