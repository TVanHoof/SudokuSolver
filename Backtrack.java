
public class Backtrack {
private SudokuBacktrackBox[][] field ;

	public Backtrack(){
		field=new SudokuBacktrackBox[9][9];
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				field[i][j]=new SudokuBacktrackBox();
	}
	
	public void UserSetValue(int x,int y,int value){
		field[x][y].setValue(value);
		field[x][y].setDefined();
	}
	
	public Boolean solve(int x,int y){
		int NewX=x, NewY=y;
		if(field[x][y].isDefined()){
			if(x==8 && y==8)
				return true;
			else{
				if(x<8) NewX=x+1;
				else{
					NewX=0;
					NewY=y+1;
				}
				return solve(NewX,NewY);
			}
		}
		
		//not defined
		for(int candidate=1;candidate<=9;candidate++)
		{
			Boolean usable=true;
			//check is this candidate is usable
			for(int i=0;i<9;i++){
				if(i<x || field[i][y].isDefined())
					if(field[i][y].getValue()==candidate)
						usable=false;
				if(i<y || field[x][i].isDefined())
					if(field[x][i].getValue()==candidate)
						usable=false;
			}
			if(false==usable)	continue;
			
			for(int i=x-x%3;i<x-x%3+3;i++){
				for(int j=y-y%3;j<y-y%3+3;j++){
					if(field[i][j].isDefined() || (j<y || (j==y && i<x ))){
						if (field[i][j].getValue()==candidate)
							usable=false;
					}
				}
			}
			
			if(false==usable) continue; //generate next candidate
			
			field[x][y].setValue(candidate);
			
			if(x==8 && y==8)
				return true;
			else if(x<8) NewX=x+1;
			else{
				NewX=0;
				NewY=y+1;
			}
			if(true==solve(NewX,NewY))
				return true;
			//else
			//	continue;
		}
		
		return false;
	}
	
	public String toString(){
		String s="";
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				s+=" " + field[i][j].toString() + (j%3==2?"|":" ");
			}
			s+="\n";
			if(i%3==2){
				s+="---------------------------\n";
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
		Backtrack SF=new Backtrack();

		SF.UserSetValue(0, 0, 5);
		SF.UserSetValue(0, 1, 3);
		SF.UserSetValue(0, 4, 7);
		SF.UserSetValue(1, 0, 6);
		SF.UserSetValue(1, 3, 1);
		SF.UserSetValue(1, 4, 9);
		SF.UserSetValue(1, 5, 5);
		SF.UserSetValue(2, 1, 9);
		SF.UserSetValue(2, 2, 8);
		SF.UserSetValue(2, 7, 6);
		SF.UserSetValue(3, 0, 8);
		SF.UserSetValue(3, 4, 6);
		SF.UserSetValue(3, 8, 3);
		SF.UserSetValue(4, 0, 4);
		SF.UserSetValue(4, 3, 8);
		SF.UserSetValue(4, 5, 3);
		SF.UserSetValue(4, 8, 1);
		SF.UserSetValue(5, 0, 7);
		SF.UserSetValue(5, 4, 2);
		SF.UserSetValue(5, 8, 6);
		SF.UserSetValue(6, 1, 6);
		SF.UserSetValue(6, 6, 2);
		SF.UserSetValue(6, 7, 8);
		SF.UserSetValue(7, 3, 4);
		SF.UserSetValue(7, 4, 1);
		SF.UserSetValue(7, 5, 9);
		SF.UserSetValue(7, 8, 5);
		SF.UserSetValue(8, 4, 8);
		SF.UserSetValue(8, 7, 7);
		SF.UserSetValue(8, 8, 9);
		
		System.out.println(SF.toString());
		
		System.out.println(true==SF.solve(0,0)?"Solved":"Error");
		System.out.println(SF.toString());
	}

}
