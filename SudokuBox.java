public class SudokuBox {
private boolean[] possible;
private int Value;
private boolean Locked=false;

	public SudokuBox(){
		possible=new boolean[9];
		for(int i=0;i<9;i++) possible[i]=true;
		Value=0;
		Locked=false;
	}
	
	public void setFalse(int f){
		if(possible[f-1]==true){
			possible[f-1]=false;
			check();
		}
	}
	
	public void setTrue(int t){
		possible[t-1]=true;
		check();
	}
	
	public boolean isPossible(int p){
		return possible[p-1];
	}
	
	public boolean isLocked() {
		return Locked;
	}

	public void setLocked() {
		Locked =true;
	}

	
	public void setValue(int value) throws IllegalAccessException,IllegalArgumentException{
		if(value<1 || value>9)
			throw new IllegalArgumentException();
		
		if(isLocked()==false){
			this.Value=value;
			for(int i=0;i<possible.length;i++){
				if(i+1==value)
					possible[i]=true;
				else
					possible[i]=false;
			}
		setLocked();
		}
		else
			throw new IllegalAccessException();
	}
	
	public int getValue(){
			return this.Value;
	}
	
	public String toString(){
		if(getValue()==0)
			return " ";
		else
			return getValue()+"";
	}
	
	private void check(){
		int count=0;
		int i;
		for(i=0;i<possible.length;i++)
			if(true==possible[i])
				count++;
		if(1==count){
			for(i=0;false==possible[i];i++);
			try{
				setValue(i+1);
			}
			catch(Exception e){
				System.out.println("Impossible to give Value");
			}
		}		
	}
}
