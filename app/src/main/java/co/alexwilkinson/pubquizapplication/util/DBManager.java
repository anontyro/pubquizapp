package co.alexwilkinson.pubquizapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by Alex on 04/01/2017.
 */

public class DBManager {

    private SQLiteDatabase sqlDB;
    public static final String dbName = "PubQuiz";
    public static final int dbVersion = 1;

    public static final String tableQuestions = "questions";

    public static final String colID = "questionID";
    public static final String colDifficulty = "difficulty";
    public static final String colTitle = "title";
    public static final String colQuestion = "question";
    public static final String colNumberOfAnswers = "numberofanswers";
    public static final String colAnswers = "answers";
    public static final String colCorrectAnswer = "correctanswer";
    public static final String colcategory = "category";

    public final static String buildQuestionsTable =
            "CREATE TABLE IF NOT EXISTS " + tableQuestions +" (" +
                    colID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    colDifficulty + " TEXT, " +
                    colTitle + " TEXT, " +
                    colQuestion + " TEXT, " +
                    colNumberOfAnswers + " TEXT, " +
                    colAnswers + " TEXT, " +
                    colCorrectAnswer + " TEXT, " +
                    colcategory + " TEXT, " +

                    ");";


    public DBManager(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        sqlDB = db.getWritableDatabase();
    }

    public long insertQuestion(ContentValues values){
        long id = sqlDB.insert(tableQuestions,"",values);
        return id;
    }

    public void removeQuestion(String title){
        sqlDB.execSQL("DELETE FROM " +tableQuestions +" WHERE " +colTitle + " = " + title + ";");
    }

    public Cursor queryQuestions(String[]columns, String where, String[]whereArgs, String sortOrder){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(tableQuestions);

        Cursor cursor = qb.query(sqlDB,columns,where,whereArgs,null,null,sortOrder);

        return cursor;
    }




    public static class DatabaseHelper extends SQLiteOpenHelper{
        private Context context;

        public DatabaseHelper(Context context){
            super(context,dbName,null,dbVersion);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(buildQuestionsTable);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }




}
