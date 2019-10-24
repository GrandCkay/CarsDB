package com.example.android.carsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import Data.DatabaceHandler;
import Model.Car;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaceHandler databaceHandler = new DatabaceHandler(this);

        Log.d("CarsCount", String.valueOf(databaceHandler.getCarsCount()));

        /* Создаем базу данных, выводим в Лог */

//        databaceHandler.addCar(new Car("Toyota", "30 000 $"));
//        databaceHandler.addCar(new Car("Opel", "20 000 $"));
//        databaceHandler.addCar(new Car("Kia", "25 000 $"));
//        databaceHandler.addCar(new Car("Daewoo", "5 000 $"));

        List<Car> catList = databaceHandler.getAllCars();

//        for (Car car : catList) {
//
//            Log.d("Car Info", "ID " + car.getId() + ", name " + car.getName() + ", price " + car.getPrice());
//        }


        /* Обнавляем базу данных, выводим в Лог  */

//        Car car = databaceHandler.getCar(1);
//
////        Log.d("Car Info", "ID " + car.getId() + ", name " + car.getName() + ", price " + car.getPrice());
//
//        car.setName("Tesla");
//        car.setPrice("50 000 $");
//
//        databaceHandler.updateCar(car);
//
//        Log.d("Car Info", "ID " + car.getId() + ", name " + car.getName() + ", price " + car.getPrice());
//


        /* Удаляем елемент из базы данных, выводим в Лог */

//        Car deleteCar = databaceHandler.getCar(1);  //
//
//        databaceHandler.deleteCar(deleteCar);

        for (Car car : catList) {

            Log.d("Car Info", "ID " + car.getId() + ", name " + car.getName() + ", price " + car.getPrice());
        }
    }
}