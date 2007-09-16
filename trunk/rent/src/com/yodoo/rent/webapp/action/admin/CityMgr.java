/**
 * 
 */
package com.yodoo.rent.webapp.action.admin;

import java.io.IOException;

import org.nestframework.action.FileItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.localization.ActionMessages;

import com.yodoo.rent.service.ICityManager;

/**
 * @author audin
 *
 */
public class CityMgr {
	@DefaultAction
	public Object show() {
		return "/admin/city_import.jsp";
	}
	
	public Object importCSV(ActionMessages msg) {
		try {
			cityManager.importFormCSV(file.getInputStream());
		} catch (IOException e) {
			msg.add("file", "文件读取错误.", false);
		}
		
		return show();
	}
	
	@Spring
	private ICityManager cityManager;
	
	private FileItem file;

	public FileItem getFile() {
		return file;
	}

	public void setFile(FileItem file) {
		this.file = file;
	}
}
