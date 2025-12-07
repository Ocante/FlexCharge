package com.bless.flexcharge

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class RequestFragment : Fragment() {

    companion object {
        private const val ARG_TYPE = "arg_type"
        fun newInstance(type: Int): RequestFragment {
            val frag = RequestFragment()
            val b = Bundle()
            b.putInt(ARG_TYPE, type)
            frag.arguments = b
            return frag
        }
    }

    private var pricePerUnit = 0.0

    private lateinit var edtInput: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioValue: RadioButton
    private lateinit var radioAmount: RadioButton
    private lateinit var txtResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_request, container, false)

        edtInput   = view.findViewById(R.id.edt_input)
        radioGroup = view.findViewById(R.id.radio_group_mode)
        radioValue = view.findViewById(R.id.radio_value)
        radioAmount = view.findViewById(R.id.radio_amount)
        txtResult  = view.findViewById(R.id.txt_result)

        // Define preço base conforme tipo
        val type = requireArguments().getInt(ARG_TYPE)
        if (type == 0) {
            // Combustível
            pricePerUnit = 6.00
        } else {
            // Elétrico
            pricePerUnit = 2.50
        }

        // Quando o usuário digitar algo ou trocar opção
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                calculate()
            }
            override fun afterTextChanged(s: Editable?) { }
        }
        edtInput.addTextChangedListener(watcher)

        radioGroup.setOnCheckedChangeListener { _, _ ->
            calculate()
        }

        // Valor padrão
        radioValue.isChecked = true

        return view
    }

    private fun calculate() {
        val input = edtInput.text.toString().toDoubleOrNull()
        if (input == null) {
            txtResult.text = "Total:"
            return
        }

        if (radioValue.isChecked) {
            // Usuário digitou R$, converter para quantidade
            val amount = input / pricePerUnit
            txtResult.text = String.format("Quantidade: %.2f", amount)
        } else {
            // Usuário digitou quantidade, converter para valor
            val total = input * pricePerUnit
            txtResult.text = String.format("Total: R$ %.2f", total)
        }
    }
}
