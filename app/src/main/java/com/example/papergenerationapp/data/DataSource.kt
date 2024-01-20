package com.example.papergenerationapp.data

import androidx.annotation.DrawableRes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModelItem(
    @SerialName("grade")
    val grade: String,
    @SerialName("subject")
    val subject: String,
    @SerialName("topics")
    val topicList: List<String>
)

@Serializable
data class QuestionListItem(
    @SerialName("topic_name")
    val topic: String,
    @SerialName("question_list")
    val questionList: List<QuestionItem> = listOf(),
)

@Serializable
data class QuestionItem(
//    @Transient val selected: Boolean = false,
    @SerialName("topic_name")
    val question: String,
    @SerialName("question_image")
    @DrawableRes val questionImage: Int = -1,
    @SerialName("tags")
    val tagList: List<String> = listOf(),
    @SerialName("options_image_list")
    @DrawableRes val optionsImageList: List<Int> = listOf(),
    @SerialName("options_list")
    val optionsList: List<String> = listOf()
)