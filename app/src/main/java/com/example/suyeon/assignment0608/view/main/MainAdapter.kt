package com.example.suyeon.assignment0608.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.suyeon.assignment0608.R
import com.example.suyeon.assignment0608.data.Employee
import kotlinx.android.synthetic.main.item_main.view.*


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

        // ViewHolder.itemView 범위 내에서 코드를 수행합니다.
        with(holder.itemView) {
            person_id.text = item.id
            email.text = item.email
            name.text = item.firstName.plus(" ").plus(item.lastName)
            avatar.text = item.avatar

            //item이 final이라서 계속 새로 넣어줘야함
            btn_delete.setOnClickListener {
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