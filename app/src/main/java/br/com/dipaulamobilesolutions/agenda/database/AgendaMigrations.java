package br.com.dipaulamobilesolutions.agenda.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class AgendaMigrations {

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
        }
    };
    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //remover coluna ou alterar seu tipo deve seguir estes passos

            // Criar nova tablea com as informações desejadas
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo" +
                    "` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT," +
                    " `sobrenome` TEXT," +
                    " `telefone` TEXT," +
                    " `email` TEXT)");


            // Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email) " +
                    "SELECT id, nome, telefone, email FROM Aluno");

            // Remove tabela antiga
            database.execSQL("DROP TABLE Aluno");

            // Renomear a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };
    public static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };
    public static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
        }
    };
    public static final Migration[] TODAS_MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5};
}
