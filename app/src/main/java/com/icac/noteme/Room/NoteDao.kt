package com.icac.noteme.Room

import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM `Note` ORDER BY id DESC")
    suspend fun getAllNote(): List<Note>

    @Query("SELECT * FROM 'Note' WHERE id=:note_id")
    suspend fun getNote(note_id: Int): List<Note>

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}