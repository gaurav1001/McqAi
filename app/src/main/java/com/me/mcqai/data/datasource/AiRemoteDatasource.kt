package guru.mcqai.www.data.datasource

import com.google.firebase.ai.FirebaseAI
import com.google.firebase.ai.GenerativeModel
import com.google.firebase.ai.type.ResponseModality
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.content
import com.google.firebase.ai.type.generationConfig
import guru.mcqai.www.model.Mcq
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class AiRemoteDatasource @Inject constructor (
    private val firebaseAI: FirebaseAI
){


    suspend fun mcqGenerator(context:String): List<Mcq>? {
        val prompt = content {
            text(
                """
"Generate exactly 20 multiple-choice questions based on this context: $context"
                
                """.trimIndent()
            )
        }

        val response = mcqSchemaModel.generateContent(prompt)

        return response.text?.let {
            Json.decodeFromString<List<Mcq>>(it)
        }
    }

    private val mcqSchemaModel: GenerativeModel get() =
        firebaseAI.generativeModel(
            modelName = "gemini-2.5-flash-lite",
            generationConfig = generationConfig {
                responseModalities = listOf(ResponseModality.TEXT)
                responseMimeType = "application/json"
                responseSchema = Schema.array(
                    Schema.obj(
                        mapOf(
                            "question" to Schema.string(),
                            "option" to Schema.array(Schema.string()),
                            "answer" to Schema.string()
                        )
                    )

                )
            }
        )
}