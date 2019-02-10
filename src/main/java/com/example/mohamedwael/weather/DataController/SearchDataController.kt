package com.example.mohamedwael.weather.DataController

import android.content.Context
import com.example.mohamedwael.weather.Model.Place
import android.os.Handler
import android.os.Looper
import android.util.Log
import okhttp3.*
import java.io.IOException
import kotlin.math.log

open class SearchDataController(val context: Context)
{
    private lateinit var listOfPlaces: ArrayList<Place>
    private val client = OkHttpClient();

    public open fun searchFor(searchTerm: String, successHandler: (List<Place>) -> Unit)
    {
        Log.d("TestRun","Started Run Method")
        run("https://jsonplaceholder.typicode.com/posts/42")
    }

    open fun sizeOfListOfPlaces(): Int
    {
        return listOfPlaces.size
    }

    open fun getPlaceNameAtPosition(position: Int): String
    {
        return listOfPlaces[position].name
    }

    open fun getPlaceScreenNameAtPosition(position: Int): String
    {
        return listOfPlaces[position].temperature
    }

    fun run(url: String)
    {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback
        {
            override fun onFailure(call: Call, e: IOException)
            {
                Log.i("TestAPI","Called API and it failed")
            }
            override fun onResponse(call: Call, response: Response)
            {
                Log.i("TestAPI","Called API and the Response is" + response.body()?.string())
            }
        })
    }

    class Factory
    {
        companion object
        {
            private val isSimulatingResults: Boolean = false

            public fun newInstance(context: Context): SearchDataController
            {
                return if (isSimulatingResults)
                {
                    SearchDataControllerProxy(context)
                }
                else
                {
                    SearchDataController(context)
                }
            }
        }
    }
}

class SearchDataControllerProxy(context: Context) : SearchDataController(context)
{
    private lateinit var listOfPlaces: ArrayList<Place>

    public override fun searchFor(searchTerm: String, successHandler: (List<Place>) -> Unit)
    {
        object : Thread()
        {
            override fun run()
            {
                Thread.sleep(2500)
                listOfPlaces = ArrayList<Place>()
                listOfPlaces.add(Place("Alexandria", "24"))
                listOfPlaces.add(Place("Cairo", "30"))
                listOfPlaces.add(Place("Port Said", "22"))
                listOfPlaces.add(Place("Aswan", "33"))
                listOfPlaces.add(Place("Asyout", "33"))
                Handler(Looper.getMainLooper()).post { successHandler(listOfPlaces) }
            }
        }.start()
    }

    override fun sizeOfListOfPlaces(): Int
    {
        return listOfPlaces.size
    }

    override fun getPlaceNameAtPosition(position: Int): String
    {
        return listOfPlaces[position].name
    }

    override fun getPlaceScreenNameAtPosition(position: Int): String
    {
        return listOfPlaces[position].temperature
    }

}
