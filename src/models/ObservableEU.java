package models;

public class ObservableEU implements Comparable{
	
	public ObservableConfiguration o;
	public double eu;
	
	public ObservableEU(ObservableConfiguration o, double eu){
		this.o = o;
		this.eu = eu;
	}

	@Override
	public int compareTo(Object arg0) {
		ObservableEU o1 = (ObservableEU) arg0;
		if(this.eu > o1.eu){
			return -1;
		}else if(this.eu == o1.eu){
			return 0;
		}else{
			return 1;
		}
	}
	
	public String toString(){
		return "o"+o.id+" : "+eu;
	}

}
