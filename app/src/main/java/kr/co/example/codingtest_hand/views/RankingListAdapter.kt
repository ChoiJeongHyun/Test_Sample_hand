package kr.co.example.codingtest_hand.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.co.example.codingtest_hand.R
import kr.co.example.codingtest_hand.base.BaseViewHolder
import kr.co.example.codingtest_hand.database.entity.Ranking
import kr.co.example.codingtest_hand.databinding.ItemRankingBinding
import kr.co.example.codingtest_hand.utils.PLog

class RankingListAdapter(var rankingItems: List<Ranking> , val vm: MainViewModel) : RecyclerView.Adapter<RankingListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ranking,parent,false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.vm = vm
        holder.binding.item = rankingItems[position]
        holder.binding.rankingPosition = if (vm.sortType.value!!) position + 1 else itemCount - position
        holder.binding.lastItemPosition = itemCount
        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = rankingItems.size

    inner class ItemViewHolder(view: View) : BaseViewHolder<ItemRankingBinding>(view)






}