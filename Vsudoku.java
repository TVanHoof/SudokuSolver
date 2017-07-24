import java.awt.*;

import javax.swing.*;

public class Vsudoku extends JComponent {
private VBoxValue vbv[][];
private SudokuField Field;

	public Vsudoku(SudokuField sudoku){
		vbv=new VBoxValue[9][9];
		setSudokuField(sudoku);
		setLayout(new GridLayout(11, 11));
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				vbv[i][j]=new VBoxValue(getSudokuField().getField()[i][j]);
				this.add(vbv[i][j]);
				if((j+1)%3==0 && j!=8) this.add(new JLabel());
			}
			if((i+1)%3==0 && i!=8)
				for(int j=0;j<11;j++)
					this.add(new JLabel());
		}
	}
	
	public void setSudokuField(SudokuField sudoku){
		this.Field=sudoku;
	}
	
	public SudokuField getSudokuField(){
		return this.Field;
	}
	
	public void update(){
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++){
				int buffer=vbv[i][j].update();
				if(!this.Field.getField()[i][j].isLocked())
					if(buffer!=0) this.Field.setValue(i,j,buffer);
			}
	}
}
