package com.example.thelong0705.drumpad;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button btDo;
    private Button btRe;
    private Button btMi;
    private Button btFa;
    private Button btSon;
    private Button btLa;
    private Button btSi;
    final MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btDo=(Button) findViewById(R.id.btDo);
        btRe=(Button) findViewById(R.id.btRe);
        btMi=(Button) findViewById(R.id.btMi);
        btFa=(Button) findViewById(R.id.btFa);
        btSon=(Button) findViewById(R.id.btSon);
        btLa=(Button) findViewById(R.id.btLa);
        btSi=(Button) findViewById(R.id.btSi);
        btDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound("do.wav");
            }
        });
        btRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound("re.wav");
            }
        });
        btMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound("mi.wav");
            }
        });
        btFa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound("fa.wav");
            }
        });
        btSon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound("son.wav");
            }
        });
        btLa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound("la.wav");
            }
        });
        btSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playSound("si.wav");
            }
        });
    }

    private void playSound(String soundName)
    {
        if(mp.isPlaying())
        {
            mp.stop();
        }

        try {
            mp.reset();
            AssetFileDescriptor afd;
            afd = getAssets().openFd(soundName);
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
