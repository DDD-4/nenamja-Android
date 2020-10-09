package com.nenamja.volunteer.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nenamja.volunteer.R
import com.nenamja.volunteer.data.remote.model.VolunteerContent
import kotlinx.android.synthetic.main.item_volunteer_list.view.*

class VolunteerListAdapter :
    RecyclerView.Adapter<VolunteerListAdapter.VolunteerContentViewHolder>() {

    private val volunteerList: MutableList<VolunteerContent> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolunteerContentViewHolder {
        return VolunteerContentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_volunteer_list, parent, false)
        )
    }

    override fun getItemCount(): Int = volunteerList.size

    override fun onBindViewHolder(holder: VolunteerContentViewHolder, position: Int) {
        holder.setData(volunteerList[position])
    }

    fun setVolunteerList(list: List<VolunteerContent>) {
        if (volunteerList.isNotEmpty()) {
            volunteerList.clear()
        }
        volunteerList.addAll(list)
        notifyDataSetChanged()
    }

    class VolunteerContentViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun setData(data: VolunteerContent) {
            view.tv_title.text =
                String.format(view.context.getString(R.string.volunteer_program_label), data.PGMNM)
            view.tv_org_name.text = String.format(
                view.context.getString(R.string.volunteer_org_name_label),
                data.ORGANNM
            )
            view.tv_target.text =
                String.format(view.context.getString(R.string.volunteer_target_label), data.TARGET)
        }
    }
}