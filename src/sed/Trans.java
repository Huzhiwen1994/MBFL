package sed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Trans {
	public static void main(String[] args) {
		Trans tran=new Trans();

		File file2=new File("D:\\MBFL\\sed\\sed_results\\v3_1\\1\\res_record.txt");
		File file1=new File("D:\\MBFL\\sed\\sed_results\\v3_1\\1\\res_original_version.in");
		//File file2=new File("D:\\MBFL\\tot_info\\tot_info_results\\v10_1\\1\\res_record.txt");
		//File file1=new File("D:\\MBFL\\tot_info\\tot_info_results\\v10_1\\1\\res_original_version.in");
		int len1=0,column1=0,len2=0,column2=0;
		len1 = getLength(file1);
		column1 = getColumn(file1);
		len2 =	getLength(file2);
		column2 = getColumn(file2);
		int A[][]=new int [len1][column1+1];
		System.out.println(len1);
		System.out.println(column1);
		System.out.println(len2);
		System.out.println(column2);
		String vector[] = new String [len2];
		tran.getData(A, file1);
		tran.getData(vector, file2);
		System.out.println(vector[9]);
		tran.translate(A,vector);
		System.out.println(A[0][0]);
	}
	public void getData(String [] A,File file){
		BufferedReader  reader = null;
        int count=0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempchar = " ";
            while ((tempchar = reader.readLine()) != null) {
            		A[count]=tempchar;   	   
            	    //System.out.println(tempchar); 
            		count++;
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       //System.out.println(i);
	}
	public void getData(int [][] A,File file){
        Reader reader = null;
        int count=0,i=0;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
            		if(((char) tempchar)!='\r'){
            			A[count][i]=tempchar-48;   	   
            			//System.out.println(()tempchar);
            			i++;
            		}
            	    if(((char) tempchar)=='\n'){
            	    	//System.out.println(i);
            	    	i=0;
            	    	count++;
            	    }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       //System.out.println(i);
	}
	public void translate(int [][] A,String [] vector){
		try {
		File file1 = new File("D:\\MBFL\\sed\\sed_results\\v3_1\\1\\translate.arff");
		//File file1 = new File("D:\\MBFL\\tot_info\\tot_info_results\\v10_1\\1\\translate.arff");//存放数组数据的文件 
	    FileWriter out1;
	    String vector1[]=new String[vector.length];
		out1 = new FileWriter(file1);
		out1.write("@relation sed.symbolic.attribute"+"\r\n"+"\r\n");
		for(int i=0;i<2;i++){
			out1.write("@attribute "+"symbol"+i+" numeric"+"\r\n");
		}
		out1.write("@attribute "+"class"+"{0,1}"+"\r\n");
		out1.write("\r\n"+"@data"+"\r\n");
		System.out.println(""+vector.length);
		for(int i=0;i<vector.length;i++){
			vector1[i] = "";
			for(int j=0;j<12;j++){
				if(vector[i].charAt(j)>=48&&vector[i].charAt(j)<=57){
					vector1[i]+=vector[i].charAt(j);
				}
			}
		} 
		System.out.println("vector的长度是"+vector.length);
		System.out.println("A[0]的长度是"+A[0].length);
		for(int i=0;i<vector.length;i++){
			for(int j=0;j<A[0].length-1;j++){
				if(j>=i)
				out1.write(vector1[i]+','+j+','+String.valueOf(A[i][j])+"\r\n");
				//else if(j<i&&i<361)
				//out1.write("___________"+vector1[j]+','+i+','+String.valueOf(A[j][i])+"\r\n");
			}
			for(int k = i+1 ;k<A[0].length-1;k++){
				out1.write(vector1[k]+','+i+','+String.valueOf(A[k][i])+"\r\n");
			}
		}
		out1.close();
		System.out.println("finished!");
		} catch (IOException e) {  
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //文件写入流 
		
	}
	
	public static int getLength(File file){
		Reader reader = null;
        int count=0;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
            	    if(((char) tempchar)=='\n'){
            	    	//System.out.println(i);
            	    	count++;
            	    }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
	}
	public static int getColumn(File file){
		Reader reader = null;
        int count=0;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
            	    if(((char) tempchar)!='\n'){
            	    	//System.out.println(i);
            	    	count++;
            	    }
            	    else{
            	    	break;
            	    }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
	}
}
