package com.pryjda.webpdf.service

import org.apache.commons.io.IOUtils
import org.springframework.stereotype.Service
import java.io.*
import java.util.*

@Service
class CreatePdfServiceImpl : CreatePdfService {

    val command: String = "wkhtmltopdf - -"

    override fun createPdf(): InputStream {

        val sourceFile = File("src/main/resources/Test.html")
        val fis = FileInputStream(sourceFile)
        val fos = ByteArrayOutputStream()
        runProcess(fis, fos)
        val value = Base64.getEncoder().encodeToString(fos.toByteArray())
        val inputArray = Base64.getDecoder().decode(value)
        val input = inputArray.inputStream()
        return input
    }

    private fun runProcess(html: InputStream, outputFile: OutputStream) {
        val wkhtml: Process = Runtime.getRuntime().exec(command)
        val errors = ByteArrayOutputStream()

        val errThread: Thread = createErrorThread(wkhtml, errors)
        val htmlReadThread: Thread = createHtmlReadThread(wkhtml, html)
        val pdfWriteThread: Thread = createPdfWriteThread(wkhtml, outputFile)

        errThread.start()
        pdfWriteThread.start()
        htmlReadThread.start()

        wkhtml.waitFor()
    }

    private fun createErrorThread(wkhtml: Process, outputStream: OutputStream): Thread =
            Thread {
                try {
                    IOUtils.copy(wkhtml.errorStream, outputStream)
                } catch (e: IOException) {
                    throw RuntimeException()
                }
            }

    private fun createHtmlReadThread(wkhtml: Process, html: InputStream): Thread =
            Thread {
                try {
                    IOUtils.copy(html, wkhtml.outputStream)
                    wkhtml.outputStream.flush()
                    wkhtml.outputStream.close()
                } catch (e: IOException) {
                    throw RuntimeException()
                }
            }

    private fun createPdfWriteThread(wkhtml: Process, outputFile: OutputStream): Thread =
            Thread {
                try {
                    IOUtils.copy(wkhtml.inputStream, outputFile)
                } catch (e: IOException) {
                    throw RuntimeException()
                }
            }
}

/*    override fun createPdf(): InputStream {

        val destinationFile = File("output2.pdf")
        val sourceFile = File("src/main/resources/Test.html")

        val fis = FileInputStream(sourceFile)
//        val fos = FileOutputStream(destinationFile)
        val fos = ByteArrayOutputStream()

        runProcess(fis, fos)
        /*val output = FileOutputStream(destinationFile)
        output.write(fos.toByteArray())
        output.close()*/


        /*val value = String(fos.toByteArray(), Charset.forName("UTF-8"))

        val input = value.byteInputStream(Charset.forName("UTF-8"))*/

        val value = Base64.getEncoder().encodeToString(fos.toByteArray())

        val inputArray = Base64.getDecoder().decode(value)

        val input = inputArray.inputStream()


//        val input = ByteArrayInputStream(fos.toByteArray())
//        input.close()

//        val result = fos.toString(charset("UTF-8"))
//        val result = String(fos.toByteArray(), Charset.forName("UTF-8"))


//        val input = ByteArrayInputStream(fos.toByteArray())
        return input
//        return result
    }*/


/*
override fun createPdf(): InputStream {

    val destinationFile = File("output2.pdf")
    val sourceFile = File("src/main/resources/Test.html")

    val fis = FileInputStream(sourceFile)
//        val fos = FileOutputStream(destinationFile)
    val fos = ByteArrayOutputStream()

    runProcess(fis, fos)
    val output = FileOutputStream(destinationFile)
    output.write(fos.toByteArray())
    output.close()

//        val result = fos.toString(charset("UTF-8"))
//        val result = String(fos.toByteArray(), Charset.forName("UTF-8"))



    return ByteArrayInputStream(fos.toByteArray())
//        return result
}*/

/*    override fun createPdf(): InputStream {

        val destinationFile = File("output2.pdf")
        val sourceFile = File("src/main/resources/Test.html")

        val fis = FileInputStream(sourceFile)
//        val fos = FileOutputStream(destinationFile)
        val fos = ByteArrayOutputStream()

        runProcess(fis, fos)
        /*val output = FileOutputStream(destinationFile)
        output.write(fos.toByteArray())
        output.close()*/

        val input = ByteArrayInputStream(fos.toByteArray())
        input.close()

//        val result = fos.toString(charset("UTF-8"))
//        val result = String(fos.toByteArray(), Charset.forName("UTF-8"))



        return input
//        return result
    }*/

/* override fun createPdf(): InputStream {

        val destinationFile = File("output2.pdf")
        val sourceFile = File("src/main/resources/Test.html")

        val fis = FileInputStream(sourceFile)
//        val fos = FileOutputStream(destinationFile)
        val fos = ByteArrayOutputStream()

        runProcess(fis, fos)
        /*val output = FileOutputStream(destinationFile)
        output.write(fos.toByteArray())
        output.close()*/


        val value = String(fos.toByteArray(), Charset.forName("UTF-8"))

        val input = value.byteInputStream(Charset.forName("UTF-8"))


//        val input = ByteArrayInputStream(fos.toByteArray())
//        input.close()

//        val result = fos.toString(charset("UTF-8"))
//        val result = String(fos.toByteArray(), Charset.forName("UTF-8"))



        return input
//        return result
    }*/