package com.android.usedemo;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

public class ConvertOperatorDemo {
    public static void main(String[] args) {
        System.out.println("=============");
        ConvertOperatorDemo demo = new ConvertOperatorDemo();
        demo.test1();
        System.out.println("=============");
    }

    private void test1() {
        Observable.just(1, 2, 3).toList().subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Throwable {
                for (int i : integers) {
                    System.out.println("toList: " + i);
                }
            }
        });

        Observable.just(3, 1, 2).toSortedList().subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Throwable {
                for (int i : integers) {
                    System.out.println("toSortedList: " + i);
                }
            }
        });

        People p1 = new People("小A", "A");
        People p2 = new People("小B", "B");
        People p3 = new People("小C", "C");
        Observable.just(p1, p2, p3).toMap(new Function<People, String>() {

            @Override
            public String apply(People people) throws Throwable {
                return people.id;
            }
        }).subscribe(new Consumer<Map<String, People>>() {
            @Override
            public void accept(Map<String, People> stringPeopleMap) throws Throwable {
                System.out.println("toMap:" + stringPeopleMap.get("B").name);
            }
        });
    }
}
