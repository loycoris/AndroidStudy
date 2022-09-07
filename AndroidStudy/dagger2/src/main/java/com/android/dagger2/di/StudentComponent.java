package com.android.dagger2.di;


import com.android.dagger2.SecondActivity;

import dagger.Subcomponent;

//子组件
@Subcomponent(modules = StudentModule.class)
public interface StudentComponent {
    @Subcomponent.Factory
    interface Factory {
        StudentComponent create();
    }

    void inject(SecondActivity secondActivity);
}
