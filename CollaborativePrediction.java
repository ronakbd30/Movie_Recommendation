package SWM;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CollaborativePrediction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			int MOVIE_COUNT=6140;
			int USER_COUNT=36146;
		    FileInputStream fstream = new FileInputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\MovieIDs.txt");
			DataInputStream in = new DataInputStream(fstream);
		  	BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  	String strLine="";
		  	HashMap<Integer, Integer> h = new HashMap<>();
			while ((strLine = br.readLine()) != null)   {
				String[] s = strLine.split("\t");
				h.put(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
			}
			int[][] coll = new int[MOVIE_COUNT][USER_COUNT];
			FileInputStream fstream1 = new FileInputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\DataSet.txt");
			DataInputStream in1 = new DataInputStream(fstream1);
		  	BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
		  	System.out.println("A");
		  	while ((strLine = br1.readLine()) != null)   {
				String[] s = strLine.split("\t");
				coll[h.get(Integer.parseInt(s[1]))][Integer.parseInt(s[0])-1] = Integer.parseInt(s[2]);
			}
		  	int[] trainingCount = new int[USER_COUNT];
		  	for(int j=0;j<USER_COUNT;j++){
		  		int count = 0;
			  	for(int i=0;i<MOVIE_COUNT;i++){
			  		if(coll[i][j]>0)
			  			count++;
			  	}
			  	trainingCount[j]=(count*7)/10;
			}
		  	int[] trainingData = new int[USER_COUNT];
		  	for(int j=0;j<USER_COUNT;j++){
		  		int count = 0;
			  	for(int i=0;i<MOVIE_COUNT;i++){
			  		if(coll[i][j]>0)
			  			count++;
			  		if(count==trainingCount[j]){
			  			trainingData[j]=i;
			  			break;
			  		}
			  	}
			}
		  	//for(int i=0;i<USER_COUNT;i++)
		  		//System.out.print(trainingData[i]+",");
		  	//cosine similarity = cos(x,y) = x.y/|x|*|y| = a/b*c 
		  	HashMap<Integer, List<Similarity>> similarUser = new HashMap<>();
		  	double a=0;
		  	double b=0;
		  	double c=0;
		  	for(int i=0;i<USER_COUNT;i++){
		  		for(int j=i+1;j<USER_COUNT;j++){
		  			double z;
		  			if(i==j)
		  				z=-1.0;
		  			else{
		  				b=0;c=0;a=0;
	  				  	for(int l=0;l<trainingData[i];l++){
	  				  		b += (double)coll[l][i]*coll[l][i];
	  				  		c += (double)coll[l][j]*coll[l][j];
	  				  		a += (double)coll[l][j]*coll[l][i];
	  				  	}
	  				  	if(b!=0 && c!=0 && a!=0)
	  				  		z = a/(Math.sqrt(b)*Math.sqrt(c));
	  				  	else
	  				  		z=-1.0;
	  				  	if(z!=-1.0){
	  				  	Similarity sim = new Similarity(j, z);
	  				  		if(similarUser.containsKey(i))
	  				  			similarUser.get(i).add(sim);
	  				  		else{
	  				  			List<Similarity> l = new LinkedList<>();
	  				  			l.add(sim);
	  				  			similarUser.put(i, l);
	  				  		}
	  				  	}
		  			}
		  		}
		  		//TODO: Sort lists in HashMap and perform testing
		  		//
		  		//
		  		//
		  		System.out.println(i);
		  	}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
