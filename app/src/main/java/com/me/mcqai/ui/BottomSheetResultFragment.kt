package com.me.mcqai.ui

import android.R.attr.src
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import guru.mcqai.www.R
import guru.mcqai.www.databinding.FragmentBottomSheetResultBinding

@AndroidEntryPoint
class BottomSheetResultFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetResultBinding

    companion object{
        fun instance(data:Int): BottomSheetResultFragment {
            val fragment = BottomSheetResultFragment()
            val args = Bundle()
            args.putInt("result",data)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomSheetResultBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = arguments?.getInt("result")


        if (result == 1){
             binding.resultIcon.setImageResource(R.drawable.green_bg)
             binding.msg.text = "Well Done!"
        }else{
           binding.resultIcon.setImageResource(R.drawable.red_bg)
           binding.msg.text = "Try Again!"

        }

        binding.endBtn.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.scoreScreen)
        }

        binding.continueBtn.setOnClickListener {
            dismiss()
        }

    }


}