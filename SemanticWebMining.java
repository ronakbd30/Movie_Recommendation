package SWM;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SemanticWebMining {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> features = new HashSet<>();
		try{	
		    FileInputStream fstream = new FileInputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\movies.dat.txt");
			DataInputStream in = new DataInputStream(fstream);
		  	BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  	String strLine="";
		  	int y = 0;
		  	ArrayList<String> movies = new ArrayList<>();
			while ((strLine = br.readLine()) != null)   {
				//System.out.println(strLine);
				movies.add(strLine);
				String[] s = strLine.split("::");
				if(s.length==3){
					String[] x = s[2].split("&&");
					for(int i=0;i<x.length;i++){
						features.add(x[i]);
					}
				}
			}
			System.out.println(features);
			String[][] output = new String[20585][30];
			for(int i=0;i<movies.size();i++){
				String[] s = movies.get(i).split("::");
				output[i][0] = "'"+s[0];
				output[i][1] = "'"+s[1];
				if(s.length==3){
					String[] x = s[2].split("&&");
					for(int j=0;j<x.length;j++){
						int z = getIndex(x[j]);
						output[i][z+2] = "1";
					}
				}
			}
			FileOutputStream fos = new FileOutputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\moviesData.csv",true);
			fos.write("Movies,Name,".getBytes());
			fos.write(features.toString().getBytes());
			fos.write("\n".getBytes());
			for(int i=0;i<output.length;i++){
				for(int j=0;j<30;j++){
					fos.write((output[i][j]+",").getBytes());
				}
				fos.write("\n".getBytes());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static int getIndex(String s){
		switch(s){
		case "Film-Noir": return 0;
		case "Crime": return 1;
		case "Short": return 2;
		case "Comedy": return 3;
		case "News": return 4;
		case "Mystery": return 5;
		case "Biography": return 6;
		case "Talk-Show": return 7;
		case "Action": return 8;
		case "History": return 9;
		case "Fantasy": return 10;
		case "War": return 11;
		case "Adult": return 12;
		case "Music": return 13;
		case "Musical": return 14;
		case "Game-Show": return 15;
		case "Thriller": return 16;
		case "Romance": return 17;
		case "Animation": return 18;
		case "Horror": return 19;
		case "Western": return 20;
		case "Adventure": return 21;
		case "Sci-Fi": return 22;
		case "Family": return 23;
		case "Sport": return 24;
		case "Reality-TV": return 25;
		case "Drama": return 26;
		case "Documentary": return 27;
		default : return 0;
		}
	}
}
