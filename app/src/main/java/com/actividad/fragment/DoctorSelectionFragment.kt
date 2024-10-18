package com.actividad.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DoctorSelectionFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_doctor_selection, container, false)

        // Establecer el título del fragmento en español
        requireActivity().title = getString(R.string.doctor_selection_title)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val doctorList = listOf(
            Doctor("Dr. Juan Pérez", "Cardiología", "Disponible"),
            Doctor("Dra. Ana López", "Dermatología", "Disponible"),
            Doctor("Dr. Carlos Gómez", "Pediatría", "Ocupado"),
            Doctor("Dra. Mariana Rodríguez", "Ginecología", "Disponible"),
            Doctor("Dr. Felipe Sánchez", "Neurología", "Ocupado"),
            Doctor("Dra. Sofía Morales", "Psiquiatría", "Disponible"),
            Doctor("Dr. Pablo Fernández", "Traumatología", "Disponible"),
            Doctor("Dra. Valentina Torres", "Oftalmología", "Disponible"),
            Doctor("Dr. Ricardo Castro", "Endocrinología", "Ocupado"),
            Doctor("Dra. Laura González", "Medicina Interna", "Disponible")
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewDoctors)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = DoctorAdapter(doctorList) { selectedDoctor ->
            sharedViewModel.selectDoctor(selectedDoctor)
            findNavController().navigate(R.id.action_doctorSelection_to_appointmentDetails)
        }

        return view
    }
}
