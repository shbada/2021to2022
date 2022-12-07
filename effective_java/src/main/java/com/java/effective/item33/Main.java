package com.java.effective.item33;

public class Main {
    public static void main(String[] args) {
        Favorites f = new Favorites();

        f.putFavorite(String.class, "Java");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);

        // Java, cafebabe, class com.java.effective.item33.Favorites
        System.out.printf("%s, %x, %s%n", favoriteString, favoriteInteger, favoriteClass);
    }
}
