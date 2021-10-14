package com.isaiasfilho.listausuario.ui.lista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.isaiasfilho.listausuario.R
import org.koin.android.ext.android.inject
import com.isaiasfilho.listausuario.domain.user.model.User

class ListaUsuarioFragment : Fragment() {

    companion object {
        fun newInstance() = ListaUsuarioFragment()
    }

    private val viewModel: ListaUsuarioViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = ComposeView(requireContext())
        view.apply {
            setContent {
                Scaffold(
                    topBar= { topBar() },
                    content = { usersList() },
                )
            }
        }

        return view
    }

    @Composable
    private fun topBar(){
        TopAppBar(
            title = {
                Text(stringResource(R.string.app_name))
            }
        )
    }

    @Composable
    private fun usersList(){
        val users: List<User> by viewModel.userList.observeAsState(listOf())
        LazyColumn {
            items(users){
                user -> itemList(user)
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun itemList(user: User){
        ListItem(
            icon = {Icon(
                Icons.Filled.Person,
                contentDescription = null,
                Modifier.size(30.dp),
                Color(R.color.grey_light)
            )},
            text = { Text(user.fullName()) },
            secondaryText = { Text(user.email) }
        )
    }
}