package com.souvenir.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener { appendOnExpresstion("1", true) }
        btn2.setOnClickListener { appendOnExpresstion("2", true) }
        btn3.setOnClickListener { appendOnExpresstion("3", true) }
        btn4.setOnClickListener { appendOnExpresstion("4", true) }
        btn5.setOnClickListener { appendOnExpresstion("5", true) }
        btn6.setOnClickListener { appendOnExpresstion("6", true) }
        btn7.setOnClickListener { appendOnExpresstion("7", true) }
        btn8.setOnClickListener { appendOnExpresstion("8", true) }
        btn9.setOnClickListener { appendOnExpresstion("9", true) }
        btn0.setOnClickListener { appendOnExpresstion("0", true) }
        btnDel.setOnClickListener { appendOnExpresstion(".", true) }

        btnSum.setOnClickListener { appendOnExpresstion("+", false) }
        btnSub.setOnClickListener { appendOnExpresstion("-", false) }
        btnMul.setOnClickListener { appendOnExpresstion("*", false) }
        btnDiv.setOnClickListener { appendOnExpresstion("/", false) }
        btnOpen.setOnClickListener { appendOnExpresstion("(", false) }
        btnClose.setOnClickListener { appendOnExpresstion(")", false) }

        btnClear.setOnClickListener {
            tvResult.text = ""
            tvNumber.text = ""
        }

        btnBack.setOnClickListener{
            val string = tvNumber.text.toString()
            if(string.isNotEmpty()){
                tvNumber.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }

        btnResult.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvNumber.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble()){
                    tvResult.text = longResult.toString()
                }else{
                    tvResult.text = result.toString()
                }
            }catch (e : Exception){
                Log.d("Exception", "message: "+e.message)
            }
        }
    }

    fun appendOnExpresstion(string : String , canClear : Boolean){
        if (tvResult.text.isNotEmpty()){
            tvNumber.text = ""
        }
        if (canClear){
            tvResult.text = ""
            tvNumber.append(string)
        }else{
            tvNumber.append(tvResult.text)
            tvNumber.append(string)
            tvResult.text = ""
        }
    }
}
