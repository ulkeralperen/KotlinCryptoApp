package com.deneme.kotlinretrofit.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.deneme.kotlinretrofit.R
import com.deneme.kotlinretrofit.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*


class RecyclerViewAdapter(private val cryptoList : ArrayList<CryptoModel>,private val listener:Listener ) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {
    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }
    private val colors: Array<String> = arrayOf("#4b4041","#3d2d2e","#544f4f","#5b3d3f","#7e7b7b","#2a1315","#3b181b","#270609")

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(cryptoModel: CryptoModel, colors:Array<String>,position: Int,listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            println("model: ${cryptoModel}")
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
            itemView.text_name.text= cryptoModel.currency
            itemView.text_price.text=cryptoModel.price
        }
    }

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