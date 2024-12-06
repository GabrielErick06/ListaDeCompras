package com.example.listadecompras

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productsAdapter  = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        //definindo o adaptador na lista.
        binding.listViewProducts.adapter = productsAdapter

        //definindo ação do botão
        binding.btnInsert.setOnClickListener {
            val txtProduct = binding.txtProduct.text
            val product = txtProduct.toString()

            if (product.isNotEmpty()){
            productsAdapter.add(product)
            println("Gabe " + product)
            binding.txtProduct.text.clear()
        }else{
            binding.txtProduct.error = "Preencha o campo!"
            }
            //Buscando item clicado
            binding.listViewProducts.setOnItemLongClickListener { adapterView: AdapterView<*>, view : View, position: Int, id: Long->
                val item  = productsAdapter.getItem(position)

                //Removendo item clicado
                productsAdapter.remove(item)

                //retorno indicando que o click foi realizado com sucesso
                true
            }


    }
}
}