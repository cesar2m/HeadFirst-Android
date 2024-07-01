package com.cesar2m.bitandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cesar2m.bitandpizzas.databinding.FragmentOrderBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?
        , savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        _binding = FragmentOrderBinding.inflate(inflater,container,false)
        val view = binding.root


        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)


        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{

            val pizzaType = binding.pizzaGroup.checkedRadioButtonId

            if(pizzaType == -1){

                val textMsj = ("\ud83d\ude01") + "¡Necesitas elegir una pizza!"
                Toast.makeText(activity, textMsj, Toast.LENGTH_SHORT).show()

            }else{


                var text = (when(pizzaType){ R.id.radio_mexicana -> "Mexicana" else -> "Hawaiana"})
                val parmesan = binding.chipParmesano
                text += if (parmesan.isChecked) " extra parmesano" else ""
                val chiliOil = binding.chipAguacate
                text += if (chiliOil.isChecked)  " extra agucate " else ""
                val textMsj = "¡¡¡ "  + text + "!!!"
                Snackbar.make(fab,textMsj, Snackbar.LENGTH_SHORT).setAction("Siempre NO"){

                    val pizzasGroup = binding.pizzaGroup
                    pizzasGroup.clearCheck()
                    val chipGroupExtras = binding.chipGroupExtras
                    chipGroupExtras.clearCheck()

                    Toast.makeText(activity, "Deshacemos tu selección " +   ("\ud83d\ude01"), Toast.LENGTH_SHORT).show()

                }.show()
            }

        }



        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}