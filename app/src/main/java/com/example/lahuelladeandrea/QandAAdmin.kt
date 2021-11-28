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
import kotlin.coroutines.CoroutineContext

class QandAAdmin : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private var questionAdminAdapter: RecyclerView ? = null
    private var questionList = emptyList<QAModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qand_aadmin)

        questionAdminAdapter= findViewById<RecyclerView>(R.id.questionAdminRV)
        LinearLayoutManager(applicationContext).also { questionAdminAdapter?.layoutManager = it }

        database = AppDatabase.getDatabase(this)

        database.qa().getQuestions().observe(this, {

            questionList = it

            // Adapter del ListView.
            val adapter = QuestionAdminAdapter( questionList )
            questionAdminAdapter?.adapter = adapter

        })
    }

    private inner class QuestionAdminAdapter(var questions: List<QAModel>): RecyclerView.Adapter<QuestionAdminAdapter.QuestionAdminHolder>() {

        //Seteamos el layout de la pregunta
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdminHolder {
            val holder = layoutInflater.inflate(R.layout.question_admin_holder,parent,false)
            return QuestionAdminHolder(holder)
        }

        // Seteamos el número de preguntas (elementos) que tendrá el RecyclerView a través de la longitud de la lista.
        override fun getItemCount(): Int {
            return questions.size
        }

        private inner class QuestionAdminHolder(view: View): RecyclerView.ViewHolder(view) {

            private lateinit var question: QAModel

            val questionLabel: TextView = itemView.findViewById(R.id.questionLabel)
            val answerEditText: EditText = itemView.findViewById(R.id.answerEditText)
            val replyBtn: TextView = itemView.findViewById(R.id.replyBtn)
            val deleteBtn: TextView = itemView.findViewById(R.id.deleteBtn)

            // Seteamos los valores de las preguntas en la tabla (elemento del RecyclerView).
            @SuppressLint("SetTextI18n")

            fun binding(question: QAModel) {
                this.question = question

                questionLabel.text = question.question
                answerEditText.setText(question.answer)

                replyBtn.setOnClickListener {
                    val answer = answerEditText.text.toString()

                    if (answer.length < 10) return@setOnClickListener

                    CoroutineScope(Dispatchers.IO).launch {
                        database.qa().putAnswer(question.questionId, answer)
                    }

                }
            }
        }

        override fun onBindViewHolder(holder: QuestionAdminHolder, position: Int) {
            holder.binding(questions[position]) // Ligamos la posesión (questions) con la función binding del holder.
        }
    }
}