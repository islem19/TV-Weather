package dz.islem.weatherapp.ui.forecast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dz.islem.weatherapp.R
import dz.islem.weatherapp.data.remote.model.weather.Hour
import dz.islem.weatherapp.util.StringUtils


class RecyclerViewAdapter(val hours: List<Hour>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private lateinit var context : Context


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvHour = itemView.findViewById<TextView>(R.id.tvHour)
        val ivIconCondition = itemView.findViewById<ImageView>(R.id.ivIconCondition)
        val tvCondition = itemView.findViewById<TextView>(R.id.tvCondition)
        val tvTemperature = itemView.findViewById<TextView>(R.id.tvTemperature)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_hour, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvHour.text = StringUtils.formatTime(hours[position].time)
        holder.tvCondition.text = hours[position].condition.text
        holder.tvTemperature.text = StringUtils.formatTemperature(hours[position].temp_c.toString())
        Glide.with(context)
            .load(StringUtils.formatUrl(hours[position].condition.icon))// image url
            .error(R.drawable.condition_error) // any image in case of error
            .override(100, 100) // resizing
            .centerCrop()
            .into(holder.ivIconCondition) // imageview object
    }

    override fun getItemCount(): Int {
        return hours.size?:0
    }

}
