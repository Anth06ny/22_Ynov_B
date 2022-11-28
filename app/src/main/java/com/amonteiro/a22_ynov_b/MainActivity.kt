package com.amonteiro.a22_ynov_b

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import com.amonteiro.a22_ynov_b.databinding.ActivityMainBinding
import java.util.Calendar

const val MENU_TIME_PICKER = 18
const val MENU_WEATHER = 19

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    //instanciation du xml à  retardement lors de la 1ere utilisation
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    val calendar = Calendar.getInstance()

    //Création de l'écran
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Affichage
        setContentView(binding.root)

        //Clic sur valider
        binding.btValidate.setOnClickListener {
            if(binding.rbLike.isChecked) {
                binding.editText.setText(binding.rbLike.text)
            }
            else  if(binding.rbDislike.isChecked) {
                binding.editText.setText(binding.rbDislike.text)
            }
            binding.imageView.setImageResource(R.drawable.ic_baseline_delete_forever_24)
        }

        binding.btCancel.setOnClickListener {
            binding.rg.clearCheck()
            binding.editText.setText("")
            binding.imageView.setImageResource(R.drawable.ic_baseline_flag_24)
        }
    }

    //Callback création du menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0,MENU_TIME_PICKER,0,"TimePicker")
        menu.add(0,MENU_WEATHER,0,"Météo")

        return super.onCreateOptionsMenu(menu)
    }

    //CallBack clic sur un menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == MENU_TIME_PICKER){
              TimePickerDialog(this, this, calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE), true).show()
        }
        else if(item.itemId == MENU_WEATHER){
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

    //Callback du TimePicker
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Toast.makeText(this, "$hourOfDay:$minute", Toast.LENGTH_LONG).show()

        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
    }


}