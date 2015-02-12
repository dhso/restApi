/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.api.ehome.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.minws.api.ehome.entity.Data;
import com.minws.api.ehome.entity.Device;
import com.minws.api.ehome.entity.DeviceData;

public interface EhomeMapper {

	/**
	 * 添加设备
	 * 
	 * @param name
	 * @param type
	 * @param unit
	 * @param status
	 * @param dee_bur_id
	 */
	void addDevice(@Param("name") String name, @Param("type") String type, @Param("unit") String unit, @Param("status") Integer status, @Param("dee_bur_id") Integer dee_bur_id);

	/**
	 * 根据name,type,dee_bur_id查出设备数量
	 * 
	 * @param name
	 * @param type
	 * @param dee_bur_id
	 */
	Integer countDeviceNum(@Param("name") String name, @Param("type") String type, @Param("type") Integer dee_bur_id);

	/**
	 * 是否存在设备
	 * 
	 * @param id
	 */
	Integer existDevice(@Param("id") Integer id);

	/**
	 * 删除设备
	 * 
	 * @param id
	 */
	void delDevice(@Param("id") Integer id);

	/**
	 * 列出所有设备
	 * 
	 * @param dee_bur_id
	 */
	List<Device> listAll(@Param("dee_bur_id") Integer dee_bur_id);

	/**
	 * 列出所有设备类型
	 * 
	 * @param dee_bur_id
	 */
	List<String> listType(@Param("dee_bur_id") Integer dee_bur_id);

	/**
	 * 根据type,dee_bur_id列出所有设备
	 * 
	 * @param type
	 * @param dee_bur_id
	 */
	List<Device> listByType0(@Param("type") String type, @Param("dee_bur_id") Integer dee_bur_id);

	/**
	 * 根据type,dee_bur_id列出所有设备
	 * 
	 * @param type
	 * @param dee_bur_id
	 */
	List<DeviceData> listByType1(@Param("type") String type, @Param("dee_bur_id") Integer dee_bur_id);

	/**
	 * 信号输入
	 * 
	 * @param device_id
	 * @param device_value
	 * @param device_date
	 */

	void inData(@Param("device_id") Integer device_id, @Param("device_value") String device_value, @Param("device_date") Long device_date);

	/**
	 * 信号输出
	 * 
	 * @param device_id
	 * @param device_value
	 * @param device_date
	 * @return
	 */
	List<Data> outData(@Param("device_id") Integer device_id, @Param("limit") Integer limit);

}