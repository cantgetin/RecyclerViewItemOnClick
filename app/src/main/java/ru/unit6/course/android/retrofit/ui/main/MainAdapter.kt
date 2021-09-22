package ru.unit6.course.android.retrofit.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.unit6.course.android.retrofit.R
import ru.unit6.course.android.retrofit.data.model.User

class MainAdapter(private val users: ArrayList<User>,val itemClickListener: (View, Int, Int) -> Unit) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.apply {
                val textViewUserName = this.findViewById<TextView>(R.id.textViewUserName)
                val textViewUserEmail = this.findViewById<TextView>(R.id.textViewUserEmail)
                val imageViewAvatar = this.findViewById<ImageView>(R.id.imageViewAvatar)

                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }

        fun onClick(itemClickListener: (View, Int, Int) -> Unit) {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        //DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        val vh = DataViewHolder(view);
        vh.onClick(itemClickListener)
        return vh
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener { view ->
            itemClickListener.invoke(view,users[position].id.toInt(),2)
        }
    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}