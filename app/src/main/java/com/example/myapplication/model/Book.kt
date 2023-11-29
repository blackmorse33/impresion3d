package com.example.myapplication.model

class Book (
    val id: String,
    val coverURL: String,
    val title: String,
    val author: String,
    val rating: Float,
    val downloads: Int)
{
    constructor(): this("" ,"", "", "",0.0f,0)
}
