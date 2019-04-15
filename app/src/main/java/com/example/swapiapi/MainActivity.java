package com.example.swapiapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView peopleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peopleList = (ListView) findViewById(R.id.peopleList);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://swapi.co/api").addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        SwapiClient client = retrofit.create(SwapiClient.class);

        Call<List<StarWarsPeople>> call = client.reposForActors();

        call.enqueue(new Callback<List<StarWarsPeople>>() {
            @Override
            public void onResponse(Call<List<StarWarsPeople>> call, Response<List<StarWarsPeople>> response) {
                    List<StarWarsPeople> reposSwPeopleList = response.body();

                    peopleList.setAdapter(new StarWarsPeopleAdapter());
            }

            @Override
            public void onFailure(Call<List<StarWarsPeople>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error during downling people form SW", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
