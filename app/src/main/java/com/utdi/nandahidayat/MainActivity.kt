/** berikut adalah implementasi dari codelab, terkait tugas yang diberikan yaitu,
 * mendapatkan data dari internet dengan API
 * dan codelab ini dibuat dengan langkah pertama yaitu terlebih dahulu
 * mengambil/menggunakan API dari website rickandmorty.com
 * untuk melakukan itu menambahkan library retrofit terlebih dahulu
 * yang akan dikerjakan pada file build.gradle**/
package com.utdi.nandahidayat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//file MainActivity dibawah ini  aktifitas utama dalam aplikasi MortyMania berikut.
class MainActivity : AppCompatActivity() {

    //pada bagian ini dideklarasikan fungsi onCreate, yang akan dijalankan
    //saat aktivitas dibuat.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //bagian ini setContentView saat aplikasi dijalankan
        //maka akan mengarah ke tampilan yang berisikan elemen UI
        //yang terdapat pada file activity_main.xml
        setContentView(R.layout.activity_main)

        //kemudian val rick ini digunakna untuk mendapatkan elemen dari layout activity_main.xml
        //dengan  ID dari rv_character
        val rick = findViewById<RecyclerView>(R.id.rv_character)

        //dan dibagian ini adalah bagian saat aplikasi dijalankan, yang mana terdapat respon
        //atau loading yang dikerjakan pada bagian responseRick
        //kemudian terdapat dataRick, yaitu hasil dari responseRick
        //dimana setelah respon, maka dilanjutkan dengan mengambil data daftar karekter
        //selanjutnya terdapat juga rickAdapter yang berfungsi sebagai adapter pada data

        ApiConfig.getService().getRick().enqueue(object : Callback<ResponseRick>{
            override fun onResponse(call: Call<ResponseRick>, response: Response<ResponseRick>){
                if(response.isSuccessful){
                    val responseRick = response.body()
                    val dataRick = responseRick?.results
                    val rickAdapter = RickAdapter(dataRick as List<ResultsItem>)
                    rick.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        setHasFixedSize(true)
                        rickAdapter.notifyDataSetChanged()
                        adapter = rickAdapter
                    }
                }
            }
            override fun onFailure (call: Call<ResponseRick>, t: Throwable){
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}