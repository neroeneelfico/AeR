package com.example.aer_anestesiaerianimazione

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast



class FragmentCompensi : Fragment() {

    lateinit var acrespactext : TextView
    lateinit var acrespcrtext : TextView
    lateinit var alcrespactext : TextView
    lateinit var alcrespcrtext : TextView
    lateinit var acmettext : TextView
    lateinit var alcmettext : TextView

    var compensi = Compensi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_compensi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        acrespactext = view.findViewById(R.id.acrespactext)
        acrespcrtext = view.findViewById(R.id.acrespcrtext)
        alcrespactext = view.findViewById(R.id.alcrespactext)
        alcrespcrtext = view.findViewById(R.id.alcrespcrtext)
        acmettext = view.findViewById(R.id.acmettext)
        alcmettext = view.findViewById(R.id.alcmettext)
                super.onViewCreated(view, savedInstanceState)

    }

    fun aggiornaCompensi(tipo: String, valore: Float){
        compensi.calcoloCompensi(tipo,valore)
        acrespactext.text = getString(R.string.co2_res_testo, compensi.getAcRespAc())
        acrespcrtext.text = getString(R.string.co2_res_testo, compensi.getAcRespCr())
        alcrespactext.text = getString(R.string.co2_res_testo, compensi.getAlcRespAc())
        alcrespcrtext.text = getString(R.string.co2_res_testo, compensi.getAlcRespCr())
        acmettext.text = getString(R.string.hco3_res_testo, compensi.getAcMet())
        alcmettext.text = getString(R.string.hco3_res_testo, compensi.getAlcMet())

    }

    class Compensi(){
        var co2 : Float = 0.0f
        var hco3: Float = 0.0f
        var acrespac : Float = 0.0f
        var acrespcr : Float = 0.0f
        var alcrespac : Float = 0.0f
        var alcrespcr : Float = 0.0f
        var acmet : Float = 0.0f
        var alcmet : Float = 0.0f

        fun getAcRespAc() : Float{
            return (Math.round(acrespac*100)/100).toFloat()
        }
        fun getAcRespCr() : Float {
            return (Math.round(acrespcr * 100.0)/100.0).toFloat()
        }
        fun getAlcRespAc() : Float {
            return  (Math.round(alcrespac * 100.0)/100.0).toFloat()
        }
        fun getAlcRespCr() : Float {
            return  (Math.round(alcrespcr * 100.0)/100.0).toFloat()
        }
        fun getAcMet() : Float {
            return (Math.round(acmet * 100.0)/100.0).toFloat()
        }
        fun getAlcMet() : Float {
            return (Math.round(alcmet * 100.0)/100.0).toFloat()
        }


        fun calcoloCompensi(tipo: String, valore:Float){
            if(tipo == "co2") this.co2 = valore
            else this.hco3 = valore

            if (co2 > 43.0f) {
                acrespac = 24 + (0.1f * (co2 - 40));
                acrespcr = 24 + (0.4f * (co2 - 40));
                alcrespac = 0.0f;
                alcrespcr = 0.0f;
            } else if (co2 < 37) {
                alcrespac = 24 - (0.2f * (40 - co2));
                alcrespcr = 24 - (0.5f * (40 - co2));
                acrespac = 0.0f;
                acrespcr =0.0f;
            } else {
                acrespac = 0.0f;
                acrespcr =0.0f;
                alcrespac = 0.0f;
                alcrespcr = 0.0f;
            }

            if (hco3 < 24) {
                acmet = 40 - (1.3f * (24 - hco3));
                alcmet =  0.0f;
            } else if (hco3 >= 24) {
                alcmet = 40 + (0.7f * (hco3 - 24));
                acmet =  0.0f;
            } else {

                acmet = 0.0f;
                alcmet =0.0f;
            }
        }

    }



}