package com.diegotorres.formulario

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var salida:String
    lateinit var hobbies:String
    lateinit var textofin:String
    private var sexo:String = "Femenino"
    private var auxhobbie1:String = ""
    private var auxhobbie2:String = ""
    private var auxhobbie3:String = ""
    private var auxhobbie4:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contenedor = findViewById<RadioGroup>(R.id.generoRG)

        botonf.setOnClickListener {
            sexo = getString(R.string.femenino_RB)
        }

        botonm.setOnClickListener {
            sexo = getString(R.string.masculino_RB)
        }

        gymCB.setOnClickListener{
            if(gymCB.isChecked){
                auxhobbie1 = getString(R.string.gimnasio_CB)
            }
            else{
                auxhobbie1 = ""
            }
        }

        cineCB.setOnClickListener{
            if(cineCB.isChecked){
                auxhobbie2 = getString(R.string.cine_CB)
            }
            else{
                auxhobbie2 = ""
            }
        }

        serieCB.setOnClickListener{
            if(serieCB.isChecked){
                auxhobbie3 = getString(R.string.series_CB)
            }
            else{
                auxhobbie3 = ""
            }
        }

        baileCB.setOnClickListener {
            if(baileCB.isChecked){
                auxhobbie4 = getString(R.string.bailar_CB)
            }
            else{
                auxhobbie4 = ""
            }
        }

        contenedor.setOnCheckedChangeListener { group, checkedId ->  }

        fechanacimiento.setOnClickListener {
            val fecha = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                val fecha2 = Calendar.getInstance()
                fecha2.set(Calendar.YEAR,year)
                fecha2.set(Calendar.MONTH,month)
                fecha2.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val dia = fecha2.get(Calendar.DAY_OF_MONTH).toString()
                val mes = (fecha2.get(Calendar.MONTH)+1).toString()
                val yearr = fecha2.get(Calendar.YEAR).toString()
                salida = dia + "/" + mes + "/" + yearr
                formatotv.text = salida
            },
                fecha.get(Calendar.YEAR),fecha.get(Calendar.MONTH),fecha.get(Calendar.DAY_OF_MONTH))
            datePicker.show()

        }

        guardadatos.setOnClickListener {
            mostrarDatos()
        }

    }

    fun mostrarDatos(){
        val nombre = nombreET.text.toString()
        val correo = correoET.text.toString()
        val telefono = telET.text.toString()
        val pass = contraET.text.toString()
        val reppass = repcontraET.text.toString()

        if(nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || pass.isEmpty() || reppass.isEmpty()){
            Toast.makeText(this,"Llena todos los campos",Toast.LENGTH_LONG).show()
        }
        else if(pass != reppass){
            Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_LONG).show()
        }
        else{
            val spinciudad = findViewById<Spinner>(R.id.spinciudad)
            val auxciudad = arrayOf("Medellín","Barranquilla","Cali","Bogotá","Manizales")
            hobbies = auxhobbie1 + " " + auxhobbie2 + " " + auxhobbie4 + " " + auxhobbie3
            textofin = "Datos\n" + "Nombre: " + nombre + "\n" + "Correo: " +correo + "\n" +
                    "Teléfono: " + telefono + "\n" + "Password: " + pass + "\n" +
                    "Genero: " + sexo + "\n" + "Pasatiempos: " + hobbies + "\n" +
                    "Fecha de nacimiento: " + salida + "\n" +
                    "Lugar de nacimiento: " + auxciudad.get(spinciudad.selectedItemPosition)
            datosalida.text = textofin
        }
    }
}
