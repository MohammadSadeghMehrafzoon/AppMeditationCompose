package ir.misterdeveloper.aramkadeapplication.repository.category

import ir.misterdeveloper.aramkadeapplication.model.data.Category
import ir.misterdeveloper.aramkadeapplication.model.data.Items
import ir.misterdeveloper.aramkadeapplication.model.data.PodcastItem
import ir.misterdeveloper.aramkadeapplication.net.ApiService

class MethodRepositoryImpl(
    private val apiService: ApiService,
) : MethodRepository {
    override suspend fun getCategory(): List<Category> {

        val dataFromApi = apiService.getCategory()

        return dataFromApi
        return listOf()
    }


    override suspend fun getAllCategoryBy(category: Int): List<Items> {
        val dataFromApi = apiService.getItem(category)
        return dataFromApi
        return listOf()
    }

    override suspend fun getPodcastBy(itemId: Int): List<PodcastItem> {
        val dataFromApi = apiService.getSeesion(itemId)
        return dataFromApi
        return listOf()
    }


}