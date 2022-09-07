package com.android.apt;

import com.android.annotation.Provider;

public class User_Factory implements Provider<User> {
    @Override
    public User get() {
        return new User();
    }
}
