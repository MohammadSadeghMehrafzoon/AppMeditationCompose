package ir.misterdeveloper.aramkadeapplication.repository.category

import ir.misterdeveloper.aramkadeapplication.model.data.Category
import ir.misterdeveloper.aramkadeapplication.model.data.Items
import ir.misterdeveloper.aramkadeapplication.model.data.PodcastItem

interface MethodRepository {

    suspend fun getCategory() : List<Category>
    suspend fun getAllCategoryBy(category:Int):List<Items>
    suspend fun getPodcastBy(itemId:Int):List<PodcastItem>
}