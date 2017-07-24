import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JFrame {
private Vsudoku vsudoku;
private SudokuField sudoku;
private JButton solve;
	public GUI(SudokuField s){
		super("sudokusolver");
		setPreferredSize(new Dimension(300,400));
		sudoku=s;
		vsudoku=new Vsudoku(sudoku);
		vsudoku.setPreferredSize(new Dimension(300,300));
		solve=new JButton("Solve");
		solve.setPreferredSize(new Dimension(300,100));
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints c= new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.weightx=4;
		c.weighty=4;
		c.gridwidth=3;
		c.gridheight=3;
		c.gridx=0;
		c.gridy=0;
		getContentPane().add(vsudoku,c); 
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridwidth=1;
		c.gridheight=1;
		c.gridx=0;
		c.gridy=4;
		getContentPane().add(solve,c);
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		solve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vsudoku.update();
//				System.out.println(sudoku.toString());
				sudoku.solve();
//				System.out.println(sudoku.toString());
				vsudoku.update();
			}
		});
	}
	
	public static void main(String Args[]){
		SudokuField SF=new SudokuField();

//		SF.setValue(0, 0, 5);
//		SF.setValue(0, 1, 3);
//		SF.setValue(0, 4, 7);
//		SF.setValue(1, 0, 6);
//		SF.setValue(1, 3, 1);
//		SF.setValue(1, 4, 9);
//		SF.setValue(1, 5, 5);
//		SF.setValue(2, 1, 9);
//		SF.setValue(2, 2, 8);
//		SF.setValue(2, 7, 6);
//		SF.setValue(3, 0, 8);
//		SF.setValue(3, 4, 6);
//		SF.setValue(3, 8, 3);
//		SF.setValue(4, 0, 4);
//		SF.setValue(4, 3, 8);
//		SF.setValue(4, 5, 3);
//		SF.setValue(4, 8, 1);
//		SF.setValue(5, 0, 7);
//		SF.setValue(5, 4, 2);
//		SF.setValue(5, 8, 6);
//		SF.setValue(6, 1, 6);
//		SF.setValue(6, 6, 2);
//		SF.setValue(6, 7, 8);
//		SF.setValue(7, 3, 4);
//		SF.setValue(7, 4, 1);
//		SF.setValue(7, 5, 9);
//		SF.setValue(7, 8, 5);
//		SF.setValue(8, 4, 8);
//		SF.setValue(8, 7, 7);
//		SF.setValue(8, 8, 9);
		
		new GUI(SF);
	}
}
