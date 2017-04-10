package com.example.thelong0705.drumpad;

import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
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

    private List<ImageView> ivKeys;
    private List<PressedKeyInfo> pressedKeyInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivKeys = new ArrayList<>();
        ivKeys.add((ImageView) findViewById(R.id.key_1));
        ivKeys.add((ImageView) findViewById(R.id.key_2));
        ivKeys.add((ImageView) findViewById(R.id.key_3));
        ivKeys.add((ImageView) findViewById(R.id.key_4));
        ivKeys.add((ImageView) findViewById(R.id.key_5));
        ivKeys.add((ImageView) findViewById(R.id.key_6));
        ivKeys.add((ImageView) findViewById(R.id.key_7));
        ivKeys.add((ImageView) findViewById(R.id.key_8));
        ivKeys.add((ImageView) findViewById(R.id.key_9));
        ivKeys.add((ImageView) findViewById(R.id.key_10));
        ivKeys.add((ImageView) findViewById(R.id.key_11));
        ivKeys.add((ImageView) findViewById(R.id.key_12));
        pressedKeyInfos = new ArrayList<>();

    }

    public boolean onTouchEvent(MotionEvent event) {

        int pointerIndex = MotionEventCompat.getActionIndex(event);
        int pointerId = event.getPointerId(pointerIndex);
        float pointerX = event.getX(pointerIndex);
        float pointerY = event.getY(pointerIndex);
        int pointerAction = event.getActionMasked();
        ImageView pressedKey = findPressedKey(pointerX, pointerY);
        if (pointerAction == MotionEvent.ACTION_MOVE) {
            for (int i = 0; i < pressedKeyInfos.size(); i++) {
                PressedKeyInfo pressedKeyInfo = pressedKeyInfos.get(i);
                if (pressedKeyInfo.getPointerId() == pointerId
                        && !isInside(pointerX, pointerY, pressedKeyInfo.getIvKey())) {
                    pressedKeyInfos.remove(i);
                    setPressed(pressedKeyInfo.getIvKey(), false);
                }
            }
        }
        if (pressedKey != null) {
            if (pointerAction == MotionEvent.ACTION_DOWN
                    || pointerAction == MotionEvent.ACTION_POINTER_DOWN || pointerAction == MotionEvent.ACTION_MOVE) {
                if (!containsKeyInfoWith(pressedKey)) {
                    pressedKeyInfos.add(new PressedKeyInfo(pressedKey, pointerId));

                }
                setPressed(pressedKey, true);
            }
            if (pointerAction == MotionEvent.ACTION_POINTER_UP || pointerAction == MotionEvent.ACTION_UP) {
                for (int i = 0; i < pressedKeyInfos.size(); i++) {
                    PressedKeyInfo pressedKeyInfo = pressedKeyInfos.get(i);
                    if (pressedKeyInfo.getPointerId() == pointerId)
                        pressedKeyInfos.remove(i);
                    setPressed(pressedKey, false);
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private boolean isInside(float pointerX, float pointerY, View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + v.getWidth();
        int bottom = top + v.getHeight();
        return pointerX > left && pointerX < right
                && pointerY > top && pointerY < bottom;
    }

    private void setPressed(ImageView view, boolean isPressed) {
        if (isPressed) {
            if (ivKeys.contains(view)) {
                view.setBackgroundColor(Color.GREEN);
            }
        } else {
            view.setBackgroundColor(Color.BLUE);
        }
    }

    private ImageView findPressedKey(float pointerX, float pointerY) {

        for (int i = 0; i < ivKeys.size(); i++) {
            if (isInside(pointerX, pointerY, ivKeys.get(i))) {
                return ivKeys.get(i);
            }
        }

        return null;
    }

    private boolean containsKeyInfoWith(ImageView imageView) {
        for (PressedKeyInfo pressedKeyInfo : pressedKeyInfos) {
            if (pressedKeyInfo.getIvKey() == imageView)
                return true;
        }
        return false;
    }

}


