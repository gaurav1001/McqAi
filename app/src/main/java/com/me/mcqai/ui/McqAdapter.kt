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

        val item = dataSet[position]
        viewHolder.apply {
            question.text = item.question
            optionA.text = item.option[0]
            optionB.text = item.option[1]
            optionC.text = item.option[2]
            optionD.text = item.option[3]
            correctAnswer.text = item.answer
            answerLayout.visibility = View.GONE


            optionsGroup.setOnCheckedChangeListener(null)

            val selectedId = when(item.selectionId){
                0 -> R.id.optionA
                1 -> R.id.optionB
                2 -> R.id.optionC
                3 -> R.id.optionD
                else -> -1  // No selection
            }

            if (selectedId != -1){
                optionsGroup.check(selectedId)
                answerLayout.visibility = View.VISIBLE
            }else{
                optionsGroup.clearCheck()
            }

            optionsGroup.setOnCheckedChangeListener { _,checkedId ->

                val selectedIndex = when (checkedId) {
                    R.id.optionA -> 0
                    R.id.optionB -> 1
                    R.id.optionC -> 2
                    R.id.optionD -> 3
                    else -> -1
                }

                // Save selection state
                item.selectionId = selectedIndex


                // Check correct answer
                if (selectedIndex != -1) {
                    if (item.option[selectedIndex] == item.answer) {
                        this@McqAdapter.itemClickListener(1)
                    }else{
                        this@McqAdapter.itemClickListener(0)
                    }
                }

            }
        }
    }

    override fun getItemCount() = dataSet.size
}