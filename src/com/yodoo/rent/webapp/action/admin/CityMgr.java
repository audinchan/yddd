/**
 * 
 */
package com.yodoo.rent.webapp.action.admin;

import java.io.IOException;
import java.util.List;

import org.nestframework.action.FileItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.localization.ActionMessages;

import com.yodoo.rent.model.Province;
import com.yodoo.rent.service.ICityManager;
import com.yodoo.rent.service.IProvinceManager;

/**
 * @author audin
 *
 */
public class CityMgr {
	@DefaultAction
	public Object show() {
		provinceList = provinceManager.findAll();
		provinceList.add(0, new Province());
		return "/admin/city_import.jsp";
	}
	
	public Object importCSV(ActionMessages msg) {
		try {
			cityManager.importFormCSV(provinceId, file.getInputStream());
		} catch (IOException e) {
			msg.add("file", "文件读取错误.", false);
		}
		
		return show();
	}
	
	@Spring
	private ICityManager cityManager;
	
	@Spring
	private IProvinceManager provinceManager;
	
	private String provinceId;
	
	private FileItem file;
	
	private List<Province> provinceList;

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

	public List<Province> getProvinceList() {
		return provinceList;
	}
}
