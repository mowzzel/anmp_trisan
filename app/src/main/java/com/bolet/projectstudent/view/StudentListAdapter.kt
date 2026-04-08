package com.bolet.projectstudent.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bolet.projectstudent.databinding.StudentCardBinding
import com.bolet.projectstudent.model.Student


class StudentListAdapter(val studentList:ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var binding: StudentCardBinding)
        :RecyclerView.ViewHolder(binding.root)

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.count()
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.txtNrp.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name

        holder.binding.btnDetail.setOnClickListener {
            val student = studentList[position]
            val action = StudentListDirections.actionStudentDetail(student)
            Navigation.findNavController(it).navigate(action)
        }

    }
}
