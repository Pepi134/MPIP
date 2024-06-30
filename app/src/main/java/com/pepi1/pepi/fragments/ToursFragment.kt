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
import com.pepi1.pepi.models.TourModel
import com.pepi1.pepi.adapters.ToursRecyclerViewAdapter
import com.pepi1.pepi.activities.TourDetailsActivity
import com.google.gson.Gson

class ToursFragment: Fragment()  {
    private lateinit var _view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _view = inflater.inflate(R.layout.tours_fragment, container, false)
        return _view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        properRecyclerView()

    }

    private fun properRecyclerView() {
        val data = Gson().fromJson(ReadJSONFromAssets(requireContext(),"data/tours.json"), TourModel::class.java)
        val itemAdapter= ToursRecyclerViewAdapter(data, onClicked =::tourDetails,requireContext())
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context )
        recyclerView.adapter = itemAdapter
    }

    private fun tourDetails(tour: TourModel.Tour){
        var intent= Intent(requireContext(), TourDetailsActivity::class.java)
        intent.putExtra("KEY_TOUR",tour)
        startActivity(intent)
    }

}