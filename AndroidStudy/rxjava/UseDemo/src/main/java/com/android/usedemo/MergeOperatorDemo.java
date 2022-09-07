package com.android.usedemo;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;

public class MergeOperatorDemo {
    public static void main(String[] args) {
        System.out.println("====================");
        MergeOperatorDemo demo = new MergeOperatorDemo();
        demo.test1();
        System.out.println("====================");
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

    void test1() {
        //将多个观察者对象组合在一起，按照之前的顺序发送，串行发送
//        Observable.concat(Observable.just("111"), Observable.just("222")).subscribe(observer);
        //merge与concat作用基本一样，merge并行发送事件

        //combineLatest和zip
        Observable<Integer> obs1 = Observable.just(1, 2, 3);
        Observable<String> obs2 = Observable.just("a", "b", "c");

        Observable.combineLatest(obs1, obs2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Throwable {
                return integer + s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println("combineLatest...." + s);
            }
        });
    }
}
