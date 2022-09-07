package com.android.annotations;

//注解定义 @interface
public @interface Swordsman {
    String name() default "";

    int age() default 1;
}