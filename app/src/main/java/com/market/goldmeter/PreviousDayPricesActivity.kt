package com.market.goldmeter

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.market.goldmeter.databinding.ActivityPreviousDayPricesBinding
import org.jsoup.Jsoup
import java.lang.Exception

class PreviousDayPricesActivity : AppCompatActivity() {
    @SuppressLint("Range")

    private lateinit var binding: ActivityPreviousDayPricesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviousDayPricesBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    runOnUiThread {
                        when (range) {
                            1 -> binding.d1.text = text + " :"
                            2 -> binding.d2.text = text + " :"
                            3 -> binding.d3.text = text + " :"
                            4 -> binding.d4.text = text + " :"
                            5 -> binding.d5.text = text + " :"
                            6 -> binding.d6.text = text + " :"
                            7 -> binding.d7.text = text + " :"
                            8 -> binding.d8.text = text + " :"
                            9 -> binding.d9.text = text + " :"
                            10 -> binding.d10.text = text + " :"
                        }
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
                    runOnUiThread {
                        when (range + 2) {
                            3 -> { binding.p1.text = "1 Gram : ₹ " + text + "0"
                                binding.P1.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            4 -> { binding.p2.text = "1 Gram : ₹ " + text + "0"
                                binding.P2.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            5 -> { binding.p3.text = "1 Gram : ₹ " + text + "0"
                                binding.P3.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            6 -> { binding.p4.text = "1 Gram : ₹ " + text + "0"
                                binding.P4.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            7 -> { binding.p5.text = "1 Gram : ₹ " + text + "0"
                                binding.P5.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            8 -> { binding.p6.text = "1 Gram : ₹ " + text + "0"
                                binding.P6.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            9 -> { binding.p7.text = "1 Gram : ₹ " + text + "0"
                                binding.P7.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            10 -> { binding.p8.text = "1 Gram : ₹ " + text + "0"
                                binding.P8.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            11 -> { binding.p9.text = "1 Gram : ₹ " + text + "0"
                                binding.P9.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            12 -> { binding.p10.text = "1 Gram : ₹ " + text + "0"
                                binding.P10.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        }
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
                    runOnUiThread {
                        when (range + 1) {
                            2 -> { binding.p1.text = "1 Gram : ₹ " + text
                                binding.P1.text = "1 KG : ₹ " + text8.toString() + "0"}
                            3 -> { binding.p2.text = "1 Gram : ₹ " + text
                                binding.P2.text = "1 KG : ₹ " + text8.toString() + "0"}
                            4 -> { binding.p3.text = "1 Gram : ₹ " + text
                                binding.P3.text = "1 KG : ₹ " + text8.toString() + "0"}
                            5 -> { binding.p4.text = "1 Gram : ₹ " + text
                                binding.P4.text = "1 KG : ₹ " + text8.toString() + "0"}
                            6 -> { binding.p5.text = "1 Gram : ₹ " + text
                                binding.P5.text = "1 KG : ₹ " + text8.toString() + "0"}
                            7 -> { binding.p6.text = "1 Gram : ₹ " + text
                                binding.P6.text = "1 KG : ₹ " + text8.toString() + "0"}
                            8 -> { binding.p7.text = "1 Gram : ₹ " + text
                                binding.P7.text = "1 KG : ₹ " + text8.toString() + "0"}
                            9 -> { binding.p8.text = "1 Gram : ₹ " + text
                                binding.P8.text = "1 KG : ₹ " + text8.toString() + "0"}
                            10 -> { binding.p9.text = "1 Gram : ₹ " + text
                                binding.P9.text = "1 KG : ₹ " + text8.toString() + "0"}
                            11 -> { binding.p10.text = "1 Gram : ₹ " + text
                                binding.P10.text = "1 KG : ₹ " + text8.toString() + "0"}
                        }
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
                    runOnUiThread {
                        when (range + 2) {
                            3 -> { binding.p1.text = "1 Gram : ₹ " + text + "0"
                                binding.P1.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            4 -> { binding.p2.text = "1 Gram : ₹ " + text + "0"
                                binding.P2.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            5 -> { binding.p3.text = "1 Gram : ₹ " + text + "0"
                                binding.P3.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            6 -> { binding.p4.text = "1 Gram : ₹ " + text + "0"
                                binding.P4.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            7 -> { binding.p5.text = "1 Gram : ₹ " + text + "0"
                                binding.P5.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            8 -> { binding.p6.text = "1 Gram : ₹ " + text + "0"
                                binding.P6.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            9 -> { binding.p7.text = "1 Gram : ₹ " + text + "0"
                                binding.P7.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            10 -> { binding.p8.text = "1 Gram : ₹ " + text + "0"
                                binding.P8.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            11 -> { binding.p9.text = "1 Gram : ₹ " + text + "0"
                                binding.P9.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            12 -> { binding.p10.text = "1 Gram : ₹ " + text + "0"
                                binding.P10.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        }
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
                    runOnUiThread {
                        when (range) {
                            2 -> { binding.p1.text = "1 Gram : ₹ " + text
                                binding.P1.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            3 -> { binding.p2.text = "1 Gram : ₹ " + text
                                binding.P2.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            4 -> { binding.p3.text = "1 Gram : ₹ " + text
                                binding.P3.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            5 -> { binding.p4.text = "1 Gram : ₹ " + text
                                binding.P4.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            6 -> { binding.p5.text = "1 Gram : ₹ " + text
                                binding.P5.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            7 -> { binding.p6.text = "1 Gram : ₹ " + text
                                binding.P6.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            8 -> { binding.p7.text = "1 Gram : ₹ " + text
                                binding.P7.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            9 -> { binding.p8.text = "1 Gram : ₹ " + text
                                binding.P8.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            10 -> { binding.p9.text = "1 Gram : ₹ " + text
                                binding.P9.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            11 -> { binding.p10.text = "1 Gram : ₹ " + text
                                binding.P10.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        }
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
                    runOnUiThread {
                        when (range) {
                            3 -> { binding.p1.text = "1 Gram : ₹ " + text
                                binding.P1.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            4 -> { binding.p2.text = "1 Gram : ₹ " + text
                                binding.P2.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            5 -> { binding.p3.text = "1 Gram : ₹ " + text
                                binding.P3.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            6 -> { binding.p4.text = "1 Gram : ₹ " + text
                                binding.P4.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            7 -> { binding.p5.text = "1 Gram : ₹ " + text
                                binding.P5.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            8 -> { binding.p6.text = "1 Gram : ₹ " + text
                                binding.P6.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            9 -> { binding.p7.text = "1 Gram : ₹ " + text
                                binding.P7.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            10 -> { binding.p8.text = "1 Gram : ₹ " + text
                                binding.P8.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            11 -> { binding.p9.text = "1 Gram : ₹ " + text
                                binding.P9.text = "8 Gram : ₹ " + text8.toString() + "0"}
                            12 -> { binding.p10.text = "1 Gram : ₹ " + text
                                binding.P10.text = "8 Gram : ₹ " + text8.toString() + "0"}
                        }
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
                    runOnUiThread {
                        when (range) {
                            2 -> { binding.p1.text = "1 USD : ₹ " + text
                                binding.P1.text = "_______"}
                            3 -> { binding.p2.text = "1 USD : ₹ " + text
                                binding.P2.text = "_______"}
                            4 -> { binding.p3.text = "1 USD : ₹ " + text
                                binding.P3.text = "_______"}
                            5 -> { binding.p4.text = "1 USD : ₹ " + text
                                binding.P4.text = "_______"}
                            6 -> { binding.p5.text = "1 USD : ₹ " + text
                                binding.P5.text = "_______"}
                            7 -> { binding.p6.text = "1 USD : ₹ " + text
                                binding.P6.text = "_______"}
                            8 -> { binding.p7.text = "1 USD : ₹ " + text
                                binding.P7.text = "_______"}
                            9 -> { binding.p8.text = "1 USD : ₹ " + text
                                binding.P8.text = "_______"}
                            10 -> { binding.p9.text = "1 USD : ₹ " + text
                                binding.P9.text = "_______"}
                            11 -> { binding.p10.text = "1 USD : ₹ " + text
                                binding.P10.text = "_______"}
                        }
                    }
                }
            } catch (e: Exception) {

            }
        }

        val petrolThread = Thread{}

        val dieselThread = Thread{}

        if (commodity == "gold22") {
            binding.commodityTitle.text = "22 Carat Gold Rate for Last 10 Days"
            gold22Thread.start()
        } else if (commodity == "gold24") {
            binding.commodityTitle.text = "24 Carat Gold Rate for Last 10 Days"
            gold24Thread.start()
        } else if (commodity == "silver") {
            binding.commodityTitle.text = "Silver Rate for Last 10 Days"
            silverThread.start()
        } else if (commodity == "platinum") {
            binding.commodityTitle.text = "Platinum Rate for Last 10 Days"
            platinumThread.start()
        } else if (commodity == "gold18") {
            binding.commodityTitle.text = "18 Carat Gold Rate for Last 10 Days"
            gold18Thread.start()
        } else if (commodity == "petrol") {
            binding.commodityTitle.text = "Petrol Rate for Last 10 Days"
            //petrolThread.start()
        } else if (commodity == "diesel") {
            binding.commodityTitle.text = "Diesel Rate for Last 10 Days"
            //dieselThread.start()
        } else if (commodity == "usd") {
            binding.commodityTitle.text = "US Dollar Rate for Last 10 Days"
            usdThread.start()
        }
    }
}