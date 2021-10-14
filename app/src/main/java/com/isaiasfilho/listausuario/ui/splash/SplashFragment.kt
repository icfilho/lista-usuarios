package com.isaiasfilho.listausuario.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.findNavController
import com.isaiasfilho.listausuario.R
import com.isaiasfilho.listausuario.data.ActionState
import org.koin.android.ext.android.inject


class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private val viewModel: SplashViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = ComposeView(requireContext())
        view.apply {
            setContent {
                Scaffold(
                    content = { Content(viewModel) }
                )
            }
        }

        listenStatus()

        return view
    }

    private fun listenStatus(){
        viewModel.stateProcess.observe(viewLifecycleOwner,){
            if(it is ActionState.Imported){
                val navController = findNavController()
                navController.navigate(R.id.listaUsuarioFragment)
            }
        }
    }

    // Construção tela Splash
    @Composable
    fun Content(model: SplashViewModel){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                // Nome do app
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(stringResource(id = R.string.app_name), fontSize = 30.sp, fontWeight = FontWeight.Bold);
                    }
                )
                // Loading indicador
                CircularProgressIndicator()
                Spacer(modifier = Modifier.padding(30.dp))
                // Mensagem de status da importação dos dados (API)
                val message: String by model.messageEvent.observeAsState(stringResource(R.string.txt_check))
                Text(message, fontSize = 20.sp)
            }
        )
    }
}