package com.example.listadecompras

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listadecompras.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    //definindo o ActivityResultLauncher
    private val getContent =    registerForActivityResult(ActivityResultContracts.GetContent()){uri: Uri? -> uri?.let{
        binding.imgPhotoProduct.setImageURI(it)
    } ?: run{
        Toast.makeText(this,"Nenhuma imagem selecionada",Toast.LENGTH_SHORT).show()
    }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.txtPrice.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val input = it.toString()
                    if (input.contains(",")) {
                        val updatedInput = input.replace(",", ".")
                        binding.txtPrice.setText(updatedInput)
                        binding.txtPrice.setSelection(updatedInput.length) // Move o cursor para o final
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.imgPhotoProduct.setOnClickListener{
            getContent.launch("image/*")

        }

        binding.btnInsert.setOnClickListener {
            val txtProduct = binding.txtProduct.text
            val price = binding.txtPrice.text.toString()
            var quantity = binding.txtQuantity.text.toString()
            val product = txtProduct.toString()


            //fazendo verificação do preenchimento dos campos e adição à lista.
            if (product.isNotEmpty() && price.isNotEmpty() && quantity.isNotEmpty()) {
                val prod = Product(product, quantity.toInt(), price.toDouble())
                val imgProduct = binding.imgPhotoProduct.drawable
                if (imgProduct != null) {
                    prod.photo = imgProduct.toBitmap()
                }
                productGlobal.add(prod)
                binding.txtProduct.text.clear()
                binding.txtQuantity.text.clear()
                binding.txtPrice.text.clear()

            } else {
                binding.txtProduct.error = if (product.isEmpty()) "Preencha o produto!" else null
                binding.txtPrice.error = if (price.isEmpty()) "Preencha o valor!" else null
                binding.txtQuantity.error =
                    if (quantity.isEmpty()) "Preencha a quantidade!" else null
            }

        }



            binding.btnBack.setOnClickListener {
                finish()
            }
    }

}

