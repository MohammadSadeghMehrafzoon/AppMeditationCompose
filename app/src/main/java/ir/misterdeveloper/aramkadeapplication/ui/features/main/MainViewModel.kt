package ir.misterdeveloper.aramkadeapplication.ui.features.main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.misterdeveloper.aramkadeapplication.model.data.Category
import ir.misterdeveloper.aramkadeapplication.model.data.Items
import ir.misterdeveloper.aramkadeapplication.repository.category.MethodRepository
import ir.misterdeveloper.aramkadeapplication.util.coroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(
    private val methodRepository: MethodRepository,
    private val checkInternet: Boolean,
    categoryId: Int
) : ViewModel() {

    val categoryList = mutableStateOf<List<Category>>(listOf())
    val itemsData = mutableStateOf<List<Items>>(listOf())
    val showProgress = mutableStateOf(false)

    init {
        refreshAllDataInternet(categoryId)
    }

    private fun refreshAllDataInternet(catId:Int) {

        viewModelScope.launch(coroutineExceptionHandler) {

            if (checkInternet)
                showProgress.value = true
            val newData = async { methodRepository.getCategory() }
            val newItem = async { methodRepository.getAllCategoryBy(catId) }
            updateData(newData.await())
            updateItem(newItem.await())
            showProgress.value = false
        }
    }

    private fun updateData(categories: List<Category>) {
        categoryList.value = categories
        Log.v("updateData",categories.toString())
    }

    private fun updateItem(items: List<Items>) {
        itemsData.value = items
        Log.v("updateData",items.toString())
    }

    fun updateCategory(categoryId: Int) {

        val items = itemsData.value.filter { it.id == categoryId }

        viewModelScope.launch(coroutineExceptionHandler) {

            if (checkInternet)
                showProgress.value = true
            val newItem = async { methodRepository.getAllCategoryBy(categoryId) }
            updateItem(newItem.await())
            showProgress.value = false
        }

    }
}
