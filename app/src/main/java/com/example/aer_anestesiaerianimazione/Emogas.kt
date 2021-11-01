package com.example.aer_anestesiaerianimazione

import android.widget.Toast
import java.lang.Math.log10
import java.math.RoundingMode
import kotlin.math.roundToLong

class Emogas(co2 : Float, hco3 : Float) {

    private var co2: Float = 0.0f
    private var hco3: Float = 0.0f
    private var ph: Float = 0.0f
    private var ch: Float = 0.0f
    private var colorebox : String = "#ffffff"
    private var descrizione : String = "Valori normali"

    init {
        this.co2 = co2
        this.hco3 = hco3
        calcolaValori()
    }

    fun getPh(): Float {
        controlloBoxCv()
        return (Math.round(ph * 100.0)/100.0).toFloat()
    }
    fun getCh(): Float {
        return ch
    }

    fun setHco3(hco3 : Float) {
        this.hco3 = hco3
        calcolaValori()


    }
    fun setCo2(co2 : Float) {
        this.co2 = co2
        calcolaValori()
    }

    fun getColoreBox() : String  {
        return colorebox
    }
    fun getDescrizione() : String {
        return descrizione
    }

    fun calcolaValori() {
        ch = 23.9f * co2 / hco3
        ph = Math.abs(log10((ch.toDouble()/ 1000000000)).toFloat())
    }
    fun controlloBoxCv(){
        if (this.ph <= 7.45 && this.ph >= 7.35) {
            this.colorebox = "#5aa556"
            this.descrizione = "Valori Normali"
        } else if (this.ph < 7.35 && this.ph >= 7.30) {
            //colore arancione
            this.colorebox = "#ff7f50"
            this.descrizione = "Rilevata Acidosi"
        } else if (this.ph > 7.45) {
            //Colore blu
            this.colorebox = "#635fff"
            this.descrizione = "Rilevata Alcalosi"
        } else if (this.ph < 7.30) {
            //colore rosso
            this.colorebox = "#dc143c"
            this.descrizione = "Rilevata Acidosi"
        }
    }
}