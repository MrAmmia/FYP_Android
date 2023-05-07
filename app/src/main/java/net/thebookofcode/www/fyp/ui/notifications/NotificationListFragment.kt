package net.thebookofcode.www.fyp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import net.thebookofcode.www.fyp.adapters.NotificationListAdapter
import net.thebookofcode.www.fyp.databinding.FragmentNotificationListBinding
import net.thebookofcode.www.fyp.model.MainViewModel
import net.thebookofcode.www.fyp.room.entity.Notification

@AndroidEntryPoint
class NotificationListFragment  : Fragment() {
    private var _binding:FragmentNotificationListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    lateinit var adapter: NotificationListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationListBinding.inflate(inflater,container,false)
        binding.notificationRecyclerView.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        viewModel.getAllNotifications.observe(viewLifecycleOwner, Observer {notifications ->
            adapter = NotificationListAdapter(notifications as ArrayList<Notification>)
            binding.notificationRecyclerView.adapter = adapter
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteNotification(adapter.getNotification(viewHolder.adapterPosition))
                Toast.makeText(
                    context,
                    "Deleted Successfully",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }).attachToRecyclerView(binding.notificationRecyclerView)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}