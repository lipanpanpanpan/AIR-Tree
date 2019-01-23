package com.lipan.crud.test;

import java.io.*;
import java.text.*;
import java.util.*;

//import PriceAndMileage.PriceAndMileage;

public class TextSimilitude {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long starTime = System.currentTimeMillis();
			
			int recordNum=7389;//记录条数
			//int textNum=10;//关键词的个数
			String text[][]=new String[recordNum][];//文本信息
			double textsilitude[][]=new double[recordNum][recordNum];//对象之间的文本相似度
			String str1;
			// 读取文件
	        
			File filePath=new File("D:\\graduation project\\poidata1.txt");
			InputStreamReader ips =new InputStreamReader(new FileInputStream(filePath), "utf-8");
			BufferedReader file=new BufferedReader(ips);
			
			
			for (int i = 0; i < recordNum; i++) {
				String attributeItem[] = file.readLine().split(",");
				//System.out.println(attributeItem.length-5);
				str1=attributeItem[3];//从文本开始
			    for(int j=1;j<(attributeItem.length-5);j++){//关键词
			    	//System.out.println(attributeItem[j+3]);
			    	//System.out.println(text[i][j]);
			    	str1=str1+","+attributeItem[j+3];			    	
			    }
			   
			    str1=str1+","+attributeItem[attributeItem.length-2]+","
			         +attributeItem[attributeItem.length-2]+","+attributeItem[attributeItem.length-2]+","
			         +attributeItem[attributeItem.length-2]+","+attributeItem[attributeItem.length-2];
			    text[i]=str1.split(",");
			    //做手脚，提取出的倒数第个对象就是类别名，
			    //这样同一类对象的类别名相同，就多了五个相同的关键词，不同类别的对象相似度更小了
				//text[i]=(attributeItem[1]+";"+attributeItem[2]).split(";");//将第2个和第3个字符串连接后，再分解，数组的大小可变
				//System.out.println(text[i].length);					
			}
	
			file.close();
			ips.close();
			
			for(int i=0;i<recordNum;i++){
				for(int j=0;j<recordNum;j++){
					int count=0;//初始化
					for(int k=0;k<text[i].length;k++){
						for(int m=0;m<text[j].length;m++){
							if(text[i][k].equals(text[j][m])){
								count++;						//交集个数	
								break;
							}
						}						
					}

					textsilitude[i][j]=(double)count/(text[i].length+text[j].length-count);//总数减去交集个数就是并集个数
					//textsilitude[j][i]=	textsilitude[i][j];///只算一半
				}
			}
            //写入文件
			FileWriter file_writer;
			BufferedWriter out;
			File file1 = new File("D:\\graduation project\\textsimilitude2.txt");
			file_writer = new FileWriter(file1);
			out = new BufferedWriter(file_writer);
			//String s=String.valueOf(n); 
			
			String str;
			int a=0,b=0;
			for (int i = 0; i < recordNum; i++) {
				// System.out.print(view[i]+" "+":");
				for (int j = 0; j < recordNum; j++) {
					a=i+1;
					b=j+1;
//					str=count+": "+view[i]+" and "+view[j]+":"+String.format("%.4f", viewCsim[i][j]);
					str=a+","+b+","+String.format("%.4f", textsilitude[i][j]);
					out.write(str, 0, str.length());//文件第一行记录不同关键字的个数
					out.flush();
					out.newLine();
					
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
