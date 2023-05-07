package net.thebookofcode.www.fyp.ui.snapshots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import net.thebookofcode.www.fyp.databinding.FragmentImageOrVideoBinding

class ImageOrVideoFragment : Fragment() {
    private var _binding:FragmentImageOrVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImageOrVideoBinding.inflate(LayoutInflater.from(context),container,false)
        //binding.imageView.load()

        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}