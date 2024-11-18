package ir.misterdeveloper.aramkadeapplication.ui.features.meetings

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.misterdeveloper.aramkadeapplication.model.data.PodcastItem
import ir.misterdeveloper.aramkadeapplication.repository.category.MethodRepository
import ir.misterdeveloper.aramkadeapplication.util.coroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MeetingViewModel(
    private val methodRepository: MethodRepository,
    itemId: Int
) : ViewModel() {

    val dataPodcast = mutableStateOf<List<PodcastItem>>(listOf())

    init {
        loadDataPodcastBy(itemId)
    }

    private fun loadDataPodcastBy(item: Int) {

        viewModelScope.launch {
            val dataFromLocal = async {methodRepository.getPodcastBy(item)}
            updateItem(dataFromLocal.await())
        }
    }
    private fun updateItem(items: List<PodcastItem>) {
        dataPodcast.value = items
        Log.v("updateData",items.toString())
    }


    fun updatePodcastBy(id: Int) {
   
        val filteredPodcast = dataPodcast.value.filter { it.id == id }
        
        viewModelScope.launch(coroutineExceptionHandler) {
            val newItem = async { methodRepository.getPodcastBy(id) }
            updateItem(newItem.await())

        }
    }
}

