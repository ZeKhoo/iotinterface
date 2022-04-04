package com.example.iotinterface.create

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.iotinterface.R

class createAdapter(private val createList: ArrayList<Attr>): RecyclerView.Adapter<createAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener
    private lateinit var toggleBtn: ToggleButton

    interface onItemClickListener {
        fun onItemClick(position: Int)
        fun visibility (itemView: View)
    }

    fun setOnClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.displaywgt, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return createList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(createList[position])
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: Attr) {
            val tvName = itemView.findViewById(R.id.textViewWidgetName) as TextView
            val tvStatus = itemView.findViewById(R.id.textViewStatus) as TextView
            tvName.text = user.name
            tvStatus.text = user.status
        }

//        Log.d("Test", "b")
    }
}
