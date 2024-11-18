package ir.misterdeveloper.aramkadeapplication.net

import ir.misterdeveloper.aramkadeapplication.model.data.Category
import ir.misterdeveloper.aramkadeapplication.model.data.Items
import ir.misterdeveloper.aramkadeapplication.model.data.PodcastItem
import ir.misterdeveloper.aramkadeapplication.util.BASE_URL

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("meditation/meditation_cat.php")
    suspend fun getCategory(): List<Category>

    @GET("meditation/meditation_item.php")
    suspend fun getItem(
        @Query("cat_id") catId: Int
    ): List<Items>

    @GET("meditation/meditation_session.php")
    suspend fun getSeesion(
        @Query("item_id") itemId: Int
    ): List<PodcastItem>

}

    fun createApiService(): ApiService {


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)

    }

