/**
 * 
 */
package com.yodoo.rent.webapp.action.house;

import javax.servlet.http.HttpSession;

import org.nestframework.action.FileItem;
import org.nestframework.action.Redirect;
import org.nestframework.addons.spring.Spring;

import com.yodoo.rent.extservice.IUploadManager;
import com.yodoo.rent.model.HouseInfo;
import com.yodoo.rent.service.IHouseInfoManager;
import com.yodoo.rent.webapp.action.BaseAction;

/**
 * ͼ图片上传操作.
 * 
 * @author audin
 *
 */
public class Photo extends BaseAction {
	
	public Object show() {
		houseInfo = houseInfoManager.get(id);
		return "/house/photo_upload.jsp";
	}
	
	public Object upload(HttpSession s) {
		uploadManager.upload(getLoginUser(s), id, new FileItem[] {file1, file2, file3, file4, file5});
		return new Redirect("/house/Photo.a?id=" + id);
	}

/////////////// 业务类 /////////
	@Spring
	private IUploadManager uploadManager;
	
	@Spring
	private IHouseInfoManager houseInfoManager;
	
/////////////// 属性 ///////////
	
	private FileItem file1;
	
	private FileItem file2;
	
	private FileItem file3;
	
	private FileItem file4;
	
	private FileItem file5;
	
	/**
	 * 房屋信息标识号.
	 */
	private String id;

	/**
	 * 房屋基本信息.
	 */
	private HouseInfo houseInfo;
	
	public FileItem getFile1() {
		return file1;
	}

	public void setFile1(FileItem file1) {
		this.file1 = file1;
	}

	public FileItem getFile2() {
		return file2;
	}

	public void setFile2(FileItem file2) {
		this.file2 = file2;
	}

	public FileItem getFile3() {
		return file3;
	}

	public void setFile3(FileItem file3) {
		this.file3 = file3;
	}

	public FileItem getFile4() {
		return file4;
	}

	public void setFile4(FileItem file4) {
		this.file4 = file4;
	}

	public FileItem getFile5() {
		return file5;
	}

	public void setFile5(FileItem file5) {
		this.file5 = file5;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}	
}
