package com.weex.app.extend;

import android.content.Intent;
import android.net.VpnService;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/3/14.
 */

public abstract class VpnUIActivity extends AppCompatActivity {

    private static final int REQUESTCODE_VPN = 0x123;
    /**
     * 开启VPNservice服务
     */
    protected void startVpnService() {
        Intent intent = VpnService.prepare(this);
        if (intent != null) {
            startActivityForResult(intent,REQUESTCODE_VPN);
        } else {
            onActivityResult(REQUESTCODE_VPN,RESULT_OK,null);
        }
    }

    /**
     * 停止VPNsevice服务
     */
    protected void stopVpnService() {
        Intent intent = new Intent(MyVpnService.ACTION_SHUTDOWN);
        sendBroadcast(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //开启VPN服务
        if (resultCode == RESULT_OK && requestCode == REQUESTCODE_VPN) {
            Intent intent = new Intent(this,MyVpnService.class);
            startService(intent);
        }
    }
}
