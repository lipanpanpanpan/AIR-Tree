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
//概率密度
public class Cluster {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long starTime = System.currentTimeMillis();
			
			int recordNum=7389;//记录条数
			double probability_density[]=new double[recordNum];//概率密度
			double temp[]=new double[recordNum];//存放临时数据，概率密度
			double temp1[]=new double[recordNum];//存放代表性对象数据
			double similitude_matrix[][]=new double[recordNum][recordNum];//相似度矩阵

			int object[][]=new int[recordNum][2];//记录对象以及其属于的聚类
//			double buildyearCsim[][]=new double[100][100];//不同数目buildyear之间的数值耦合度，结果是一个矩阵
			int clusternum=0;//聚类个数
			int terminate=0;//成功聚类的元素个数
			double threshold=0.88;//阈值，自己设定
			int sum=0;//求和
			int count=0;//计数,n^2
//			int cluster=0;
			for(int i = 0; i < recordNum; i++){//给对象赋值，感觉不赋值也行，可以用一个一维数组直接表示对象的类别
				object[i][0]=i;
			}
			
            
			// 读取文件
			FileReader file_reader;
			BufferedReader in;
			File file = new File("D:\\graduation project\\similitudematrix.txt");
//			File file2 = new File("D:\\distancesimilitude.txt");
			file_reader = new FileReader(file);
			in = new BufferedReader(file_reader);
		
			for (int i = 0; i < recordNum; i++) {
				for (int j = 0; j < recordNum; j++) {
					String attributeItem[] = in.readLine().split(",");
					similitude_matrix[i][j]=Double.parseDouble(attributeItem[2]);
				}
				
			}

			in.close();
			file_reader.close();
		
			while(terminate!=recordNum){//所有对象都成功聚类后终止循环
			
			
			//求h
			sum=0;
			count=0;
			
            for (int i = 0; i < recordNum; i++) {
            	for (int j = 0; j < recordNum; j++) {
            		if(object[i][1]==0&&object[j][1]==0){//只算还没有被聚类的对象
                		sum+=similitude_matrix[i][j];//求和
                		count++;//统计个数=n^2
                	}
            	}            					
			}
			
			double average=(double)(sum/count);
			double variance=0;//方差
			for (int i = 0; i < recordNum; i++) {
				for (int j = 0; j < recordNum; j++) {
					if(object[i][1]==0&&object[j][1]==0){
					    variance+=Math.pow((similitude_matrix[i][j]-average),2)/count;//方差
					}
				}
				
			}
            double beta=Math.sqrt(variance);
//            System.out.println(beta);
            double h=1.06*beta*(1/Math.pow((double)(count), 0.2));//其实h也在变，因为n在变
//            System.out.println(1/Math.pow((double)(recordNum), 0.2));
//            System.out.println(h);
            
            for (int i = 0; i < recordNum; i++) {  //初始化
            	probability_density[i]=0;
            }
            for (int i = 0; i < recordNum; i++) {  //初始化
            	temp[i]=0;
            }
            //计算概率密度
            for (int i = 0; i < recordNum; i++) {
            	if(object[i][1]==0){
            		for(int j = 0; j < recordNum; j++) {
            			if(object[j][1]==0){
            				probability_density[i]+=1/((recordNum-terminate)*Math.sqrt(2*3.1416))*(1/Math.pow(2.7183, Math.pow(similitude_matrix[i][j],2)/2*Math.pow(h, 2)));
            			}//计算概率密度recordNum-terminate=n
            		}
            		temp[i]=probability_density[i];
            	}
            	else{
            		temp[i]=probability_density[i]=0;
            	}
            	//System.out.println(probability_density[i]);
            }
            
            
            Arrays.sort(temp);
//          System.out.println(temp[recordNum-1]);//概率密度最大值
            for (int i = 0; i < recordNum; i++) {
            	if(probability_density[i]==temp[recordNum-1]){
            		clusternum++;
            		temp1[clusternum]=i+1;//代表性对象

            		for (int j = 0; j < recordNum; j++) {
            			if(object[j][1]==0&&similitude_matrix[i][j]<=threshold){
//            				System.out.println(similitude_matrix[i][j]);
            				object[j][1]=clusternum;
            				terminate++;//每次都会经历一次i=j，就相当于代表性对象和聚类对象被一起赋的聚类值
//            				System.out.println(terminate);
            			}
            		}
            		break;//找到一个代表性对象就跳出
            	}
            }
 	
			
			
			}
			
			
			
			FileWriter file_writer;
			BufferedWriter out;
			File file1 = new File("D:\\graduation project\\0.88creativecluster.txt");
			file_writer = new FileWriter(file1);
			out = new BufferedWriter(file_writer);
			//String s=String.valueOf(n); 
//			int count=0;
			String str;
			for (int i = 1; i <= clusternum; i++) {//存的时候就是从1开始的
				// System.out.print(view[i]+" "+":");
				str=String.format("%.0f", temp1[i]);
//				str="";				
//				for (int j = 0; j < recordNum; j++) {
//					if(object[j][1]==i){
//						str+=j+",";	
//					}					
////					count++;
//				}
				out.write(str, 0, str.length());//文件第一行记录不同关键字的个数
				out.flush();
				out.newLine();
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
