package com.github.skyfe79.helloroom.utils

import com.thedeanda.lorem.Lorem
import com.thedeanda.lorem.LoremIpsum


object WordUtils {
    private val generator = LoremIpsum.getInstance()
    var randomWord: String = ""
        get() = generator.getWords(1)
}