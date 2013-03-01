package DataHandler;
import java.io.*;
import java.util.*;

import Structure.Activity;
import Structure.Resource;

public class StructMaker {
	
	//FILE HANDLING CONSTANTS
	private static final int START_ACT_FILE = 220;
	private static final int END_ACT_FILE = 444;
	private static final int LONG_ACT_TEXT = 7;
	private static final int LONG_RES_TEXT = 3;
	private static final int START_RES_FILE = 82;
	private static final int END_RES_FILE = 391;
//	private static final int END_RES_FILE = 650;
	private static final int LEFT_MARGIN_RES = 3;
	private static final int RIGHT_MARGIN_RES = 7;
	private static final int START_PREC_FILE = 62;
	private static final int END_PREC_FILE = 206;
	
	private int nActivities;
	private int nResources;
	private int nSkills;
	
	public StructMaker(int activities, int resources, int skills){

		this.nActivities = activities;
		this.nResources = resources;
		this.nSkills = skills;
	}
	
	private ArrayList<String> parseFile(String path, int begin, int end)throws IOException{
		ArrayList<String> actLog=new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(new File(path));
	        //skip number of lines passed in "begin"
	        int i;
	    	for( i=1;i<=begin;i++){
	    		sc.next();
	    	}
	    	//quit parsing the file when i reaches end
	        while (sc.hasNext() && i <= end ) {
	            actLog.add(sc.next());
	            i++;
	        }
	        sc.close();
	    } catch (IOException e) {
	        System.out.println("File input error");
	    }
	
		return actLog;
	}
		
	public ArrayList<Activity> getListAct() throws IOException{
		
		ArrayList<String> actList= this.parseFile("j308_1.sm", START_ACT_FILE, END_ACT_FILE );
		ArrayList<Activity> listActivity = new ArrayList<Activity>(); 
		for(int j=0;j<32;j++){
			listActivity.add(j, getActivity(j,actList));			
		}
		return listActivity;
	}
	
	private Activity getActivity(int index, ArrayList<String> actList){
		index*=LONG_ACT_TEXT;
		//parse activity id
		int i = Integer.parseInt(actList.get(index));
		//parse activity duration
		int d = Integer.parseInt(actList.get(index+2));
		//parse needed resources
		int[] r = new int[this.nSkills];
		int c=0;
		for(int j=index+LEFT_MARGIN_RES;j<index+RIGHT_MARGIN_RES;j++){
			r[c++]=Integer.parseInt(actList.get(j));
			
		}
		return new Activity(i,d,r);
	}
	
	private void initMatrix(boolean[][] precedence){
		
		for(int i=0;i < precedence.length; i++){
			for(int j=0;j < precedence.length; j++){
				precedence[i][j]=false;
			}
		}
	}
	
	public boolean[][] getPrecedences() throws IOException{
		
		boolean[][] precedence= new boolean[this.nActivities][this.nActivities];
		this.initMatrix(precedence);
		ArrayList<String> precList= this.parseFile("j308_1.sm", START_PREC_FILE, END_PREC_FILE);
		int counter = 0;
		
		for(int index=0; index < precList.size(); index= index+counter){
			int i = Integer.parseInt(precList.get(index));
			counter = Integer.parseInt(precList.get(index + 2));
			index = index+3;
			for(int j=0 ; j < counter ; j++){
				precedence[i-1][Integer.parseInt(precList.get(index + j))-1]= true;
			}
		}
		return precedence;
	}

	public ArrayList<Resource> getResources() throws IOException{
		
		ArrayList<Resource> resources = new ArrayList<Resource>();
		ArrayList<String> resList=this.parseFile("j308_1(EfectividadRecursos).txt", START_RES_FILE, END_RES_FILE);
		for(int i=0; i < this.nResources ; i++){
			resources.add(i,this.getResource(i, resList));
		}
		return resources;
	}

	private Resource getResource(int index, ArrayList<String> actList){
		index*=LONG_RES_TEXT;
		//parse resource id
		int i = Integer.parseInt(actList.get(index));
		//parse skill
		int s = Integer.parseInt(actList.get(index+1));
		//parse effectivness
		double e = Double.parseDouble(actList.get(index+2));
		return new Resource(i,s,e);
	}
}
