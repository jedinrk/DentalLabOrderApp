package com.example.naveen.dentalorder.model;

/**
 * Created by Naveen on 10/30/2015.
 */
public class Pojo {

    public String typeofwork;
    public boolean selected ;

    public Pojo() {
        typeofwork = "";
        selected = false;
    }

    public Pojo(Pojo obj) {
        this.typeofwork = obj.typeofwork;
        this.selected = obj.selected;
    }
}
