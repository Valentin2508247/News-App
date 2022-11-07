package com.valentin.newsapp.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "enclosure", strict = false)
data class Enclosure(
    @field:Attribute(name = "url", required = false)
    var url: String? = null,
    @field:Attribute(name = "type", required = false)
    var type: String? = null
)