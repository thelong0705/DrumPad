package com.example.thelong0705.drumpad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.example.thelong0705.drumpad.touch.Touch;
import com.example.thelong0705.drumpad.touch.TouchManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;


public class MainActivity extends AppCompatActivity {
    private List<ImageView> keyList;
    private List<PressedKeyInfo> pressedKeyInfos;

    public class PressedKeyInfo {
        private ImageView ivKey;
        private int pointerId;

        public ImageView getIvKey() {
            return ivKey;
        }

        public int getPointerId() {
            return pointerId;
        }

        public PressedKeyInfo(ImageView ivKey, int pointerId) {
            this.ivKey = ivKey;
            this.pointerId = pointerId;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keyList = new ArrayList<>();
        keyList.add((ImageView) findViewById(R.id.key_1));
        keyList.add((ImageView) findViewById(R.id.key_2));
        keyList.add((ImageView) findViewById(R.id.key_3));
        keyList.add((ImageView) findViewById(R.id.key_4));
        keyList.add((ImageView) findViewById(R.id.key_5));
        keyList.add((ImageView) findViewById(R.id.key_6));
        keyList.add((ImageView) findViewById(R.id.key_7));
        keyList.add((ImageView) findViewById(R.id.key_8));
        keyList.add((ImageView) findViewById(R.id.key_9));
        keyList.add((ImageView) findViewById(R.id.key_10));
        keyList.add((ImageView) findViewById(R.id.key_11));
        keyList.add((ImageView) findViewById(R.id.key_12));
        pressedKeyInfos = new ArrayList<>();
        SoundManager.loadSoundIntoList(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        List<Touch> touchList = TouchManager.toTouches(event);
        if (touchList.size() > 0) {
            Touch firstTouch = touchList.get(0);
            ImageView pressedKey = findPressedKey(firstTouch);
            if (firstTouch.getAction() == ACTION_DOWN || firstTouch.getAction() == ACTION_POINTER_DOWN) {
                if (pressedKey != null && !containsKeyInfoWith(pressedKey)) {
                    pressedKeyInfos.add(new PressedKeyInfo(pressedKey, firstTouch.getId()));
                    setPressed(pressedKey, true);
                    SoundManager.playSound(pressedKey.getTag().toString());
                }
            } else if (firstTouch.getAction() == ACTION_UP || firstTouch.getAction() == ACTION_POINTER_UP) {
                Iterator<PressedKeyInfo> iterator = pressedKeyInfos.iterator();
                while (iterator.hasNext()) {
                    PressedKeyInfo pressedKeyInfo = iterator.next();
                    if (pressedKeyInfo.getPointerId() == firstTouch.getId()) {
                        iterator.remove();
                    }
                }
                setPressed(pressedKey, false);
            } else if (firstTouch.getAction() == ACTION_MOVE) {
                for (Touch touch : touchList) {
                    pressedKey = findPressedKey(touch);
                    if (pressedKey != null && !containsKeyInfoWith(pressedKey)) {
                        pressedKeyInfos.add(new PressedKeyInfo(pressedKey, touch.getId()));
                        setPressed(pressedKey, true);
                        SoundManager.playSound(pressedKey.getTag().toString());

                    }
                    Iterator<PressedKeyInfo> iterator = pressedKeyInfos.iterator();
                    while (iterator.hasNext()) {
                        PressedKeyInfo pressedKeyInfo = iterator.next();
                        ImageView ivKey = pressedKeyInfo.getIvKey();
                        if (pressedKeyInfo.getPointerId() == touch.getId() &&
                                !touch.isInside(ivKey)) {
                            iterator.remove();
                            setPressed(ivKey, false);
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private boolean containsKeyInfoWith(ImageView imageView) {
        for (PressedKeyInfo pressedKeyInfo : pressedKeyInfos) {
            if (pressedKeyInfo.getIvKey() == imageView)
                return true;
        }
        return false;
    }

    private ImageView findPressedKey(Touch touch) {

        for (int i = 0; i < keyList.size(); i++) {
            if (touch.isInside(keyList.get(i))) {
                return keyList.get(i);
            }
        }

        return null;
    }

    private void setPressed(ImageView view, boolean isPressed) {
        if (isPressed) {
            if (keyList.contains(view)) {
                view.setImageResource(R.drawable.pressed);
            }

        } else {
            if (keyList.contains(view)) {
                view.setImageResource(R.drawable.button);
            }

        }
    }
}


