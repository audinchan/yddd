/**
 * 
 */
package com.yodoo.rent.webapp.ajax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nestframework.commons.hibernate.IPage;
import org.nestframework.commons.utils.StringUtil;

import com.yodoo.rent.commons.PermissionDeniedException;
import com.yodoo.rent.model.HouseInfo;
import com.yodoo.rent.model.User;
import com.yodoo.rent.service.IHouseInfoManager;
import com.yodoo.rent.service.IUserManager;
import com.yodoo.rent.webapp.action.BaseAction;

/**
 * 出租信息发布Ajax工具类.
 * 
 * @author audin
 * 
 */
public class RentUtil {
	/**
	 * Logger for this class
	 */
	private static final Log log = LogFactory.getLog(RentUtil.class);

	private IHouseInfoManager houseInfoManager;
	
	private IUserManager userManager;

	public void setHouseInfoManager(IHouseInfoManager houseInfoManager) {
		if (log.isDebugEnabled()) {
			log.debug("setHouseInfoManager(IHouseInfoManager) - start");
		}

		this.houseInfoManager = houseInfoManager;

		if (log.isDebugEnabled()) {
			log.debug("setHouseInfoManager(IHouseInfoManager) - end");
		}
	}

	public void setUserManager(IUserManager userManager) {
		if (log.isDebugEnabled()) {
			log.debug("setUserManager(IUserManager) - start");
		}

		this.userManager = userManager;

		if (log.isDebugEnabled()) {
			log.debug("setUserManager(IUserManager) - end");
		}
	}

	/**
	 * 发布出租信息，并返回标识号。
	 * 
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @param address 详细地址
	 * @param rooms 居室数
	 * @param price 价格
	 * @param provider 联系人
	 * @param phone 电话
	 * @param email 邮件地址
	 * @return 标识号
	 */
	public String publishRent(float longitude, float latitude, String address,
			int rooms, int price, String provider, String phone, String email,
			HttpSession s) {
		if (log.isDebugEnabled()) {
			log
					.debug("publishRent(float, float, String, int, int, String, String, String) - start");
		}
		
		User loginUser = BaseAction.getLoginUser(s);
		if (loginUser == null) {
			throw new PermissionDeniedException();
		}

		HouseInfo h = new HouseInfo();
		
		h.setProvince(1);
		h.setCity(1);
		h.setArea(1);
		
		h.setLongitude(longitude);
		h.setLatitude(latitude);
		h.setAddressDetail(address);
		h.setRooms(rooms);
		h.setPrice(price);
		h.setProvider(provider);
		h.setPhone(phone);
		h.setEmail(email);
		h.setRentType(1);
		h.setPublishTime(new Date());
		
		h.setUser(userManager.get(loginUser.getUsername()));

		houseInfoManager.save(h);

		String returnString = h.getId();
		if (log.isDebugEnabled()) {
			log
					.debug("publishRent(float, float, String, int, int, String, String, String) - end");
		}
		return returnString;
	}
	
	public String saveHouse(HouseInfo h, HttpSession s) {
		if (log.isDebugEnabled()) {
			log.debug("saveHouse(HouseInfo, HttpSession) - start");
		}

		User loginUser = BaseAction.getLoginUser(s);
		if (loginUser == null) {
			throw new PermissionDeniedException();
		}
		
		h.setProvince(1);
		h.setCity(1);
		h.setArea(1);
		h.setRentType(1);
		h.setPublishTime(new Date());
		h.setUser(userManager.get(loginUser.getUsername()));
		houseInfoManager.save(h);
		
		String id = h.getId();
		

		if (log.isDebugEnabled()) {
			log.debug("saveHouse(HouseInfo, HttpSession) - end");
		}
		return id;
	}
	
	public HouseInfo saveHouseEdit(HouseInfo h, HttpSession s) {
		if (log.isDebugEnabled()) {
			log.debug("saveHouse(HouseInfo, HttpSession) - start");
		}

		User loginUser = BaseAction.getLoginUser(s);
		if (loginUser == null) {
			throw new PermissionDeniedException();
		}
		
//		if (StringUtil.isEmpty(h.getId())) {
//			throw new PermissionDeniedException();
//		}
		
		HouseInfo hi = h;
		
		if (StringUtil.isNotEmpty(h.getId())) { // 修改
			hi = houseInfoManager.get(h.getId());
			
			if (hi == null) {
				throw new PermissionDeniedException();
			}
			
			if (!hi.getUser().getUsername().equals(loginUser.getUsername())) {
				throw new PermissionDeniedException();
			}
			
			hi.setAddressDetail(h.getAddressDetail());
			hi.setRooms(h.getRooms());
			hi.setPrice(h.getPrice());
			hi.setProvider(h.getProvider());
			hi.setPhone(h.getPhone());
			hi.setEmail(h.getEmail());
		} else { // 添加
			hi.setProvince(1);
			hi.setCity(1);
			hi.setArea(1);
			hi.setRentType(1);
			hi.setUser(userManager.get(loginUser.getUsername()));
		}
		
		hi.setPublishTime(new Date());

		houseInfoManager.save(hi);
		
		if (log.isDebugEnabled()) {
			log.debug("saveHouse(HouseInfo, HttpSession) - end");
		}
		return hi;
	}
	
	public void deleteHouse(String id, HttpSession s) {
		if (log.isDebugEnabled()) {
			log.debug("delete(String, HttpSession) - start");
		}

		User loginUser = BaseAction.getLoginUser(s);
		if (loginUser == null) {
			throw new PermissionDeniedException();
		}
		HouseInfo hi = houseInfoManager.get(id);
		
		if (hi == null) {
			throw new PermissionDeniedException();
		}
		
		if (!hi.getUser().getUsername().equals(loginUser.getUsername())) {
			throw new PermissionDeniedException();
		}
		houseInfoManager.remove(hi);

		if (log.isDebugEnabled()) {
			log.debug("delete(String, HttpSession) - end");
		}
	}
	
	/**
	 * 修改某个发布信息的地理位置.
	 * 
	 * @param houseId 房屋标识号.
	 * @param longitude 经度.
	 * @param latitude 纬度.
	 */
	public void changePosition(String houseId, float longitude, float latitude, HttpSession s) {
		if (log.isDebugEnabled()) {
			log.debug("changePosition(String, float, float) - start");
		}

		HouseInfo h = houseInfoManager.get(houseId);
		
		User loginUser = BaseAction.getLoginUser(s);
		
		if (loginUser == null || !loginUser.getUsername().equals(h.getUser().getUsername())) {
			throw new PermissionDeniedException();
		}
		
		h.setLongitude(longitude);
		h.setLatitude(latitude);
		h.setPublishTime(new Date());
		
		houseInfoManager.save(h);

		if (log.isDebugEnabled()) {
			log.debug("changePosition(String, float, float) - end");
		}
	}
	
	public HouseInfo[] fetchMarkers(float minLgn, float maxLgn, float minLat, float maxLat) {
		if (log.isDebugEnabled()) {
			log.debug("fetchMarkers(float, float, float, float) - start");
		}

		HouseInfo[] returnHouseInfoArray = houseInfoManager.loadRangeInfos(
				minLgn, maxLgn, minLat, maxLat);
		if (log.isDebugEnabled()) {
			log.debug("fetchMarkers(float, float, float, float) - end");
		}
		return returnHouseInfoArray;
	}
	
	/**
	 * 获取服务器的当前时间（微秒）.
	 * @return
	 */
	public long getServerTime() {
		if (log.isDebugEnabled()) {
			log.debug("getServerTime() - start");
		}

		long returnlong = System.currentTimeMillis();
		if (log.isDebugEnabled()) {
			log.debug("getServerTime() - end");
		}
		return returnlong;
	}
	
	public IPage<HouseInfo> findHouses(final Map<String, Object> params, final int pageNo, final int pageSize) {
		if (log.isDebugEnabled()) {
			log.debug("findHouses(Map<String,Object>, int, int) - start");
		}

		IPage<HouseInfo> returnIPage = houseInfoManager.findHouses(params, pageNo, pageSize);
		if (log.isDebugEnabled()) {
			log.debug("findHouses(Map<String,Object>, int, int) - end");
		}
		return returnIPage;
	}
	
	public IPage<HouseInfo> findUserHouses(String keyword, final int pageNo, final int pageSize, HttpSession s) {
		if (log.isDebugEnabled()) {
			log.debug("findUserHouses(String, int, int, HttpSession) - start");
		}

		User user = BaseAction.getLoginUser(s);
		if (user == null) {
			throw new PermissionDeniedException();
		}
		IPage<HouseInfo> page = houseInfoManager.findUserHouses(user.getUsername(), keyword, pageNo, pageSize);

		if (log.isDebugEnabled()) {
			log.debug("findUserHouses(String, int, int, HttpSession) - end");
		}
		return page;
	}
}
