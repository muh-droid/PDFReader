package com.muh.mypdf;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    PdfAdapter pdfAdapter;
    ArrayList<HashMap<String, Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        Search_Dir(Environment.getExternalStorageDirectory());
    }
    public void Search_Dir(File dir) {
        String pdfPattern = ".pdf";

        File FileList[] = dir.listFiles();

        if (FileList != null) {
            for (int i = 0; i < FileList.length; i++) {

                if (FileList[i].isDirectory()) {
                    Search_Dir(FileList[i]);
                } else {
                    if (FileList[i].getName().endsWith(pdfPattern)){

                        HashMap<String, Object> map = new HashMap<>();
                        map.put("name", FileList[i].getName());
                        map.put("file", FileList[i].getAbsoluteFile().toString());
                        list.add(map);
                    }
                }
            }
        }
        pdfAdapter = new PdfAdapter(MainActivity.this, list);
        recyclerView.setAdapter(pdfAdapter);
        progressBar.setVisibility(View.GONE);
    }
}