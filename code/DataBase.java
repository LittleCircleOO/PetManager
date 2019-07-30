package code;

public class DataBase {
	public String[][] data;
	int thelast = 0;
	int maxline;
	DataBase(){
		maxline = 50;
		data=new String[50][4];
	}
	DataBase(int max){
		maxline = max;
		data=new String[max][4];
	}
}
