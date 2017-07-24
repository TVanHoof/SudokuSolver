import java.util.ArrayList;


public class SudokuField {
private SudokuBox[][] Field;

	public SudokuField(){
		Field=new SudokuBox[9][9];
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				Field[i][j]=new SudokuBox();
	}
	
	public SudokuBox[][] getField() {
		return this.Field;
	}

	public void setField(SudokuBox[][] field) {
		this.Field = field;
	}
	
	public void setValue(int x, int y, int Value){
		try{
			this.getField()[x][y].setValue(Value);
			for(int i=0;i<9;i++){
				this.getField()[i][y].setFalse(Value);
				this.getField()[x][i].setFalse(Value);
			}
			for(int i=x-x%3;i<x-x%3+3;i++){
				for(int j=y-y%3;j<y-y%3+3;j++)
					this.getField()[i][j].setFalse(Value);
			}
		}
		catch(IllegalAccessException e){
			System.out.println("Impossible to give Value to "+x+" "+y+"\n");
		}
	}
	
	public void solve(){
		boolean full=true;
		boolean changed=false;
		ArrayList<Integer> posxzero=new ArrayList<Integer>();
		ArrayList<Integer> posyzero=new ArrayList<Integer>();
		
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				if(getField()[i][j].getValue()==0){
					posxzero.add(i);
					posyzero.add(j);
				}
		
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				//only one possible in row
				for(int v=1;v<10;v++){
					boolean exist=false;
					for(int x=0;x<9;x++) exist|=getField()[x][j].getValue()==v;
					if(exist==false){
						//value v doesn't exist in this row
						ArrayList<Integer> pos=new ArrayList<Integer>();
						for(int x=0;x<9;x++)
							if(getField()[x][j].isPossible(v)) pos.add(x);
						
						if(pos.size()==1)
							try {
								getField()[pos.get(0)][j].setValue(v);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
					}
				}
				//only one possible in column
				for(int v=1;v<10;v++){
					boolean exist=false;
					for(int y=0;y<9;y++) exist|=getField()[i][y].getValue()==v;
					if(exist==false){
						//value v doesn't exist in this column
						ArrayList<Integer> pos=new ArrayList<Integer>();
						for(int y=0;y<9;y++)
							if(getField()[i][y].isPossible(v)) pos.add(y);
						
						if(pos.size()==1)
							try {
								getField()[i][pos.get(0)].setValue(v);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
					}
				}
				//only one possible in subtable
				for(int v=1;v<10;v++){
					boolean exist=false;
					for(int x=i-i%3;x<i-i%3+3;x++) 
						for(int y=j-j%3;y<j-j%3+3;y++)
							exist|=getField()[x][y].getValue()==v;
					
					if(exist==false){
						//value v doesn't exist in this subtable
						ArrayList<Integer> posx=new ArrayList<Integer>();
						ArrayList<Integer> posy=new ArrayList<Integer>();
						for(int x=i-i%3;x<i-i%3+3;x++) 
							for(int y=j-j%3;y<j-j%3+3;y++)
								if(getField()[x][y].isPossible(v)) {
									posx.add(x);
									posy.add(y);
								}
						
						if(posx.size()==1 && posy.size()==1)
							try {
								getField()[posx.get(0)][posy.get(0)].setValue(v);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
					}
				}
			}
		}
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				full&=getField()[i][j].getValue()!=0;
		
		for(int i=0;i<posxzero.size();i++)
				changed|=getField()[posxzero.get(i)][posyzero.get(i)].getValue()!=0;
		if(!full && changed) solve();
		if(!changed) System.out.println("Couldn't solve sudoku completely!!");
	}
	
	public String toString(){
		String s="";
		for(int i=0;i<9;i++){
			if(i%3==0){
				for(int j=0;j<9;j++) s+="-";
				s+="\n";
			}
			for(int j=0;j<9;j++){
				s+=(j%3==0?"|":" ")+getField()[i][j];
			}
			s+="\n";
		}
		return s;
	}
	
	public static void main(String Args[]){
		SudokuField SF=new SudokuField();

		SF.setValue(0, 0, 5);
		SF.setValue(0, 1, 3);
		SF.setValue(0, 4, 7);
		SF.setValue(1, 0, 6);
		SF.setValue(1, 3, 1);
		SF.setValue(1, 4, 9);
		SF.setValue(1, 5, 5);
		SF.setValue(2, 1, 9);
		SF.setValue(2, 2, 8);
		SF.setValue(2, 7, 6);
		SF.setValue(3, 0, 8);
		SF.setValue(3, 4, 6);
		SF.setValue(3, 8, 3);
		SF.setValue(4, 0, 4);
		SF.setValue(4, 3, 8);
		SF.setValue(4, 5, 3);
		SF.setValue(4, 8, 1);
		SF.setValue(5, 0, 7);
		SF.setValue(5, 4, 2);
		SF.setValue(5, 8, 6);
		SF.setValue(6, 1, 6);
		SF.setValue(6, 6, 2);
		SF.setValue(6, 7, 8);
		SF.setValue(7, 3, 4);
		SF.setValue(7, 4, 1);
		SF.setValue(7, 5, 9);
		SF.setValue(7, 8, 5);
		SF.setValue(8, 4, 8);
		SF.setValue(8, 7, 7);
		SF.setValue(8, 8, 9);
		
//		SF.setValue(0, 1, 7);
//		SF.setValue(0, 2, 3);
//		SF.setValue(0, 4, 9);
//		SF.setValue(0, 7, 4);
//		SF.setValue(1, 0, 9);
//		SF.setValue(1, 3, 4);
//		SF.setValue(1, 5, 3);
//		SF.setValue(1, 8, 7);
//		SF.setValue(2, 3, 6);
//		SF.setValue(2, 8, 8);
//		SF.setValue(3, 1, 4);
//		SF.setValue(3, 6, 3);
//		SF.setValue(3, 7, 6);
//		SF.setValue(4, 0, 7);
//		SF.setValue(4, 8, 1);
//		SF.setValue(5, 1, 2);
//		SF.setValue(5, 2, 9);
//		SF.setValue(5, 7, 5);
//		SF.setValue(6, 0, 5);
//		SF.setValue(6, 5, 1);
//		SF.setValue(7, 0, 6);
//		SF.setValue(7, 3, 7);
//		SF.setValue(7, 5, 8);
//		SF.setValue(7, 8, 3);
//		SF.setValue(8, 1, 3);
//		SF.setValue(8, 4, 4);
//		SF.setValue(8, 6, 1);
//		SF.setValue(8, 7, 7);
		
		
		System.out.println(SF);
		SF.solve();
		System.out.println(SF);
	}
}
