package com.example.mohamedwael.weather.ViewController

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mohamedwael.weather.DataController.SearchDataController
import com.example.mohamedwael.weather.R

class SearchResultsRecyclerViewAdapter(val searchDataController: SearchDataController) : RecyclerView.Adapter<SearchResultsRecyclerViewAdapter.PlaceViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder
    {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.place_tuple, parent, false)
        return PlaceViewHolder(mainView)
    }

    override fun getItemCount(): Int
    {
        return searchDataController.sizeOfListOfPlaces()
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int)
    {
        holder.nameTextView.text = searchDataController.getPlaceNameAtPosition(position)
        holder.temperatureTextView.text = searchDataController.getPlaceScreenNameAtPosition(position)
    }

    class PlaceViewHolder(val mainView: View) : RecyclerView.ViewHolder(mainView)
    {
        val nameTextView: TextView
        val temperatureTextView: TextView
        init
        {
            nameTextView= mainView.findViewById(R.id.nameTextView)
            temperatureTextView = mainView.findViewById(R.id.temperatureTextView)
        }
    }
}
