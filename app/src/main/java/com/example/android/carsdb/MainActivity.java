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

        databaceHandler.addCar(new Car("Toyota", "30 000 $"));
        databaceHandler.addCar(new Car("Opel", "20 000 $"));
        databaceHandler.addCar(new Car("Kia", "25 000 $"));
        databaceHandler.addCar(new Car("Deawoo", "5 000 $"));

        List<Car> catList = databaceHandler.getAllCars();

        for (Car car : catList) {

            Log.d("Car Info ", "ID " + car.getId() + ", name " + car.getName() + ", price " + car.getPrice());

        }


    }
}
