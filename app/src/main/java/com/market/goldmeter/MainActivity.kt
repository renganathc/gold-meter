package com.market.goldmeter

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        Log.d("TAG", "Works")

        var window = this.window
        window.statusBarColor = Color.parseColor("#f44336")

        val result = intent.getStringExtra("SPLASH_SCREEN_INTENT")

        var clickSound : String? = null


        if (result == "sound") {
                Snackbar.make(findViewById(R.id.tex), "Welcome Back!!", Snackbar.LENGTH_LONG).show()



            FirebaseDatabase.getInstance().getReference("/Settings")
                .addChildEventListener(object : ChildEventListener {
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        if(snapshot.key == "Welcome") {
                            if (snapshot.value.toString() == "1") {

                                var m = MediaPlayer.create(this@MainActivity, R.raw.welcome2)
                                m.setVolume(0.05F, 0.05F)
                                m.start()

                            } else if (snapshot.value.toString() == "2") {

                                var m = MediaPlayer.create(this@MainActivity, R.raw.nostalgia)
                                m.setVolume(0.01F, 0.01F)
                                m.start()

                            } else if (snapshot.value.toString() == "3") {

                                var m = MediaPlayer.create(this@MainActivity, R.raw.nokia)
                                m.setVolume(0.015F, 0.015F)
                                m.start()

                            } else if (snapshot.value.toString() == "0") {
                                // Do nothing ...
                            } else if (snapshot.value.toString() == "4") {
                                Toast.makeText(this@MainActivity, "You have been denied Access", Toast.LENGTH_LONG).show()
                                finish()
                            }
                        }

                        else if(snapshot.key == "Click"){
                            clickSound == snapshot.value
                        }
                    }

                    override fun onChildChanged(
                        snapshot: DataSnapshot,
                        previousChildName: String?
                    ) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                        TODO("Not yet implemented")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })



        }


        val c = Calendar.getInstance().time
        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate: String = df.format(c)
        date.text = formattedDate

        var time = SimpleDateFormat("HH", Locale.getDefault())
        var formattedTime = time.format(c).toInt()

        if (formattedTime > 1 && formattedTime < 11) greeting.text = "Good Morning \uD83D\uDC4B\uD83D\uDE4F"
        else if (formattedTime > 11 && formattedTime < 16) greeting.text = "Good Afternoon \uD83D\uDC4B\uD83D\uDE4F"
        else greeting.text = "Good Evening \uD83D\uDC4B\uD83D\uDE4F"

        var num = LocalDate.now().dayOfWeek.toString()

        when (num) {
            "SUNDAY" -> num = "Sunday"
            "MONDAY" -> num = "Monday"
            "TUESDAY" -> num = "Tuesday"
            "WEDNESDAY" -> num = "Wednesday"
            "THURSDAY" -> num = "Thursday"
            "FRIDAY" -> num = "Friday"
            "SATURDAY" -> num = "Saturday"
        }

        day.text = num


        Thread(
            Runnable {
                try {
                    val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                    val element = doc.select("tr:nth-of-type(1) > td:nth-of-type(4)")

                    val manipulatedElement = element.text().toString().filter{ it.isDigit() }

                    var final1 =  manipulatedElement.toFloat()
                    var final8 = final1*8

                    runOnUiThread {
                        g221.text = "₹ " + final1.toString() + "0"
                        g228.text = "₹ " + final8.toString() + "0"
                    }
                } catch (e : IOException) {

                }
            }
        ).start()


        Thread(
            Runnable {
                try {
                    val doc = Jsoup.connect("https://www.google.com/search?q=usd+to+inr&rlz=1C5CHFA_enIN1052IN1052&oq=usd+to+inr&gs_lcrp=EgZjaHJvbWUqBggAEEUYOzIGCAAQRRg7MgYIARBFGEAyBggCEEUYPDIGCAMQRRg8MgYIBBBFGDzSAQgxMzUxajBqN6gCALACAA&sourceid=chrome&ie=UTF-8").get()
                    val element = doc.select(".DFlfde.SwHCTb")
                    val element2 = doc.select(".hqAUc.k0Rg6d > span")


                    runOnUiThread {
                        dollar1.text = "₹ " + element.text().toString()
                        last_update.text = element2.text().toString()
                    }
                } catch (e : IOException) {

                }
            }
        ).start()

        Thread(
            Runnable {
                try {
                    val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                    val element = doc.select(".silver-rates.table.table-bordered.table-striped > tbody > tr:nth-of-type(1) > td:nth-of-type(2)")

                    var final1 = element.text().toString().toFloat()
                    var final8 = final1*1000

                    runOnUiThread {
                        s1.text = "₹ " + final1.toString() + "0"
                        s8.text = "₹ " + final8.toString() + "0"
                    }

                } catch (e : IOException) {

                }
            }
        ).start()

        Thread(
            Runnable {
                try {
                    val doc = Jsoup.connect("https://www.livechennai.com/18k_goldrate_chennai.asp").get()
                    val element = doc.select("tr:nth-of-type(3) > td:nth-of-type(2) > font")

                    var final1 = element.text().toString().toFloat()
                    var final8 = final1*8

                    runOnUiThread {
                        gold18.text = "₹ " + final1.toString() + "0"
                        gold18_p.text = "₹ " + final8.toString()  + "0"
                    }
                } catch (e : IOException) {

                }
            }
        ).start()

        Thread(
            Runnable {
                try {
                    val doc = Jsoup.connect("https://www.livechennai.com/platinum_rate_chennai.asp").get()
                    val element = doc.select("tr:nth-of-type(2) > td:nth-of-type(2)")

                    var final1 = element[0].text().toString().toFloat()
                    var final8 = final1*8

                    runOnUiThread {
                        p1.text = "₹ " + final1.toString() + "0"
                        p8.text = "₹ " + final8.toString() + "0"
                    }
                } catch (e : IOException) {

                }
            }
        ).start()

        Thread(
            Runnable {
                try {
                    val doc = Jsoup.connect("https://www.livechennai.com/petrol_price.asp").get()
                    val element = doc.select(".gold-rates.table.table-bordered > tbody:nth-of-type(1) > tr > td:nth-of-type(2)")

                    var final1 = element[0].text().toString().toDouble()
                    var final8 = final1*3

                    runOnUiThread {
                        petrol.text = "₹ " + final1.toString()
                        petrolG.text = "₹ " + final8.toString()
                    }
                } catch (e : IOException) {

                }
            }
        ).start()

        Thread(
            Runnable {
                try {
                    val doc = Jsoup.connect("https://www.livechennai.com/petrol_price.asp").get()
                    val element = doc.select(".silver-rates.table.table-bordered > tbody:nth-of-type(1) > tr > td:nth-of-type(2)")

                    var final1 = element[0].text().toString().toFloat()
                    var final8 = final1*3

                    runOnUiThread {
                        diesel.text = "₹ " + final1.toString()
                        dieselG.text = "₹ " + final8.toString()
                    }
                } catch (e : IOException) {

                }
            }
        ).start()

        Thread(
            Runnable {
                try {
                    val doc = Jsoup.connect("https://www.livechennai.com/scrap_prices_Chennai.asp").get()
                    val element = doc.select("tr:nth-of-type(4) > td:nth-of-type(3)")

                    var final1 = element[0].text().toString().toFloat()//var final8 = doc.select("tr:nth-of-type(4) > td:nth-of-type(3)").text().toString()

                    runOnUiThread {
                        copper.text = "₹ " + (final1 + 2.5).toString() + "0"
                        copperKG.text = "₹ " + final1.toString() + "0"
                    }
                } catch (e : IOException) {

                }
            }
        ).start()

        Thread(Runnable {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                val element = doc.select(".gold-rates.table.table-bordered.table-striped > tbody > tr:nth-of-type(1) > td:nth-of-type(2)")

                val manipulatedElement = element.text().toString().filter { it.isDigit() }


                var final1 =  manipulatedElement.toFloat()
                var final8 = final1*8

                runOnUiThread {
                    g241.text = "₹ " + final1.toString() + "0"
                    g248.text = "₹ " + final8.toString() + "0"
                }
            } catch (e : IOException) {

            }
        }).start()




        val intent = Intent(this, PreviousDayPricesActivity::class.java)
        var mp = MediaPlayer.create(this, R.raw.click)
        mp.setVolume(0.04F, 0.04F)

        gold22Card.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "gold22")
            startActivity(Intent(intent))

        }

        gold24Card.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "gold24")
            startActivity(Intent(intent))
        }

        silverCard.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "silver")
            startActivity(Intent(intent))
        }

        platinumCard.setOnClickListener {
            Snackbar.make(findViewById(R.id.tex), "Platinum Price History is Unavailable at the moment", Snackbar.LENGTH_LONG).show()

            //mp.start()
            //intent.putExtra("commodity", "platinum")
            //startActivity(Intent(intent))
        }

        gold18Card.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "gold18")
            startActivity(Intent(intent))

        }

        usdCard.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "usd")
            startActivity(Intent(intent))
        }

        copperCard.setOnClickListener {
            Snackbar.make(findViewById(R.id.tex), "Copper Price History is Unavailable at the moment", Snackbar.LENGTH_LONG).show()

            //mp.start()
            //intent.putExtra("commodity", "petrol")
            //startActivity(Intent(intent))
        }

        petrolCard.setOnClickListener {
            Snackbar.make(findViewById(R.id.tex), "Fuel Price History is Unavailable at the moment", Snackbar.LENGTH_LONG).show()

            //mp.start()
            //intent.putExtra("commodity", "petrol")
            //startActivity(Intent(intent))
        }

        dieselCard.setOnClickListener {
            Snackbar.make(findViewById(R.id.tex), "Fuel Price History is Unavailable at the moment", Snackbar.LENGTH_LONG).show()

            //mp.start()
            //intent.putExtra("commodity", "diesel")
            //startActivity(Intent(intent))
        }

    }

}