package com.example.proyecto_sisvita.data.model

data class Preguntas(
    val text: String,
    val options: List<String> = listOf(
        "Me preocupo fácilmente",//1
        "Tengo pensamientos o pensamientos negativos sobre mi: inferioridad o torpeza",//2
        "Me siento inseguro de mi mismo",//3
        "Doy demasiadas vueltas a las cosas sin llegar a decidirme",//4
        "Siento miedo",//5
        "Me cuesta concentrarme",//6
        "Pienso que la gente se dará cuenta de mis problemas o de la torpeza de mis actos"//7
    )
)
