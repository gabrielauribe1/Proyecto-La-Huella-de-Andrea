package com.example.lahuelladeandrea

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lahuelladeandrea.database.AppDatabase
import com.example.lahuelladeandrea.database.QAModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QandA : AppCompatActivity() {
    private lateinit var qaRV: RecyclerView
    private lateinit var database: AppDatabase
    private var questionList = emptyList<QAModel>()
    private lateinit var questionET: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qand)
        qaRV = findViewById(R.id.qa_recycler_view)
        qaRV.layoutManager = LinearLayoutManager(this)
        database = AppDatabase.getDatabase(this)

        questionET = findViewById(R.id.preguntatxt)
        database.qa().getQuestions().observe(this, {

            questionList = it
            // Adapter del ListView.
            val adapter = QAAdapter( questionList )
            qaRV.adapter = adapter
        })
    }

    /*Función para agregar pregunta a la base de datos*/
    @SuppressLint("NotifyDataSetChanged")
    fun addQuestion(button: View) {
        val question = questionET.text.toString()
        if( question.length < 5 ) return

        val questionModel = QAModel( question, "" )

        println("Add Question ${ questionModel.question }")

        CoroutineScope(Dispatchers.IO).launch {
            database.qa().insertQuestion(questionModel)
            //qaRV.adapter?.notifyDataSetChanged()
        }

    }

    /*Clase que crea adaptador y actualiza el RecyclerView*/
    private inner class QAAdapter(var list: List<QAModel>): RecyclerView.Adapter<QAAdapter.QAHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QAAdapter.QAHolder {
            val holder = layoutInflater.inflate(R.layout.qa_question, parent, false)
            return QAHolder(holder)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: QAAdapter.QAHolder, position: Int) {
            val item = this.list[position]
            holder.binding(item)
        }

        /*Clase que crea el holder y actualiza el RecyclerView*/
        private inner class QAHolder(vista: View): RecyclerView.ViewHolder(vista) {
            private val questionTV = itemView.findViewById<TextView>(R.id.pregunta)
            private val answerTV = itemView.findViewById<TextView>(R.id.answerLabel)

            fun binding(qaModel: QAModel) {
                questionTV.text = qaModel.question
                answerTV.text = if( qaModel.answer.isEmpty() ) "No se ha respondido a esta pregunta" else qaModel.answer
            }

        }
    }


}