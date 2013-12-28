import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;


public class Ruzzle {
	HashSet<String> dico = new HashSet<String>();
	HashSet<String> result = new HashSet<String>();
	String plateau[][]  = new String[4][4];
	public void init(String init) {
		int x = 0;
		int y = 0;
		for(char c : init.toCharArray()) {
			plateau[x][y]= Character.toString(c);
			y++;
			y = y%4;
			if(y==0) {
				x++;
				x=x%4;
			}
		}
	}
	public void display() {
		
	    for(int x = 0;x <4;x++) {
	    	for(int y = 0; y < 4;y++) {
	    		System.out.print(" "+plateau[x][y]+" ");
	    	}
	    	System.out.println("|");
	    }
	}
public void display(String[][]plateau) {
		
	    for(int x = 0;x <4;x++) {
	    	for(int y = 0; y < 4;y++) {
	    		System.out.print(" "+plateau[x][y]+" ");
	    	}
	    	System.out.println("|");
	    }
	}
	public void navigate(int X,int Y,String mot,String[][] plt,int deep) {
		if(deep<=10) {
		if(plt[X][Y]!=null) {
			String pl[][] = clone(plt);
			mot +=pl[X][Y];
			pl[X][Y]=null;
			//System.out.println(mot);
			if(dico.contains(mot)) {
				//System.out.println(mot);
				result.add(mot);
			}
			for(int[] pair : nexts(X,Y,pl)) {
				navigate(pair[0],pair[1],mot,pl,deep+1);
			}
		}
		}
	}
	ArrayList<int[]> nexts(int X,int Y,String[][] plt) {
		ArrayList<int[]> ret = new ArrayList<int[]>();
		if(X >=0 && X <4 && Y>=0 && X <4){
			if(X+1 < 4 && plt[X+1][Y]!=null) {
				ret.add(new int[]{X+1,Y});
			}
			if(X-1 >= 0 && plt[X-1][Y]!=null) {
				ret.add(new int[]{X-1,Y});
			}
			if(Y+1 < 4 && plt[X][Y+1]!=null) {
				ret.add(new int[]{X,Y+1});
			}
			if(Y-1 >= 0 && plt[X][Y-1]!=null) {
				ret.add(new int[]{X,Y-1});
			}
			
			if(X+1 < 4 && Y+1<4 && plt[X+1][Y+1]!=null) {
				ret.add(new int[]{X+1,Y+1});
			}
			if(X-1 >= 0 && Y-1>=0 && plt[X-1][Y]!=null) {
				ret.add(new int[]{X-1,Y-1});
			}
			if(Y+1 < 4 && X-1 >= 0 && plt[X-1][Y+1]!=null) {
				ret.add(new int[]{X-1,Y+1});
			}
			if(Y-1 >= 0 && X+1 < 4 && plt[X+1][Y-1]!=null) {
				ret.add(new int[]{X+1,Y-1});
			}
			
			
			
		}
		return ret;
				
	}
	public Ruzzle(String init) {
		init(init);
		display();
		load_dico();
		for(int x = 0;x <4;x++) {
	    	for(int y = 0; y < 4;y++) {
	    		navigate(x,y,"",plateau.clone(),1);
	    	}
	    
	    }
		ArrayList<String> finalrst = new ArrayList<String>(result);
		Collections.sort(finalrst, new Comparator(){

			@Override
			public int compare(Object o1, Object o2) {
				String s1 = (String) o1;
				String s2 = (String) o2;// TODO Auto-generated method stub
				return 	Integer.compare(s1.length(), s2.length());
			}});
		for(String r : finalrst) {
			System.out.println(r+"\n");
		}
	
		
	}
	private void load_dico() {
		 try{
			  // Open the file that is the first 
			  // command line parameter
			  FileInputStream fstream = new FileInputStream("dico");
			  // Get the object of DataInputStream
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  //Read File Line By Line
			  while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
				  dico.add(strLine);
			  }
			  //Close the input stream
			  in.close();
			    }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
	}
	public String[][] clone(String[][] str) {
		String ret [][] = new String[4][4];
		  for(int x = 0;x <4;x++) {
		    	for(int y = 0; y < 4;y++) {
		    		ret[x][y] = str[x][y];
		    	}
		    
		    }
		  return ret;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Ruzzle(args[0]);

	  
	}

}
