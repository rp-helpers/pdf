package com.pryjda.webpdf.service

import java.io.InputStream

interface CreatePdfService {
    fun createPdf(): InputStream
}