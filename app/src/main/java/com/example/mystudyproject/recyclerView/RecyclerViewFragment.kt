package com.example.mystudyproject.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystudyproject.App
import com.example.mystudyproject.databinding.FragmentRecyclerViewBinding


class RecyclerViewFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerViewBinding
    private lateinit var adapter: UsersAdapter

    private val usersService: UsersService
        get() = (requireActivity().applicationContext as App).usersService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)

        adapter = UsersAdapter(object : UserActionListener {
            override fun onUserMove(user: User, moveBy: Int) {
                usersService.moveUser(user, moveBy)
            }

            override fun onUserDelete(user: User) {
                usersService.deleteUser(user)
            }

            override fun onUserDetails(user: User) {
                Toast.makeText(requireActivity(), "more", Toast.LENGTH_SHORT).show()
            }

        })

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter


        usersService.addListener(usersListener)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        usersService.removeListener(usersListener)
    }

    private val usersListener: UsersListener = {
        adapter.users = it
    }
}