package com.hotel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String db_name = "hotel_mgnt.sqlite";
    private static final int db_version = 1;
    public static SQLiteDatabase sql_db;

    //Master Tables
    public String tbl_category = "CATEGORY";
    public String tbl_subcategory = "SUBCATEGORY";
    public String tbl_cart = "CART";

    private DataBaseHelper(Context context) {
        super(context, db_name, null, db_version);
        dbHelper = this;
    }

    private static Context ctx;

    private static DataBaseHelper dbHelper = null;

    public static DataBaseHelper getInstance(Context context) {
        ctx = context;
        if (dbHelper == null) {
            dbHelper = new DataBaseHelper(context);
            openConnecion();
        }
        return dbHelper;
    }

    private static void openConnecion() {
        if (sql_db == null) {
            sql_db = dbHelper.getWritableDatabase();
        }
    }

    public synchronized void closeConnecion() {
        if (dbHelper != null) {
            dbHelper.close();
            sql_db.close();
            dbHelper = null;
            sql_db = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        SimpleDateFormat sdfdatetime = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        SimpleDateFormat sdfdate = new SimpleDateFormat("dd/MM/yyyy");
        String curr_datetime = sdfdatetime.format(Calendar.getInstance().getTime());
        String curr_date = sdfdate.format(Calendar.getInstance().getTime());

        /////MASTER Tables/////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            db.beginTransactionNonExclusive();
        }
        try {
            String category = "CREATE TABLE " + tbl_category + "(CATEGORY_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "CATEGORY_NAME TEXT NOT NULL," +
                    "CATEGORY_IMAGE TEXT)";

            db.execSQL(category);

            db.execSQL("INSERT INTO " + tbl_category + " (CATEGORY_NAME)VALUES ('Breakfast')");
            db.execSQL("INSERT INTO " + tbl_category + " (CATEGORY_NAME)VALUES ('Main Course')");
            db.execSQL("INSERT INTO " + tbl_category + " (CATEGORY_NAME)VALUES ('Dessart')");
            db.execSQL("INSERT INTO " + tbl_category + " (CATEGORY_NAME)VALUES ('Drink')");

            //Category Table

            String exam = "CREATE TABLE " + tbl_subcategory + "(SUB_CATEGORY_ID integer PRIMARY KEY AUTOINCREMENT," +
                    "SUB_CATEGORY_NAME TEXT NOT NULL," +
                    "SUB_CATEGORY_IMAGE TEXT," +
                    "CATEGORY_ID INTEGER NOT NULL," +
                    "FOREIGN KEY (CATEGORY_ID) REFERENCES " + tbl_category + "(CATEGORY_ID))";

            db.execSQL(exam);

            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory1',1)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory2',1)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory3',1)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory4',2)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory5',2)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory6',2)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory7',3)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory8',3)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory9',3)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory10',4)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory11',4)");
            db.execSQL("INSERT INTO " + tbl_subcategory + " (SUB_CATEGORY_NAME,CATEGORY_ID)VALUES ('Subcategory12',4)");

            //Sub Category Table

            String cart = "CREATE TABLE " + tbl_cart + "(CART_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ITEM_ID INTEGER NOT NULL," +
                    "ITEM_NAME TEXT NOT NULL," +
                    "ITEM_PRICE INTEGER NOT NULL," +
                    "ITEM_QUANTITY INTEGER NOT NULL," +
                    "ITEM_REQUEST TEXT," +
                    "CATEGORY_ID INTEGER NOT NULL," +
                    "SUB_CATEGORY_ID INTEGER NOT NULL," +
                    "FOREIGN KEY (CATEGORY_ID) REFERENCES " + tbl_category + "(CATEGORY_ID)," +
                    "FOREIGN KEY (SUB_CATEGORY_ID) REFERENCES " + tbl_subcategory + "(SUB_CATEGORY_ID))";

            db.execSQL(cart);

            //Cart table

            db.setTransactionSuccessful();

        } catch (Exception e) {
            dropTables(db);
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        //User Login History

    }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        dropTables(db);
    }//onUpgrade

    public void dropTables(SQLiteDatabase sql_db) {

        if (sql_db == null)
            sql_db = getWritableDatabase();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            sql_db.beginTransactionNonExclusive();
        }
        try {
           /* sql_db.execSQL("DROP TABLE IF EXISTS tbl_examtype");
            sql_db.execSQL("DROP TABLE IF EXISTS tbl_exam");
            sql_db.execSQL("DROP TABLE IF EXISTS tbl_exam_examtype_mapping");
            sql_db.execSQL("DROP TABLE IF EXISTS tbl_subject");
            sql_db.execSQL("DROP TABLE IF EXISTS tbl_topic");
            sql_db.execSQL("DROP TABLE IF EXISTS tbl_language");
            sql_db.execSQL("DROP TABLE IF EXISTS tbl_question");*/

            sql_db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sql_db.endTransaction();
        }
        onCreate(sql_db);
    }

    public Cursor getCategiories()
    {
        String selectQuery="Select * from "+tbl_category;
        return sql_db.rawQuery(selectQuery, null);
    }

    public Cursor getSubCategiories(int category_id)
    {
        String selectQuery="Select * from "+tbl_subcategory+" where CATEGORY_ID="+category_id;
        return sql_db.rawQuery(selectQuery, null);
    }

  /*  public Cursor getQuestionList(String topic_id,String exam_id,String year)
    {
        String selectQuery="Select * from tbl_question where topic_id="+topic_id+" and examtype_id in("+exam_id+") ";
        return sql_db.rawQuery(selectQuery, null);
    }

    public Cursor getExam()
    {
        String query="select * from tbl_exam";
        return sql_db.rawQuery(query,null);
    }

    public Cursor getExamtype(int examid)
    {
        String query="Select * from tbl_examtype tet where tet.examtype_id in(select examtype_id from tbl_exam_examtype_mapping tee where tee.exam_id="+examid+")";
        return sql_db.rawQuery(query,null);
    }

    public Cursor getSubject(int examid)
    {
        String query="select * from tbl_subject tee where tee.exam_id="+examid+"";
        return sql_db.rawQuery(query,null);
    }
    public Cursor getTopics(int subject_id)
    {
        String query="select * from tbl_topic tee where tee.subject_id="+subject_id+"";
        return sql_db.rawQuery(query,null);
    }

    public Cursor getQuestionCount(int topicid)
    {
        String query="select * from tbl_question where topic_id="+topicid+"";
        return sql_db.rawQuery(query,null);
    }*/
}
