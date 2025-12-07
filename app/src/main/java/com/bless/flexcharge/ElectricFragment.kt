package com.bless.flexcharge.electric

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bless.flexcharge.R
import kotlin.math.roundToInt

class ElectricFragment : Fragment() {

    private val priceKwh = 2.50

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_electric, container, false)

        val edt = view.findViewById<EditText>(R.id.edt_electric)
        val rbMoney = view.findViewById<RadioButton>(R.id.rb_money_e)
        val rbKwh = view.findViewById<RadioButton>(R.id.rb_kwh_e)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group_electric)
        val txtResult = view.findViewById<TextView>(R.id.txt_result_electric)
        val btnClear = view.findViewById<Button>(R.id.btn_clear_electric)
        val btnRequest = view.findViewById<Button>(R.id.btn_request_electric)

        fun calculate() {
            val value = edt.text.toString().replace(",", ".").toDoubleOrNull()
            if (rbMoney.isChecked && value != null) {
                val kwh = value / priceKwh
                val rounded = (kwh * 100.0).roundToInt() / 100.0
                txtResult.text = "Total: R$ %.2f → %.2f kWh".format(value, rounded)
            } else if (rbKwh.isChecked && value != null) {
                val total = value * priceKwh
                txtResult.text = "Total: %.2f kWh → R$ %.2f".format(value, total)
            } else {
                txtResult.text = "Total: -"
            }
        }

        edt.addTextChangedListener { calculate() }
        radioGroup.setOnCheckedChangeListener { _, _ -> calculate() }

        btnClear.setOnClickListener {
            edt.text.clear()
            txtResult.text = "Total: -"
        }

        btnRequest.setOnClickListener {
            val value = edt.text.toString().replace(",", ".").toDoubleOrNull()
            if (value == null) {
                Toast.makeText(requireContext(), "Digite valor ou kWh", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val summary = if (rbMoney.isChecked) {
                val kwh = value / priceKwh
                "Resumo:\nValor: R$ %.2f\nkWh: %.2f".format(value, kwh)
            } else {
                val total = value * priceKwh
                "Resumo:\nkWh: %.2f\nTotal: R$ %.2f".format(value, total)
            }
            txtResult.text = summary
            Toast.makeText(requireContext(), "Solicitação enviada", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
