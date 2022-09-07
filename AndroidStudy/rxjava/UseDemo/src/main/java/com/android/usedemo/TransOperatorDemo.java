package com.android.usedemo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.GroupedObservable;

public class TransOperatorDemo {
    //map,flatmap,concatmap
    public static void main(String[] args) {
        System.out.println("====================");
        TransOperatorDemo demo = new TransOperatorDemo();
        demo.test5();
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

    //map:对被观察者实例处理进行处理
    // 把原来发射的事件进行处理，并且产生新的事件，再发射
    void test1() {
        String Host = "http://**************";
        Observable.just(".cn").map(new Function<String, Object>() {
            @Override
            public Object apply(String s) throws Throwable {
                return Host + s;
            }
        }).subscribe(observer);

        Observable.just("aaa").map(s -> "ccc").subscribe(observer);
    }

    //flatmap:可以将事件的元素进行整合加工，返回一个新的被观察者着，有可能无序的，通常是有序的
    //concatmap则是有序的，功能基本一致
    //网络请求场景当中常用的操作符，实现嵌套调用
    void test2() {
        /*Observable.just("register").flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Throwable {
                System.out.println("register success");
                return Observable.just("apply login");
            }
        }).subscribe(observer);*/

        Observable.just("111", "222", "333", "444", "555").concatMap(new Function<Object, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Object o) throws Throwable {
                return Observable.just(o + "ssss");
            }
        }).cast(String.class).subscribe(observer);
    }

    void test3() {
        /*
        * ====================
        onSubscribe....
        onNext....[111, 222, 333]
        onNext....[444, 555, 666]
        onNext....[777, 888, 999]
        onNext....[000]
        onComplete....
        ====================
        */
        Observable.just("111", "222", "333", "444", "555", "666", "777", "888", "999", "000")
                .buffer(3)
                .subscribe(observer);

        Observable.just(1, 2, 3).flatMapIterable(new Function<Integer, Iterable<?>>() {
            @Override
            public Iterable<?> apply(Integer integer) throws Throwable {
                List<Integer> mList = new ArrayList<>();
                mList.add(integer + 2);
                return mList;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                System.out.println("flatMapIterable: " + o);
            }
        });
    }

    //compose

    void test5() {
        People p1 = new People("周杰伦", "A");
        People p2 = new People("张震岳", "SS");
        People p3 = new People("刘德华", "S");
        People p4 = new People("胡一天", "S");
        People p5 = new People("林志颖", "A");
        People p6 = new People("鲁迅儿", "SS");
        People p7 = new People("胡适儿", "S");
        People p8 = new People("李大钊", "A");


        Observable<GroupedObservable<String, People>> GroupedObservable =
                Observable.just(p1, p2, p3, p4, p5, p6, p7, p8).groupBy(new Function<People, String>() {
                    @Override
                    public String apply(People people) throws Throwable {
                        return people.id;
                    }
                });
        /*GroupedObservable.subscribe(new Consumer<io.reactivex.rxjava3.observables.GroupedObservable<String, People>>() {
            @Override
            public void accept(io.reactivex.rxjava3.observables.GroupedObservable<String, People> stringPeopleGroupedObservable) throws Throwable {
                stringPeopleGroupedObservable.subscribe(new Consumer<People>() {
                    @Override
                    public void accept(People people) throws Throwable {
                        System.out.println("group: " + stringPeopleGroupedObservable.getKey());
                    }
                });
            }
        });*/

        Observable.concat(GroupedObservable).subscribe(new Consumer<People>() {
            @Override
            public void accept(People people) throws Throwable {

                System.out.println("groupBy:" + people.id + "----" + people.name);
            }
        });
    }
}
