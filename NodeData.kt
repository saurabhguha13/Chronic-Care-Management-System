package com.example.chroniccaremanagementapp

class NodeData {
    companion object{
        const val TABLE_NAME = "notes"
        const val COLUMN_ID = "id"
        const val COLUMN_NOTE_TITLE = "notes_title"
        const val COLUMN_NOTE_SUB_TITLE = "notes_subtitle"
        const val COLUMN_NOTE_DESCRIPTION = "notes_desc"
        const val COLUMN_NOTE_SET_REMINDER = "note_set_reminder"
        const val COLUMN_NOTE_REMINDER_TIME = "note_reminder_time"
        const val COLUMN_TIMESTAMP = "notes_remtime"
        const val TABLE_SQL_QUERY = ("CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NOTE_TITLE + " TEXT," + COLUMN_NOTE_SUB_TITLE + " TEXT," + COLUMN_NOTE_DESCRIPTION + " TEXT," + COLUMN_NOTE_SET_REMINDER + " INTEGER," + COLUMN_NOTE_REMINDER_TIME + " INTEGER," + COLUMN_TIMESTAMP + " TEXT"+ ")")
    }
}