package com.example.bsac_schedule.ui.chooseGroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.bsac_schedule.R
import com.example.bsac_schedule.apiUtils.ApiClient
import com.example.bsac_schedule.apiUtils.impl.ApiClientImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChooseGroupFragment : Fragment() {

    private lateinit var chooseGroupViewModel: ChooseGroupViewModel
    private lateinit var root: View
    private val apiClient: ApiClient = ApiClientImpl()
    private var linksValueMap: Map<String, String> = HashMap()
    val viewModelScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chooseGroupViewModel =
            ViewModelProviders.of(this).get(ChooseGroupViewModel::class.java)
        root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        val progressBar = root.findViewById<ProgressBar>(R.id.indeterminateBar)
        makeAsycnScheduleRequest(progressBar, textView)
        progressBar.visibility = VISIBLE
        return root
    }

    private fun makeAsycnScheduleRequest(
        progressBar: ProgressBar,
        textView: TextView
    ) {
        val spinner = root.findViewById<Spinner>(R.id.course_spinner)
        viewModelScope.launch {
            linksValueMap = apiClient.makeRequestForHtmlDownloadPage()
            setSpinnerValues(spinner)
            progressBar.visibility = INVISIBLE
            textView.text = resources.getString(R.string.choose_schedule)
            setSpinnerListener(spinner)
        }
    }

    private fun setSpinnerListener(spinner: Spinner) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModelScope.launch {
                    println("test123")
                }
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModelScope.launch {
                    apiClient.donloadAndParseGroups(linksValueMap.get(spinner.selectedItem.toString()))
                }
            }
        }
    }

    private fun setSpinnerValues(spinner: Spinner) {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            linksValueMap.keys.toTypedArray()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}
