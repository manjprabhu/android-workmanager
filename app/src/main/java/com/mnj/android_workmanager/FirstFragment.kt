package com.mnj.android_workmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.mnj.android_workmanager.databinding.FragmentFirstBinding
import com.mnj.android_workmanager.workmanager.WorkRequestScheduler

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    val TASK_KEY = "Input_TASK_KEY"

    private lateinit var composeView: ComposeView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.root
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView.setContent {
            MainContent(onClickOneTimeTask = {
                WorkRequestScheduler.constructOneTimeWork(requireContext())
            },
                onClickRepetitiveTask = {
                    WorkRequestScheduler.constructRepeatingWork(requireContext())
                })
        }

        /*  binding.buttonFirst.setOnClickListener {
              findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
          }*/

        /*binding.buttonFirst.setOnClickListener {
            println("==>> Clicked....")
            this.context?.let { it1 -> WorkRequestScheduler.constructOneTimeWork(it1) }
        }

        binding.buttonSecond.setOnClickListener {
            println("==>> Clicked....")
            this.context?.let { it1 -> WorkRequestScheduler.retryWork(it1) }
        }*/
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainContent(onClickOneTimeTask: () -> Unit, onClickRepetitiveTask: () -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "WorkManager",
                            color = Color.White
                        )
                    },
                    backgroundColor = Color(0xff0f9d58)
                )
            },
            content = { MyContent(onClickOneTimeTask, onClickRepetitiveTask) }
        )
    }

    @Composable
    fun MyContent(onClickOneTimeTask: () -> Unit, onClickRepetitiveTask: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = onClickOneTimeTask,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
            ) {
                Text("One Time")
            }

            Button(
                onClick = onClickRepetitiveTask,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58))
            ) {
                Text("Repetitive")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}