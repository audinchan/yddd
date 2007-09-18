/**
 * 
 */
package com.yodoo.rent.webapp.action.admin;

import java.io.IOException;

import org.nestframework.action.FileItem;
import org.nestframework.addons.spring.Spring;
import org.nestframework.annotation.DefaultAction;
import org.nestframework.localization.ActionMessages;

import com.yodoo.rent.service.IProvinceManager;

/**
 * @author audin
 *
 */
public class ProvinceMgr {
	@DefaultAction
	public Object show() {
		return "/admin/province_import.jsp";
	}
	
	public Object importCSV(ActionMessages msg) {
		try {
			provinceManager.importFormCSV(file.getInputStream());
		} catch (IOException e) {
			msg.add("file", "文件读取错误.", false);
		}
		
		return show();
	}
	
	@Spring
	private IProvinceManager provinceManager;
	
	private FileItem file;

	public FileItem getFile() {
		return file;
	}

	public void setFile(FileItem file) {
		this.file = file;
	}
}
