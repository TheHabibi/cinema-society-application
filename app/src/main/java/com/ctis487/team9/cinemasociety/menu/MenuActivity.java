package com.ctis487.team9.cinemasociety.menu;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.ctis487.team9.cinemasociety.DatabaseHelper;
import com.ctis487.team9.cinemasociety.Event;
import com.ctis487.team9.cinemasociety.EventDB;
import com.ctis487.team9.cinemasociety.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.ctis487.team9.cinemasociety.menu.ui.main.SectionsPagerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    public static ArrayList<Event> eventsListRecycle;
    RecyclerView recyclerView;
    public DatabaseHelper dbHelper;
    public LinearLayoutManager layoutManager;
    RecyclerView recyclerViewEvents;
    ArrayList<Event> eventList;
    public static MediaPlayer ring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        recyclerViewEvents = findViewById(R.id.recyclerViewFragment);




        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);



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
        dbHelper = new DatabaseHelper(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewEvents.setLayoutManager(layoutManager);
        eventsListRecycle = EventDB.getAllEvents(dbHelper);


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
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("soundFile",R.raw.lacosta);
System.out.println("müzik");
            startService(intent);

        }
       // MyRecycleAdapter adapter = new MyRecycleAdapter(this,eventsListRecycle);
        //recyclerViewEvents.setAdapter(adapter);

    }

