package com.ainsigne.home.presenter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ainsigne.common.utils.extension.bulleted
import com.ainsigne.domain.entities.SwitchGameDomainEntities
import com.ainsigne.home.R
import com.ainsigne.home.databinding.ItemSwitchGameBinding


class SwitchGamesAdapter(private var switchGames: List<SwitchGameDomainEntities.SwitchGameData>) :
    RecyclerView.Adapter<SwitchGamesAdapter.SwitchGamesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SwitchGamesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_switch_game,
                parent,
                false
            )

        return SwitchGamesViewHolder(
            ItemSwitchGameBinding.bind(view)
        )
    }

    fun updateList(switchGames: List<SwitchGameDomainEntities.SwitchGameData>) {
        this.switchGames = switchGames
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = switchGames.size

    override fun onBindViewHolder(holder: SwitchGamesViewHolder, position: Int) {
        holder.bind(switchGames[position])
    }


    class SwitchGamesViewHolder(private val binding: ItemSwitchGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(switchGame: SwitchGameDomainEntities.SwitchGameData) {
            binding.textSwitchGameName.text = switchGame.name
            binding.textSwitchGameDesc.text = switchGame.genre.joinToString(", ")
            binding.textSwitchGameDeveloperLabel.text = itemView.context.getString(R.string.developer_label)
            binding.textSwitchGameDeveloper.text = switchGame.developers.bulleted()

            binding.textSwitchGamePublisherLabel.text = itemView.context.getString(R.string.publisher_label)
            binding.textSwitchGamePublisher.text = switchGame.publishers.bulleted()
            binding.textSwitchGameReleaseDatesLabel.text = itemView.context.getString(R.string.release_dates_label)
            binding.textSwitchGameReleaseDates.text =
                itemView.context.getString(
                    R.string.release_dates,
                    switchGame.releaseDates.australia,
                    switchGame.releaseDates.japan,
                    switchGame.releaseDates.europe,
                    switchGame.releaseDates.northAmerica
                )
        }
    }
}
