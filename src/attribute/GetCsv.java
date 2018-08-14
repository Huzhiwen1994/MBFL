package attribute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class GetCsv {
	
	public List<String> getCsv(){
		
		File csv = new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\10%.csv");  // 
	    BufferedReader br = null;
	    List<String> allString = new ArrayList<>();
	    try
	    {
	        br = new BufferedReader(new FileReader(csv));
	    } catch (FileNotFoundException e)
	    {
	        e.printStackTrace();
	    }
	    String line = "";
	    String everyLine = "";
	    String[] num =new String[10];
	    try { 
	            while ((line = br.readLine()) != null)  
	            {
	                everyLine = line;
	                if(everyLine.equals(""))break;
	                num=everyLine.split(",");
	               //System.out.println(everyLine);
	               //System.out.println(num[0]);
	               //System.out.println(num[3]);
	                if(num[3].equals("+")&&num!=null)
	                allString.add(num[0]);
	            }
	            System.out.println("csv的行数是"+allString.size());
               /* for(String attribute : allString) {
              	  System.out.println(attribute);
              }*/
	    } catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    
		return allString;
		
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
	public void translate(List<String> list,Instances instances,double percent){
		try {
		File file1 = new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\translate_10%vector.arff");  //存放数组数据的文件 
	    FileWriter out1;
		out1 = new FileWriter(file1);
		int start = (int) (instances.numInstances()*percent);
		for(int i=0;i<list.size();i++){
			//System.out.println(Integer.parseInt(list.get(i)));
			System.out.println(instances.get(Integer.parseInt(list.get(i))+start).classValue());
			if(instances.get(Integer.parseInt(list.get(i))+start).classValue()==0){
				instances.get(Integer.parseInt(list.get(i))+start).setClassValue(1);
			}
			else{
				instances.get(Integer.parseInt(list.get(i))+start).setClassValue(0);
			}
		}
		out1.write(instances.toString());
		out1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //文件写入流 
	}
	public static void main(String[] args) throws IOException {
		GetCsv getscv =new GetCsv();
		List<String> list = new ArrayList<String>();
		list=getscv.getCsv();
		File file=new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\translate.arff");
		Instances instances = getscv.getArff(file);
		getscv.translate(list, instances, 0.10);
		//getscv.getCsv();
		
	}
}
