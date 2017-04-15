package com.example.thelong0705.drumpad.touch;

import android.view.View;

/**
 * Created by Inpriron on 4/15/2017.
 */

public class Touch {
    private float x;
    private float y;
    private int id;
    private int action;

    public boolean isInside(View v)
    {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + v.getWidth();
        int bottom = top + v.getHeight();
        if (x > left && x < right
                && y > top && y < bottom) {
            return true;
        }
        return false;
    }





    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Touch(float x, float y, int id, int action) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.action = action;
    }

    @Override
    public String toString() {
        return "Touch{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                ", action=" + action +
                '}';
    }
}
