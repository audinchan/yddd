/**
 * 
 */
package com.yodoo.rent.extservice;

import org.nestframework.action.FileItem;

import com.yodoo.rent.model.User;

/**
 * 上载管理.
 * 
 * @author audin
 *
 */
public interface IUploadManager {
	/**
	 * 保存房屋信息的相关图片.
	 * 
	 * @param uploader 上载者.
	 * @param houseId 房屋信息标识号.
	 * @param files 上传的文件.
	 */
	public void upload(User uploader, String houseId, FileItem[] files);
}
