package com.example.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agenda.model.Telefone;
import com.example.agenda.model.TipoTelefone;

import java.util.List;

@Dao
public interface TelefoneDAO {

    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);

    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId AND tipoTelefone = :tipoTelefone LIMIT 1")
    Telefone buscaTelefoneDoAluno(int alunoId, TipoTelefone tipoTelefone);

    @Insert
    void salva(Telefone... telefones);

    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId")
    List<Telefone> buscaTodosTelefonesDoAluno(int alunoId);

    @Update
    void edita(Telefone telefone);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualizaLista(Telefone... telefonesDoAluno);
}
