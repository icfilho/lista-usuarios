package com.isaiasfilho.listausuario.ui.splash


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaiasfilho.listausuario.data.ActionState
import com.isaiasfilho.listausuario.domain.user.IUserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(
    private val repository: IUserRepository
) : ViewModel() {

    private val _stateProcess = MutableLiveData<ActionState>(ActionState.Checking)
    val stateProcess: LiveData<ActionState> get() = _stateProcess

    private val _message = MutableLiveData<String>("Consultando registros...")
    val messageEvent : LiveData<String> get() = _message


    init {
       launchView()
    }

    private fun launchView(){
        viewModelScope.launch() {
            try {
                startApp()
            }catch (e: Exception){
                setMessage("Erro: ${e.message}")
            }
        }
    }

    private suspend fun startApp() {
        delay(1500)
        if(!repository.isUserImported()){
            setMessage("Importando registros...")
            repository.importUsers()
        }
        setStateSplash(ActionState.Imported)
    }

    private fun setMessage(message: String){
        _message.value = message
    }

    private fun setStateSplash(state: ActionState){
        _stateProcess.value = state
    }

}