import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.chroniccaremanagementapp.NodeData
import com.example.chroniccaremanagementapp.Notes

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "notes_db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(NodeData.TABLE_SQL_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE if EXISTS " + NodeData.TABLE_NAME)
        onCreate(db)
    }
    private fun getValues(note: Notes):ContentValues{
        val values = ContentValues()
        Log.d("HELPER", note.title)

        values.put(NodeData.COLUMN_NOTE_TITLE, note.title)
        values.put(NodeData.COLUMN_NOTE_SUB_TITLE, note.subTitle)
        values.put(NodeData.COLUMN_NOTE_DESCRIPTION, note.desc)
        values.put(NodeData.COLUMN_NOTE_REMINDER_TIME, note.reminderTime)
        values.put(NodeData.COLUMN_NOTE_SET_REMINDER, note.isReminder)
        values.put(NodeData.COLUMN_TIMESTAMP, note.modTime)
        return values
    }

    fun insertData(note: Notes){
        val db = this.writableDatabase
        val result = db.insert(NodeData.TABLE_NAME, null, getValues(note))
        Log.d("DatabaseHelper", "REcord iNserted......")
        Log.d("DatabaseHelper", "${result}")
        db.close()
    }

    fun updateData(note: Notes){
        val db = this.writableDatabase
        val result = db.update(NodeData.TABLE_NAME, getValues(note), NodeData.COLUMN_ID + " = ? ", arrayOf(note.noteId.toString()) )
        db.close()
    }
    fun deleteData(note: Notes){
        val db = this.writableDatabase
        val result = db.delete(NodeData.TABLE_NAME, NodeData.COLUMN_ID + " = ? ", arrayOf(note.noteId.toString()) )
        db.close()
    }

    fun allNotes(): ArrayList<Notes> {
        val db = this.writableDatabase
        val noteArray = ArrayList<Notes>()

        val cursor = db.rawQuery( "SELECT * FROM " + NodeData.TABLE_NAME
            , null)
        if(cursor.moveToFirst()){
            do {
                noteArray.add(getNote(cursor))
            } while (cursor.moveToNext())
        }
        db.close()
        return noteArray
    }

    fun getNote(cursor: Cursor): Notes{
        val note = Notes(
            1,
            cursor.getString(cursor.getColumnIndex(NodeData.COLUMN_NOTE_TITLE)),
            cursor.getString(cursor.getColumnIndex(NodeData.COLUMN_NOTE_SUB_TITLE)),
            cursor.getString(cursor.getColumnIndex(NodeData.COLUMN_NOTE_DESCRIPTION)),
            cursor.getString(cursor.getColumnIndex(NodeData.COLUMN_TIMESTAMP)),
            cursor.getInt(cursor.getColumnIndex(NodeData.COLUMN_NOTE_SET_REMINDER)),
            cursor.getLong(cursor.getColumnIndex(NodeData.COLUMN_TIMESTAMP))
        )
        note.isReminder = cursor.getInt(cursor.getColumnIndex(NodeData.COLUMN_NOTE_SET_REMINDER))
        note.reminderTime = cursor.getLong(cursor.getColumnIndex(NodeData.COLUMN_NOTE_REMINDER_TIME))
        note.noteId = cursor!!.getInt(cursor.getColumnIndex(NodeData.COLUMN_ID))
        return note
    }
    fun deleteMe(context: Context){
        context.deleteDatabase("notes_db")

    }
    fun deleteDb(){
        val db = this.writableDatabase
        db.delete(NodeData.TABLE_NAME, null, null)
    }
}