package com.example.proyecto_sisvita.data.model

data class Test (
    val id_test: Int,
    val id_tipotest: Int,
    val estado: Boolean,
    val id_categoria: Categoria,
    val tipo_test: TipoTest
){
}