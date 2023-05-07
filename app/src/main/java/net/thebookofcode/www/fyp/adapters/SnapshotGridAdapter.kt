package net.thebookofcode.www.fyp.adapters

import android.content.Context
import android.media.MediaMetadataRetriever
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import net.thebookofcode.www.fyp.R
import net.thebookofcode.www.fyp.databinding.AlbumItemLayoutBinding
import net.thebookofcode.www.fyp.entity.Album

class SnapshotGridAdapter(private val context: Context, private val data: ArrayList<Album>) :
    BaseAdapter() {
    private var listener: ItemClickListener? = null
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Album {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addAlbum(album: Album) {
        data.add(album)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val itemBinding: AlbumItemLayoutBinding

        if (convertView == null) {
            itemBinding =
                AlbumItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
            itemBinding.root.tag = itemBinding
        } else {
            itemBinding = convertView.tag as AlbumItemLayoutBinding
        }
        val album = getItem(position)
        with(itemBinding) {
            if (album.quantity == 0) {
                img.setBackgroundResource(R.drawable.ic_warning)
            } else {
                if (album.type == "images") {
                    Glide.with(context)
                        .load(album.thumbnailUrls?.get(0))
                        .into(img)
                    // Log.d("Album:",album.albumName + album.thumbnailUrls?.get(0))
                } else if (album.type == "videos") {
                    /*val mediaMetadataRetriever = MediaMetadataRetriever()
                    mediaMetadataRetriever.setDataSource(album.thumbnailUrls?.get(0))

                    val bitmap = mediaMetadataRetriever.getFrameAtTime(
                        0,
                        MediaMetadataRetriever.OPTION_CLOSEST_SYNC
                    )
                    img.setImageBitmap(bitmap)*/
                    Glide.with(context)
                        .load("http://192.168.228.220:8000/motion_detection/motion_images/motion2023-05-05%2010%3A21%3A48.jpg")
                        .into(img)
                }
            }
            title.text = album.albumName
            quantity.text = album.quantity.toString()
        }
        itemBinding.root.setOnClickListener {
            if (listener != null) {
                listener!!.onItemClick(data[position])
            }
        }
        return itemBinding.root
    }

    interface ItemClickListener {
        fun onItemClick(album: Album)
    }

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}