import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CollaborativeFiltering {

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
		  	HashMap<Integer, Integer> hInv = new HashMap<>();
			while ((strLine = br.readLine()) != null)   {
				String[] s = strLine.split("\t");
				h.put(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
				hInv.put(Integer.parseInt(s[1]), Integer.parseInt(s[0]));
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
		  	//This file was developed using CosineSimilarityCalculation.java
			FileInputStream fstream2 = new FileInputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\CosineSymmetryMatrix.csv");
			DataInputStream in2 = new DataInputStream(fstream2);
		  	BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
		  	strLine="";
		  	HashMap<Integer, List<Similarity>> similarUser = new HashMap<>();
			ArrayList<Integer> users = new ArrayList<>();
		  	while ((strLine = br2.readLine()) != null)   {
				String[] s = strLine.split(",");
				int i = Integer.parseInt(s[0]);
				int j = Integer.parseInt(s[1]);
				double z = Double.parseDouble(s[2]);
				Similarity sim = new Similarity(j, z);
				if(similarUser.containsKey(i))
		  			similarUser.get(i).add(sim);
		  		else{
		  			List<Similarity> l = new LinkedList<>();
		  			l.add(sim);
		  			similarUser.put(i, l);
		  			users.add(i);
		  		}
			}
			//System.out.println(similarUser);
		  	//Similarity was found for only 2718 users maybe because of reduced dataset
		  	FileOutputStream fos = new FileOutputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\TestOutput3.csv",true);
		  	fos.write(("USER 1,USER 2,MOVIE,RATING 1,RATING 2,DIFFERENCE\n").getBytes());
		  	for(int i=0;i<users.size();i++){
		  		List<Similarity> l = similarUser.get(users.get(i));
		  		Collections.sort(l, new Comparator<Similarity>(){
					@Override
					public int compare(Similarity a, Similarity b){
						if(a.cosSimilarity>b.cosSimilarity)
							return -1;
						else
							return 1;
					}
				});
				/*
		  		int NEAREST_NEIGHBOURS=3;
		  		FileOutputStream fos = new FileOutputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\UserSimilaritySorted_"+NEAREST_NEIGHBOURS+".csv",true);
		  		fos.write((users.get(i)+1+",").getBytes());
		  		for(int j =0;j<l.size() && j<NEAREST_NEIGHBOURS;j++){
					int b = l.get(j).user+1;
					fos.write((b+",").getBytes());
		  		}
		  		fos.write(("\n").getBytes());
		  		System.out.println(i);
		  		*/
		  		Set<Integer> set = new HashSet<>();
		  		for(int j =0;j<l.size();j++){
					//System.out.print(l.get(j).user+"::"+l.get(j).cosSimilarity+"->");
					int a = users.get(i);
					int b = l.get(j).user;
					boolean flag = false;
					for(int k=trainingData[a]+1;k<MOVIE_COUNT;k++){
						if(coll[k][a]>0 && coll[k][b]>0){
							if(!set.contains(k)){
								int c = a+1;
								int d = b+1;
								fos.write((c+","+d+","+hInv.get(k)+","+coll[k][a]+","+coll[k][b]+","+Math.abs(coll[k][a]-coll[k][b])+"\n").getBytes());
								set.add(k);
							}
						}
					}
					//break;
		  		}
		  		
		  	}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
