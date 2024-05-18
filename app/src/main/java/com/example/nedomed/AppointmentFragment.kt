package com.example.nedomed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nedomed.databinding.FragmentAppointmentBinding

class AppointmentFragment : Fragment() {

    private var _binding: FragmentAppointmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        val calendarView: CalendarView = binding.calendarView
        val timeButtons = listOf<Button>(
            binding.time3pm,
            binding.time430pm,
            binding.time530pm,
            binding.time6pm
        )
        val bookButton: Button = binding.bookAppointmentButton

        // Spinner for patient names (Optional: Set up spinner adapter if needed)
        val patientSpinner: Spinner = binding.patientSpinner

        // Handle calendar date change
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val date = "$dayOfMonth/${month + 1}/$year"
            Toast.makeText(requireContext(), "Date: $date", Toast.LENGTH_SHORT).show()
        }

        // Handle time button clicks
        timeButtons.forEach { button ->
            button.setOnClickListener {
                Toast.makeText(requireContext(), "Time: ${button.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle book appointment button click
        bookButton.setOnClickListener {
            val selectedDate = "${calendarView.date}"
            val selectedTime = timeButtons.firstOrNull { it.isSelected }?.text ?: "No time selected"
            Toast.makeText(requireContext(), "Appointment booked for $selectedDate at $selectedTime", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}