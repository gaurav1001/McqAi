package guru.mcqai.www

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import guru.mcqai.www.databinding.FragmentMcqScreenBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class McqScreen() : Fragment() {

    val viewModel: McqGenerateViewmodel by activityViewModels()
    val args: McqScreenArgs by navArgs()

    private lateinit var binding: FragmentMcqScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMcqScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = args.data

        Log.d("TAG", "onViewCreated: $content")



        lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){

                viewModel.mcqUiState.collect { state->
                    when(state){

                        is McqUiState.Idle ->{
                            if (content == null) return@collect
                            viewModel.generateQuestions(content)
                        }

                        is McqUiState.Loading ->{
                            Glide.with(this@McqScreen)
                                .load(R.drawable.loading)
                                .into(binding.loadingImg)
                        }

                        is McqUiState.Success -> {

                            binding.mcqRecycler.visibility = View.VISIBLE
                            val adapter = state.list?.let { McqAdapter(it){score->
                                viewModel.updateScore(score)
                             }
                            }
                            binding.mcqRecycler.adapter = adapter

                        }
                        is McqUiState.Error -> {

                            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                            builder
                                .setMessage("${state.exception.message}")
                                .setTitle("Error")
                                .setPositiveButton("Ok") { dialog, which ->
                                    dialog.dismiss()
                                    findNavController().navigateUp()
                                }

                            val dialog: AlertDialog = builder.create()
                            dialog.show()

                        }

                    }
                }
            }

        }

        binding.fabBtn.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.scoreScreen)

        }

    }

    override fun onDestroy() {
        super.onDestroy()

    }


}