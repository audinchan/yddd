/**
 * 
 */
package com.yodoo.rent.extservice;

import org.nestframework.action.FileItem;

import com.yodoo.rent.model.User;

/**
 * ���ع���.
 * 
 * @author audin
 *
 */
public interface IUploadManager {
	/**
	 * ���淿����Ϣ�����ͼƬ.
	 * 
	 * @param uploader ������.
	 * @param houseId ������Ϣ��ʶ��.
	 * @param files �ϴ����ļ�.
	 */
	public void upload(User uploader, String houseId, FileItem[] files);
}
