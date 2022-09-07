package com.android.usedemo;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class BooleanOperatorDemo {
    public static void main(String[] args) {
        System.out.println("=============");
        BooleanOperatorDemo demo = new BooleanOperatorDemo();
        demo.test1();
        System.out.println("=============");
    }

    private void test1() {
        Observable.just(1, 2, 3).all(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Throwable {
                System.out.println("all test..." + integer);
                return integer < 2;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                System.out.println("all accept..." + aBoolean);
            }
        });

        Observable.just(1, 2, 3).contains(1).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                System.out.println("contains accept..." + aBoolean);
            }
        });

        Observable.just(1, 2, 3).isEmpty().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                System.out.println("isEmpty accept..." + aBoolean);
            }
        });

        Observable.ambArray(Observable.just(1,2,3).delay(2, TimeUnit.SECONDS),Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println("amb accept..." + integer);
                    }
                });

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onComplete();
            }
        }).defaultIfEmpty(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("defaultIfEmpty accept..." + integer);
            }
        });
    }
}
