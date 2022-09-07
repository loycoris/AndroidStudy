package com.android.mvpdagger2.ipinfo

import com.android.mvpdagger2.net.NetTaskComponent
import com.android.mvpdagger2.util.FragmentScoped
import dagger.Component

@FragmentScoped
@Component(dependencies = [NetTaskComponent::class], modules = [IpInfoModule::class])
interface IpInfoComponent {
    fun inject(ipInfoActivity: IpInfoActivity?)
}
