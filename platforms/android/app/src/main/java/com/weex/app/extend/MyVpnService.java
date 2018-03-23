package com.weex.app.extend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.VpnService;
import android.os.Build;
import android.os.ParcelFileDescriptor;

import java.io.IOException;

/**
 * 自定义Vpnservice服务
 *
 * Created by AlbertLi on 2018/3/14.
 */

public class MyVpnService extends VpnService {

    //从虚拟网卡拿到的文件描述符
    private ParcelFileDescriptor mInterface;

    //停止Vpn服务广播
    public static final String ACTION_SHUTDOWN = "vpn_shutdown";

    private StopVpnReceiver stopVpnReceiver;
    @Override
    public void onCreate() {
        super.onCreate();
        //创建接受停止Vpn服务的广播
        stopVpnReceiver = new StopVpnReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_SHUTDOWN);
        registerReceiver(stopVpnReceiver,intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initVpn();
        return super.onStartCommand(intent, flags, startId);
    }

    //初始化vpn服务
    public void initVpn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Builder builder = new Builder();
            builder.setMtu(1500);//MaximunTransmission Unit,网络端口的最大传输单元，如果数据包长度大于这个数字，则会分包
            builder.addAddress("10.1.10.1",32);//VPN转发的IP地址,虚拟网络端口的IP地址
            builder.addRoute("0.0.0.0",0);//匹配IP包，只有匹配上的才能被路由到虚拟端口上。0.0.0.0/0，则代表把所有的IP包都路由到虚拟端口
            builder.setSession("Our VpnService");//Vpn链接名字
            mInterface = builder.establish();
        }

    }

    @Override
    public void onDestroy() {
        if (stopVpnReceiver != null) {
            unregisterReceiver(stopVpnReceiver);
        }
        super.onDestroy();
    }

    /**
     * vpn停止广播
     */
    public class StopVpnReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && ACTION_SHUTDOWN.equals(intent.getAction())) {
                if (mInterface != null) {
                    try {
                        mInterface.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
