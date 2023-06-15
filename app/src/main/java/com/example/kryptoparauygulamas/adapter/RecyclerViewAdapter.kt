package com.example.kryptoparauygulamas.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kryptoparauygulamas.R
import com.example.kryptoparauygulamas.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.text_name
import kotlinx.android.synthetic.main.row_layout.view.text_price


class RecyclerViewAdapter(private val cryptoList: ArrayList<CryptoModel>, private val listener :Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    private val colors:Array<String> = arrayOf("#800080","#6495ED","#40E0D0","#CCCCFF","#DE3163","#FF7F50","#DFFF00")
    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    class RowHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(cryptoModel:CryptoModel,colors:Array<String>,position: Int ,listener:Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position%7]))
            itemView.text_name.text=cryptoModel.currency
            itemView.text_price.text=cryptoModel.price
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position],colors,position,listener)
    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }
}