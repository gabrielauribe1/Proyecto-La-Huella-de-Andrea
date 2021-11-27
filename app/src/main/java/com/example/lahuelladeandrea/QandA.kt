package com.example.lahuelladeandrea

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

class QandA : AppCompatActivity() {
    private lateinit var qaRV: RecyclerView
    private var adaptador: QAAdapter? = null
    private lateinit var database: AppDatabase
    private var questionList = emptyList<QAModel>()
    private lateinit var questionET: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qand)
        qaRV = findViewById(R.id.qa_recycler_view)
        qaRV.layoutManager = LinearLayoutManager(applicationContext)
        database = AppDatabase.getDatabase(this)

        questionET = findViewById(R.id.preguntatxt)
        database.qa().getQuestions().observe(this, {

            questionList = it

            // Adapter del ListView.
            val adapter = QAAdapter( questionList )
            qaRV?.adapter = adapter

        })


    }

    fun addQuestion(button: View) {
        val question = QAModel(questionET.text.toString(), "")
        database.qa().insertQuestion(question)
    }

    private inner class QAAdapter(var list: List<QAModel>): RecyclerView.Adapter<QandA.QAHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QandA.QAHolder {
            val holder = layoutInflater.inflate(R.layout.qa_question, parent, false)
            return QAHolder(holder)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: QandA.QAHolder, position: Int) {
            val item = this.list[position]
            holder.binding(item)
        }
    }

    private inner class QAHolder(vista: View): RecyclerView.ViewHolder(vista) {
        private val questionTV = itemView.findViewById<TextView>(R.id.pregunta)

        fun binding(question: QAModel) {
            questionTV.text = question.toString()
        }

    }
}