package com.pw3.coreapp.ui.students.allStudents.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pw3.CoreApp.R
import com.pw3.CoreApp.databinding.LayoutTuitionItemBinding
import com.pw3.coreapp.model.Student

class TuitionItemAdapter(private val onClickListener: (Student) -> Unit) : ListAdapter<Student, TuitionItemAdapter.TuitionItemViewHolder>(
    StudentDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TuitionItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TuitionItemViewHolder(
            LayoutTuitionItemBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TuitionItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TuitionItemViewHolder(private val binding: LayoutTuitionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            val context = binding.root.context
            binding.textStudentName.text = student.name
            binding.textStudentPaymentStatus.text = student.paymentStatus
            binding.textStudentPaymentDueDay.text =
                context.getString(R.string.student_payment_due_day, student.paymentDueDate)

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
