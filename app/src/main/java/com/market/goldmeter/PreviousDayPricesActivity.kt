package com.market.goldmeter

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_previous_day_prices.*
import org.jsoup.Jsoup
import java.lang.Exception

class PreviousDayPricesActivity : AppCompatActivity() {
    @SuppressLint("Range")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous_day_prices)

        supportActionBar?.hide()
        this.window.statusBarColor = Color.parseColor("#f44336")

        val commodity = intent.getStringExtra("commodity")

        Toast.makeText(this, "You can now add Live Price Widgets to home screen", Toast.LENGTH_LONG).show()

        Thread{
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                for (range in 1..10) {
                    val element = doc.select(".silver-rates.table.table-bordered.table-striped > tbody > tr:nth-of-type("+range+") > td:nth-of-type(1)")
                    val text = element.text().toString()
                    when (range) {
                        1 -> d1.text = text + " :"
                        2 -> d2.text = text + " :"
                        3 -> d3.text = text + " :"
                        4 -> d4.text = text + " :"
                        5 -> d5.text = text + " :"
                        6 -> d6.text = text + " :"
                        7 -> d7.text = text + " :"
                        8 -> d8.text = text + " :"
                        9 -> d9.text = text + " :"
                        10 -> d10.text = text + " :"
                    }
                }
            } catch (e: Exception) {

            }
        }.start()

        val gold22Thread = Thread {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                for (range in 1..10) {
                    val element = doc.select("tr:nth-of-type("+range+") > td:nth-of-type(4)")

                    val manipulatedElement = element.text().toString().filter { it.isDigit() }

                    val text = manipulatedElement.toFloat().toString()
                    val text8 = text.toFloat()*8
                    when (range + 2) {
                        3 -> { p1.text = "1 Gram : ₹ " + text + "0"
                            P1.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        4 -> { p2.text = "1 Gram : ₹ " + text + "0"
                            P2.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        5 -> { p3.text = "1 Gram : ₹ " + text + "0"
                            P3.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        6 -> { p4.text = "1 Gram : ₹ " + text + "0"
                            P4.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        7 -> { p5.text = "1 Gram : ₹ " + text + "0"
                            P5.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        8 -> { p6.text = "1 Gram : ₹ " + text + "0"
                            P6.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        9 -> { p7.text = "1 Gram : ₹ " + text + "0"
                            P7.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        10 -> { p8.text = "1 Gram : ₹ " + text + "0"
                            P8.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        11 -> { p9.text = "1 Gram : ₹ " + text + "0"
                            P9.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        12 -> { p10.text = "1 Gram : ₹ " + text + "0"
                            P10.text = "8 Gram : ₹ " + text8.toString() + "0"}
                    }
                }
            } catch (e: Exception) {

            }
        }

        val silverThread = Thread {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                for (range in 1..10) {
                    val element = doc.select(".silver-rates.table.table-bordered.table-striped > tbody > tr:nth-of-type(" + range + ") > td:nth-of-type(2)")
                    val text = element.text().toString()
                    val text8 = text.toFloat()*1000
                    when (range + 1) {
                        2 -> { p1.text = "1 Gram : ₹ " + text
                            P1.text = "1 KG : ₹ " + text8.toString() + "0"}
                        3 -> { p2.text = "1 Gram : ₹ " + text
                            P2.text = "1 KG : ₹ " + text8.toString() + "0"}
                        4 -> { p3.text = "1 Gram : ₹ " + text
                            P3.text = "1 KG : ₹ " + text8.toString() + "0"}
                        5 -> { p4.text = "1 Gram : ₹ " + text
                            P4.text = "1 KG : ₹ " + text8.toString() + "0"}
                        6 -> { p5.text = "1 Gram : ₹ " + text
                            P5.text = "1 KG : ₹ " + text8.toString() + "0"}
                        7 -> { p6.text = "1 Gram : ₹ " + text
                            P6.text = "1 KG : ₹ " + text8.toString() + "0"}
                        8 -> { p7.text = "1 Gram : ₹ " + text
                            P7.text = "1 KG : ₹ " + text8.toString() + "0"}
                        9 -> { p8.text = "1 Gram : ₹ " + text
                            P8.text = "1 KG : ₹ " + text8.toString() + "0"}
                        10 -> { p9.text = "1 Gram : ₹ " + text
                            P9.text = "1 KG : ₹ " + text8.toString() + "0"}
                        11 -> { p10.text = "1 Gram : ₹ " + text
                            P10.text = "1 KG : ₹ " + text8.toString() + "0"}
                    }
                }
            } catch (e: Exception) {

            }
        }

        val gold24Thread = Thread{
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                for (range in 1..10) {
                    val element = doc.select(".gold-rates.table.table-bordered.table-striped > tbody > tr:nth-of-type("+range+") > td:nth-of-type(2)")

                    val manipulatedElement = element.text().toString().filter { it.isDigit() }

                    val text = manipulatedElement.toFloat().toString()
                    val text8 = text.toFloat()*8
                    when (range + 2) {
                        3 -> { p1.text = "1 Gram : ₹ " + text + "0"
                            P1.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        4 -> { p2.text = "1 Gram : ₹ " + text + "0"
                            P2.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        5 -> { p3.text = "1 Gram : ₹ " + text + "0"
                            P3.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        6 -> { p4.text = "1 Gram : ₹ " + text + "0"
                            P4.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        7 -> { p5.text = "1 Gram : ₹ " + text + "0"
                            P5.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        8 -> { p6.text = "1 Gram : ₹ " + text + "0"
                            P6.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        9 -> { p7.text = "1 Gram : ₹ " + text + "0"
                            P7.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        10 -> { p8.text = "1 Gram : ₹ " + text + "0"
                            P8.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        11 -> { p9.text = "1 Gram : ₹ " + text + "0"
                            P9.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        12 -> { p10.text = "1 Gram : ₹ " + text + "0"
                            P10.text = "8 Gram : ₹ " + text8.toString() + "0"}
                    }
                }
            } catch (e: Exception) {

            }
        }

        val platinumThread = Thread{
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/platinum_rate_chennai.asp").get()
                for (range in 2..11) {
                    val element = doc.select("body > div.wrapper > div.veg-cointainer > div:nth-child(7) > div.col-sm-8 > div:nth-child(3) > div > table > tbody > tr:nth-child("+range+") > td:nth-child(2)")
                    val text = element.text().toString()
                    val text8 = text.toFloat()*8
                    when (range) {
                        2 -> { p1.text = "1 Gram : ₹ " + text
                            P1.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        3 -> { p2.text = "1 Gram : ₹ " + text
                            P2.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        4 -> { p3.text = "1 Gram : ₹ " + text
                            P3.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        5 -> { p4.text = "1 Gram : ₹ " + text
                            P4.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        6 -> { p5.text = "1 Gram : ₹ " + text
                            P5.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        7 -> { p6.text = "1 Gram : ₹ " + text
                            P6.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        8 -> { p7.text = "1 Gram : ₹ " + text
                            P7.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        9 -> { p8.text = "1 Gram : ₹ " + text
                            P8.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        10 -> { p9.text = "1 Gram : ₹ " + text
                            P9.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        11 -> { p10.text = "1 Gram : ₹ " + text
                            P10.text = "8 Gram : ₹ " + text8.toString() + "0"}
                    }
                }
            } catch (e: Exception) {

            }
        }

        val gold18Thread = Thread{
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/18k_goldrate_chennai.asp").get()
                for (range in 3..12) {
                    val element = doc.select("tr:nth-of-type("+range+") > td:nth-of-type(2) > font")
                    val text = element.text().toString()
                    val text8 = text.toFloat()*8
                    when (range) {
                        3 -> { p1.text = "1 Gram : ₹ " + text
                            P1.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        4 -> { p2.text = "1 Gram : ₹ " + text
                            P2.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        5 -> { p3.text = "1 Gram : ₹ " + text
                            P3.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        6 -> { p4.text = "1 Gram : ₹ " + text
                            P4.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        7 -> { p5.text = "1 Gram : ₹ " + text
                            P5.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        8 -> { p6.text = "1 Gram : ₹ " + text
                            P6.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        9 -> { p7.text = "1 Gram : ₹ " + text
                            P7.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        10 -> { p8.text = "1 Gram : ₹ " + text
                            P8.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        11 -> { p9.text = "1 Gram : ₹ " + text
                            P9.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        12 -> { p10.text = "1 Gram : ₹ " + text
                            P10.text = "8 Gram : ₹ " + text8.toString() + "0"}
                    }
                }
            } catch (e: Exception) {

            }
        }

        val usdThread = Thread{
            try {
                val doc = Jsoup.connect("https://www.bankbazaar.com/currency-exchange/usd-to-inr.html").get()
                for (range in 2..11) {
                    val element = doc.select("#react-hfm-placeholder > div > div.ui.container.mt > div > div > div.thirteen.wide.computer.sixteen.wide.mobile.thirteen.wide.tablet.column > div:nth-child(5) > div > div:nth-child(2) > div.hfm-table > table > tbody > tr:nth-child(2) > td:nth-child(2) > p")
                    val text = element.text().toString()
                    when (range) {
                        2 -> { p1.text = "1 USD : ₹ " + text
                            P1.text = "_______"}
                        3 -> { p2.text = "1 USD : ₹ " + text
                            P2.text = "_______"}
                        4 -> { p3.text = "1 USD : ₹ " + text
                            P3.text = "_______"}
                        5 -> { p4.text = "1 USD : ₹ " + text
                            P4.text = "_______"}
                        6 -> { p5.text = "1 USD : ₹ " + text
                            P5.text = "_______"}
                        7 -> { p6.text = "1 USD : ₹ " + text
                            P6.text = "_______"}
                        8 -> { p7.text = "1 USD : ₹ " + text
                            P7.text = "_______"}
                        9 -> { p8.text = "1 USD : ₹ " + text
                            P8.text = "_______"}
                        10 -> { p9.text = "1 USD : ₹ " + text
                            P9.text = "_______"}
                        11 -> { p10.text = "1 USD : ₹ " + text
                            P10.text = "_______"}
                    }
                }
            } catch (e: Exception) {

            }
        }

        val petrolThread = Thread{}

        val dieselThread = Thread{}

        if (commodity == "gold22") {
            commodityTitle.text = "22 Carat Gold Rate for Last 10 Days"
            gold22Thread.start()
        } else if (commodity == "gold24") {
            commodityTitle.text = "24 Carat Gold Rate for Last 10 Days"
            gold24Thread.start()
        } else if (commodity == "silver") {
            commodityTitle.text = "Silver Rate for Last 10 Days"
            silverThread.start()
        } else if (commodity == "platinum") {
            commodityTitle.text = "Platinum Rate for Last 10 Days"
            platinumThread.start()
        } else if (commodity == "gold18") {
            commodityTitle.text = "18 Carat Gold Rate for Last 10 Days"
            gold18Thread.start()
        } else if (commodity == "petrol") {
            commodityTitle.text = "Petrol Rate for Last 10 Days"
            petrolThread.start()
        } else if (commodity == "diesel") {
            commodityTitle.text = "Diesel Rate for Last 10 Days"
            dieselThread.start()
        } else if (commodity == "usd") {
            commodityTitle.text = "US Dollar Rate for Last 10 Days"
            usdThread.start()
        }


    }
}