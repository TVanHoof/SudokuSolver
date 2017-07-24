import java.awt.*;
import javax.swing.*;


public class VBoxValue extends JComponent {
private SudokuBox sb;
private JLabel printarea;
private JTextField input;

	public VBoxValue(SudokuBox s){
		setLayout(new GridLayout());
		setPreferredSize(new Dimension(10,10));
		sb=s;
		printarea=new JLabel();
		printarea.setPreferredSize(new Dimension(6,6));
		input=new JTextField();
		input.setPreferredSize(new Dimension(8,8));
		if(sb!=null){
			this.add(input);
			printarea.setVisible(false);
			input.setVisible(true);
			update();
		}
	}
	
	public JLabel getPrintarea() {
		return printarea;
	}

	public JTextField getInput() {
		return input;
	}
	
	public int update(){
		int ret=0;
		if(!printarea.isVisible()){
			if(!input.getText().isEmpty()){
				ret=Integer.parseInt(input.getText());
			}
			if(sb.isLocked()){
				printarea.setText(sb.getValue()+"");
				if(input.isVisible()){
					input.setVisible(false);
					this.remove(input);
					printarea.setVisible(true);
					this.add(printarea);
				}
			}
		}
		revalidate();
		repaint();
		return ret;
	}
}
