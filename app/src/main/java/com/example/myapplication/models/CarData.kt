package com.example.myapplication.models

data class CarData(val text: String,val text2: String,

    ) {
}

data class Car(
    val Count: Int,
    val Message: String,
    val Results: List<CarResult>,
    val SearchCriteria: Any) {
}
