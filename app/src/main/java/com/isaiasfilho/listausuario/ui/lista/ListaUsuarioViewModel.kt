package com.isaiasfilho.listausuario.ui.lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaiasfilho.listausuario.domain.user.model.User
import com.isaiasfilho.listausuario.data.repository.UserRepository
import kotlinx.coroutines.launch

class ListaUsuarioViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _usersList = MutableLiveData<List<User>>(emptyList())
    val userList: LiveData<List<User>> get() = _usersList

    init {
        populateList()
    }

    private fun populateList(){
        viewModelScope.launch {
            val list = _usersList.value
            if(list?.isEmpty() == true){
                setUsersList(repository.listUsersLocal())
            }
        }

    }

    private fun setUsersList(users: List<User>){
        _usersList.value = users
    }


}