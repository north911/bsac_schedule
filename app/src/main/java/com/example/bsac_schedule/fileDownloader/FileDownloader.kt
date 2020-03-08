package com.example.bsac_schedule.fileDownloader

import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class FileDownloader {

    fun downloadFile(urlStr: String): ArrayList<String> {
        val url = URL(urlStr)
        val conection: URLConnection = url.openConnection()
        conection.connect()
        val lenghtOfFile = conection.contentLength
        println("length is:  $lenghtOfFile")
        val input: InputStream = BufferedInputStream(url.openStream(), 8192)
        var xlWb = WorkbookFactory.create(input)

        //Get reference to first sheet:
        val xlWs = xlWb.getSheetAt(0)
        var rows: Row = xlWs.getRow(1)
        var array = arrayListOf<String>()
        val progression: IntProgression = 3..rows.physicalNumberOfCells - 3 step 3
        for (i in progression) {
            array.add(rows.getCell(i).stringCellValue)
        }
        return array
    }
}