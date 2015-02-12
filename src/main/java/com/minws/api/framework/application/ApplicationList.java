/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.framework.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.minws.api.codepad.ctrl.CodepadCtrl;
import com.minws.api.ehome.ctrl.EhomeCtrl;
import com.minws.api.user.ctrl.UserCtrl;
import com.minws.api.weixin.ctrl.WeixinCtrl;

public class ApplicationList extends Application {

	HashSet<Object> singletons = new HashSet<Object>();
	HashSet<Class<?>> set = new HashSet<Class<?>>();

	public ApplicationList() {
		// singletons.add(new Library());// 单件模式，客户端公用一个线程，一个服务实例
		set.add(UserCtrl.class);// 每个请求出于独立的线程中，有独立的实例存在。
		set.add(CodepadCtrl.class);
		set.add(WeixinCtrl.class);
		set.add(EhomeCtrl.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return set;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}