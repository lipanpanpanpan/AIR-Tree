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

public class ObjectSimilitude {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long starTime = System.currentTimeMillis();
			
			int recordNum=7389;//记录条数
//			double longitude[]=new double[5000];
//			double latitude[]=new double[5000];
			float distance_similitude[]=new float[recordNum*recordNum];//记录对象之间的位置相关性
			float text_similitude[]=new float[recordNum*recordNum];//文本相关度

			float object_similitude[][]=new float[recordNum][recordNum];
//			double max_distance;
			
            
			// 读取文件
			FileReader file_reader;
			BufferedReader in;
			File file = new File("D:\\graduation project\\distancesimilitude.txt");
			File file2 = new File("D:\\graduation project\\textsimilitude2.txt");
			file_reader = new FileReader(file);
			in = new BufferedReader(file_reader);
			
			
			for (int i = 0; i < recordNum*recordNum; i++) {
				String attributeItem[] = in.readLine().split(",");
				distance_similitude[i]=Float.parseFloat(attributeItem[2]);				
			}
			
			in.close();
			file_reader.close();
//			Arrays.sort(record_distance);//排序后record_distance中数的顺序由小到大排列
//			max_distance=record_distance[24999999];
			
			file_reader = new FileReader(file2);
			in = new BufferedReader(file_reader);
			
			
			for (int i = 0; i < recordNum*recordNum; i++) {
				String attributeItem[] = in.readLine().split(",");
				text_similitude[i]=Float.parseFloat(attributeItem[2]);				
			}
			
			in.close();
			file_reader.close();

			int k=0;
			float alpha=0.1f;//末尾不加f默认是double型
			for (int i = 0; i < recordNum; i++) {
				// System.out.print(view[i]+" "+":");
				for (int j = 0; j < recordNum; j++) {
					object_similitude[i][j]=alpha*distance_similitude[k]+(1-alpha)*text_similitude[k];
					k++;
				}
				
			}
//			System.out.println(distance_similitude[0][1]);
			
			FileWriter file_writer;
			BufferedWriter out;
			File file1 = new File("D:\\graduation project\\objectsimilitude.txt");
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
					str=a+","+b+","+String.format("%.4f", object_similitude[i][j]);
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
