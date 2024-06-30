package com.cesar2m.bitandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)


        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{

            val pizzasGroup = view.findViewById<RadioGroup>(R.id.pizza_group)
            val pizzaType = pizzasGroup.checkedRadioButtonId

            if(pizzaType == -1){

                val textMsj = ("\ud83d\ude01") + "¡Necesitas elegir una pizza!"
                Toast.makeText(activity, textMsj, Toast.LENGTH_SHORT).show()

            }else{

                var text = (when(pizzaType){ R.id.radio_mexicana -> "Mexicana" else -> "Hawaiana"})
                val parmesan = view.findViewById<Chip>(R.id.chip_parmesano)
                text += if (parmesan.isChecked) " extra parmesano" else ""
                val chiliOil = view.findViewById<Chip>(R.id.chip_aguacate)
                text += if (chiliOil.isChecked)  " extra agucate " else ""
                val textMsj = "¡¡¡ "  + text + "!!!"
                Snackbar.make(fab,textMsj, Snackbar.LENGTH_SHORT).setAction("Siempre NO"){

                    val pizzasGroup = view.findViewById<RadioGroup>(R.id.pizza_group)
                    pizzasGroup.clearCheck()
                    val chipGroupExtras = view.findViewById<ChipGroup>(R.id.chip_group_extras)
                    chipGroupExtras.clearCheck()


                    Toast.makeText(activity, "Deshacemos tu selección " +   ("\ud83d\ude01"), Toast.LENGTH_SHORT).show()

                }.show()
            }

        }



        return view
    }

}