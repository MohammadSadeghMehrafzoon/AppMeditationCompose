package ir.misterdeveloper.aramkadeapplication.util

sealed class MyScreen(val rout: String) {

    object MainScreen : MyScreen("mainScreen")
    object MeetingScreen : MyScreen("meetingScreen")

}