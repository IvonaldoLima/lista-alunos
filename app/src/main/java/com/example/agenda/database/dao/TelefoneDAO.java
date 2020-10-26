package com.example.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agenda.model.Telefone;

import java.util.List;

@Dao
public interface TelefoneDAO {

    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);

    @Insert
    void salva(Telefone... telefones);

    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId")
    List<Telefone> buscaTodosTelefonesDoAluno(int alunoId);

    @Update
    void edita(Telefone telefone);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualizaLista(Telefone... telefonesDoAluno);
}
