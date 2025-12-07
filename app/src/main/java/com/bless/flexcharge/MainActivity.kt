package com.bless.flexcharge

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.bless.flexcharge.databinding.ActivityMainBinding
import com.bless.flexcharge.fuel.FuelFragment
import com.bless.flexcharge.electric.ElectricFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = listOf("Selecione...", "Combustível", "Elétrico")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spinnerType.adapter = adapter

        // garante que não há fragmento carregado no começo
        clearFragment()

        binding.spinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    1 -> loadFragment(FuelFragment())
                    2 -> loadFragment(ElectricFragment())
                    else -> clearFragment()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                clearFragment()
            }
        }
    }

    // Usa API clássica (sem KTX) para evitar erro de "unresolved reference commit"
    private fun loadFragment(fragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        // se quiser animação, adicione aqui transaction.setCustomAnimations(...)
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
    }

    private fun clearFragment() {
        val frag = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (frag != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.remove(frag)
            transaction.commit()
        }
    }
}
