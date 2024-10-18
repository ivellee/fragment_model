package com.actividad.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import java.util.Calendar

class AppointmentDetailsFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_appointment_details, container, false)

        // Establecer el título del fragmento en español
        requireActivity().title = getString(R.string.appointment_details_title)

        // Inicializar el ViewModel
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Mostrar la información del doctor
        val doctorInfoTextView: TextView = view.findViewById(R.id.textDoctorInfo)
        sharedViewModel.selectedDoctor.observe(viewLifecycleOwner) { doctor ->
            doctorInfoTextView.text = getString(R.string.doctor_info, doctor.name, doctor.specialty)
        }

        // Configurar DatePicker y TimePicker
        datePicker = view.findViewById(R.id.datePicker)
        timePicker = view.findViewById(R.id.timePicker)

        // Configurar TimePicker en modo de 24 horas
        timePicker.setIs24HourView(true)

        // Botón para continuar a la confirmación
        val buttonNext: Button = view.findViewById(R.id.buttonNext)
        buttonNext.setOnClickListener {
            // Obtener la fecha y hora seleccionadas
            val selectedDate = "${datePicker.dayOfMonth}-${datePicker.month + 1}-${datePicker.year}"
            val selectedTime = "${timePicker.hour}:${timePicker.minute}"

            // Validar la selección de fecha y hora
            if (isDateTimeValid(datePicker.year, datePicker.month, datePicker.dayOfMonth, timePicker.hour, timePicker.minute)) {
                // Guardar la fecha y hora seleccionadas en el ViewModel
                sharedViewModel.selectDateTime(selectedDate, selectedTime)

                // Navegar al fragmento de confirmación
                findNavController().navigate(R.id.action_appointmentDetails_to_confirmation)
            } else {
                // Mostrar un mensaje de error
                Toast.makeText(requireContext(), getString(R.string.invalid_date_time), Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun isDateTimeValid(year: Int, month: Int, day: Int, hour: Int, minute: Int): Boolean {
        // Obtener la fecha actual
        val currentDate = Calendar.getInstance()
        val selectedDate = Calendar.getInstance()
        selectedDate.set(year, month, day, hour, minute)

        // Comparar fechas
        return selectedDate.after(currentDate)
    }
}
