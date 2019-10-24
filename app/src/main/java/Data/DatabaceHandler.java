package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Car;
import Utils.Util;

public class DatabaceHandler extends SQLiteOpenHelper {
    public DatabaceHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // SQL - Structured Query Language (Структурированный язык запросов)

        /* Создаем структуру таблицы*/

        String CREATE_CARS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PRICE + " TEXT" + ")";

        db.execSQL(CREATE_CARS_TABLE); // создаем таблицу

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Обнавляем базу данных (таблицу)

        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME); // удаляем таблицу

        onCreate(db);    // Создаем новую таблицу
    }


    /* CRUD - Create, Read, Update, Delete
        Создаем функционал для создания, чтения, обновления и удаления записей из базы данных
     */


    // Создаем метод "добавить автомобиль" *Create

    public void addCar(Car car) {
        SQLiteDatabase db = this.getWritableDatabase();     // getWritableDatabase - записать

        ContentValues contentValues = new ContentValues(); // Тип абстракции для взаимодействия с базой данных при помощи ключ-значений
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        db.insert(Util.TABLE_NAME, null, contentValues); // Вставляем созданую запить в базу данных

        db.close(); // Закрываем соединение с базой данных
    }

    /* Создаем метод для извлечения записей с базы данных * Read */

    public Car getCar(int id) {
        SQLiteDatabase db = this.getReadableDatabase(); //getReadableDatabase - чтение


        /* Используем класс Cursor для перемещения по данных в базе данных */

        Cursor cursor = db.query(Util.TABLE_NAME, // извлекаем из базы данных запись (query)
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PRICE}, Util.KEY_ID + "=?",  // указываем массив столбцов таблицы из которых хотим извлечь значение
                new String[]{String.valueOf(id)},      // указываем отбор по столбцу id
                null, null, null, null); // сортировка и отбор по признакам


        // Проверяем и вызываем метод moveToFirst (проверка на наличие записей)

        if (cursor != null) {
            cursor.moveToFirst();
        }


        // Создаем обьект car, извлекаем информацию из cursor, помещаем информацию из cursor в обьект car, возвращаем обьект car

        Car car = new Car(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));  // в параметрах указываем индексы столбцов

        return car;
    }


    // Имплементируем метод для извленения всех записей в таблице

    public List<Car> getAllCars() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Car> carsList = new ArrayList<>();

        String selectAllCars = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllCars, null);

        if (cursor.moveToFirst()) {   // метод moveToFirst возвращает tru если метод содержет какие либо записи
            do {
                Car car = new Car(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
//                car.setId(Integer.parseInt(cursor.getString(0)));
//                car.setName(cursor.getString(1));
//                car.setPrice(cursor.getString(2));

                carsList.add(car);

            } while (cursor.moveToNext());
        }
        return carsList;
    }


    // Обновление записи в базе данных * Update

    public int updateCar(Car car) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());


        /*
            Вызываем метод Update для созданой таблицы (указываем имя таблицы, только что созданное contentValues,
            указываем ID записи куда будем помещать эту информацию, передаем данный ID который мы извлечом из обьекта
            car который передается в качестве параметра в метод updateCar (для этого помещаем ID в новый стринговый массив).
         */


        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String[] {String.valueOf(car.getId())});
    }


    // Имлементируем метод удаления данных из базы данных * Delete

    public void deleteCar (Car car) {
        SQLiteDatabase db = this.getReadableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[] {String.valueOf(car.getId())});

        db.close();
    }

    // Имплементируем метод для получения количества всех записей в таблице

    public int getCarsCount () {
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

}
