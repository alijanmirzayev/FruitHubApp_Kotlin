package com.alijan.fruithubapp.ui.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.fruithubapp.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val repo: DataStoreRepository) : ViewModel() {

    fun saveName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.saveName(name)
            } catch (e: Exception) {
                Log.e("AuthenticationViewModel", e.localizedMessage.toString())
            }
        }
    }

}