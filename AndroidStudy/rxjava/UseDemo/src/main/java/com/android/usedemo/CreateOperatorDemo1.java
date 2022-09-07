package com.android.usedemo;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class CreateOperatorDemo1 {
    public static void main(String[] args) {
        System.out.println("=============");
        CreateOperatorDemo1 demo = new CreateOperatorDemo1();
//        demo.test1();
        demo.test5();
        System.out.println("=============");
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
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                //事件产生的地方
                emitter.onNext("北京");
                emitter.onNext("生活");
                emitter.onNext("精彩");
                //onError和onComplete互斥
//                emitter.onError(new Throwable("手动丢出异常"));
//                emitter.onComplete();

                //耗时操作
                //网络请求
                //异步操作都放在这里
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                System.out.println("accept..." + o);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                System.out.println("accept..." + throwable);
            }
        });
        Observable.just("北京", "生活", "精彩").subscribe(o -> System.out.println("accept..." + o), throwable -> System.out.println("accept..." + throwable));
    }

    void test2() {
        Observable.just("北京", "生活", "精彩").subscribe(observer);
    }

    void test3() {
        Observable.fromArray("北京", "生活", "精彩").subscribe(observer);
    }

    void test4() {
        /*ArrayList<String> list= new ArrayList<>();
        list.add("111");
        list.add("222");
        Observable.fromIterable(list).subscribe(observer);*/

        /*Observable.fromFuture(new Future<Object>() {
            @Override
            public boolean cancel(boolean b) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Object get() throws InterruptedException, ExecutionException {
                return "aaaa";
            }

            @Override
            public Object get(long l, @NotNull TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        }).subscribe(observer);*/

        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "1111";
            }
        };

        Observable.fromCallable((Callable<Object>) () -> "1111").subscribe(observer);
        Observable.fromCallable(() -> "1111").subscribe(observer);
        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "1111";
            }
        }).subscribe(observer);
    }

    void test5() {
        /*Observable.interval(3, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                System.out.println("interval" + aLong.intValue());
            }
        });*/

        /*Observable.range(0, 5).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("range: " + integer);
            }
        });*/

        Observable.range(0, 3).repeat(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                System.out.println("repeat: " + integer);
            }
        });
    }
}
