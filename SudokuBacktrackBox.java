
public class SudokuBacktrackBox {
private int value;
private boolean defined;

	public SudokuBacktrackBox()
	{
		setValue(0);
		defined=false;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		if(value<=9)
			this.value = value;
	}

	public Boolean isDefined() {
		return defined;
	}

	public void setDefined() {
		this.defined = true;
	}
	
	public String toString(){
		String s[]={" ","1","2","3","4","5","6","7","8","9"};
		return s[getValue()];
	}
}
