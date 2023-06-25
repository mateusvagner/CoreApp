package com.pw3.coreapp.ui.students.allStudents.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pw3.CoreApp.databinding.LayoutAllStudentsItemBinding
import com.pw3.coreapp.model.Student

class StudentItemAdapter(private val onClickListener: (Student) -> Unit) : ListAdapter<Student, StudentItemAdapter.StudentItemViewHolder>(
    StudentDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StudentItemViewHolder(
            LayoutAllStudentsItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StudentItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StudentItemViewHolder(private val binding: LayoutAllStudentsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            val context = binding.root.context
            binding.textStudentName.text = student.name
            binding.textStudentStatus.text = student.status

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
