package com.example.mohamedwael.weather.ViewController

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.example.mohamedwael.weather.DataController.SearchDataController
import com.example.mohamedwael.weather.DataController.myDB
import com.example.mohamedwael.weather.Model.Place
import com.example.mohamedwael.weather.R
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import kotlin.math.log
import com.example.mohamedwael.weather.DataController.DBHelper



class MainActivity : AppCompatActivity()
{
    private lateinit var searchDataController: SearchDataController



    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareRecyclerView()
        prepareEditText()
        prepareDataController()
        prepareDatabase()


    }

    // Initialization

    private fun prepareRecyclerView()
    {
        searchResultsRecyclerView.setHasFixedSize(true)
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun prepareEditText()
    {
        searchBar.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
            {
                startSearch()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    private fun prepareDataController()
    {
        searchDataController = SearchDataController.Factory.newInstance(this.applicationContext)
    }

    private fun startSearch()
    {
        prepareView()
        searchFor(searchBar.text.toString())
    }

    private fun searchFor(searchTerm: String?)
    {
        if (searchTerm != null && searchTerm.isNotEmpty() && searchTerm.trim().isNotEmpty())
        {
            searchDataController.searchFor(searchBar.text.trim().toString(),
                successHandler = {
                    handleResponse(it)
                })
        }
    }

    private fun handleResponse(listOfPlaces: List<Place>)
    {
        if (!listOfPlaces.isEmpty())
        {
            searchResultsRecyclerView.adapter = SearchResultsRecyclerViewAdapter(searchDataController)
            prepareView()
        }
    }

    private fun prepareView()
    {
        searchResultsRecyclerView.visibility = View.VISIBLE
    }

    private fun prepareDatabase()
    {
        val db = myDB(this)
        db.createRecords("lol", "lol1")
        db.createRecords("lofl", "lol21")
        val arraylist = db.selectRecords()
        //val nam = cursor?.getString(cursor?.getColumnIndex(db.EMP_ID))
        //Log.i("AhoDatabase",arraylist?.size.toString())

    }

}
