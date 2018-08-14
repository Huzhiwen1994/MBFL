package com.jhd.distance;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.AddID;
import weka.filters.unsupervised.attribute.Normalize;

public class Distance {
	
	public boolean ComputeDistance() throws Exception{
		//Distance distance =new Distance();
		//List<String> list = new ArrayList<String>();
		File file=new File("D:\\project_arffs\\projects\\apns.arff");
		Instances instances_first = getArff(file);
		Normalize filter = new Normalize(); 
		String[] options = new String[4]; 
		options[0] = "-S"; 
		options[1] = "2.0"; 
		options[2] = "-T"; 
		options[3] = "1.0"; 
		filter.setOptions(options); 
		filter.setInputFormat(instances_first); 
		Instances instances = Filter.useFilter(instances_first, filter); 
		System.out.println(instances.toSummaryString()); 
		for(int i=0;i<10;i++){
			for(int j=i+1;j<10;j++){
			System.out.println(instances.get(i));
			double ed ;
			ed = Math.pow((instances.get(i).value(0)-instances.get(j).value(0)),2)+
				 Math.pow((instances.get(i).value(1)-instances.get(j).value(1)),2)+	
				 Math.pow((instances.get(i).value(2)-instances.get(j).value(2)),2)+
				 Math.pow((instances.get(i).value(3)-instances.get(j).value(3)),2)+
				 Math.pow((instances.get(i).value(4)-instances.get(j).value(4)),2)+
				 Math.pow((instances.get(i).value(5)-instances.get(j).value(5)),2)+
				 Math.pow((instances.get(i).value(6)-instances.get(j).value(6)),2)+
				 Math.pow((instances.get(i).value(7)-instances.get(j).value(7)),2)+
				 Math.pow((instances.get(i).value(8)-instances.get(j).value(8)),2)+
				 Math.pow((instances.get(i).value(11)-instances.get(j).value(11)),2)+
				 Math.pow((instances.get(i).value(12)-instances.get(j).value(12)),2)+
				 Math.pow((instances.get(i).value(13)-instances.get(j).value(13)),2);
			System.out.println("第"+i+"个训练样例和第"+j+"个训练样例之间的距离是："+ed);
			}
			//System.out.println(Integer.parseInt(list.get(i)));
			//System.out.println(instances.get(Integer.parseInt(list.get(i))+start).value(0));
			//System.out.println(instances.get(Integer.parseInt(list.get(i))+start).value(1));
		    //System.out.println(Integer.parseInt(list.get(i))+start);
			/*int x = (int) (Integer.parseInt(list.get(i))+start)/360+1;
			int y = (int) instances.get(Integer.parseInt(list.get(i))+start).value(1);
			int z = (int) instances.get(Integer.parseInt(list.get(i))+start).classValue();
			System.out.println("x="+x+" y="+y+" z="+z);*/
		}
		return true;
	}
	public Instances getArff(File file) throws IOException{
		ArffLoader atf = new ArffLoader();
		atf.setFile(file);
	    //读入数据文件
	    Instances instances = atf.getDataSet();
	    //得到数据文件实例数
	    double sum = instances.numInstances();
	    instances.setClassIndex(instances.numAttributes()-1);
	    //打印数据文件，及实例数
	   // System.out.println(instances);
	   /* for(int i=0;i<instances.size();i++){
	    	 System.out.println(instances.get(i).classValue());
	      }*/
	   //System.out.println("此数据文件中共计："+sum+"个实例数");
		return instances;
	}
	public static void main(String[] args) throws Exception {
		Distance excute = new Distance();
		excute.ComputeDistance();
	}
}
 