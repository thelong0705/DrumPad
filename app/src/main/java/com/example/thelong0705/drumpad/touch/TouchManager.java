package com.example.thelong0705.drumpad.touch;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import static android.view.MotionEvent.*;

/**
 * Created by Inpriron on 4/15/2017.
 */

public class TouchManager {
    public static List<Touch> toTouches(MotionEvent event) {
        int action = event.getActionMasked();
        ArrayList<Touch> touches = new ArrayList<>();

        if (action == ACTION_DOWN || action == ACTION_POINTER_DOWN
                || action == ACTION_UP || action == ACTION_POINTER_UP) {
            int pointerIndex = event.getActionIndex();
            float pointerX = event.getX(pointerIndex);
            float pointerY = event.getY(pointerIndex);
            int id = event.getPointerId(pointerIndex);
            touches.add(new Touch(pointerX, pointerY, id, action));
        }
        else if (action == ACTION_MOVE)
        {
            for(int pointerIndex=0;pointerIndex<event.getPointerCount();pointerIndex++)
            {
                float pointerX = event.getX(pointerIndex);
                float pointerY = event.getY(pointerIndex);
                int id = event.getPointerId(pointerIndex);
                touches.add(new Touch(pointerX, pointerY, id, action));
            }
        }
        return touches;
    }
}
