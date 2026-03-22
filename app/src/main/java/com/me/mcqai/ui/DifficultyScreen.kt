package com.me.mcqai.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.me.mcqai.utility.SettingsSerializer
import guru.mcqai.www.McqGenerateViewmodel
import guru.mcqai.www.R
import guru.mcqai.www.Settings
import guru.mcqai.www.databinding.FragmentDifficultyScreenBinding
import kotlinx.coroutines.launch


class DifficultyScreen : Fragment() {

    private lateinit var binding: FragmentDifficultyScreenBinding
    val args: DifficultyScreenArgs by navArgs()

    private lateinit var level: String

    val setupViewModel : SetupViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDifficultyScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val course = args.data

        Log.d("COURSE", "$course")

        binding.optionA.setOnCheckedChangeListener { buttonView, isChanged ->
        if (isChanged){
            level = buttonView.text.toString()
          }
        }

        binding.optionB.setOnCheckedChangeListener { buttonView, isChanged ->
            if (isChanged) {
                level = buttonView.text.toString()
            }
        }

        binding.optionC.setOnCheckedChangeListener { buttonView, isChanged ->
            if (isChanged) {
                level = buttonView.text.toString()
            }

        }

        binding.continueBtn.setOnClickListener {
            lifecycleScope.launch {
                setupViewModel.updateSetup(course, level)
            }
           findNavController().navigate(R.id.homeScreen)
        }

    }


}