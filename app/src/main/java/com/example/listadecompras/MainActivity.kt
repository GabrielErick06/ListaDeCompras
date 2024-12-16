package com.example.listadecompras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productsAdapter = ProductAdapter(this)
        productsAdapter.addAll(productGlobal)

        //definindo o adaptador na lista.
        binding.listViewProducts.adapter = productsAdapter

        //definindo ação do botão
        binding.btnAddItem.setOnClickListener {
            //iniciando a Activity
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {

        super.onResume()

        val adapter = binding.listViewProducts.adapter as ProductAdapter
        adapter.clear()

        adapter.addAll(productGlobal)
        val sum = productGlobal.sumOf { it.price * it.quantity }
        val f = NumberFormat.getCurrencyInstance(Locale("en", "us"))
        binding.txtTotal.text = "Total: ${f.format(sum)}"


    }
}