package com.valentin.newsapp.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root (name = "item", strict = false)
data class ItemXML(
    @field:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "enclosure", required = false)
    var enclosure: Enclosure? = null,
    )
