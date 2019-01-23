package com.lipan.crud.test;

import java.io.*;
import java.text.*;
import java.util.*;


public class PoiData1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long starTime = System.currentTimeMillis();
			
			int recordNum=7389;//记录条数
			
			String ID[]=new String[recordNum];
			String filename[]=new String[recordNum];
			//String foldername[]=new String[20];
			String text[]=new String[recordNum];
			String longitude[]=new String[recordNum];//经度
			String latitude[]=new String[recordNum];//纬度
			String str1;
			// 读取文件
	
			File filePath=new File("D:\\graduation project\\data1.txt");
			File filePath2=new File("D:\\graduation project\\data2.txt");
			InputStreamReader ips =new InputStreamReader(new FileInputStream(filePath), "UTF-8"); //gb2312
			BufferedReader file=new BufferedReader(ips);
			
			
			for (int i = 0; i < recordNum; i++) {
				String attributeItem[] = file.readLine().split(",");
				ID[i]=attributeItem[0];//ID
				//foldername[i]=attributeItem[2];
				filename[i]=attributeItem[3];//原始文件名
				str1=attributeItem[4];
				for(int j=1;j<(attributeItem.length-4);j++){//关键词
					str1=str1+","+attributeItem[j+4];
				}
				str1=str1+","+attributeItem[2];//最后加了个原始文件夹名
				//System.out.println(str1);
				text[i]=str1;
				//System.out.println(text[i]);
			}

			file.close();
			ips.close();
			
			InputStreamReader ips2 =new InputStreamReader(new FileInputStream(filePath2), "UTF-8"); //gb2312
			BufferedReader file2=new BufferedReader(ips2);
			
			
			for (int i = 0; i < recordNum; i++) {
				String attributeItem[] = file2.readLine().split(",");
				longitude[i]=attributeItem[5];
				latitude[i]=attributeItem[6];
			}

			file2.close();
			ips2.close();
			
            //写入文件
			FileWriter file_writer;
			BufferedWriter out;
			File file1 = new File("D:\\graduation project\\poidata1.txt");
			file_writer = new FileWriter(file1);
			out = new BufferedWriter(file_writer);
			
			String str;
			for (int j = 0; j < recordNum; j++) {
				str=ID[j]+","+longitude[j]+","+latitude[j]+","+text[j]+","+filename[j];
				out.write(str, 0, str.length());//文件第一行记录不同关键字的个数
				out.flush();
				out.newLine();					
			}
			out.close();
			file_writer.close();

			long endTime1 = System.currentTimeMillis();
			double Time1 = endTime1 - starTime;
			System.out.println("运行时间: " + Time1 + " ms");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
