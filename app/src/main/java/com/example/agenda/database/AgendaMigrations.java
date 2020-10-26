package com.example.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.agenda.model.TipoTelefone;

public class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN momentoDeCadastro INTEGER");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            //Criando uma nova tabela
            database.execSQL("CREATE TABLE `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `sobrenome` TEXT, `telefoneFixo` TEXT, `email` TEXT, `momentoDeCadastro` INTEGER, `telefoneCelular` TEXT)");

            //Copiando os dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefoneFixo, email, momentoDeCadastro)" +
                    "SELECT id, nome, telefone, email, momentoDeCadastro from Aluno");

            //Remove tabela antiga
            database.execSQL("DROP TABLE Aluno");

            //Renomear a tabela nova com o nome da antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            //Criando uma nova tabela
            database.execSQL("CREATE TABLE `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `sobrenome` TEXT,  `email` TEXT, `momentoDeCadastro` INTEGER)");

            //Copiando os dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, email, momentoDeCadastro)" +
                    "SELECT id, nome, email, momentoDeCadastro from Aluno");

            // Criando tabela de telefones
            database.execSQL("CREATE TABLE IF NOT EXISTS 'Telefone' (" +
                    "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "'numero' TEXT, " +
                    "'tipoTelefone' TEXT, " +
                    "'alunoId' INTEGER NOT NULL" +
                    ")");

            //Inserindo dados de telefone do aluno
            database.execSQL("INSERT INTO Telefone (numero, alunoId)" +
                    "SELECT telefoneFixo, id FROM Aluno");


            database.execSQL("UPDATE Telefone SET tipoTelefone = ?", new TipoTelefone[]{TipoTelefone.FIXO});

            //Remove tabela antiga
            database.execSQL("DROP TABLE Aluno");

            //Renomear a tabela nova com o nome da antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };


    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5};
}
