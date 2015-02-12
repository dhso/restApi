/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.api.codepad.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.minws.api.codepad.entity.Tree;

public interface UiMapper {

	/**
	 * 通过pid查询树形菜单
	 * 
	 * @param pid
	 * @return
	 */
	List<Tree> selectMenuTreeByPid(@Param("pid") String pid);

	/**
	 * 查询树形菜单父类
	 * 
	 * @param pid
	 * @return
	 */
	List<Tree> selectMenuTreeP();

	/**
	 * 通过id更新树形菜单
	 * 
	 * @param id
	 * @param pid
	 * @param text
	 * @param open
	 * @param writer
	 */
	void updateMenuTreeById(@Param("id") String id, @Param("pid") String pid, @Param("text") String text, @Param("open") String open, @Param("writer") String writer);

	/**
	 * 插入树形菜单
	 * 
	 * @param pid
	 * @param state
	 * @param text
	 * @param open
	 * @param writer
	 */

	void insertMenuTree(@Param("pid") String pid, @Param("state") String state, @Param("text") String text, @Param("node_type") String node_type, @Param("open") String open, @Param("writer") String writer);

	/**
	 * 通过id删除树形菜单
	 * 
	 * @param id
	 */
	void deleteMenuTreeById(@Param("id") String id);

	/**
	 * 通过tid查询tab内容
	 * 
	 * @param tid
	 * @return
	 */
	String selectContentTabByTid(@Param("tid") String tid);

	/**
	 * 通过tid更新tab内容
	 * 
	 * @param tid
	 * @param html
	 */

	void updateContentTabByTid(@Param("tid") String tid, @Param("html") String html);

	/**
	 * 通过tid插入tab内容
	 * 
	 * @param tid
	 * @param html
	 */

	void insertContentTabByTid(@Param("tid") String tid, @Param("html") String html);

	/**
	 * 通过tid删除tab内容
	 * 
	 * @param tid
	 */
	void deleteContentTabByTid(@Param("tid") String tid);

	/**
	 * 通过tid查询tab内容是否存在
	 * 
	 * @param tid
	 * @return
	 */
	Integer checkContentTabExistByTid(@Param("tid") String tid);
	
	/**
	 * 通过search查询树形菜单
	 * 
	 * @param search
	 * @return
	 */
	List<Tree> selectMenuTreeBySearch(@Param("search") String search);
	
	/**
	 * 查询最新几个列表
	 * 
	 * @param num
	 * @return
	 */
	List<Tree> selectTopMenu(@Param("num") Integer num);

}