package com.example.proyecto_sisvita.data.model

data class Test (
    val id_test: Int = 0,
    val id_tipotest: Int = 0,
    val estado: Boolean = true,
    val id_categoria: Categoria,
    val tipo_test: TipoTest
){
}