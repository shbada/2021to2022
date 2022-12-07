package com.java.effective.item89;

//public class Elvis {
//    public static final Elvis INSTANCE = new Elvis();
//    private Elvis(){
//    }
//
//    public void leaveTheBuilding(){
//
//    }
//}
import java.io.Serializable;
import java.util.Arrays;

public class Elvis implements Serializable {

    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {}

    private String[] favoriteSongs = {"KIM", "LEE"};

    public void printFavorites() {
        System.out.println(Arrays.toString(favoriteSongs));
    }

    private Object readResolve() {
        return INSTANCE;
    }

}