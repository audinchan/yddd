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
				"    	��ַ:<input type=\"text\" name=\"address\" id=\"address\" /><br/>\r\n" + 
				"    	����:<select name=\"rooms\" id=\"rooms\">\r\n" + 
				"    		<option value=\"1\">1</option>\r\n" + 
				"    		<option value=\"2\">2</option>\r\n" + 
				"    		<option value=\"3\">3</option>\r\n" + 
				"    		<option value=\"4\">4</option>\r\n" + 
				"    		<option value=\"5\">5</option>\r\n" + 
				"    		<option value=\"0\">����</option>\r\n" + 
				"    	</select>\r\n" + 
				"    	��<br/>\r\n" + 
				"    	���:<input type=\"text\" name=\"price\" id=\"price\" />Ԫ/��\r\n" + 
				"    <br /><input type=\"button\" value=\"����\" onclick=\"submitInfo()\"/></div>";
		
		
		a = "    <div id=\"userInfo\">\r\n" + 
				"    	��ϵ��:<input type=\"text\" name=\"provider\" id=\"provider\" /><br/>\r\n" + 
				"    	�绰:<input type=\"text\" name=\"phone\" id=\"phone\" /><br/>\r\n" + 
				"    	Email:<input type=\"text\" name=\"email\" id=\"email\" /><br /><input type=\"button\" value=\"����\" onclick=\"submitInfo()\"/>\r\n" + 
				"    </div>";
		
		a = "    <div id=\"houseInfo\">\r\n" + 
				"    <strong>��������ⷿ�ݵ���Ϣ</strong><br/><br/>\r\n" + 
				"    	��ַ:<input type=\"text\" name=\"address\" id=\"address\" /><br/>\r\n" + 
				"    	����:<select name=\"rooms\" id=\"rooms\">\r\n" + 
				"    		<option value=\"1\">1</option>\r\n" + 
				"    		<option value=\"2\">2</option>\r\n" + 
				"    		<option value=\"3\">3</option>\r\n" + 
				"    		<option value=\"4\">4</option>\r\n" + 
				"    		<option value=\"5\">5</option>\r\n" + 
				"    		<option value=\"0\">����</option>\r\n" + 
				"    	</select>\r\n" + 
				"    	��<br/>\r\n" + 
				"    	���:<input type=\"text\" name=\"price\" id=\"price\" />Ԫ/��<br />\r\n" + 
				"		<br/>\r\n" + 
				"    	��ϵ��:<input type=\"text\" name=\"provider\" id=\"provider\" /><br/>\r\n" + 
				"    	�绰:<input type=\"text\" name=\"phone\" id=\"phone\" /><br/>\r\n" + 
				"    	Email:<input type=\"text\" name=\"email\" id=\"email\" /><br />\r\n" + 
				"    	<input type=\"button\" value=\"����\" onclick=\"submitInfo()\"/>\r\n" + 
				"    </div>";
		
		
		a = "<strong>��Ϣ�����ɹ���</strong><br/>\r\n" + 
				"��Ҫ�����Ƭ����Ƶ��<br/>\r\n" + 
				"<input type=\"button\" value=\"�����Ƭ\" /> \r\n" + 
				"<input type=\"button\" value=\"�����Ƶ\" />";
		
		System.out.println(a);
	}

}
