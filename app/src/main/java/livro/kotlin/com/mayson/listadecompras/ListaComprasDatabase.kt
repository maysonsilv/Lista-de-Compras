package livro.kotlin.com.mayson.listadecompras

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class ListaComprasDatabase(context: Context) : ManagedSQLiteOpenHelper(ctx = context, name ="listaCompras.db", version = 1) {

        //singleton da classe
    companion object{

        private var instance: ListaComprasDatabase? = null

            @Synchronized
            fun getInstance(ctx: Context): ListaComprasDatabase {

                if (instance == null){

                    instance = ListaComprasDatabase(ctx.getApplicationContext())
                }
                return instance!!
            }

    }

    override fun onCreate(db: SQLiteDatabase?) {

        //Criação de Tabelas

        db?.createTable("produtos", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "nome" to TEXT,
            "quantidade" to INTEGER,
            "valor" to REAL,
            "foto" to BLOB)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

        // Atualização do banco de dados


    }

}

// Acesso a propriedade pelo contexto

val Context.database: ListaComprasDatabase
    get() = ListaComprasDatabase.getInstance(getApplicationContext())


