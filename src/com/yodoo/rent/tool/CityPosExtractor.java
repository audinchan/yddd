package com.yodoo.rent.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 改程序将从http://www.hjqing.com/find/jingwei/index.asp采集的坐标信息解析成单个的列表文件.
 * 
 * @author audin
 *
 */
public class CityPosExtractor implements Runnable {
	
	/**
	 * 输出文件.
	 */
	private String outputFile;
	
	/**
	 * 输入目录.
	 */
	private String inputDir;
	
	/**
	 * 是否输出省份名称.
	 */
	private boolean outputProvince = false;

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public void setInputDir(String inputDir) {
		this.inputDir = inputDir;
	}
	
	public void setOutputProvince(boolean outputProvince) {
		this.outputProvince = outputProvince;
	}

	public void run() {
		File outFile = new File(outputFile);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(outFile));
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		File dir = new File(inputDir);
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		
		for (File file : files) {
			try {
				String content = getFileContent(file);
				Pos pos = extractPos(content);
				if (outputProvince) {
					writer.write(pos.province + ",");
				}
				writer.write(pos.city + "," + pos.lng + "," + pos.lat + "\r\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	protected String getFileContent(File file) throws IOException {
		FileReader reader = new FileReader(file);
		StringBuffer result = new StringBuffer(10240);
		char[] buf = new char[1024];
		int len = 0;
		while ((len = reader.read(buf)) != -1) {
			result.append(buf, 0, len);
		}
		reader.close();
		return result.toString();
	}
	
	protected Pos extractPos(String content) throws IOException {
		Pos p = new Pos();
		
		BufferedReader reader = new BufferedReader(new StringReader(content));
		
		String line = null;
		String[] data = new String[4];
		
		while ((line = reader.readLine()) != null) {
			if (line.indexOf("<td width=\"19%\" height=\"43\" valign=\"middle\" align=\"center\">") != -1) {
				Pattern pattern = Pattern.compile(".*>([^><]+)<.*");
				for (int j = 0; j < 4; j++) {
					Matcher matcher = pattern.matcher(line);
					if (matcher.matches()) {
						for (int i = 1; i <= matcher.groupCount(); i++) {
							String s = matcher.group(i);
							data[j] = s;
						}
					}
					line = reader.readLine();
				}
				break;
			}
		}
		
		p.province = data[0];
		p.city = data[1];
		p.lng = data[2];
		p.lat = data[3];

		return p;
	}
	
	/**
	 * 位置信息.
	 */
	protected class Pos {
		protected String lat;
		protected String lng;
		protected String province;
		protected String city;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CityPosExtractor cp = new CityPosExtractor();
		cp.setInputDir("D:\\tmp\\citypos");
		cp.setOutputFile("D:\\tmp\\citys_all.txt");
		cp.run();
	}

}
