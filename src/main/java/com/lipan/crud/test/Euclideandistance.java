package com.lipan.crud.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.*;
import java.util.*;

//import PriceAndMileage.PriceAndMileage;

public class Euclideandistance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long starTime = System.currentTimeMillis();
			
			int recordNum=7389;//记录条数
			double longitude[]=new double[recordNum];
			double latitude[]=new double[recordNum];
			double record_distance[][]=new double[recordNum][recordNum];//记录对象之间的欧氏距离
			
			
			
            
			// 读取文件
			FileReader file_reader;
			BufferedReader in;
			File file = new File("D:\\graduation project\\poidata1.txt");
			file_reader = new FileReader(file);
			in = new BufferedReader(file_reader);
			
			
			for (int i = 0; i < recordNum; i++) {
				String attributeItem[] = in.readLine().split(",");
				// System.out.println("the amount of key words: " + s);
				// Double s = Double.parseDouble(in.readLine());
				// int n = Integer.parseInt(in.readLine());
				// System.out.println("the amount of key words: " + s);
				longitude[i]=Double.parseDouble(attributeItem[1]);
				latitude[i]=Double.parseDouble(attributeItem[2]);
				
				
			}

				
			
			in.close();
			file_reader.close();
		
			//计算欧氏距离
            
			for(int i=0;i<recordNum;i++)
			{
				for(int j=i;j<recordNum;j++)
				{
					if (i == j) {
						record_distance[i][j] = 0;
					} else {
					    record_distance[i][j]=Math.sqrt(Math.pow((double)((longitude[i]*1000000-longitude[j]*1000000)), 2)+Math.pow((double)((latitude[i]*1000000-latitude[j]*1000000)), 2));
					    record_distance[j][i] = record_distance[i][j];//只算一半
					}
				}
			}
			
			FileWriter file_writer;
			BufferedWriter out;
			File file1 = new File("D:\\graduation project\\Euclideandistance1.txt");
			file_writer = new FileWriter(file1);
			out = new BufferedWriter(file_writer);
			//String s=String.valueOf(n); 
			//int count=0;
			String str;
			int a=0,b=0;
			for (int i = 0; i < recordNum; i++) {
				// System.out.print(view[i]+" "+":");
				for (int j = 0; j < recordNum; j++) {
					a=i+1;
					b=j+1;
					str=a+","+b+","+String.format("%.0f", record_distance[i][j]);
//					str=String.format("%.4f", record_distance[i][j]);
					out.write(str, 0, str.length());//文件第一行记录不同关键字的个数
					out.flush();
					out.newLine();
					//count++;
				}
				//System.out.println();
			}
			
			out.close();
			file_writer.close();
			
			

			long endTime1 = System.currentTimeMillis();
			double Time1 = endTime1 - starTime;
			System.out.println("运行时间: " + Time1 + " ms");
			
		} catch (IOException e) {
			// fdload.setVisible(false);
			// fdload.dispose();
			e.printStackTrace();
		}

	}

}
