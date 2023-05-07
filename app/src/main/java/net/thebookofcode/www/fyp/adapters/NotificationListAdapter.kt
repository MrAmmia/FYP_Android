package net.thebookofcode.www.fyp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import net.thebookofcode.www.fyp.R
import net.thebookofcode.www.fyp.databinding.NotificationItemLayoutBinding
import net.thebookofcode.www.fyp.room.entity.Notification

class NotificationListAdapter(var notification : ArrayList<Notification>):RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder>() {
    private var listener: ItemClickListener? = null
    private var longClickListener: ItemLongClickListener? = null
    var isLongClickEnabled: Boolean = false
    var isSelectAll: Boolean = false
    private var selectedNotifications = ArrayList<Notification>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val itemBinding =
            NotificationItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return notification.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val currentNotification = notification[position]

        holder.itemBinding.root.setOnClickListener {
            if (isLongClickEnabled){
                if (holder.itemBinding.img.background == R.drawable.ic_unselected.toDrawable()){
                    holder.itemBinding.img.setBackgroundResource(R.drawable.ic_place_holder)
                    selectedNotifications.remove(currentNotification)
                }else{
                    holder.itemBinding.img.setBackgroundResource(R.drawable.ic_unselected)
                    selectedNotifications.add(currentNotification)
                }
            }else{
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(notification[position])
                }
            }
        }

        holder.itemBinding.root.setOnLongClickListener {
            holder.itemBinding.img.setBackgroundResource(R.drawable.ic_unselected)
            isLongClickEnabled = true
            if (longClickListener != null && position != RecyclerView.NO_POSITION) {
                longClickListener!!.onItemLongClick(notification[position])
                if (!selectedNotifications.contains(notification[position])) {
                    selectedNotifications.add(notification[position])
                }
                if (selectedNotifications.size == 0) {
                    isLongClickEnabled = false
                }
            }
            true
        }

        holder.bind(currentNotification)

    }

    fun getNotification(position: Int): Notification {
        return notification[position]
    }

    fun getSelectedNotifications(): ArrayList<Notification> {
        return selectedNotifications
    }


    fun selectAll(): ArrayList<Notification> {
        selectedNotifications.clear()
        selectedNotifications.addAll(notification)
        return selectedNotifications
    }


    interface ItemClickListener {
        fun onItemClick(notification: Notification)
    }

    interface ItemLongClickListener {
        fun onItemLongClick(notification: Notification)
    }

    fun setOnLongClickListener(longClickListener: ItemLongClickListener) {
        this.longClickListener = longClickListener
    }

    fun setOnItemClickListener(listener: ItemClickListener?) {
        this.listener = listener
    }

    inner class NotificationViewHolder(val itemBinding: NotificationItemLayoutBinding):RecyclerView.ViewHolder(itemBinding.root)
    {
        fun bind(currentNotification: Notification) = with(itemBinding) {
            if(currentNotification.imageUrl!!.isNotEmpty()){
                img.setBackgroundResource(R.drawable.ic_place_holder)
            }else{
                img.setBackgroundResource(R.drawable.ic_place_holder)
            }
            title.text = currentNotification.title
            subject.text = currentNotification.subject
            content.text = currentNotification.content
            timeStamp.text = currentNotification.time.takeLast(5)
        }

    }
}