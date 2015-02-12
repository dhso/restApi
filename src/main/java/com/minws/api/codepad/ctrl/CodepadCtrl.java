/**
 * 
 */
/**
 * @author hadong
 *
 */
package com.minws.api.codepad.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.minws.api.codepad.data.UiMapper;
import com.minws.api.codepad.entity.Tree;
import com.minws.api.framework.exception.RestResult;
import com.minws.api.framework.util.IoUtil;
import com.minws.api.framework.util.mybatis.MybatisUtil;

@Path("/codepad")
public class CodepadCtrl {

	private SqlSessionFactory codepadSqlSessionFactory = MybatisUtil.connectDb("com/minws/api/codepad/data/Codepad-mybatis-config.xml", "public");

	@GET
	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/ui/menu_tree/select_menu")
	public Response selectMenuTreeByPid(@QueryParam("id") @DefaultValue("0") String qid, @FormParam("id") @DefaultValue("0") String fid) throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		List<Tree> result = new ArrayList<Tree>();
		String id = "0";
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			if (!"0".equals(qid)) {
				id = qid;
			} else if (!"0".equals(fid)) {
				id = fid;
			}
			List<Tree> trees = uiMapper.selectMenuTreeByPid(id);
			result.addAll(trees);
		} finally {
			codepadSqlSession.close();
		}
		return IoUtil.cors(result);
	}

	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/ui/menu_tree/select_menu_p")
	public Response selectMenuTreeP() throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		List<Tree> result = new ArrayList<Tree>();
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			List<Tree> trees = uiMapper.selectMenuTreeP();
			result.addAll(trees);
		} finally {
			codepadSqlSession.close();
		}
		return IoUtil.cors(result);
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/ui/menu_tree/update_menu")
	public Response updateMenuTreeById(@Context HttpServletRequest request, @QueryParam("id") @DefaultValue("-1") String id) throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		JsonNode rootNode = new ObjectMapper().readTree(request.getInputStream()); // 读取Json
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			uiMapper.updateMenuTreeById(id, rootNode.path("pid").asText(), rootNode.path("text").asText(), rootNode.path("open").asText(), rootNode.path("writer").asText());
			codepadSqlSession.commit();
		} finally {
			codepadSqlSession.close();
		}
		return IoUtil.cors(new RestResult());
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/ui/menu_tree/add_menu")
	public Response addMenuTreeByPid(@Context HttpServletRequest request, @QueryParam("pid") @DefaultValue("0") String pid) throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		JsonNode rootNode = new ObjectMapper().readTree(request.getInputStream()); // 读取Json
		String node_type = "0";
		if ("closed".equals(rootNode.path("state").asText())) {
			node_type = "1";
		}
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			uiMapper.insertMenuTree(pid, rootNode.path("state").asText(), rootNode.path("text").asText(), node_type, rootNode.path("open").asText(), rootNode.path("writer").asText());
			codepadSqlSession.commit();
		} finally {
			codepadSqlSession.close();
		}
		return IoUtil.cors(new RestResult());
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/ui/menu_tree/del_menu")
	public Response delMenuTreeById(@Context HttpServletRequest request, @QueryParam("id") @DefaultValue("-1") String id) throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		// JsonNode rootNode = new
		// ObjectMapper().readTree(request.getInputStream()); // 读取Json
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			uiMapper.deleteMenuTreeById(id);
			uiMapper.deleteContentTabByTid(id);
			codepadSqlSession.commit();
		} finally {
			codepadSqlSession.close();
		}
		return IoUtil.cors(new RestResult());
	}

	@GET
	@Produces("text/html;charset=utf-8")
	@Path("/ui/content_tab/select_content")
	public Response selectContentTabByTid(@QueryParam("tid") @DefaultValue("0") String tid) throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		String result = null;
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			result = uiMapper.selectContentTabByTid(tid);
		} finally {
			codepadSqlSession.close();
		}
		return IoUtil.cors(result);
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/ui/content_tab/update_content")
	public Response updateContentTabByTid(@Context HttpServletRequest request, @QueryParam("tid") @DefaultValue("-1") String tid) throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		String html = IoUtil.inputStream2String(request.getInputStream());
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			if (uiMapper.checkContentTabExistByTid(tid) > 0) {
				uiMapper.updateContentTabByTid(tid, html);
			} else {
				uiMapper.insertContentTabByTid(tid, html);
			}
			codepadSqlSession.commit();
		} finally {
			codepadSqlSession.close();
		}

		return IoUtil.cors(new RestResult());
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/ui/content_tab/del_content")
	public Response delContentTabByTid(@Context HttpServletRequest request, @QueryParam("tid") @DefaultValue("-1") String tid) throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			uiMapper.deleteContentTabByTid(tid);
			codepadSqlSession.commit();
		} finally {
			codepadSqlSession.close();
		}
		return IoUtil.cors(new RestResult());
	}

	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/ui/menu_tree/select_menu_by_search")
	public Response selectMenuTreeBySearch(@QueryParam("search") @DefaultValue("") String search) throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		List<Tree> result = new ArrayList<Tree>();
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			List<Tree> trees = uiMapper.selectMenuTreeBySearch(search);
			result.addAll(trees);
		} finally {
			codepadSqlSession.close();
		}
		return IoUtil.cors(result);
	}
	
	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/ui/menu_list/select_top_menu")
	public Response selectTopMenu(@QueryParam("num") @DefaultValue("5") Integer num) throws Exception {
		SqlSession codepadSqlSession = codepadSqlSessionFactory.openSession();
		List<Tree> result = new ArrayList<Tree>();
		try {
			UiMapper uiMapper = codepadSqlSession.getMapper(UiMapper.class);
			List<Tree> trees = uiMapper.selectTopMenu(num);
			result.addAll(trees);
		} finally {
			codepadSqlSession.close();
		}
		return IoUtil.cors(result);
	}
}
