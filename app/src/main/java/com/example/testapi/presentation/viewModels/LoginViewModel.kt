package com.example.testapi.presentation.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.data.mapper.toCreateDto
import com.example.testapi.data.socket.SocketManager
import com.example.testapi.domain.model.RecoveryCodeRequest
import com.example.testapi.domain.model.UserData
import com.example.testapi.domain.repository.LocalDataSourceRepository
import com.example.testapi.domain.usecase.ChangePasswordUseCase
import com.example.testapi.domain.usecase.ChangeRoleUseCase
import com.example.testapi.domain.usecase.ConfirmEmailUseCase
import com.example.testapi.domain.usecase.ForgotPasswordUseCase
import com.example.testapi.domain.usecase.GetTokenUseCase
import com.example.testapi.domain.usecase.LoginUseCase
import com.example.testapi.domain.usecase.LogoutUseCase
import com.example.testapi.domain.usecase.RecoveryCodeUseCase
import com.example.testapi.domain.usecase.RecoveryPasswordUseCase
import com.example.testapi.domain.usecase.RegisterUseCase
import com.example.testapi.domain.usecase.SaveTokenUseCase
import com.example.testapi.presentation.states.ChangePasswordState
import com.example.testapi.presentation.states.ChangeRoleState
import com.example.testapi.presentation.states.ConfirmEmailState
import com.example.testapi.presentation.states.ForgotPasswordState
import com.example.testapi.presentation.states.LoginState
import com.example.testapi.presentation.states.RecoveryCodeState
import com.example.testapi.presentation.states.RecoveryPasswordState
import com.example.testapi.presentation.states.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val confirmMailUseCase: ConfirmEmailUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val loginUseCase: LoginUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val recoveryPasswordUseCase: RecoveryPasswordUseCase,
    private val recoveryCodeUseCase: RecoveryCodeUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val changeRoleUseCase: ChangeRoleUseCase,
    private val logoutUseCase: LogoutUseCase,
    val localRepository: LocalDataSourceRepository,
    private val socketManager: SocketManager,
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    private val _registerState = mutableStateOf(RegisterState())
    private val _confirmMailState = mutableStateOf(ConfirmEmailState())
    private val _forgotPasswordState = mutableStateOf(ForgotPasswordState())
    private val _recoveryPasswordState = mutableStateOf(RecoveryPasswordState())
    private val _recoveryCodeState = mutableStateOf(RecoveryCodeState())
    private val _changePasswordState = mutableStateOf(ChangePasswordState())
    private val _changeRoleState = mutableStateOf(ChangeRoleState())
    var token by mutableStateOf<String?>(null)
    var temporaryId by mutableStateOf<Int?>(null)
        private set
    var code by mutableStateOf<Int?>(null)
    val state: State<LoginState> = _state
    val registerState: State<RegisterState> = _registerState
    val confirmMailState: State<ConfirmEmailState> = _confirmMailState
    val forgotPasswordState: State<ForgotPasswordState> = _forgotPasswordState
    val recoveryPasswordState: State<RecoveryPasswordState> = _recoveryPasswordState
    val recoveryCodeState: State<RecoveryCodeState> = _recoveryCodeState
    val changePasswordState: State<ChangePasswordState> = _changePasswordState
    val changeRoleState: State<ChangeRoleState> = _changeRoleState

    fun saveUserData(email: String, password: String) {
        localRepository.saveData(email, password)
    }

    fun getUserData(): UserData? = localRepository.getData()

    fun register(userName: String, email: String, password: String, role: String) {
        viewModelScope.launch {
            _registerState.value = _registerState.value.copy(isLoading = true)
            try {
                val response = registerUseCase(
                    userName = userName,
                    email = email,
                    password = password,
                    role = role
                )
                temporaryId = response.temporaryId
                Log.d("LoginViewModel", "Temporary Id(register): $temporaryId")
                _registerState.value = _registerState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    register = response
                )
            } catch (e: Exception) {
                _registerState.value = _registerState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ошибка"
                )
            }
        }
    }

    fun resetRegister() {
        _registerState.value = RegisterState()
    }

    fun confirmMail(temporaryId: Int, emailCode: Int) {
        viewModelScope.launch {
            _confirmMailState.value = _confirmMailState.value.copy(isLoading = true)
            try {

                Log.d("LoginViewModel", "Temporary Id(confirmMail): $temporaryId")
                val response = confirmMailUseCase(
                    temporaryId = temporaryId,
                    code = emailCode
                )
                saveTokenUseCase(response.accessToken)
                token = getTokenUseCase()
                code = emailCode
                socketManager.connect(token = response.accessToken)
                Log.d("LoginViewModel", "Token saved(confirmMail): ${response.accessToken}")
                _confirmMailState.value = _confirmMailState.value.copy(
                    confirmMail = response,
                    isLoading = false,
                    isSuccessful = true
                )
            } catch (e: Exception) {
                _confirmMailState.value = _confirmMailState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            _forgotPasswordState.value = _forgotPasswordState.value.copy(isLoading = true)
            try {

                Log.d("LoginViewModel", "Sending forgotPassword request for email: $email")
                val response = forgotPasswordUseCase(
                    email = email
                )
                temporaryId = response.temporaryId
                Log.d("LoginViewModel", "Temporary Id(forgotPasswordResponse): $response")
                _forgotPasswordState.value = _forgotPasswordState.value.copy(
                    forgotPassword = response,
                    isSuccessful = true,
                    isLoading = false
                )
            } catch (e: Exception) {
                _forgotPasswordState.value = _forgotPasswordState.value.copy(
                    error = e.message,
                    isLoading = false
                )
                Log.d("ForgotPassword", "$e")
            }
        }
    }

    fun recoveryCode(temporaryId: Int, emailCode: Int) {
        viewModelScope.launch {
            _recoveryCodeState.value = _recoveryCodeState.value.copy(isLoading = true)
            try {
                val request = RecoveryCodeRequest(
                    temporaryId = temporaryId,
                    code = emailCode
                )
                val requestDto = request.toCreateDto()
                val response = recoveryCodeUseCase(
                    temporaryId = temporaryId,
                    code = emailCode
                )
                Log.d("RecoveryCode", "request: $requestDto")
                 code = emailCode
                _recoveryCodeState.value = _recoveryCodeState.value.copy(
                    statusCode = response.statusCode,
                    isLoading = false,
                    message = response.message
                )
                Log.d("RecoveryCode", "$response")
            } catch (e: Exception) {
                _recoveryCodeState.value = _recoveryCodeState.value.copy(
                    error = e.message,
                    isLoading = false
                )
                Log.d("RecoveryCode", "${_recoveryCodeState.value.error}")
            }
        }
    }
    fun recoveryPassword(temporaryId: Int, code: Int, password: String) {
        viewModelScope.launch {
            _recoveryPasswordState.value = _recoveryPasswordState.value.copy(isLoading = true)
            try {
                val response = recoveryPasswordUseCase(
                    temporaryId = temporaryId,
                    code = code,
                    password = password
                )
                saveTokenUseCase(response.accessToken)
                _recoveryPasswordState.value = _recoveryPasswordState.value.copy(
                    recoveryPassword = response,
                    isLoading = false
                )
                socketManager.connect(token = response.accessToken)
            } catch (e: Exception) {
                _recoveryPasswordState.value = _recoveryPasswordState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val response = loginUseCase(
                    email = email,
                    password = password,
                )
                saveTokenUseCase(response.accessToken)
                Log.d("LoginViewModel", "Token saved(login): ${response.accessToken}")
                token = getTokenUseCase()
                _state.value = _state.value.copy(
                    login = response,
                    isSuccessful = true,
                    isLoading = false
                )
                socketManager.connect(token = response.accessToken)
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun changePassword(old_password: String, new_password: String) {
        viewModelScope.launch {
            _changePasswordState.value = _changePasswordState.value.copy(
                isLoading = true
            )
            try {
                val response = changePasswordUseCase(
                    old_password = old_password,
                    new_password = new_password
                )
                saveTokenUseCase(response.accessToken)
                Log.d("LoginViewModel", "Token saved(change_password): ${response.accessToken}")
                _changePasswordState.value = _changePasswordState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    changePassword = response
                )
                socketManager.connect(token = response.accessToken)
            } catch (e: Exception) {
                _changePasswordState.value = _changePasswordState.value.copy(
                    isLoading = false,
                    isSuccessful = false,
                    error = e.message
                )

            }
        }
    }

    fun changeRole() {
        viewModelScope.launch {
            _changeRoleState.value = _changeRoleState.value.copy(
                isLoading = true
            )
            try{
                _changeRoleState.value = _changeRoleState.value.copy(
                    isLoading = false,
                    isSuccessful = true,
                    changeRole = changeRoleUseCase()
                )
            } catch (e: Exception) {
                _changeRoleState.value = _changeRoleState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try{
                logoutUseCase()
            } catch (e: Exception){
                Log.d("LoginViewModel DEBUG", "${e.message}")
            }
        }
    }

    fun resetChangeRoleState() {
        _changePasswordState.value = _changePasswordState.value.copy(
            isSuccessful = false
        )
    }

    fun clearErrors() {
        _state.value = _state.value.copy(error = null)
        _registerState.value = _registerState.value.copy(error = null)
        _confirmMailState.value = _confirmMailState.value.copy(error = null)
        _forgotPasswordState.value = _forgotPasswordState.value.copy(error = null)
        _forgotPasswordState.value = _forgotPasswordState.value.copy(isSuccessful = false)
        _recoveryPasswordState.value = _recoveryPasswordState.value.copy(error = null)
        _recoveryCodeState.value = _recoveryCodeState.value.copy(error = null)
        _recoveryCodeState.value = _recoveryCodeState.value.copy(statusCode = 0)
        _changePasswordState.value = _changePasswordState.value.copy(error = null)
    }

}