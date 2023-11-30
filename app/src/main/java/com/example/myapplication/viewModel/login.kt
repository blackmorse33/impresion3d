package com.example.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import java.lang.Exception

class login: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _isAuthenticated = MutableLiveData<Boolean>()
    val isAuthenticated: LiveData<Boolean> = _isAuthenticated

    fun signInWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        _loading.value = true
        try {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    Log.d("Taco", "signInWithEmailAndPassword logueado!!")
                    _isAuthenticated.value = true
                } else {
                    Log.d("Taco", "signInWithEmailAndPassword: ${task.result.toString()}!!")
                    _error.value = task.exception?.message
                }
            }
        } catch (ex: Exception) {
            _loading.value = false
            Log.d("Taco", "signInWithEmailAndPassword: ${ex.message}!!")
            _error.value = ex.message
        }
    }
}