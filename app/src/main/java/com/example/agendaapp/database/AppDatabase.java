package com.example.agendaapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.agendaapp.dao.CitaDao;
import com.example.agendaapp.models.Cita;

@Database(
        entities = { Cita.class },
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CitaDao citaDao();
}
