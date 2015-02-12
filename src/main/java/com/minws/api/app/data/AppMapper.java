/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.api.app.data;

import org.apache.ibatis.annotations.Param;

import com.minws.api.app.entity.App;

public interface AppMapper {

	/**
	 * 查询app
	 * 
	 * @param appid
	 * @return
	 */
	App selectAppByAppId(@Param("appid") String appid);

}