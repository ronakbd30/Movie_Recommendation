package SWM;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class CollaborativeItem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{	
		    FileInputStream fstream = new FileInputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\MovieIDs.txt");
			DataInputStream in = new DataInputStream(fstream);
		  	BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  	String strLine="";
		  	HashMap<Integer, Integer> h = new HashMap<>();
			while ((strLine = br.readLine()) != null)   {
				String[] s = strLine.split("\t");
				h.put(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
			}
			int[][] coll = new int[6140][36146];
			FileInputStream fstream1 = new FileInputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\DataSet.txt");
			DataInputStream in1 = new DataInputStream(fstream1);
		  	BufferedReader br1 = new BufferedReader(new InputStreamReader(in1));
		  	System.out.println("A");
		  	while ((strLine = br1.readLine()) != null)   {
				String[] s = strLine.split("\t");
				coll[h.get(Integer.parseInt(s[1]))][Integer.parseInt(s[0])-1] = Integer.parseInt(s[2]);
			}
		  	
		  	FileOutputStream fos = new FileOutputStream("E:\\CourseWork\\Second Semester\\Semantic Web Mining\\DataSet\\CollobarativeDataset.csv",true);
		  	System.out.println("B");
		  	for(int i=0;i<coll.length;i++){
		  		System.out.println(i);
				for(int j=0;j<coll[i].length;j++){
					fos.write((coll[i][j]+",").getBytes());
				}
				fos.write("\n".getBytes());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
