package guru.mcqai.www

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


import dagger.hilt.android.AndroidEntryPoint
import guru.mcqai.www.databinding.FragmentHomeScreenBinding
import guru.mcqai.www.utility.AlertBox

@AndroidEntryPoint
class HomeScreen : Fragment() {

    lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabBtn.setOnClickListener {
            val getContent = binding.textInputEditText.text.toString()

            if (getContent.isEmpty()){
                AlertBox.singleButtonAlert("Please enter some text",requireContext())
            }else{
                findNavController().navigate(HomeScreenDirections.actionHomeScreenToMcqScreen(getContent))
            }

        }

        binding.eraseFabBtn.setOnClickListener {
           if (binding.textInputEditText.text.toString().isEmpty()){
               AlertBox.singleButtonAlert("No text to erase",requireContext())
           }else{
               binding.textInputEditText.text.clear()
           }
        }

    }

}