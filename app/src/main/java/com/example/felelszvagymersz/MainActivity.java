package com.example.felelszvagymersz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    int szam=0;
    Vector<String>felelet=new Vector();
    Vector<String>meresek=new Vector();
    boolean fel=false;
    boolean mer=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button felelek= findViewById(R.id.felelek);
        Button merek= findViewById(R.id.merek);
        Button generator= findViewById(R.id.generator);
        TextView text_output=(TextView) findViewById(R.id.text_output);
        generator.setEnabled(false);
        try
        {
            InputStream is=getAssets().open("felel.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String sor;
            while ((sor = br.readLine()) != null)
            {
                felelet.add(sor);
            }
            br.close();
        }
        catch(Exception er){System.out.println("Error:" + er);er.printStackTrace();}
        try
        {
            InputStream is2=getAssets().open("mer.txt");
            BufferedReader br2=new BufferedReader(new InputStreamReader(is2));
            String sor;
            while ((sor = br2.readLine()) != null)
            {
                meresek.add(sor);
            }

            br2.close();
        }
        catch (Exception er){System.out.println("Error:" + er);er.printStackTrace();}
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "SIKERES BEOLVASÁS!", Toast.LENGTH_SHORT);
        toast.show();
        felelek.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fel=true;
                mer=false;
                felelek.setEnabled(false);
                merek.setEnabled(true);
                generator.setEnabled(true);
                text_output.setText("");
            }
        });
        merek.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mer=true;
                fel=false;
                merek.setEnabled(false);
                felelek.setEnabled(true);
                generator.setEnabled(true);
                text_output.setText("");
            }
        });
        generator.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (fel==true)
                {
                    szam=(int)(Math.random()*felelet.size());
                    text_output.setText(felelet.get(szam));
                }
                else if(mer==true)
                {
                    szam=(int)(Math.random()*meresek.size());
                    text_output.setText(meresek.get(szam));
                }
            }
        });
    }
}