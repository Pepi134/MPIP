package com.pepi1.pepi.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pepi1.pepi.R
import com.pepi1.pepi.ReadJSONFromAssets
import com.pepi1.pepi.activities.HotelDetailsActivity
import com.pepi1.pepi.adapters.HotelsRecyclerViewAdapter
import com.pepi1.pepi.models.HotelModel
import com.google.gson.Gson

class HotelFragment: Fragment()  {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = inflater.inflate(R.layout.hotel_fragment, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        properRecyclerView()

    }

    private fun properRecyclerView() {
        val data = Gson().fromJson(ReadJSONFromAssets(requireContext(),"data/hotels.json"), HotelModel::class.java)
        val itemAdapter= HotelsRecyclerViewAdapter(data, onClicked =::hotelDetails,requireContext())
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context )
        recyclerView.adapter = itemAdapter
    }


    private fun hotelDetails(hotel: HotelModel.Hotel){
        var intent= Intent(requireContext(), HotelDetailsActivity::class.java)
        intent.putExtra("KEY_HOTEL",hotel)
        startActivity(intent)
    }
}