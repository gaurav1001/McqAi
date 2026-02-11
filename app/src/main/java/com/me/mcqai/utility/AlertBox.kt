package guru.mcqai.www.utility

import android.app.AlertDialog
import android.content.Context

object AlertBox {
    fun singleButtonAlert(msg: String,context: Context){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage(msg)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()

    }
}