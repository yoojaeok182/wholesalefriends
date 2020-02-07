package com.wholesale.wholesalefriends.module.model;

import java.io.Serializable;

public class LockTimeModel implements Serializable {
    private boolean isLockTime = false;
    private int StartHour;
    private int StartMin;
    private int EndHour;
    private int EndMin;


    public boolean isLockTime() {
        return isLockTime;
    }

    public void setLockTime(boolean lockTime) {
        isLockTime = lockTime;
    }

    public int getStartHour() {
        return StartHour;
    }

    public void setStartHour(int startHour) {
        StartHour = startHour;
    }

    public int getStartMin() {
        return StartMin;
    }

    public void setStartMin(int startMin) {
        StartMin = startMin;
    }

    public int getEndHour() {
        return EndHour;
    }

    public void setEndHour(int endHour) {
        EndHour = endHour;
    }

    public int getEndMin() {
        return EndMin;
    }

    public void setEndMin(int endMin) {
        EndMin = endMin;
    }
}
