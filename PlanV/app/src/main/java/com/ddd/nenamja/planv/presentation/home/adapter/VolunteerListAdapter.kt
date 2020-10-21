package com.ddd.nenamja.planv.presentation.home.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddd.nenamja.planv.R
import com.ddd.nenamja.planv.data.remote.model.Item
import kotlinx.android.synthetic.main.item_volunteer_list.view.*

class VolunteerListAdapter(private val listener: UserActionListener) :
    RecyclerView.Adapter<VolunteerListAdapter.VolunteerItemViewHolder>() {

    private val volunteerList: MutableList<Item> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolunteerItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_volunteer_list, parent, false)
        return VolunteerItemViewHolder(view)
    }

    override fun getItemCount(): Int = volunteerList.size

    override fun onBindViewHolder(holder: VolunteerItemViewHolder, position: Int) {
        holder.setData(volunteerList[position])
    }

    fun setVolunteerList(list: List<Item>) {
        if (volunteerList.isNotEmpty()) {
            volunteerList.clear()
        }
        volunteerList.addAll(list)
        notifyDataSetChanged()
    }

    fun addVolunteerList(list: List<Item>) {
        volunteerList.addAll(list)
        notifyDataSetChanged()
    }

    interface UserActionListener {
        fun goToDetail(key: String)
    }

    inner class VolunteerItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                listener.goToDetail(volunteerList[adapterPosition].key1)
//                listener.goToDetail(adapterPosition.toString())
            }
        }

        fun setData(item: Item) {
            val peopleInfo = item.target.split(" ")
            val maxPeople = peopleInfo.last().replace("(", "").replace(")", "")
            val targetPeople =
                if (peopleInfo.first() == "") view.context.resources.getString(R.string.target_all) else peopleInfo.first()
            val price =
                if (item.price == "0") view.context.resources.getString(R.string.price_free) else view.context.resources.getString(
                    R.string.price_not_free
                )
            view.tv_item_program.text = item.pgmNm
            view.tv_max_people.text =
                String.format(view.context.resources.getString(R.string.max_people), maxPeople)
            view.tv_target_people.text = String.format(
                view.context.resources.getString(R.string.target_people),
                targetPeople
            )
            view.tv_location.text = String.format(
                view.context.resources.getString(R.string.target_location),
                item.organNm
            )
            view.tv_price.text = price
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.tv_price.setTextColor(
                    if (item.price == "0") view.context.resources.getColor(
                        R.color.colorBlack,
                        null
                    ) else view.context.resources.getColor(R.color.colorPrimary, null)
                )
            } else {
                view.tv_price.setTextColor(
                    if (item.price == "0") view.context.resources.getColor(R.color.colorBlack) else view.context.resources.getColor(
                        R.color.colorPrimary
                    )
                )
            }
        }
    }
}