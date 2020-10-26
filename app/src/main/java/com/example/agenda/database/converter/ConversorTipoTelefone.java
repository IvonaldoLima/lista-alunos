package com.example.agenda.database.converter;

import androidx.room.TypeConverter;

import com.example.agenda.model.TipoTelefone;

public class ConversorTipoTelefone {

    @TypeConverter
    public String toString(TipoTelefone tipoTelefone) {
        if (tipoTelefone != null) {
            return tipoTelefone.name();
        }
        return null;
    }

    @TypeConverter
    public TipoTelefone toTipoTelefone(String valor) {
        if (valor != null) {
            return  TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }
}
