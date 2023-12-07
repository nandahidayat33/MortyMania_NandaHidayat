package com.utdi.nandahidayat

import retrofit2.Call
import retrofit2.http.GET

//pada file ini dilakukan pemanggilan fungsi get retrofit
//kemudian gunakan endpoint character
//dan dibuatkan juga fungsi getRick dan buat ResponseRick
//selanjutnya lanjutkan ke file ApiConfig,
//dandilanjutkan ke file RickAdapter
interface ApiService {
    @GET ("character")
    fun getRick(): Call<ResponseRick>
}