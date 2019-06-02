package com.pryjda.webpdf.controller

import com.pryjda.webpdf.service.CreatePdfService
import org.springframework.core.io.InputStreamResource
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PdfController(private val service: CreatePdfService) {

    @GetMapping("/pdf", produces = ["application/pdf"])
    fun makePdf() = InputStreamResource(service.createPdf())
}