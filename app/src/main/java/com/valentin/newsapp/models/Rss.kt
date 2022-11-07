package com.valentin.newsapp.models

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class Rss(
    @field:ElementList(name = "channel", required = false)
    var channel: List<ItemXML>? = null
)
