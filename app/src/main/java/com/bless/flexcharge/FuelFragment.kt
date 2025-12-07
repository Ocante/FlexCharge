package com.bless.flexcharge.fuel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bless.flexcharge.R
import kotlin.math.roundToInt

class FuelFragment : Fragment() {

    private val priceGasoline = 5.50
    private val priceEtanol = 4.20
    private val priceDiesel = 4.80

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_fuel, container, false)

        val spinner = view.findViewById<Spinner>(R.id.spinner_fuel)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group_fuel)
        val edt = view.findViewById<EditText>(R.id.edt_value)
        val txtTotal = view.findViewById<TextView>(R.id.txt_total_fuel)
        val btnClear = view.findViewById<Button>(R.id.btn_clear_fuel)
        val btnRequest = view.findViewById<Button>(R.id.btn_request_fuel)

        val list = listOf("Gasolina", "Etanol", "Diesel")
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, list)

        fun fuelPrice(type: String): Double = when (type) {
            "Gasolina" -> priceGasoline
            "Etanol" -> priceEtanol
            "Diesel" -> priceDiesel
            else -> priceGasoline
        }

        fun calculateAndShow() {
            val input = edt.text.toString().replace(",", ".").toDoubleOrNull()
            val fuelType = spinner.selectedItem.toString()
            val price = fuelPrice(fuelType)
            if (input == null) {
                txtTotal.text = "Total: -"
                return
            }
            val checkedId = radioGroup.checkedRadioButtonId
            if (checkedId == R.id.rb_money) {
                val liters = input / price
                val litersRounded = (liters * 100.0).roundToInt() / 100.0
                txtTotal.text = "Total: R$ %.2f → %.2f L".format(input, litersRounded)
            } else {
                val total = input * price
                txtTotal.text = "Total: %.2f L → R$ %.2f".format(input, total)
            }
        }

        edt.addTextChangedListener { calculateAndShow() }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, pos: Int, id: Long) { calculateAndShow() }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        radioGroup.setOnCheckedChangeListener { _, _ -> calculateAndShow() }

        btnClear.setOnClickListener {
            edt.text.clear()
            txtTotal.text = "Total: -"
        }

        btnRequest.setOnClickListener {
            val input = edt.text.toString().replace(",", ".").toDoubleOrNull()
            if (input == null) {
                Toast.makeText(requireContext(), "Digite valor ou litros", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val checkedId = radioGroup.checkedRadioButtonId
            val price = fuelPrice(spinner.selectedItem.toString())
            val summary = if (checkedId == R.id.rb_money) {
                val liters = input / price
                "Resumo:\nCombustível: ${spinner.selectedItem}\nValor: R$ %.2f\nLitros: %.2f".format(input, liters)
            } else {
                val total = input * price
                "Resumo:\nCombustível: ${spinner.selectedItem}\nLitros: %.2f\nTotal: R$ %.2f".format(input, total)
            }
            txtTotal.text = summary
            Toast.makeText(requireContext(), "Solicitação enviada", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
