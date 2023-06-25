package com.pw3.coreapp.ui.students.allStudents.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pw3.CoreApp.databinding.LayoutBirthdayItemBinding
import com.pw3.coreapp.model.Student

class BirthdayItemAdapter(private val onClickListener: (Student) -> Unit) : ListAdapter<Student, BirthdayItemAdapter.BirthdayItemViewHolder>(
    StudentDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdayItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BirthdayItemViewHolder(
            LayoutBirthdayItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BirthdayItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BirthdayItemViewHolder(private val binding: LayoutBirthdayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            val context = binding.root.context
            binding.textStudentName.text = student.name
            binding.textStudentBirthday.text = student.birthDate

            binding.root.setOnClickListener {
                onClickListener(student)
            }
        }
    }

    companion object {
        class StudentDiffUtil : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Student,
                newItem: Student
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
