package com.weex.app.extend;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import mobile.Mobile;

public class EasyssModule extends WXModule {
	@JSMethod (uiThread = false)
	public String printLog(String msg)  {
		String ret = Mobile.hello(msg);
		return  ret;
	}

}

