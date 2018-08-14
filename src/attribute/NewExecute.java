package attribute;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weka.core.Instances;

public class NewExecute {
	
	public Trans trans =new Trans();
	public GetCsv getscv =new GetCsv();
	public int[][] TransMessage(double percent) throws IOException{
		List<String> list = new ArrayList<String>();
		list=getscv.getCsv();
		File file=new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\translate_10%vector.arff");
		Instances instances = getscv.getArff(file);
		
		int mutant_compare[][]=new int [5000][1700];
		File file3=new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\res_original_version.in");
		trans.getData(mutant_compare,file3);
		System.out.println( mutant_compare[0][9]);
		
		int start = (int) (instances.numInstances()*percent);
		
		for(int i=0;i<list.size();i++){
			//System.out.println(Integer.parseInt(list.get(i)));
			//System.out.println(instances.get(Integer.parseInt(list.get(i))+start).value(0));
			//System.out.println(instances.get(Integer.parseInt(list.get(i))+start).value(1));
		    //System.out.println(Integer.parseInt(list.get(i))+start);
			int x = (int) (Integer.parseInt(list.get(i))+start)/1608+1;
			int y = (int) instances.get(Integer.parseInt(list.get(i))+start).value(1);
			int z = (int) instances.get(Integer.parseInt(list.get(i))+start).classValue();
			mutant_compare[x][y]=z;
			System.out.println("x="+x+" y="+y+" z="+z);
		}
		return mutant_compare;
	}
	public static void main(String[] args) throws IOException {
		NewExecute excute = new NewExecute();
		excute.TransMessage(0.10);
	}
}
 