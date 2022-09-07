package com.android.dagger.model;

import javax.inject.Inject;

public class Swordsman {
    @Inject
    public Swordsman() {
    }

    public String fighting() {
        return "欲为大树，莫与草争";
    }
}
