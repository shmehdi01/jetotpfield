package lib.syed.jetotpfield.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OTPFieldViewModel(private val length: Int) : ViewModel() {

    private val _fields = mutableStateListOf<String>().apply {
        repeat(length) {
            add("")
        }
    }

    val focusRequesters = mutableStateListOf<FocusRequester>().apply {
        repeat(length) {
            add(FocusRequester())
        }
    }

    fun onChange(value: String, index: Int) {
        _fields[index] = value

        viewModelScope.launch {

            if (value.isEmpty()) {
                //Go Back
                delay(40L)
                if (index > 0) {
                    focusRequesters[index - 1].requestFocus()
                }
            } else if (index < length - 1) {
                focusRequesters[index + 1].requestFocus()
            }
        }
    }

    val fields get() = _fields

    val otp get() = _fields.joinToString(separator = "")
}