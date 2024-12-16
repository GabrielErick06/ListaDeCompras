@file:Suppress("UNREACHABLE_CODE")

package com.example.listadecompras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale




class ProductAdapter(contexto:Context):ArrayAdapter<Product> (contexto,0){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        val v : View

        if(convertView != null){
            v = convertView
        }else{
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item,parent,false)
        }
        val item = getItem(position)
        val txt_product =  v.findViewById<TextView>(R.id.txt_item_product)
        val txt_quantity_item = v.findViewById<TextView>(R.id.txt_quantity_item)
        val txt_price_item = v.findViewById<TextView>(R.id.txt_price_item)
        val img_photo_item = v.findViewById<ImageView>(R.id.img_photo_item)

        if (item != null) {
            txt_product.text = item.name
        }
        if (item != null) {
            txt_quantity_item.text = item.quantity.toString()
        }
        val f = NumberFormat.getCurrencyInstance(Locale("en","us"))

        if (item != null) {
            txt_price_item.text = f.format(item.price)
        }

        if (item != null) {
            if(item.photo != null){
                img_photo_item.setImageBitmap(item.photo)
            }
        }

        return v


    }
}