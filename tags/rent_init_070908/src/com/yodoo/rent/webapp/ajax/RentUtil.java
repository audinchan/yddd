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

import com.yodoo.rent.commons.PermissionDeniedException;
import com.yodoo.rent.model.HouseInfo;
import com.yodoo.rent.model.User;
import com.yodoo.rent.service.IHouseInfoManager;
import com.yodoo.rent.service.IUserManager;
import com.yodoo.rent.webapp.action.BaseAction;

/**
 * ������Ϣ����Ajax������.
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
	 * ����������Ϣ�������ر�ʶ�š�
	 * 
	 * @param longitude ����
	 * @param latitude γ��
	 * @param address ��ϸ��ַ
	 * @param rooms ������
	 * @param price �۸�
	 * @param provider ��ϵ��
	 * @param phone �绰
	 * @param email �ʼ���ַ
	 * @return ��ʶ��
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
	
	/**
	 * �޸�ĳ��������Ϣ�ĵ���λ��.
	 * 
	 * @param houseId ���ݱ�ʶ��.
	 * @param longitude ����.
	 * @param latitude γ��.
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
	 * ��ȡ�������ĵ�ǰʱ�䣨΢�룩.
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
		IPage<HouseInfo> page = houseInfoManager.findUserHouses(user.getUsername(), keyword, pageNo, pageSize);

		if (log.isDebugEnabled()) {
			log.debug("findUserHouses(String, int, int, HttpSession) - end");
		}
		return page;
	}
}