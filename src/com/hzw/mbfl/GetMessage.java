package com.hzw.mbfl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GetMessage {
	//HashMap<Integer,ArrayList<Integer>> hashhm = new HashMap<Integer, ArrayList<Integer>>(); 
	public void getData(HashMap<Integer,HashMap<Integer,Integer>> hashhm,File file){
        Reader reader = null;
        int count=0,i=0;
        try {
            reader = new InputStreamReader(new FileInputStream(file));
			HashMap<Integer,Integer> hm= new HashMap<Integer, Integer>();
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
            		if(((char) tempchar)!='\r'){ 
            			hm.put(i, tempchar - 48);    			
            			i++;
            		}
            	    if(((char) tempchar)=='\n'){
            	    	//System.out.println(i);
            			hashhm.put(count, hm);
            			//System.out.println("count="+count+" i="+i+" "+hm.get(85));;
            	    	i=0;
            	    	count++;
            	    }
            }
            Set<Integer> keyset = hashhm.keySet();  
            for (Integer key : keyset) {  
                //得到键值对 
            	System.out.println(key);
                HashMap<Integer,Integer> keyvalue = hashhm.get(key);  
                //得到键值对的键  
                Set<Integer> keyset2 = keyvalue.keySet();  
                for (Integer key2 : keyset2) {  
                    //根据键得到值  
                	//System.out.println(key2);
                    Integer value = keyvalue.get(key2);  
                    System.out.println("\t/"+key2+"---"+value);  
                }  
            }  
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       //System.out.println(i);
	}
	public static void main(String[] args) {
		HashMap<Integer,HashMap<Integer,Integer>> hashhm = new HashMap<Integer, HashMap<Integer,Integer>>(); 
		File file = new File("D:\\MBFL\\sed\\sed_results\\v3_1\\1\\res_cov_Matrix.in");
		GetMessage getMessage = new GetMessage();
		getMessage.getData(hashhm, file);
	}
}
