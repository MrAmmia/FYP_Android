package net.thebookofcode.www.fyp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.thebookofcode.www.fyp.databinding.SnapshotItemLayoutBinding

class SnapshotListAdapter(private val context: Context, var thumbnailsUrl:ArrayList<String>):RecyclerView.Adapter<SnapshotListAdapter.SnapshotListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnapshotListViewHolder {
        val itemBinding =
            SnapshotItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SnapshotListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return thumbnailsUrl.size
    }

    override fun onBindViewHolder(holder: SnapshotListViewHolder, position: Int) {
        val currentUrl = thumbnailsUrl[position]
        holder.bind(currentUrl)
    }

    inner class SnapshotListViewHolder(val itemBinding:SnapshotItemLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)
    {
        fun bind(url: String)= with(itemBinding){
            Glide.with(context)
                .load(url)
                .into(imageView)
        }
    }


}