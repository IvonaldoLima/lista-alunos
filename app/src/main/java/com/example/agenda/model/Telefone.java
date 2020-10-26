package com.example.agenda.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity
public class Telefone {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String numero;
    private TipoTelefone tipoTelefone;
    @ForeignKey(entity = Aluno.class,
            parentColumns = "id",
            childColumns = "alunoId",
            onUpdate = CASCADE,
            onDelete = CASCADE)
    private int alunoId;


    public Telefone(String numero, TipoTelefone tipoTelefone) {
        this.numero = numero;
        this.tipoTelefone = tipoTelefone;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
}
