package com.actividad.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorAdapter(
    private val doctorList: List<Doctor>,
    private val onDoctorSelected: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    inner class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val doctorName: TextView = itemView.findViewById(R.id.textDoctorName)
        val doctorSpecialty: TextView = itemView.findViewById(R.id.textDoctorSpecialty)
        val doctorAvailability: TextView = itemView.findViewById(R.id.textDoctorAvailability)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctorList[position]
        holder.doctorName.text = doctor.name
        holder.doctorSpecialty.text = doctor.specialty
        holder.doctorAvailability.text = doctor.availability
        holder.itemView.setOnClickListener {
            onDoctorSelected(doctor)
        }
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }
}
