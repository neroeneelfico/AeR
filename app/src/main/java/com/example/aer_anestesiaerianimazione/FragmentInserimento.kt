package com.example.aer_anestesiaerianimazione

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.warkiz.widget.IndicatorSeekBar
import com.warkiz.widget.OnSeekChangeListener
import com.warkiz.widget.SeekParams

class FragmentInserimento : Fragment() {
    companion object {
        fun newInstance(foo: Int): FragmentInserimento {
            val args = Bundle()
            args.putInt("foo", foo)
            val fragment = FragmentInserimento()
            fragment.arguments = args
            return fragment
        }
    }

    internal lateinit var co2SeekBar: IndicatorSeekBar
    internal lateinit var hco3SeekBar: IndicatorSeekBar
    internal lateinit var co2testo: TextView
    internal lateinit var hco3testo: TextView
    internal lateinit var pH: TextView
    internal lateinit var pHCV : CardView
    internal lateinit var pHDescrizione: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserimento, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val Ega = Emogas(36.0f,21.0f)
        var seekcoattivo : Boolean = false
        var seekhco3attivo : Boolean = false
        co2SeekBar = view.findViewById(R.id.co2_seekbar)
        hco3SeekBar = view.findViewById(R.id.hco3_seekbar)
        co2testo = view.findViewById(R.id.co2testo)
        hco3testo = view.findViewById(R.id.hco3_testo)
        pH = view.findViewById(R.id.pH)
        pHCV = view.findViewById(R.id.pHCV)
        pHDescrizione = view.findViewById(R.id.pHDescrizione)

        co2SeekBar.onSeekChangeListener = object : OnSeekChangeListener {
            override fun onSeeking(seekParams: SeekParams?) {
                if(seekcoattivo) {
                    Ega.setCo2(seekParams?.progressFloat!!)
                    pH.text = Ega.getPh().toString()
                    co2testo.text = getString(R.string.co2_res_testo, seekParams?.progressFloat)
                    pHCV.setCardBackgroundColor(Color.parseColor(Ega.getColoreBox()))
                    pHDescrizione.text = Ega.getDescrizione()
                }
            }

            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {
                seekcoattivo = true
            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {
                seekcoattivo = false
                Ega.setCo2(seekBar?.progressFloat!!)
                pH.text = Ega.getPh().toString()
                co2testo.text = getString(R.string.co2_res_testo, seekBar?.progressFloat)
                pHCV.setCardBackgroundColor(Color.parseColor(Ega.getColoreBox()))
                pHDescrizione.text = Ega.getDescrizione()
                (activity as MainActivity?)?.phadapter?.aggiornaCompensi("co2",seekBar?.progressFloat)
            }

        }

        hco3SeekBar.onSeekChangeListener = object  : OnSeekChangeListener {
            override fun onSeeking(seekParams: SeekParams?) {
                if(seekhco3attivo) {
                    Ega.setHco3(seekParams?.progressFloat!!)
                    pH.text = Ega.getPh().toString()
                    hco3testo.text = getString(R.string.hco3_res_testo, seekParams?.progressFloat)
                }            }

            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?) {
                seekhco3attivo = true
            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {
                seekhco3attivo = false
                Ega.setHco3(seekBar?.progressFloat!!)
                pH.text = Ega.getPh().toString()
                hco3testo.text = getString(R.string.hco3_res_testo, seekBar?.progressFloat)
                pHCV.setCardBackgroundColor(Color.parseColor(Ega.getColoreBox()))
                pHDescrizione.text = Ega.getDescrizione()
                (activity as MainActivity?)?.phadapter?.aggiornaCompensi("hco3",seekBar?.progressFloat)

            }

        }

    }



}