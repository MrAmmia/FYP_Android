package net.thebookofcode.www.fyp.ui.snapshots

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import net.thebookofcode.www.fyp.adapters.SnapshotGridAdapter
import net.thebookofcode.www.fyp.databinding.FragmentSnapshotsBinding
import net.thebookofcode.www.fyp.entity.Album
import net.thebookofcode.www.fyp.model.MainViewModel

@AndroidEntryPoint
class SnapshotsFragment : Fragment() {
    private var _binding: FragmentSnapshotsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var onlineImages: Album
    private lateinit var onlineVideos: Album
    private lateinit var savedImages: Album
    private lateinit var savedVideos: Album
    private lateinit var adapter: SnapshotGridAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSnapshotsBinding.inflate(inflater, container, false)
        // viewModel.getOnlinePhotos()
        // viewModel.getOnlineVideos()
        viewModel.getOnlineFiles()
        savedImages = Album(
            albumName = "saved images",
            thumbnailUrls = null,
            quantity = 0,
            type = "images"
        )
        savedVideos = Album(
            albumName = "saved videos",
            thumbnailUrls = null,
            quantity = 0,
            type = "videos"
        )


        adapter = SnapshotGridAdapter(
            requireContext(), arrayListOf(
                savedImages,
                savedVideos
            )
        )

        viewModel.myFiles.observe(viewLifecycleOwner, Observer {
            onlineImages = Album(
                albumName = "Online photos",
                thumbnailUrls = it.images as ArrayList<String>?,
                quantity = it.images.size,
                type = "images"
            )
            adapter.addAlbum(onlineImages)
            onlineVideos = Album(
                albumName = "Online Videos",
                thumbnailUrls = it.videos as ArrayList<String>?,
                quantity = it.videos.size,
                type = "videos"
            )
            Log.d("Album:Crash",onlineVideos.albumName + onlineVideos.thumbnailUrls?.get(0))
            adapter.addAlbum(onlineVideos)
        })

        /* viewModel.myImages.observe(viewLifecycleOwner, Observer {
             onlineImages = Album(
                 albumName = "Online photos",
                 thumbnailUrls = it.images as ArrayList<String>?,
                 quantity = it.images.size,
                 type = "images"
             )
             adapter.addAlbum(onlineImages)
         })

         viewModel.myVideos.observe(viewLifecycleOwner, Observer {
             onlineVideos = Album(
                 albumName = "Online Videos",
                 thumbnailUrls = it.videos as ArrayList<String>?,
                 quantity = it.videos.size,
                 type = "videos"
             )
             Log.d("Album:Crash",onlineVideos.albumName + onlineVideos.thumbnailUrls?.get(0))
             adapter.addAlbum(onlineVideos)
         })

         if(onlineImages.thumbnailUrls!!.size > 0){
             adapter.addAlbum(onlineImages)
         }

         if(onlineVideos.thumbnailUrls!!.size > 0){
             adapter.addAlbum(onlineVideos)
         }*/

        binding.gridView.adapter = adapter
        adapter.setOnItemClickListener(object : SnapshotGridAdapter.ItemClickListener {
            override fun onItemClick(album: Album) {
                val action: NavDirections =
                    SnapshotsFragmentDirections.actionSnapshotsFragmentToSnapshotListFragment(
                        urls = album.thumbnailUrls!!.toArray(
                            arrayOfNulls<String>(album.thumbnailUrls.size)
                        )
                    )
                findNavController().navigate(action)
            }
        })

        return binding.root
    }

    /*override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.gridView.numColumns = 4
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            binding.gridView.numColumns = 2
        }
    }*/

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
