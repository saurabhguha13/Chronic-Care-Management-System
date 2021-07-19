package com.example.chroniccaremanagementapp

class Notes(var noteId: Int, var title:String, var subTitle:String, var desc:String, var modTime:String, var isReminder: Int, var reminderTime: Long){
    companion object{
        val NOTE_ID_KEY = "Id"
        val NOTE_TITLE_KEY = "Title"
        val NOTE_SUBTITLE_KEY = "SubTitle"
        val NOTE_DESCRIPTION_KEY = "Description"
        val NOTE_MODIFIED_TIME_KEY = "ModifiedTime"
        val NOTE_REMINDER_TIME_KEY = "ReminderTime"
        var noteID = 0

        fun generateID():Int {
            noteID++
            return noteID
        }

        var noteArray = ArrayList<Notes>()
    }
}