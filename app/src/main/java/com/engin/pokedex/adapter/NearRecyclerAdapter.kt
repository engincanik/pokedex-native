package com.engin.pokedex.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.engin.pokedex.databinding.NearPokemonCardBinding
import com.engin.pokedex.model.Pokemon
import javax.inject.Inject

class NearRecyclerAdapter @Inject constructor() :
    RecyclerView.Adapter<NearRecyclerAdapter.NearViewHolder>() {

    class NearViewHolder(var binding: NearPokemonCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var pokemonList: List<Pokemon>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NearViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NearPokemonCardBinding.inflate(inflater, parent, false)
        return NearViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NearViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.binding.apply {
            holder.binding.pokemonNameTv.text = pokemon.name
            val type1 = pokemon.types[0].type.name
            holder.binding.cardType1Tv.text = type1
            typeColorPick(pokemon.types[0].type.name, holder.binding.cardType1Tv)
            if (pokemon.types.size > 1) {
                val type2 = pokemon.types[1].type.name
                holder.binding.cardType2Tv.text = type2
                typeColorPick(type2, holder.binding.cardType2Tv)
            } else {
                holder.binding.cardType2Tv.visibility = View.GONE
            }
        }
    }

    private fun typeColorPick(type: String, textView: TextView) {
        val gradientDrawable: GradientDrawable = textView.background.mutate() as GradientDrawable
        when (type) {
            //TODO add all types
            "fire" -> {
                gradientDrawable.setColor(Color.RED)
            }
            "water" -> {
                gradientDrawable.setColor(Color.BLUE)
            }
            "grass" -> {
                gradientDrawable.setColor(Color.GREEN)
            }
            "normal" -> {
                gradientDrawable.setColor(Color.GRAY)
            }
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}