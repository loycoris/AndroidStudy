package com.android.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        test1();
//        test2();
        test4();
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
    }

    void test3(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception{
                for(int i = 0; i < 5; i++){
                    if(i == 2){
                        emitter.onError(new Throwable("Throwable"));
                    }
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                Log.e("MistakeActivity", "在onErrorReturn处理了: " + throwable.toString());
                return 6;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.i("MistakeActivity", "onNext:" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("MistakeActivity", "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i("MistakeActivity", "onComplete");
            }
        });
    }

    void test4(){
        /*Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                for(int i = 0; i < 5; i++){
                    if(i == 1){
                        emitter.onError(new Throwable("Throwable"));
                    }else{
                        emitter.onNext(i);
                    }
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .retry(2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d("MistakeActivity","onNext:" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("MistakeActivity", "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("MistakeActivity", "onComplete");
            }
        });*/

        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                for(int i = 0; i < 5; i++){
                    if(i == 1){
                        emitter.onError(new Throwable("Throwable"));
                    }else{
                        emitter.onNext(i);
                    }
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.newThread())
                .retry(new Predicate<Throwable>() {
                    @Override
                    public boolean test(Throwable throwable) throws Throwable {
                        return true;
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d("MistakeActivity","onNext:" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("MistakeActivity", "onError:" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("MistakeActivity", "onComplete");
            }
        });
    }

    void test1() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                //发送事件
                System.out.println("subscribe..." + Thread.currentThread());

                //模拟网络请求
//                Thread.sleep(2000);
                emitter.onNext("aaa");
                emitter.onNext("bbb");
                emitter.onComplete();
            }
        })
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Throwable {
                        System.out.println("doOnNext accept..." + Thread.currentThread());
                    }
                })
                .subscribeOn(Schedulers.newThread())//主要来决定执行subscribe方法(上述)所处的线程，也就是产生事件发射所在的线程
                .observeOn(AndroidSchedulers.mainThread())//来决定下游事件被处理时所处的线程
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Throwable {
                        System.out.println("map apply..." + Thread.currentThread());

                        return "ccc";
                    }
                    //只有上游subscribeOn才会生效
                }).observeOn(Schedulers.io())
//                .subscribeOn(Schedulers.io())
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

        /*try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
