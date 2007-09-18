package com.yodoo.rent.webapp.action.admin;

import java.io.IOException;
import java.util.List;

import org.nestframework.action.FileItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.localization.ActionMessages;
import org.nestframework.validation.Validate;

import com.yodoo.rent.model.City;
import com.yodoo.rent.service.IAreaManager;
import com.yodoo.rent.service.ICityManager;
import com.yodoo.rent.webapp.action.BaseAction;

@Validate
public class AreaMgr extends BaseAction {

	@DefaultAction
	public Object show() {
		cityList = cityManager.findCityOfProvince(provinceId);
		return "/admin/area_import.jsp";
	}
	
	public Object importCSV(ActionMessages msg) {
		if (file.isUploaded()) {
			try {
				areaManager.importFromCSV(cityId, file.getInputStream());
			} catch (IOException e) {
				msg.add("file", "文件读取错误.", false);
			}
		}
		
		return show();
	}
	
	@Spring
	private IAreaManager areaManager;
	
	@Spring
	private ICityManager cityManager;
	
	@Validate(type="required", label="城市", labelFromResource=false, on="importCSV")
	private String cityId;
	
	private String provinceId;
	
	private FileItem file;
	
	private List<City> cityList;

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public FileItem getFile() {
		return file;
	}

	public void setFile(FileItem file) {
		this.file = file;
	}

	public List<City> getCityList() {
		return cityList;
	}
}
