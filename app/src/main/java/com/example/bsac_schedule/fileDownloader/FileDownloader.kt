package com.example.bsac_schedule.fileDownloader

import com.example.bsac_schedule.htmlUtils.getStringArrayOfValues
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.InputStream
import java.net.URL
import java.net.URLConnection

class FileDownloader {
    fun readFromExcelFile(filepath: String) {
        val inputStream = FileInputStream(filepath)
        //Instantiate Excel workbook using existing file:
        var xlWb = WorkbookFactory.create(inputStream)

        //Row index specifies the row in the worksheet (starting at 0):
        val rowNumber = 0
        //Cell index specifies the column within the chosen row (starting at 0):
        val columnNumber = 0

        //Get reference to first sheet:
        val xlWs = xlWb.getSheetAt(0)
        println(xlWs.getRow(rowNumber).getCell(columnNumber))
    }

    fun downloadFile(): ArrayList<String> {
        val url =
            URL("http://bsac.by/sites/default/files/content/landing/2019/71/files/09-10/raspisanie_ss0_kurs_2.xls")
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