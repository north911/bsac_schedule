package com.example.bsac_schedule.ui.chooseGroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bsac_schedule.R
import com.example.bsac_schedule.asyncTask.DownloadFileAsync
import com.example.bsac_schedule.fileDownloader.FileDownloader
import com.example.bsac_schedule.htmlUtils.getStringArrayOfValues
import com.example.bsac_schedule.httpServices.DownloadPageService
import com.example.bsac_schedule.httpServices.buildUri
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChooseGroupFragment : Fragment() {

    private lateinit var chooseGroupViewModel: ChooseGroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chooseGroupViewModel =
            ViewModelProviders.of(this).get(ChooseGroupViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        chooseGroupViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = getString(R.string.action_course_not_loaded_yet)
        })
        makeRequestForHtmlDownloadPage(textView, root.findViewById(R.id.course_spinner))
        return root
    }

    private fun setSpinnerValues(spinner: Spinner, arr: Array<String>) {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arr)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                FileDownloader().downloadFile()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                DownloadFileAsync().execute()
            }
        }
    }

    private fun makeRequestForHtmlDownloadPage(textView: TextView, spinner: Spinner) {
        val requestAddress: DownloadPageService = buildUri()
        val pageCall: Call<String> = requestAddress.get()
        pageCall.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                println("ALARM ERROR")
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                textView.text = getString(R.string.choose_schedule)
                setSpinnerValues(spinner, getStringArrayOfValues(response.body()))
            }
        })
    }
}
