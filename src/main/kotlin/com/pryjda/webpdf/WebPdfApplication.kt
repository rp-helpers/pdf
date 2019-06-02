package com.pryjda.webpdf

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebPdfApplication

fun main(args: Array<String>) {
    runApplication<WebPdfApplication>(*args)
}
