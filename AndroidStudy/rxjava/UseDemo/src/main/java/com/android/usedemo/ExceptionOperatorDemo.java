package com.android.usedemo;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;

public class ExceptionOperatorDemo {
    public static void main(String[] args) {
        System.out.println("====================");
        ExceptionOperatorDemo demo = new ExceptionOperatorDemo();
        demo.test1();
        System.out.println("====================");
    }

    private void test1() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                for (int i = 0; i < 5; i++) {
                    if (i > 2) {
                        emitter.onError(new Throwable("Throwable"));
                    }
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Throwable {
                return 6;
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe...");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("onNext..." + o);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("onError...");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete...");
            }
        });
    }
}
