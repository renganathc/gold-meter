package com.market.goldmeter

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import androidx.lifecycle.lifecycleScope
import com.market.goldmeter.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                                m.setVolume(0.12F, 0.12F)
                                m.start()

                            } else if (snapshot.value.toString() == "2") {

                                var m = MediaPlayer.create(this@MainActivity, R.raw.nostalgia)
                                m.setVolume(0.035F, 0.035F)
                                m.start()

                            } else if (snapshot.value.toString() == "3") {

                                var m = MediaPlayer.create(this@MainActivity, R.raw.nokia)
                                m.setVolume(0.03F, 0.03F)
                                m.start()

                            } else if (snapshot.value.toString() == "0") {
                                // Do nothing ...
                            } else if (snapshot.value.toString() == "4") {
                                Toast.makeText(this@MainActivity, "You have been denied Access", Toast.LENGTH_LONG).show()
                                finish()
                            }
                        }

                        else if(snapshot.key == "Click"){
                            clickSound = snapshot.value.toString()
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
        binding.date.text = formattedDate

        var time = SimpleDateFormat("HH", Locale.getDefault())
        var formattedTime = time.format(c).toInt()

        if (formattedTime > 1 && formattedTime < 11) binding.greeting.text = "Good Morning \uD83D\uDC4B"
        else if (formattedTime > 11 && formattedTime < 16) binding.greeting.text = "Good Afternoon \uD83D\uDC4B"
        else binding.greeting.text = "Good Evening \uD83D\uDC4B"

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

        binding.day.text = num


        // GOLD 22k
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                val element = doc.select("tr:nth-of-type(1) > td:nth-of-type(4)")
                val manipulatedElement = element.text().filter { it.isDigit() }
                val final1 = manipulatedElement.toFloat()
                val final8 = final1 * 8

                withContext(Dispatchers.Main) {
                    binding.g221.text = "₹ ${final1}0"
                    binding.g228.text = "₹ ${final8}0"
                }
            } catch (_: IOException) {}
        }

// USD TO INR
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect("https://www.bookmyforex.com/currency-converter/usd-to-inr/").get()
                val element = doc.select(".first_usdorinr_input_inr")

                withContext(Dispatchers.Main) {
                    binding.dollar1.text = "${element.text()}"
                    binding.lastUpdate.text = "Last Updated : $formattedDate"
                }
            } catch (_: IOException) {}
        }

// SILVER
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                val element = doc.select(".silver-rates.table.table-bordered.table-striped > tbody > tr:nth-of-type(1) > td:nth-of-type(2)")
                val final1 = element.text().toFloat()
                val final8 = final1 * 1000

                withContext(Dispatchers.Main) {
                    binding.s1.text = "₹ ${final1}0"
                    binding.s8.text = "₹ ${final8}0"
                }
            } catch (_: IOException) {}
        }

// GOLD 18k
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/18k_goldrate_chennai.asp").get()
                val element = doc.select("tr:nth-of-type(3) > td:nth-of-type(2) > font")
                val final1 = element.text().toFloat()
                val final8 = final1 * 8

                withContext(Dispatchers.Main) {
                    binding.gold18.text = "₹ ${final1}0"
                    binding.gold18P.text = "₹ ${final8}0"
                }
            } catch (_: IOException) {}
        }

// PLATINUM
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/platinum_rate_chennai.asp").get()
                val element = doc.select("tr:nth-of-type(2) > td:nth-of-type(2)")
                val final1 = element[0].text().toFloat()
                val final8 = final1 * 8

                withContext(Dispatchers.Main) {
                    binding.p1.text = "₹ ${final1}0"
                    binding.p8.text = "₹ ${final8}0"
                }
            } catch (_: IOException) {}
        }

// PETROL
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/petrol_price.asp").get()
                val element = doc.select(".gold-rates.table.table-bordered > tbody:nth-of-type(1) > tr > td:nth-of-type(2)")
                val final1 = element[0].text().toDouble()
                val final8 = final1 * 3

                withContext(Dispatchers.Main) {
                    binding.petrol.text = "₹ $final1"
                    binding.petrolG.text = "₹ $final8"
                }
            } catch (_: IOException) {}
        }

// DIESEL
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/petrol_price.asp").get()
                val element = doc.select(".silver-rates.table.table-bordered > tbody:nth-of-type(1) > tr > td:nth-of-type(2)")
                val final1 = element[0].text().toFloat()
                val final8 = final1 * 3

                withContext(Dispatchers.Main) {
                    binding.diesel.text = "₹ $final1"
                    binding.dieselG.text = "₹ $final8"
                }
            } catch (_: IOException) {}
        }

// COPPER
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/scrap_prices_Chennai.asp").get()
                val element = doc.select("tr:nth-of-type(4) > td:nth-of-type(3)")
                val final1 = element[0].text().toFloat()

                withContext(Dispatchers.Main) {
                    binding.copper.text = "₹ ${final1 + 2.5}0"
                    binding.copperKG.text = "₹ ${final1}0"
                }
            } catch (_: IOException) {}
        }

// GOLD 24k
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val doc = Jsoup.connect("https://www.livechennai.com/gold_silverrate.asp").get()
                val element = doc.select(".gold-rates.table.table-bordered.table-striped > tbody > tr:nth-of-type(1) > td:nth-of-type(2)")
                val manipulatedElement = element.text().filter { it.isDigit() }
                val final1 = manipulatedElement.toFloat()
                val final8 = final1 * 8

                withContext(Dispatchers.Main) {
                    binding.g241.text = "₹ ${final1}0"
                    binding.g248.text = "₹ ${final8}0"
                }
            } catch (_: IOException) {}
        }




        val intent = Intent(this, PreviousDayPricesActivity::class.java)
        var mp = MediaPlayer.create(this, R.raw.click)
        mp.setVolume(0.04F, 0.04F)

        binding.gold22Card.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "gold22")
            startActivity(Intent(intent))

        }

        binding.gold24Card.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "gold24")
            startActivity(Intent(intent))
        }

        binding.silverCard.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "silver")
            startActivity(Intent(intent))
        }

        binding.platinumCard.setOnClickListener {
            Snackbar.make(findViewById(R.id.tex), "Platinum Price History is Unavailable at the moment", Snackbar.LENGTH_LONG).show()

            //mp.start()
            //intent.putExtra("commodity", "platinum")
            //startActivity(Intent(intent))
        }

        binding.gold18Card.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "gold18")
            startActivity(Intent(intent))

        }

        binding.usdCard.setOnClickListener {
            if (clickSound == "1") {
                mp.start()
            }

            intent.putExtra("commodity", "usd")
            startActivity(Intent(intent))
        }

        binding.copperCard.setOnClickListener {
            Snackbar.make(findViewById(R.id.tex), "Copper Price History is Unavailable at the moment", Snackbar.LENGTH_LONG).show()

            //mp.start()
            //intent.putExtra("commodity", "petrol")
            //startActivity(Intent(intent))
        }

        binding.petrolCard.setOnClickListener {
            Snackbar.make(findViewById(R.id.tex), "Fuel Price History is Unavailable at the moment", Snackbar.LENGTH_LONG).show()

            //mp.start()
            //intent.putExtra("commodity", "petrol")
            //startActivity(Intent(intent))
        }

        binding.dieselCard.setOnClickListener {
            Snackbar.make(findViewById(R.id.tex), "Fuel Price History is Unavailable at the moment", Snackbar.LENGTH_LONG).show()

            //mp.start()
            //intent.putExtra("commodity", "diesel")
            //startActivity(Intent(intent))
        }

    }

}