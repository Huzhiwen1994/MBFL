  package attribute;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Mbfl {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Mbfl mb =new Mbfl();
		NewExecute excute = new NewExecute();
		int cover_length;
		int cover[][]=new int [1700][600];
		File file1=new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\res_cov_Matrix.in");
		mb.getData(cover,file1);
		System.out.println( cover[1][1]);
		
		int vector[][]=new int [1700][2];
		File file2=new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\res_vector.in");
		mb.getData(vector,file2);
		System.out.println( vector[1][0]);
		
		int mutant_compare[][]=new int [5000][1700];
		File file3=new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\res_original_version.in");
		mb.getData(mutant_compare,file3);
		mutant_compare = excute.TransMessage(0.10);
		System.out.println( mutant_compare[0][7]);
		
		mb.cal_Sup(cover, vector, mutant_compare);
		cover_length=mb.cover_Length(cover);
		System.out.println("finished!");
		
		/*int line[][]=new int [][600];
		File file4=new File("H:\\MBFL\\tcas\\tcas_results\\v10_1\\12\\Sup_Ochiai.txt");
		mb.getData(line, file4);*/
		
		
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
	
	public void cal_Sup(int [][]cover,int [][]vector,int [][]mutant_compare){
		int length;
		length=mutant_compare.length;
		System.out.println("length is"+length);
		System.out.println(cover[0].length);
		
		Mbfl mb =new Mbfl();
		int [][]anp=new int[length][cover[0].length-1];
		int [][]anf=new int[length][cover[0].length-1];
		int [][]akp=new int[length][cover[0].length-1];
		int [][]akf=new int[length][cover[0].length-1];
		double [][]sup=new double[length][cover[0].length-1];
		double []max=new double[cover[0].length-1];
		int []state=new int[cover[0].length-1];
		for(int k=0;k<cover[0].length-1;k++){
			if(cover[0][k]==2)continue;
		for(int i=0;i<length;i++){
			for(int j=0;j<cover.length;j++){
				if(cover[j][k]==0){
					if(vector[j][0]==1){
						anf[i][k]++;
					}
					else if(vector[j][0]==0){
						anp[i][k]++;
					}  
				}
				if(cover[j][k]==1){
					if(vector[j][0]==0&&mutant_compare[i][j]==1)
						akp[i][k]++;
					if(vector[j][0]==0&&mutant_compare[i][j]==0)
						anp[i][k]++;
					if(vector[j][0]==1&&mutant_compare[i][j]==0)
						anf[i][k]++;
					if(vector[j][0]==1&&mutant_compare[i][j]==1)
						akf[i][k]++;
				}   
			}
		}
		}
	    File supFile = new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\Sup_Op2_All_change.txt");  //存放数组数据的文件  
		File file1 = new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\Sup_Op2_change.txt");  //存放数组数据的文件 
		 //File file1 = new File("D:\\MBFL\\tcas\\tcas_results\\v10_1\\2\\Sup_Ochiai.txt"); 
		 //FileWriter out;
		 FileWriter out1;
		 FileWriter out2;
		try {
			//out = new FileWriter(file);
			out1 = new FileWriter(file1);
			out2 = new FileWriter(supFile);
			for(int k=0;k<cover[0].length-1;k++){
				//out.write("第"+s+"行语句的怀疑度值为："+"\r\n");
				out2.write("The"+k+"line's sup is ");
				for(int i=0;i<length;i++){
						//sup[i][k]=akf[i][k]/(Math.sqrt((akf[i][k]+anf[i][k])*(akf[i][k]+akp[i][k])));//OChiai
						sup[i][k]=akf[i][k]-akp[i][k]/(akp[i][k]+anp[i][k]+1);//Op2
						//System.out.println("anp is "+anp[i][k]);
						//System.out.println("anf is "+anf[i][k]);
						//System.out.println("akp is "+akp[i][k]);
						//System.out.println("akf is "+akf[i][k]);
						//System.out.println("sup is "+sup[i][k]);
						//out1.write(sup[i][k]+"\t");  
						//out.write("\r\n");
						if(sup[i][k]!=0)
						out2.write(sup[i][k]+"\t");
				}
				max[k]=mb.max_Sup(sup,k);
				if(max[k]!=0){
				out1.write(k+" "+max[k]+"\t");
				out1.write("\r\n"); 
				out2.write("\r\n");
				}
				//System.out.println(max[k]);
				}
			    //out.close();  
			    out1.close();
			    out2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //文件写入流 
		
	}
	public double max_Sup(double [][]sup,int k) throws IOException{//每个语句的最大怀疑度值
		double max_sup;
		max_sup=sup[0][k];
		for(int i=0;i<sup.length;i++){  
			if(max_sup<sup[i][k]){
				max_sup=sup[i][k];
			}
		}
		return max_sup;
	}
	
	public int cover_Length(int [][]cover){
		int length=0;
		for(int i=0;i<cover[0].length;i++){
			if(cover[0][i]!=2)length++;
		}
		return length;
	}
	
	/*public double cal_Score(){
		
	}*/

}
