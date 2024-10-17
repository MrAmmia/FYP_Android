package net.thebookofcode.www.fyp.ui

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.MimeTypes
import net.thebookofcode.www.fyp.R
import net.thebookofcode.www.fyp.databinding.FragmentHomeBinding
import net.thebookofcode.www.fyp.util.Constants.Companion.BASE_URL
import net.thebookofcode.www.fyp.util.Constants.Companion.STREAMING_FILE
import net.thebookofcode.www.fyp.util.Constants.Companion.STREAMING_PATH
import net.thebookofcode.www.fyp.util.Constants.Companion.STREAMING_PORT


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L

    private val liveStreamUrl = "$BASE_URL/$STREAMING_PATH/$STREAMING_FILE"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.llSettings.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_settingsFragment)
        }

        binding.llNotifications.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_notificationListFragment)
        }

        binding.llSnapshots.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_snapshotsFragment)
        }
        binding.llVideoView.setOnClickListener {
            //playLiveVideo()
            playLiveStream()
        }

        return binding.root
    }

    private fun playLiveVideo() {
        val videoUri = Uri.parse("$BASE_URL:$STREAMING_PORT")
        val videoUrl = "$BASE_URL:$STREAMING_PORT"
        binding.videoView.visibility = View.VISIBLE
        binding.exoPlayerView.visibility = View.GONE
        binding.videoView.loadUrl(videoUrl)
        // binding.videoView.setVideoURI(videoUri)
    }

    private fun playLiveStream() {
        binding.videoView.visibility = View.GONE
        binding.exoPlayerView.visibility = View.VISIBLE
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        binding.exoPlayerView.player = exoPlayer
        val mediaItem = MediaItem.Builder()
            .setUri(liveStreamUrl)
            .setMimeType(MimeTypes.APPLICATION_M3U8)
            .build()
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.playWhenReady = true
    }

    private fun releasePlayer() {
        exoPlayer?.let { player ->
            playbackPosition = player.contentPosition
            player.release()
            exoPlayer = null
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        releasePlayer()
    }
}