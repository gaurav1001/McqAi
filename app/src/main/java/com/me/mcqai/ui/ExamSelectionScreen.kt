package com.me.mcqai.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import guru.mcqai.www.R
import guru.mcqai.www.databinding.FragmentExamSelectionScreenBinding


class ExamSelectionScreen : Fragment() {

    private lateinit var binding: FragmentExamSelectionScreenBinding
    private lateinit var course: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExamSelectionScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.optionA.setOnCheckedChangeListener { buttonView,isChanged ->
            if (isChanged){
                course = buttonView.text.toString()
            }
        }

        binding.optionB.setOnCheckedChangeListener { buttonView, isChanged ->
            if (isChanged) {
                course = buttonView.text.toString()

             }
            }

        binding.optionC.setOnCheckedChangeListener { buttonView, isChanged ->
            if (isChanged) {
                course = buttonView.text.toString()
            }

          }

        binding.optionD.setOnCheckedChangeListener { buttonView, isChanged ->
            if (isChanged){
                course = buttonView.text.toString()
            }
        }


        binding.optionE.setOnCheckedChangeListener { buttonView, isChanged ->
            if (isChanged){
                course = buttonView.text.toString()
            }
        }

        binding.continueBtn.setOnClickListener {

            val action = ExamSelectionScreenDirections.actionExamSelectionScreenToDifficultyScreen(course)
            findNavController().navigate(action)

        }


        }

}