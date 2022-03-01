package com.ainsigne.futurama.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ainsigne.common.utils.extension.loadUrl
import com.ainsigne.domain.entities.FuturamaDomainEntities
import com.ainsigne.futurama.R
import com.ainsigne.futurama.databinding.ItemFuturamaCharacterBinding


class FuturamaCharactersAdapter(private var futuramaCharacters: List<FuturamaDomainEntities.FuturamaCharacterData>) :
    RecyclerView.Adapter<FuturamaCharactersAdapter.FuturamaCharactersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuturamaCharactersViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_futurama_character,
                parent,
                false
            )

        return FuturamaCharactersViewHolder(
            ItemFuturamaCharacterBinding.bind(view)
        )
    }

    fun updateList(futuramaCharacters: List<FuturamaDomainEntities.FuturamaCharacterData>) {
        this.futuramaCharacters = futuramaCharacters
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = futuramaCharacters.size

    override fun onBindViewHolder(holder: FuturamaCharactersViewHolder, position: Int) {
        holder.bind(futuramaCharacters[position])
    }


    class FuturamaCharactersViewHolder(private val binding: ItemFuturamaCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(futuramaCharacter: FuturamaDomainEntities.FuturamaCharacterData) {
            binding.imageFuturamaCharacter.loadUrl(
                futuramaCharacter.images.main
            )
            binding.textFuturamaCharacterGenderLabel.text = itemView.context.getString(R.string.gender_label)
            binding.textFuturamaCharacterGender.text = futuramaCharacter.gender

            binding.textFuturamaCharacterSpeciesLabel.text = itemView.context.getString(R.string.species_label)
            binding.textFuturamaCharacterSpecies.text = futuramaCharacter.species

            binding.textFuturamaCharacterOccupationLabel.text = itemView.context.getString(R.string.occupation_label)
            binding.textFuturamaCharacterOccupation.text = futuramaCharacter.occupation

            binding.textFuturamaCharacterName.text = itemView.context.getString(
                R.string.full_name,
                futuramaCharacter.name.first,
                futuramaCharacter.name.middle,
                futuramaCharacter.name.last
            )
        }
    }
}
