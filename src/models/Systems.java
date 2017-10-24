package models;

public class Systems implements Comparable{
	
	public static int ID = 1;
	public String name;
	public Configuration f;
	public ObservableConfiguration tf;
	public int id;
	
	public Systems(){
		name = "k"+ID;
		id = ID;
		ID++;
	}
	
	public Systems(Configuration f){
		this.f = f;
		name = "k"+ID;
		id = ID;
		ID++;
	}
	
	public Systems(int id, Configuration f){
		this.id = id;
		name="k"+id;
		ID++;
		this.f = f;
	}

	public void assignObservable(ObservableConfiguration tf){
		this.tf = tf;
	}
	
	public void assignConfig(Configuration c){
		this.f = f;
	}
	
	public String toString(){
		return name;//+" Configuration: "+f.name+" Utility: "+f.utility;
	}

	@Override
	public int compareTo(Object arg0) {
		Systems k1 = (Systems) arg0;
		if(this.f.utility < k1.f.utility){
			return 1;
		}else if(this.f.utility == k1.f.utility){
			return 0;
		}else{
			return -1;
		}
	}
	
	
}
