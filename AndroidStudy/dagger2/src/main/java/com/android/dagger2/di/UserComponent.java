package com.android.dagger2.di;

import com.android.dagger2.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@UserScope
@Component(modules = UserModule.class, dependencies = ApplicationComponent.class)

/*
 * 无@Scope的@Component不能依赖有@Scope的@Component，
 * 有@Scope的@Component可以依赖无@Scope的@Component，
 * 有@Scope的@Component可以依赖有@Scope的@Component，并且两者的@Scope不能相同。
 * @Singleton的@Component不能依赖有@Scope的@Component(包括@Singleton)
 * */
public interface UserComponent {
    void inject(MainActivity mainActivity);
}
