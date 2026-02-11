package guru.mcqai.www.model

import kotlinx.serialization.Serializable

@Serializable
data class Mcq(
    val question: String,
    val option: List<String> = emptyList(),
    val answer: String
)