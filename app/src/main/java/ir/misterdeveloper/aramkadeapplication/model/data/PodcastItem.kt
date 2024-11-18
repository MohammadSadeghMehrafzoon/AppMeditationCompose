package ir.misterdeveloper.aramkadeapplication.model.data

data class PodcastItem(
    val id: Int,
    val session: String,
    val url: String,
    val time: String,
    val room_id: Int,
    val free: Int
)