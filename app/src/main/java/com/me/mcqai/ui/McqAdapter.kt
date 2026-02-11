package guru.mcqai.www

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import guru.mcqai.www.model.Mcq


class McqAdapter(
    private val dataSet: List<Mcq>,
    val itemClickListener: (Int) -> Unit

) :
    RecyclerView.Adapter<McqAdapter.ViewHolder>() {



  inner  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val question: TextView = view.findViewById(R.id.question)
        val optionA: RadioButton = view.findViewById(R.id.optionA)
        val optionB: RadioButton = view.findViewById(R.id.optionB)
        val optionC: RadioButton = view.findViewById(R.id.optionC)
        val optionD: RadioButton = view.findViewById(R.id.optionD)
        val optionsGroup: RadioGroup = view.findViewById(R.id.optionGroup)
        val answerLayout: View = view.findViewById(R.id.answerLayout)
        val correctAnswer: TextView = view.findViewById(R.id.correctAnswer)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.mcq_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val isSelected = true
        val item = dataSet[position]
        viewHolder.apply {
            question.text = item.question
            optionA.text = item.option[0]
            optionB.text = item.option[1]
            optionC.text = item.option[2]
            optionD.text = item.option[3]
            correctAnswer.text = item.answer
            optionsGroup.clearCheck()
            answerLayout.visibility = View.GONE

            // Clear listener to avoid trigger on recycling
            optionsGroup.setOnCheckedChangeListener(null)



            optionsGroup.setOnCheckedChangeListener { _, _ ->
                when (optionsGroup.checkedRadioButtonId) {
                    R.id.optionA -> {
                        if (correctAnswer.text == item.option[0]){
                            this@McqAdapter.itemClickListener(1)

                        }
                    }
                    R.id.optionB -> {
                        if (correctAnswer.text == item.option[1]){
                            this@McqAdapter.itemClickListener(1)
                        }
                    }
                    R.id.optionC -> {
                        if(correctAnswer.text == item.option[2]){
                            this@McqAdapter.itemClickListener(1)
                        }
                    }
                    R.id.optionD -> {
                        if (correctAnswer.text == item.option[3]){
                            this@McqAdapter.itemClickListener(1)
                        }
                    }
                }

                answerLayout.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount() = dataSet.size
}