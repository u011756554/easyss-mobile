package com.weex.app.extend;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.weex.app.WXPageActivity;

import mobile.Mobile;

public class EasyssModule extends WXModule {
//	@JSMethod (uiThread = false)
//	public String start(String msg)  {
//		String ret = Mobile.hello(msg);
//		return  ret;
//	}

    /**
     * 开启vpn链接
     * 注意：vpn发起请求，必须在WXPageActivity生命周期内调用。
     */
    @JSMethod (uiThread = false)
    public void start() {
        if (WXPageActivity.instance != null) {
            WXPageActivity.instance.startVpnService();
        }
    }

    @JSMethod (uiThread = false)
    public void stop() {
        if (WXPageActivity.instance != null) {
            WXPageActivity.instance.stopVpnService();
        }
    }
}

