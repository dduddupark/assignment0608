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
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var list = ArrayList<Employee>()

    private val TAG = "MainAdapter"

    fun setData(list: ArrayList<Employee>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.id.text = item.id
        holder.email.text = item.email
        holder.name.text = item.firstName.plus(" ").plus(item.lastName)
        holder.avatar.text = item.avatar

        if (!holder.itemView.hasOnClickListeners()) {

            Log.d(
                TAG,
                "holder.itemView.hasOnClickListeners() = " + holder.itemView.hasOnClickListeners() + " / id = " + item.id
            )

            holder.delete.setOnClickListener {
                itemClickListener.onDelete(item)
            }

            holder.itemView.setOnClickListener {
                itemClickListener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.findViewById(R.id.person_id)
        val email: TextView = itemView.findViewById(R.id.email)
        val name: TextView = itemView.findViewById(R.id.name)
        val avatar: TextView = itemView.findViewById(R.id.avatar)
        val delete: TextView = itemView.findViewById(R.id.btn_delete)

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
    fun onDelete(employee: Employee)
}