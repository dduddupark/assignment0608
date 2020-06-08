package com.example.suyeon.assignment0608.view.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.data.Employee


/**
 * Created by SuYeon Park on 2020-06-08.
 * YapCompany
 * suyeon.park@yap.net
 *
 * Description :
 */
class MainAdapter(
    private val list: List<Employee>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val TAG = "MainAdapter"

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.id.text = item.id
        holder.name.text = item.employee_name
        holder.salary.text = item.employee_salary
        holder.age.text = item.employee_age

        if (!holder.itemView.hasOnClickListeners()) {

            Log.d(
                TAG,
                "holder.itemView.hasOnClickListeners() = " + holder.itemView.hasOnClickListeners() + " / id = " + item.id
            )

            holder.itemView.setOnClickListener {
                itemClickListener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.findViewById(R.id.id)
        val name: TextView = itemView.findViewById(R.id.name)
        val salary: TextView = itemView.findViewById(R.id.salary)
        val age: TextView = itemView.findViewById(R.id.age)

        companion object {

            fun from(parent: ViewGroup): ViewHolder {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
                return ViewHolder(view)
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface ItemClickListener {
    fun onClick(employee: Employee)
}