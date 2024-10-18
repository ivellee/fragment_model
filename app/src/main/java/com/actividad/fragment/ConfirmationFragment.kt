package com.actividad.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.widget.Button

class ConfirmationFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_confirmation, container, false)

        // Establecer el título del fragmento en español
        requireActivity().title = getString(R.string.confirmation_title)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val confirmationTextView: TextView = view.findViewById(R.id.textConfirmation)

        sharedViewModel.selectedDoctor.observe(viewLifecycleOwner) { doctor ->
            sharedViewModel.selectedDateTime.observe(viewLifecycleOwner) { dateTime ->
                confirmationTextView.text = getString(
                    R.string.confirmation_info,
                    doctor.name,
                    doctor.specialty,
                    dateTime.first,
                    dateTime.second
                )
            }
        }

        val buttonConfirm: Button = view.findViewById(R.id.buttonConfirm)
        buttonConfirm.setOnClickListener {
            Toast.makeText(context, R.string.appointment_confirmed, Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
