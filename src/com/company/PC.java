package com.company;

public class PC {
    private int id;
    private boolean estReserve;

    public PC(int _id) {
        this.estReserve = false;
        this.id = _id;
    }

    public void setEstReserve(boolean estReserve) {
        this.estReserve = estReserve;
    }
}
