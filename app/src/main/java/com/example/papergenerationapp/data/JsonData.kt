package com.example.papergenerationapp.data

import com.example.papergenerationapp.R

//private const val BASE_PATH = "andorid.resource://com.example.papergenerationapp/raw/appdata.json"
//
//interface JsonAPI{
//    suspend fun loadJson(): JsonModel
//}

object JsonData{

//    val inputstream : BufferedReader = File(BASE_PATH).bufferedReader()
//
//    val JsonService: JsonAPI by lazy{
//        Gson().fromJson(
//            inputstream.use { it.readText() }
//            , JsonAPI::class.java)
//    }

    suspend fun loadJson(): List<ModelItem>{
        return listOf(
            ModelItem(
                grade = "Class 12",
                subject = "Mathematics",
                topicList = listOf(
                    "Relations and Functions",
                    "Algebra",
                    "Calculus",
                    "Linear Programming",
                    "Probability",
                    "Vectors",
                    "3D Geometry"
                )
            ), ModelItem(
                grade = "Class 11",
                subject = "Mathematics",
                topicList = listOf(
                    "Sets and Functions",
                    "Linear Inequalities",
                    "Sequence And Series",
                    "Trigonometric Functions",
                    "Coordinate Geometry",
                    "Statistics And Probability",
                    "Complex Numbers",
                )

            ), ModelItem(
                grade = "Class 10",
                subject = "Mathematics",
                topicList = listOf(
                    "Real Numbers",
                    "Polynomial",
                    "Quadratic Equations",
                    "Arithmetic Progression",
                    "Coordinate Geometry",
                    "Circle"
                )
            )
        )
    }

    suspend fun loadQuestionsJson(): List<QuestionListItem>{
        return listOf(
            /*
            CLASS 12
            "Relations and Functions",
            "Algebra",
            "Calculus",
            "Linear Programming",
            "Probability",
            "Vectors",
            "3D Geometry"
             */
            QuestionListItem(
                topic = "Class 12 Relations and Functions",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE","NEET","Board","GATE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))
                )
            ),
            QuestionListItem(
                topic = "Class 12 Algebra",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))
                )
            ),
            QuestionListItem(
                topic = "Class 12 Calculus",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))
                )
            ),
            QuestionListItem(
                topic = "Class 12 Linear Programming",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))
                )
            ),
            QuestionListItem(
                topic = "Class 12 Probability",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))
                )
            ),
            QuestionListItem(
                topic = "Class 12 Vectors",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))
                )
            ),
            QuestionListItem(
                topic = "Class 12 3D Geometry",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))
                )
            ),

            /*
            CLASS 11
            "Sets and Functions",
            "Linear Inequalities",
            "Sequence And Series",
            "Trigonometric Functions",
            "Coordinate Geometry",
            "Statistics And Probability",
            "Complex Numbers",
             */
            QuestionListItem(
                topic = "Class 11 Sets and Functions",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))
                )
            ),
            QuestionListItem(
                topic = "Class 11 Linear Inequalities",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))                )
            ),
            QuestionListItem(
                topic = "Class 11 Sequence And Series",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))                )
            ),
            QuestionListItem(
                topic="Class 11 Trigonometric Functions",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))                )
            ),
            QuestionListItem(
                topic = "Class 11 Coordinate Geometry",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))                )
            ),
            QuestionListItem(
                topic = "Class 11 Statistics And Probability",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))                )
            ),
            QuestionListItem(
                topic = "Class 11 Complex Numbers",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))                )
            ),
            /*
            CLASS 10
            "Real Numbers",
            "Polynomial",
            "Quadratic Equations",
            "Arithmetic Progression",
            "Coordinate Geometry",
            "Circle"
             */
            QuestionListItem(
                topic = "Class 10 Real Numbers",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))                )
            ),
            QuestionListItem(
                topic = "Class 10 Polynomial",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))                )
            ),
            QuestionListItem(
                topic = "Class 10 Quadratic Equations",
                questionList = listOf(
                    QuestionItem(question = "Equation of (x+1)2-x2=0 has number of real roots equal to:",tagList = listOf("JEE"), optionsList = listOf("1","2","3","4","5")),
                    QuestionItem(question = "Find two numbers whose sum is 27 and product is 182",tagList = listOf("Board")),
                    QuestionItem(question = "Find the roots of the following quadratic equations, if they exist, by the method of completing the square",questionImage = R.drawable.class_10_quadratic_equation_1,tagList = listOf("Board"))                )
            ),
            QuestionListItem(
                topic="Class 10 Arithmetic Progression",
                questionList = listOf(
                    QuestionItem(question = "How many terms of the AP. 9, 17, 25... must be taken to give a sum of 636?",tagList = listOf("Board"), optionsList = listOf("12","10","9","13")),
                    QuestionItem(question = "Arithmetic mean is equal to sum of all observations divided by the first term of the AP",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "Find the sum of first 30 terms of AP: -30, -25, -8 ...",tagList = listOf("Board")),
                )
            ),
            QuestionListItem(
                topic = "Class 10 Coordinate Geometry",
                questionList = listOf(
                    QuestionItem(question = "this is ques 1 and its a multiple choice question",tagList = listOf("JEE"), optionsList = listOf("option1","option2","option3","option4","option5")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "this is ques 3 and its a normal text based question",tagList = listOf("Board")),
                    QuestionItem(question = "this is ques 4 and its a text based question with Image",tagList = listOf("Board"))
                )
            ),
            QuestionListItem(
                topic = "Class 10 Statistics And Probability",
                questionList = listOf(
                    QuestionItem(question = "Which of the following cannot be the probability of a ",tagList = listOf("JEE"), optionsList = listOf("2/3","-1.5","15%","0.7")),
                    QuestionItem(question = "this is ques 2 and its a true false question",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "2 defective pens are accidentally mixed with 132 good ones. It is not possible to just look at a pen and tell whether or not it is defective. " +
                            "One pen is taken out at random from this lot. Determine the probability that the pen taken out is a good one",tagList = listOf("Board"))
                )
            ),
            QuestionListItem(
                topic = "Class 10 Circle",
                questionList = listOf(
                    QuestionItem(question = "If tangents PA and PB from a point P to a circle with centre O are inclined to each other at an angle of 80°, then POA is equal to",tagList = listOf("JEE"), optionsList = listOf("50 degree","60 degree","70 degree","80 degree")),
                    QuestionItem(question = "Two tangents drawn from an external point are equal in length",tagList = listOf("NEET"),optionsList = listOf("True","False")),
                    QuestionItem(question = "Prove that opposite sides of a quadrilateral circumscribing a circle subtend supplementary angles at the centre of the circle. ",tagList = listOf("Board")),
                    QuestionItem(question = "In Figure 1, a triangle ABC with B = 90° is shown. Taking AB as diameter," +
                            " a circle has been drawn intersecting AC at point P. Prove that the tangent drawn at point P bisects BC. ", questionImage = R.drawable.class_10_circle_1, tagList = listOf("Board"))
                )
            )
        )

    }
}
