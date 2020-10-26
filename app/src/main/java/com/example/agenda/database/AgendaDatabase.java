package com.example.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.agenda.database.converter.ConversorCalendar;
import com.example.agenda.database.converter.ConversorTipoTelefone;
import com.example.agenda.database.dao.AlunoDao;
import com.example.agenda.database.dao.TelefoneDAO;
import com.example.agenda.model.Aluno;
import com.example.agenda.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 5, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public abstract AlunoDao getRoomAlunoDao();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, "agenda.db")
                .addMigrations(AgendaMigrations.TODAS_MIGRATIONS)
                .build();
    }
}
