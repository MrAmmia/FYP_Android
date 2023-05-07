package net.thebookofcode.www.fyp.ui.snapshots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import net.thebookofcode.www.fyp.adapters.SnapshotListAdapter
import net.thebookofcode.www.fyp.databinding.FragmentSnapshotListBinding

class SnapshotListFragment : Fragment() {
    private var _binding: FragmentSnapshotListBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<SnapshotListFragmentArgs>()
    lateinit var adapter: SnapshotListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =
            FragmentSnapshotListBinding.inflate(LayoutInflater.from(context), container, false)
        if (args.urls == null) {
            binding.notEmpty.visibility = View.GONE
            binding.empty.visibility = View.VISIBLE
        } else {
            binding.empty.visibility = View.GONE
            binding.notEmpty.visibility = View.VISIBLE
            binding.recycler.layoutManager = GridLayoutManager(
                requireActivity(),2
            )
            adapter = SnapshotListAdapter(requireContext(),
                ArrayList(args.urls!!.toList())
            )
            binding.recycler.adapter = adapter
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}