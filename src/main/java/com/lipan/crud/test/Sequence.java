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


public class Sequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long starTime = System.currentTimeMillis();
			int representNum=41;//代表性对象个数
			int recordNum=7389;//记录条数
			int representative[]=new int[representNum];//记录代表性对象，会被排序，不排也行
			double objectsimilitude[]=new double[recordNum*recordNum];//记录对象之间的相似度
			int sequence[][]=new int[representNum][recordNum];//每个代表性对象对应的序列,最后输出时将序列中的代表性对象去掉
			double temp[][]=new double[recordNum][2];//存储一个代表性序列的相似度以及每个对象是否已进入序列的标记
			double temp1[]=new double[recordNum];//存储一个代表性序列的相似度，会被排序
			
            
			// 读取文件
			FileReader file_reader;
			BufferedReader in;
			File file = new File("D:\\graduation project\\0.88creativecluster.txt");
			File file2 = new File("D:\\graduation project\\objectsimilitude.txt");
			//相同对象之间的相似度是1
			

			//排序后需要重新读取数据
			file_reader = new FileReader(file);
			in = new BufferedReader(file_reader);
			
			
			for (int i = 0; i < representNum; i++) {
				String attributeItem[] = in.readLine().split(",");
				//System.out.println(attributeItem[0]);
				representative[i]=Integer.parseInt(attributeItem[0]);//代表性对象赋值				
			}
			
			in.close();
			file_reader.close();
			
			Arrays.sort(representative);//按照编号升序排序,得到的结果序列也是按代表性对象升序排列
			
			file_reader = new FileReader(file2);
			in = new BufferedReader(file_reader);
			
			
			for (int i = 0; i < recordNum*recordNum; i++) {
				String attributeItem[] = in.readLine().split(",");
				objectsimilitude[i]=Double.parseDouble(attributeItem[2]);	//对象之间的相似度			
			}
			
			in.close();
			file_reader.close();
			
			for (int i = 0; i < representNum; i++) {//每循环一次构成一个代表性序列，i控制一个序列，j控制一个元素或相似度
				for (int j = 0; j < recordNum; j++) {//初始化
					temp1[j]=0;					
				}
				for (int j = 0; j < recordNum; j++) {//初始化
					temp[j][0]=0;
					temp[j][1]=0;
				}
				for (int j = 0; j < recordNum; j++) {//按1-recordNum顺序赋值,每循环一次赋一个
					if(representative[i]!=(j+1)){//代表性对象范围是从1~recordNum
						temp[j][0]=temp1[j]=objectsimilitude[(representative[i]-1)*recordNum+j];//representative[i]-1是代表性对象前面的对象个数
					}
					else{
						temp[j][0]=temp1[j]=40;//直接将代表性对象赋值成最大的，一会排序就排到是最后一个，最后输出时从sequence第二个开始
					}
				}
				Arrays.sort(temp1);//相似度升序
				for (int j = 0; j < recordNum; j++) {//每循环一次填充一个对象
					for (int k = 0; k < recordNum; k++) {
						if(temp[k][1]==0&&temp1[j]==temp[k][0]){//没进入过序列
							sequence[i][recordNum-j-1]=k+1;//代表性对象降序排列
							temp[k][1]=1;//已进入序列
							break;
						}
					}
				}
			}
			

			FileWriter file_writer;
			BufferedWriter out;
			File file1 = new File("D:\\graduation project\\0.88creativeclustersequence.txt");
			file_writer = new FileWriter(file1);
			out = new BufferedWriter(file_writer); 
			String str;
			for (int i = 0; i < representNum; i++) {
				str="";
				for (int j = 1; j < recordNum; j++) {//把代表性对象去掉
					str+=sequence[i][j]+",";	//第二个对象开始
				}
				out.write(str, 0, str.length()-1);//把最后一个逗号去掉
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
