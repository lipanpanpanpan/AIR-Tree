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
import java.util.Arrays;

//import PriceAndMileage.PriceAndMileage;

public class DistanceSimilitude {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long starTime = System.currentTimeMillis();
			
			int recordNum=7389;//记录条数

			float record_distance[]=new float[recordNum*recordNum];//记录对象之间的欧氏距离
			float standard_distance[]=new float[recordNum*recordNum];//标准化后的欧式欧氏距离
			float distance_similitude[][]=new float[recordNum][recordNum];//位置相关性
			float max_distance;
			
            
			// 读取文件
			FileReader file_reader;
			BufferedReader in;
			File file = new File("D:\\graduation project\\Euclideandistance1.txt");
			file_reader = new FileReader(file);
			in = new BufferedReader(file_reader);
			
			
			for (int i = 0; i < recordNum*recordNum; i++) {
				String attributeItem[] = in.readLine().split(",");
				record_distance[i]=Float.parseFloat(attributeItem[2]);				
			}
			
			in.close();
			file_reader.close();
			Arrays.sort(record_distance);//排序后record_distance中数的顺序由小到大排列
			max_distance=record_distance[recordNum*recordNum-1];
			
			
//			FileReader file_reader;
//			BufferedReader in;
//			File file = new File("D:\\record distance.txt");
			//排序后需要重新读取数据
			file_reader = new FileReader(file);
			in = new BufferedReader(file_reader);
			
			
			for (int i = 0; i < recordNum*recordNum; i++) {
				String attributeItem[] = in.readLine().split(",");
				record_distance[i]=Float.parseFloat(attributeItem[2]);				
			}
			
			in.close();
			file_reader.close();
			
			for(int i=0;i<recordNum*recordNum;i++)
			{
				standard_distance[i]=record_distance[i]/max_distance;//归一化处理
				
			}
//			System.out.println(standard_distance[1]);
			int k=0;
			for (int i = 0; i < recordNum; i++) {
				// System.out.print(view[i]+" "+":");
				for (int j = 0; j < recordNum; j++) {
					distance_similitude[i][j]=1-standard_distance[k];//得到位置相关度
					k++;
				}
				
			}
//			System.out.println(distance_similitude[0][1]);
			
			FileWriter file_writer;
			BufferedWriter out;
			File file1 = new File("D:\\graduation project\\distancesimilitude.txt");
			file_writer = new FileWriter(file1);
			out = new BufferedWriter(file_writer);
			//String s=String.valueOf(n); 
			int count=0;
			String str;
			int a=0,b=0;
			for (int i = 0; i < recordNum; i++) {
				// System.out.print(view[i]+" "+":");
				for (int j = 0; j < recordNum; j++) {
					a=i+1;
					b=j+1;
					str=a+","+b+","+String.format("%.4f", distance_similitude[i][j]);
					out.write(str, 0, str.length());//文件第一行记录不同关键字的个数
					out.flush();
					out.newLine();
					count++;
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
