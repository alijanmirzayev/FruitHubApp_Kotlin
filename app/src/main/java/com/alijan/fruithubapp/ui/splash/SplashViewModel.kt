package com.alijan.fruithubapp.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alijan.fruithubapp.data.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    private val _name = MutableLiveData<String?>()
    val name: LiveData<String?> get() = _name

    init {
        getName()
    }

    private fun getName() {
        viewModelScope.launch {
            try {
                val name = dataStoreRepository.getName()

                if(name != null) {
                    _name.value = name
                }else {
                    _name.value = null
                }

            } catch (e: Exception){
                Log.e("SplashViewModel", e.localizedMessage.toString())
            }
        }
    }

}