/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.api.ehome.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.codehaus.jackson.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.minws.api.ehome.data.EhomeMapper;
import com.minws.api.ehome.entity.Data;
import com.minws.api.ehome.entity.Device;
import com.minws.api.ehome.entity.ListDataResult;
import com.minws.api.ehome.entity.ListDeviceResult;
import com.minws.api.framework.aspect.checkApp.CheckApp;
import com.minws.api.framework.exception.RestResult;
import com.minws.api.framework.util.IoUtil;
import com.minws.api.framework.util.mybatis.MybatisUtil;

@Controller
@Path("/ehome")
public class EhomeCtrl {

	private SqlSessionFactory ehomeSqlSessionFactory = MybatisUtil.connectDb("com/minws/api/ehome/data/Ehome-mybatis-config.xml", "public");

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/data/in/{deviceId}")
	@CheckApp(allowAppId = { "3" })
	public Response inData(@RequestBody Data data, @PathParam("deviceId") @DefaultValue("0") Integer deviceId) throws Exception {
		SqlSession ehomeSqlSession = ehomeSqlSessionFactory.openSession();
		try {
			EhomeMapper ehomeMapper = ehomeSqlSession.getMapper(EhomeMapper.class);
			if (ehomeMapper.existDevice(deviceId) < 1) {
				throw new Exception("device is not found!");
			}
			ehomeMapper.inData(data.getDevice_id(), data.getDevice_value(), data.getDevice_date());
			ehomeSqlSession.commit();
			return IoUtil.cors(new RestResult());
		} finally {
			ehomeSqlSession.close();
		}
	}

	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/data/out/{deviceId}/{limit}")
	@CheckApp(allowAppId = { "3" })
	public Response outData(@PathParam("deviceId") @DefaultValue("0") Integer deviceId, @PathParam("limit") @DefaultValue("1") Integer limit) throws JsonProcessingException, IOException {
		SqlSession ehomeSqlSession = ehomeSqlSessionFactory.openSession();
		ListDataResult result = new ListDataResult();
		try {
			EhomeMapper ehomeMapper = ehomeSqlSession.getMapper(EhomeMapper.class);
			List<Data> datas = ehomeMapper.outData(deviceId, limit);
			result.setData(datas);
			return IoUtil.cors(result);
		} finally {
			ehomeSqlSession.close();
		}
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/device/add")
	@CheckApp(allowAppId = { "3" })
	public Response addDevice(@RequestBody Device device) throws Exception {
		SqlSession ehomeSqlSession = ehomeSqlSessionFactory.openSession();
		try {
			EhomeMapper ehomeMapper = ehomeSqlSession.getMapper(EhomeMapper.class);
			if (ehomeMapper.countDeviceNum(device.getName(), device.getType(), device.getDee_bur_id()) > 0) {
				throw new Exception("device is already exists!");
			}
			ehomeMapper.addDevice(device.getName(), device.getType(), device.getUnit(), 1, device.getDee_bur_id());
			ehomeSqlSession.commit();
			return IoUtil.cors(new RestResult());
		} finally {
			ehomeSqlSession.close();
		}
	}

	@GET
	@Produces("application/json;charset=utf-8")
	@Path("/device/del/{deviceId}")
	@CheckApp(allowAppId = { "3" })
	public Response delDevice(@PathParam("deviceId") @DefaultValue("0") Integer deviceId) throws JsonProcessingException, IOException {
		SqlSession ehomeSqlSession = ehomeSqlSessionFactory.openSession();
		try {
			EhomeMapper ehomeMapper = ehomeSqlSession.getMapper(EhomeMapper.class);
			ehomeMapper.delDevice(deviceId);
			ehomeSqlSession.commit();
			return IoUtil.cors(new RestResult());
		} finally {
			ehomeSqlSession.close();
		}

	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/device/listall")
	@CheckApp(allowAppId = { "3" })
	public Response listAll(@RequestBody Device device) throws JsonProcessingException, IOException {
		SqlSession ehomeSqlSession = ehomeSqlSessionFactory.openSession();
		ListDeviceResult result = new ListDeviceResult();
		try {
			EhomeMapper ehomeMapper = ehomeSqlSession.getMapper(EhomeMapper.class);
			List<Device> devices = ehomeMapper.listAll(device.getDee_bur_id());
			result.setDevice(devices);
			return IoUtil.cors(result);
		} finally {
			ehomeSqlSession.close();
		}
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/device/listtype")
	@CheckApp(allowAppId = { "3" })
	public Response listType(@RequestBody Device device) throws JsonProcessingException, IOException {
		SqlSession ehomeSqlSession = ehomeSqlSessionFactory.openSession();
		List<String> result = new ArrayList<String>();
		try {
			EhomeMapper ehomeMapper = ehomeSqlSession.getMapper(EhomeMapper.class);
			result = ehomeMapper.listType(device.getDee_bur_id());
			return IoUtil.cors(result);
		} finally {
			ehomeSqlSession.close();
		}
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@Path("/device/listbytype/{detail}")
	@CheckApp(allowAppId = { "3" })
	public Response listByType(@PathParam("detail") @DefaultValue("0") Integer detail, @RequestBody Device device) throws JsonProcessingException, IOException {
		SqlSession ehomeSqlSession = ehomeSqlSessionFactory.openSession();
		try {
			EhomeMapper ehomeMapper = ehomeSqlSession.getMapper(EhomeMapper.class);
			List<?> result = null;
			if (detail == 1) {
				result = ehomeMapper.listByType1(device.getType(), device.getDee_bur_id());
			} else {
				result = ehomeMapper.listByType0(device.getType(), device.getDee_bur_id());
			}
			return IoUtil.cors(result);
		} finally {
			ehomeSqlSession.close();
		}
	}

}