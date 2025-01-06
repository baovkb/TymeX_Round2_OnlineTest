package com.vkbao.currencyconverter.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.vkbao.currencyconverter.R
import com.vkbao.currencyconverter.data.models.Currency
import com.vkbao.currencyconverter.databinding.CurrencyItemDropdownSpinnerBinding
import com.vkbao.currencyconverter.databinding.CurrencyItemSpinnerBinding

class CurrencySpinnerAdapter(
    private val context: Context,
    private val items: Array<Currency>,
    private var disablePosition: Array<Int> = emptyArray()
) : BaseAdapter() {
    private lateinit var binding: CurrencyItemSpinnerBinding

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun isEnabled(position: Int): Boolean {
        return !disablePosition.contains(position)
    }

    fun setNewDisable(disablePosition: Array<Int>) {
        this.disablePosition = disablePosition
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (convertView != null) {
            binding = convertView.tag as CurrencyItemSpinnerBinding
        } else {
            binding = CurrencyItemSpinnerBinding.inflate(LayoutInflater.from(context), parent, false)
            binding.root.tag = binding
        }

        binding.code.text = items[position].code

        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = CurrencyItemDropdownSpinnerBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.symbol.text = items[position].symbol
        binding.code.text = items[position].code
        binding.name.text = items[position].name

        if (!isEnabled(position)) {
            val disableColor = context.getColor(R.color.light_black)
            binding.symbol.setTextColor(disableColor)
            binding.code.setTextColor(disableColor)
            binding.name.setTextColor(disableColor)
        }

        return binding.root
    }
}