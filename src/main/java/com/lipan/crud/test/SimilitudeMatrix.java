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

public class SimilitudeMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long starTime = System.currentTimeMillis();
			
			int recordNum=7389;//记录条数

//			double distance_similitude[]=new double[25000000];//记录对象之间的欧氏距离
			double object_similitude[]=new double[recordNum*recordNum];

			double similitude_matrix[][]=new double[recordNum][recordNum];

			
            
			// 读取文件
			FileReader file_reader;
			BufferedReader in;
			File file = new File("D:\\graduation project\\objectsimilitude.txt");
//			File file2 = new File("D:\\attribute similitude.txt");
			file_reader = new FileReader(file);
			in = new BufferedReader(file_reader);
			
			
			for (int i = 0; i < recordNum*recordNum; i++) {
				String attributeItem[] = in.readLine().split(",");
				object_similitude[i]=Double.parseDouble(attributeItem[2]);				
			}
			
			in.close();
			file_reader.close();


			int k=0;
//			double alpha=0.3;
			for (int i = 0; i < recordNum; i++) {
				// System.out.print(view[i]+" "+":");
				for (int j = 0; j < recordNum; j++) {
					similitude_matrix[i][j]=1-object_similitude[k];
					k++;
				}
				
			}

			
			FileWriter file_writer;
			BufferedWriter out;
			File file1 = new File("D:\\graduation project\\similitudematrix.txt");
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
					str=a+","+b+","+String.format("%.4f", similitude_matrix[i][j]);
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
