package com.java.effective.item33;

import jdk.jfr.AnnotationElement;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }
    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }

    static Annotation getAnnotation(AnnotationElement element, String annotationTypeName) {
        Class<?> annotationType = null; // 비한정적 타입 토큰

        try {
            annotationType = Class.forName(annotationTypeName);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }

        return element.getAnnotation(annotationType.asSubclass(Annotation.class));
    }
}
