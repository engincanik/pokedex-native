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
import com.bumptech.glide.RequestManager
import com.engin.pokedex.databinding.NearPokemonCardBinding
import com.engin.pokedex.model.Pokemon
import com.engin.pokedex.util.Constants
import com.engin.pokedex.util.Types
import java.util.*
import javax.inject.Inject

class NearRecyclerAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<NearRecyclerAdapter.NearViewHolder>() {

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
            glide.load(pokemon.sprites.frontDefault).into(holder.binding.nearPokemonIV)
            holder.binding.pokemonNameTv.text = pokemon.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            val type1 = pokemon.types[0].type.name
            holder.binding.cardType1Tv.text = type1.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
            typeColorPick(pokemon.types[0].type.name, holder.binding.cardType1Tv)
            if (pokemon.types.size > 1) {
                val type2 = pokemon.types[1].type.name
                holder.binding.cardType2Tv.text = type2.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }
                typeColorPick(type2, holder.binding.cardType2Tv)
            } else {
                holder.binding.cardType2Tv.visibility = View.INVISIBLE
            }
        }
    }

    private fun typeColorPick(type: String, textView: TextView) {
        val gradientDrawable: GradientDrawable = textView.background.mutate() as GradientDrawable
        when (type) {
            Types.FIRE.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.fireColor))
            }
            Types.WATER.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.waterColor))
            }
            Types.GRASS.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.grassColor))
            }
            Types.NORMAL.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.normalColor))
            }
            Types.BUG.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.bugColor))
            }
            Types.DRAGON.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.dragonColor))
            }
            Types.ELECTRIC.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.electricColor))
            }
            Types.FIGHTING.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.fightingColor))
            }
            Types.FLYING.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.flyingColor))
            }
            Types.GHOST.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.ghostColor))
            }
            Types.GROUND.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.groundColor))
            }
            Types.ICE.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.iceColor))
            }
            Types.POISON.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.poisonColor))
            }
            Types.ROCK.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.rockColor))
            }
            Types.PSYCHIC.typeName -> {
                gradientDrawable.setColor(Color.parseColor(Constants.psychicColor))
            }
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}