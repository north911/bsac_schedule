package com.example.bsac_schedule.asyncTask

import android.os.AsyncTask
import com.example.bsac_schedule.fileDownloader.FileDownloader
import java.net.URL

class DownloadFileAsync : AsyncTask<URL, Int, ArrayList<String>>() {
    override fun doInBackground(vararg params: URL?): ArrayList<String> {
        return FileDownloader().downloadFile()
    }
}