package br.com.alura.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by augustoxavier on 22/02/17.
 */

public class AlunoDAO extends SQLiteOpenHelper {
    public AlunoDAO(Context context) {
        super(context, "agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE alunos(id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, site TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS alunos;";
        db.execSQL(sql);
        onCreate(db);
    }



    public void insere(Aluno aluno) {
//        String sql = "INSERT INTO alunos (nome, endereco, telefone, site, nota) VALUES ('"+aluno.getNome()+"');";
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("site", aluno.getSite());
        dados.put("nota", aluno.getNota());

        db.insert("alunos", null, dados);
    }

    public List<Aluno> buscaAlunos() {
        String sql = "SELECT * FROM alunos order by id";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        Aluno aluno;
        while (cursor.moveToNext()) {
            aluno = new Aluno();
            aluno.setId(cursor.getInt(cursor.getColumnIndex("id")));
            aluno.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            aluno.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
            aluno.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            aluno.setSite(cursor.getString(cursor.getColumnIndex("site")));
            aluno.setNota(cursor.getDouble(cursor.getColumnIndex("nota")));
            alunos.add(aluno);
        }
        cursor.close();
        return alunos;
    }
}
