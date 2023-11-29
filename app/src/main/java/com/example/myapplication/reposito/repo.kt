package com.example.myapplication.reposito

import com.example.myapplication.model.Book
import com.google.firebase.firestore.FirebaseFirestore

class repo {
    fun addNewBook(book: Book){
        val firestore = FirebaseFirestore.getInstance()
    }
}