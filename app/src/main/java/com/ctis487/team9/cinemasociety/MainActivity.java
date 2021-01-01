package com.ctis487.team9.cinemasociety;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;


import com.ctis487.team9.cinemasociety.menu.MenuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;

    public static ArrayList<Event> upcomingList = new ArrayList<>();

    RecyclerView recyclerView;
    public static String PACKAGE_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        try {
            String fileToDatabase = "/data/data/" + getPackageName() + "/databases/"+DatabaseHelper.DATABASE_NAME;
            File file = new File(fileToDatabase);
            File pathToDatabasesFolder = new File("/data/data/" + getPackageName() + "/databases/");
            if (!file.exists()) {
                pathToDatabasesFolder.mkdirs();
                Log.d("BURDA", "BURDA");
                CopyDB( getResources().getAssets().open(DatabaseHelper.DATABASE_NAME+".db"),
                        new FileOutputStream(fileToDatabase));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PACKAGE_NAME = getApplicationContext().getPackageName();
        dbHelper = new DatabaseHelper(this);
        getFilmList();



    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        Log.d("BURDA", "BURDA2");

        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
            Log.d("BURDA", "BURDA3");
        }
        inputStream.close();
        outputStream.close();
    }

    public void onClick(View view) {
        Intent intent = null;
        if (view.getId() == R.id.buttonLogin)  {

            intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

    }

    private void getFilmList() {

        String myJSONstr = loadJSONFromAssets();

        try {
            JSONObject rootJsonObject = new JSONObject(myJSONstr);

            JSONArray filmJsonArray = rootJsonObject.getJSONArray("upcoming");

            for (int i = 0; i < filmJsonArray.length(); i++) {
                Event e = new Event();

                JSONObject jsonObject = filmJsonArray.getJSONObject(i);
                e.setId(jsonObject.getInt("id"));
                e.setTitle(jsonObject.getString("title"));
                e.setYear(jsonObject.getString("year"));
                e.setDirector(jsonObject.getString("director"));
                e.setDate(jsonObject.getString("date"));
                e.setTime(jsonObject.getString("time"));
                e.setImage(jsonObject.getString("image"));

                System.out.println(e);
                upcomingList.add(e);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String loadJSONFromAssets()
    {
        String json = null;

        try {
            InputStream inputStream = getAssets().open("upcoming.json");
            int size = inputStream.available();

            byte[] buffer = new byte [size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, StandardCharsets.UTF_8);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }


}