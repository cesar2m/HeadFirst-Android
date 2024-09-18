package com.cesar2m.guessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.cesar2m.guessinggame.databinding.FragmentGameBinding
import com.cesar2m.guessinggame.databinding.FragmentResultBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: ResultViewModel
    lateinit var viewModelFactory: ResultViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false).apply {
            composeView.setContent {
                //Text("Este texto es creado con Composable.")
                MaterialTheme{
                    Surface{
                        view?.let { ResultFragmentContent(it,viewModel)}
                    }
                }

            }
        }

        view
        val view = binding.root

        val result  = ResultFragmentArgs.fromBundle(requireArguments()).result
        viewModelFactory = ResultViewModelFactory(result)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)
        binding.resultViewModel = viewModel //para acceder a los datos desde el diseño-
        //binding.wonLost.text = viewModel.result //ya se puede acceder este resultado desde la vista en la línea de arriba


        binding.newGameButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }
        return view
    }

    @Composable
    fun ResultText(result: String){
        Text(text = result,
            fontSize = 28.sp,
            textAlign = TextAlign.Center)
    }

    @Composable
    fun NewGameButton(clicked: () -> Unit){
        Button(onClick = clicked ){
            Text("Jugar nuevamente")
        }
    }

    @Composable
    fun ResultFragmentContent(view: View, viewModel: ResultViewModel){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally){
                ResultText(viewModel.result)
                NewGameButton {
                    view.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
                }
        }
    }

    fun onDestroyeView(){
        super.onDestroyView()
        _binding = null
    }



}