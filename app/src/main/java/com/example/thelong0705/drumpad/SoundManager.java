package com.example.thelong0705.drumpad;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Inpriron on 4/11/2017.
 */

public class SoundManager {
    private static int NUMBER_OF_NOTES=12;
    public static SoundPool soundPool= new SoundPool(NUMBER_OF_NOTES, AudioManager.STREAM_MUSIC,0);
    public static ArrayList<Integer> soundList= new ArrayList<>();
    public static void loadSoundIntoList(Context context)
    {
        for(int i=1;i<=NUMBER_OF_NOTES;i++)
        {
            int resIDSound= context.getResources().getIdentifier("drum_pad_"+i,"raw",context.getPackageName());
            int soundPoolID=soundPool.load(context,resIDSound,1);
            soundList.add(soundPoolID);
        }
    }
    static HashMap<String,Integer> listSoundID= new HashMap<>();
    static {
        listSoundID.put("1",0);
        listSoundID.put("2",1);
        listSoundID.put("3",2);
        listSoundID.put("4",3);
        listSoundID.put("5",4);
        listSoundID.put("6",5);
        listSoundID.put("7",6);
        listSoundID.put("8",7);
        listSoundID.put("9",8);
        listSoundID.put("10",9);
        listSoundID.put("11",10);
        listSoundID.put("12",11);

    }
    public static void playSound(String string)
    {
        soundPool.play(soundList.get(listSoundID.get(string)),1,1,1,0,1);

    }
}
