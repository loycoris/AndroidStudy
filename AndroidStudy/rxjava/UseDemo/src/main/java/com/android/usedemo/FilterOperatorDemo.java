package com.android.usedemo;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class FilterOperatorDemo {
    public static void main(String[] args) {
        System.out.println("====================");
        FilterOperatorDemo demo = new FilterOperatorDemo();
        demo.test1();
        System.out.println("====================");
    }

    private void test1() {
        /*Observable.range(1, 10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Throwable {
                        return integer < 5;//为true不会被过滤
                    }
                })
                .subscribe(observer);*/

        /*Observable.range(1, 5).elementAt(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("elementAt...." + integer);
            }
        });*/

        /*Observable.just(1,2,2,3,4,1,3).distinctUntilChanged().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("distinct...." + integer);
            }
        });*/

        /*Observable.just(1,2,3,4,5,6).skip(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("skip...." + integer);
            }
        });*/

        /*Observable.just(1,2,3,4,5,6).take(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("take...." + integer);
            }
        });*/

        /*Observable.just(1,2,3,4,5,6).skipLast(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("skip...." + integer);
            }
        });*/

        /*Observable.just(1,2,3,4,5,6).takeLast(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("take...." + integer);
            }
        });*/

        /*Observable.just(1,2,3,4,5,6).ignoreElements().subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete....");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });*/

        /*Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                    Thread.sleep(100);
                }
                emitter.onComplete();
            }
        }).throttleFirst(200, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                System.out.println("throttleFirst...." + o);
            }
        });*/

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                    int sleep = 100;
                    if (i % 3 == 0) {
                        sleep = 300;
                    }
                    Thread.sleep(sleep);
                }
                emitter.onComplete();
            }
        }).throttleWithTimeout(200,TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                System.out.println("throttleWithTimeout...." + o);
            }
        });
    }

    Observer observer = new Observer<Object>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            System.out.println("onSubscribe....");
        }

        @Override
        public void onNext(@NonNull Object o) {
            System.out.println("onNext...." + o);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            System.out.println("onError...." + e.toString());
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete....");
        }
    };
}
