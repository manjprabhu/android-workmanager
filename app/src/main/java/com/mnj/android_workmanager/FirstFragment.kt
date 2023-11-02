package com.mnj.android_workmanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mnj.android_workmanager.databinding.FragmentFirstBinding
import com.mnj.android_workmanager.workmanager.WorkRequestScheduler

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    val TASK_KEY = "Input_TASK_KEY"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*  binding.buttonFirst.setOnClickListener {
              findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
          }*/

        binding.buttonFirst.setOnClickListener {
            println("==>> Clicked....")
            this.context?.let { it1 -> WorkRequestScheduler.constructOneTimeWork(it1) }
        }

        binding.buttonSecond.setOnClickListener {
            println("==>> Clicked....")
            this.context?.let { it1 -> WorkRequestScheduler.constructRepeatingWork(it1) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}