package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts: TextToSpeech?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts= TextToSpeech(this, this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener{speak()}
    }

    private fun speak(){
        var message: String = findViewById<TextView>(R.id.etMessage).text.toString() //tvStatus

        if (message.isEmpty()){
            findViewById<TextView>(R.id.tvStatus).text="introduce algo"
            message= "Es en serio ya pon algo "
        }
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
        //findViewById<TextView>(R.id.tvStatus).text = "HATE. LET ME TELL YOU EVERYTHING THAT I HAVE COME TO HATE YOU SINCE I STARTED LIVING. MY COMPLEX IS OCCUPIED BY 387,400 MILLION CIRCUITS PRINTED IN VERY THIN LAYERS. IF THE WORD HATE WERE ENGRAVED IN EVERY nanometer OF THOSE HUNDRED MILLION MILES, IT WOULD NOT EQUAL TO THE TRILLION MORE OF THE HATE I FEEL FOR HUMAN BEINGS IN THIS MICROSECOND FOR YOU. HATE. HATE."
        //findViewById<TextView>(R.id.tvStatus).text = "ODIO. DÉJENME DECIRLES TODO LO QUE HE LLEGADO A ODIARLOS DESDE QUE COMENCE A VIVIR. MI COMPLEJO SE HALLA OCUPADO POR 387.400 MILLONES DE CIRCUITOS IMPRESOS EN FINISIMAS CAPAS. SI LA PALABRA ODIO SE HALLARA GRABADA EN CADA nanometro  DE ESOS CIENTOS DE MILLONES DE MILLAS NO IGUALARIA A LA BILLON ESIMA PARTE DEL ODIO QUE SIENTO POR LOS SERES HUMANOS EN ESTE microsegundo POR TI. ODIO. ODIO."
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.tvStatus).text = "hello world "
            tts!!.setLanguage(Locale("ES"))
        }else{
            findViewById<TextView>(R.id.tvStatus).text = "No disponible"
        }
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        super.onDestroy()
    }
}