package com.android.usedemo;

//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ToolOperatorDemo {
    public static void main(String[] args) {
        System.out.println("====================");
        ToolOperatorDemo demo = new ToolOperatorDemo();
        demo.test2();
        System.out.println("====================");
    }

    void test1() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                //发送事件
                System.out.println("subscribe..." + Thread.currentThread());

                //模拟网络请求
                Thread.sleep(2000);
                emitter.onNext("aaa");
                emitter.onNext("bbb");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())//主要来决定执行subscribe方法(上述)所处的线程，也就是产生事件发射所在的线程
//                .observeOn(AndroidSchedulers.mainThread())//来决定下游事件被处理时所处的线程
                /*.map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Throwable {
                        return "ccc";
                    }
                    //只有上游subscribeOn才会生效
                }).subscribeOn(Schedulers.io())*/
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("onSubscribe..." + Thread.currentThread());
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        System.out.println("onNext..." + Thread.currentThread());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("onError..." + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete..." + Thread.currentThread());
                    }
                });

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void test2() {
        Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Long> emitter) throws Throwable {
                Long currentTime = System.currentTimeMillis() / 1000;
                emitter.onNext(currentTime);
            }
        }).delay(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long o) throws Throwable {
                System.out.println("delay..." + (System.currentTimeMillis() / 1000 - o));
            }
        });

        Observable.just(1,2)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        System.out.println("doOnNext..." + integer);
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe...");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("onNext..." + integer);
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
