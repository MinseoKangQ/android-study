package com.project.week9_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class HomeFragment : Fragment(R.layout.home_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            val viewModel : MyViewModel by activityViewModels()
            viewModel.increase()
            findNavController().navigate(R.id.action_homeFragment_to_nav1Fragment)
        }

        val viewModel : MyViewModel by activityViewModels()
        viewModel.liveData.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.textView)?.text = it.toString()
        }
    }
}

class Nav1Fragment : Fragment(R.layout.nav1_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            val viewModel : MyViewModel by activityViewModels()
            viewModel.increase()
            findNavController().navigate(R.id.action_nav1Fragment_to_nav2Fragment)
        }

        val viewModel : MyViewModel by activityViewModels()
        viewModel.liveData.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.textView)?.text = it.toString()
        }
    }
}

class Nav2Fragment : Fragment(R.layout.nav2_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            val viewModel : MyViewModel by activityViewModels()
            viewModel.increase()
            findNavController().navigate(R.id.action_nav2Fragment_to_homeFragment)
        }

        val viewModel : MyViewModel by activityViewModels()
        viewModel.liveData.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.textView)?.text = it.toString()
        }
    }

}