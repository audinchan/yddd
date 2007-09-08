package com.yodoo.rent.test;

import java.io.IOException;

import sun.misc.BASE64Decoder;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println(new String(new BASE64Decoder()
					.decodeBuffer("MTE4LjEwMTk0MiwyNC40NzkyNzQ5Ng==")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String a = "    <div id=\"houseInfo\">\r\n" + 
				"    	地址:<input type=\"text\" name=\"address\" id=\"address\" /><br/>\r\n" + 
				"    	居室:<select name=\"rooms\" id=\"rooms\">\r\n" + 
				"    		<option value=\"1\">1</option>\r\n" + 
				"    		<option value=\"2\">2</option>\r\n" + 
				"    		<option value=\"3\">3</option>\r\n" + 
				"    		<option value=\"4\">4</option>\r\n" + 
				"    		<option value=\"5\">5</option>\r\n" + 
				"    		<option value=\"0\">更多</option>\r\n" + 
				"    	</select>\r\n" + 
				"    	居<br/>\r\n" + 
				"    	租金:<input type=\"text\" name=\"price\" id=\"price\" />元/月\r\n" + 
				"    <br /><input type=\"button\" value=\"发布\" onclick=\"submitInfo()\"/></div>";
		
		
		a = "    <div id=\"userInfo\">\r\n" + 
				"    	联系人:<input type=\"text\" name=\"provider\" id=\"provider\" /><br/>\r\n" + 
				"    	电话:<input type=\"text\" name=\"phone\" id=\"phone\" /><br/>\r\n" + 
				"    	Email:<input type=\"text\" name=\"email\" id=\"email\" /><br /><input type=\"button\" value=\"发布\" onclick=\"submitInfo()\"/>\r\n" + 
				"    </div>";
		
		a = "    <div id=\"houseInfo\">\r\n" + 
				"    <strong>请输入出租房屋的信息</strong><br/><br/>\r\n" + 
				"    	地址:<input type=\"text\" name=\"address\" id=\"address\" /><br/>\r\n" + 
				"    	居室:<select name=\"rooms\" id=\"rooms\">\r\n" + 
				"    		<option value=\"1\">1</option>\r\n" + 
				"    		<option value=\"2\">2</option>\r\n" + 
				"    		<option value=\"3\">3</option>\r\n" + 
				"    		<option value=\"4\">4</option>\r\n" + 
				"    		<option value=\"5\">5</option>\r\n" + 
				"    		<option value=\"0\">更多</option>\r\n" + 
				"    	</select>\r\n" + 
				"    	居<br/>\r\n" + 
				"    	租金:<input type=\"text\" name=\"price\" id=\"price\" />元/月<br />\r\n" + 
				"		<br/>\r\n" + 
				"    	联系人:<input type=\"text\" name=\"provider\" id=\"provider\" /><br/>\r\n" + 
				"    	电话:<input type=\"text\" name=\"phone\" id=\"phone\" /><br/>\r\n" + 
				"    	Email:<input type=\"text\" name=\"email\" id=\"email\" /><br />\r\n" + 
				"    	<input type=\"button\" value=\"发布\" onclick=\"submitInfo()\"/>\r\n" + 
				"    </div>";
		
		
		a = "<strong>信息发布成功！</strong><br/>\r\n" + 
				"需要添加照片或视频吗？<br/>\r\n" + 
				"<input type=\"button\" value=\"添加照片\" /> \r\n" + 
				"<input type=\"button\" value=\"添加视频\" />";
		
		System.out.println(a);
	}

}
