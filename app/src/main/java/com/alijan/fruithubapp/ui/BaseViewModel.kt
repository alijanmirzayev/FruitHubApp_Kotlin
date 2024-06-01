package com.alijan.fruithubapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {

    protected open fun <T> handleApiRequest(
        request: suspend () -> Response<T>,
        onSuccess: (T) -> Unit,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = request()
                if (response.isSuccessful) {
                    onSuccess(response.body()!!)
                } else {
                    onError(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                onError(e.localizedMessage)
            }
        }
    }

}