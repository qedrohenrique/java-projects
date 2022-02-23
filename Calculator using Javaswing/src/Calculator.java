import java.awt.*;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.event.*;

public class Calculator implements ActionListener{

	
	JFrame frame;
	JTextPane currentNumberPanel, pastNumberPanel;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[14];
	JButton addButton, subButton, mulButton, divButton,
			percentButton, sqrRootButton, sqrPowerButton, invButton,
			signalButton, clrElementButton, clrFullButton, clrUnitButton, 
			pointButton, equButton;
	JPanel panel;
	
	Font myFont = new Font("Arial", Font.BOLD, 24);
	Font pastNumberFont = new Font("Arial", Font.PLAIN, 18);
	
	double lastnumber = 0, currentnumber = 0, result = 0;
	char operator = 'f', lastoperator;
	boolean cleanable = true, isExpression = false, isDouble = false, isResult = false;
	
	Color grayBG = new Color(32, 32, 32);
	
	Calculator(){
		
		// Inicializando JFrame
		
		frame = new JFrame("Calculadora");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(670, 450);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.getContentPane().setBackground(grayBG);
		
		// Visor
		
		SimpleAttributeSet Right_to_left = new SimpleAttributeSet();
		StyleConstants.setAlignment(Right_to_left, StyleConstants.ALIGN_RIGHT);
		
		currentNumberPanel = new JTextPane();
		
		StyledDocument doc = currentNumberPanel.getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), Right_to_left, false);	
		
		currentNumberPanel.setBounds(15, 45, 625, 45);
		currentNumberPanel.setFont(myFont);
		currentNumberPanel.setEditable(false);
		currentNumberPanel.setBackground(grayBG);
		currentNumberPanel.setForeground(Color.WHITE);
		currentNumberPanel.setText("0");
		frame.add(currentNumberPanel);
		
		pastNumberPanel = new JTextPane();
		
		doc = pastNumberPanel.getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), Right_to_left, false);
		
		pastNumberPanel.setBounds(15, 15, 625, 35);
		pastNumberPanel.setFont(pastNumberFont);
		pastNumberPanel.setEditable(false);
		pastNumberPanel.setBackground(grayBG);
		pastNumberPanel.setForeground(new Color(196,196,196));
		pastNumberPanel.setText("");
		frame.add(pastNumberPanel);
		
		// Configurando botões de operação
		
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("x");
		divButton = new JButton("÷");
		percentButton = new JButton("%");
		sqrRootButton = new JButton("^1/2");
		sqrPowerButton  = new JButton("^2");
		invButton = new JButton("1/x");
		signalButton = new JButton("+/-");
		clrElementButton = new JButton("CE");
		clrFullButton = new JButton("C");
		clrUnitButton = new JButton("<");
		pointButton = new JButton(".");
		equButton = new JButton("=");
		
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = percentButton;
		functionButtons[5] = sqrRootButton;
		functionButtons[6] = sqrPowerButton;
		functionButtons[7] = invButton;
		functionButtons[8] = clrUnitButton;
		functionButtons[9] = clrElementButton;
		functionButtons[10] = clrFullButton;
		functionButtons[11] = signalButton;
		functionButtons[12] = pointButton;
		functionButtons[13] = equButton;
		
		for(int i = 0; i < functionButtons.length; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
			functionButtons[i].setOpaque(true);
			functionButtons[i].setBorderPainted(false);
			functionButtons[i].setBackground(new Color(20, 20, 20));
			functionButtons[i].setForeground(Color.WHITE);
		}
		
		functionButtons[11].setBackground(Color.BLACK);
		functionButtons[11].setForeground(Color.WHITE);
		functionButtons[12].setBackground(Color.BLACK);
		functionButtons[12].setForeground(Color.WHITE);
		functionButtons[13].setBackground(new Color(164, 25, 25));
		functionButtons[13].setForeground(Color.WHITE);
		// Configurando botões de números
		
		for(int i = 0; i < numberButtons.length; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
			numberButtons[i].setOpaque(true);
			numberButtons[i].setBorderPainted(false);
			numberButtons[i].setBackground(Color.BLACK);
			numberButtons[i].setForeground(Color.WHITE);
		}

		// Painel
		
		panel = new JPanel();
		panel.setBounds(15, 100, 625, 300);
		panel.setBackground(grayBG);
		panel.setLayout(new GridLayout(6, 4, 3, 3));
		panel.add(percentButton);
		panel.add(clrElementButton);
		panel.add(clrFullButton);
		panel.add(clrUnitButton);
		panel.add(invButton);
		panel.add(sqrPowerButton);
		panel.add(sqrRootButton);
		panel.add(divButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(signalButton);
		panel.add(numberButtons[0]);
		panel.add(pointButton);
		panel.add(equButton);
		
		frame.add(panel);
	}
	
	
	public static void main(String[] args) {
		Calculator calc = new Calculator();
	}
	
	private void equButt() {
		if(!isResult) {
			currentnumber = Double.parseDouble(currentNumberPanel.getText());
			if(!(operator == 'r' || operator == 'p' || operator == 'i' || operator == '%'))
			isResult = true;
		}
		if(operator == 'f') result = Double.parseDouble(currentNumberPanel.getText());
		System.out.println(currentnumber);
		pastNumberPanelUpdate();
		calcResult(operator);
		currentNumberPanel.setText(String.valueOf(result));
		currentnumber = result;
		isDouble = false; // Essa condição não é de fato se o número é um ponto flutuante,
						  // ela se torna falsa, pois precisamos disso para o funcionamento
						  // correto do pointButton.
		isExpression = false;
		cleanable = true;
	}
	
	public void calcResult(char operator) {
		isDouble = true;
		switch(operator) {
			case '+':
				result = lastnumber + currentnumber;
				break;
			case '-':
				result = lastnumber - currentnumber;
				break;
			case 'x':
				result = lastnumber * currentnumber;
				break;
			case '÷':
				result = lastnumber / currentnumber;
				break;
			case '%':
				result = currentnumber/100; 			// Esta funcionalidade é diferente da calculadora do windows
				break;
			case 'p':
				result = Math.pow(currentnumber, 2);
				break;
			case 'r':
				result = Math.sqrt(currentnumber);
				break;
			case 'i':
				result = 1 / currentnumber;
				break;	
		}
	}
	
	private void actionPerformedIfExpression() {
		if(isExpression) {
			currentnumber = Double.parseDouble(currentNumberPanel.getText()); 
			calcResult(lastoperator);
			lastnumber = result;
			currentNumberPanel.setText(String.valueOf(result));
		}else {
			lastnumber = Double.parseDouble(currentNumberPanel.getText());
		}
		cleanable = true;
		isExpression = true;
		pastNumberPanelUpdate();
	}
	
	private void pastNumberPanelUpdate() {	
		if(isResult) {
			String aux = pastNumberPanel.getText().concat
					(String.valueOf(Double.parseDouble(currentNumberPanel.getText()))+'=');
			pastNumberPanel.setText(aux);
		}else {
			double aux = Double.parseDouble(currentNumberPanel.getText());
			if(operator == 'r' || operator == 'p' || operator == 'i' || operator == '%') {
				switch(operator) {
					case 'r':
						pastNumberPanel.setText("sqr("+String.valueOf(aux)+')');
						break;
					case 'p':
						pastNumberPanel.setText("pow("+String.valueOf(aux)+')');
						break;
					case 'i':
						pastNumberPanel.setText("1/"+String.valueOf(aux));
						break;
					case '%':
						pastNumberPanel.setText(String.valueOf(aux)+'%');
						break;
				}
			}else pastNumberPanel.setText(String.valueOf(aux)+operator);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i < numberButtons.length; i++) {
			if(e.getSource() == numberButtons[i]) {
					if(cleanable) {
						cleanable = false;
						currentNumberPanel.setText("");
					}
					currentNumberPanel.setText(currentNumberPanel.getText().concat(String.valueOf(i)));
				}
			}
		
		if(e.getSource() == pointButton) {
			if(!isDouble) {
				if(cleanable) {
					cleanable = false;
					currentNumberPanel.setText("");
					currentNumberPanel.setText(currentNumberPanel.getText().concat("0."));
				}
				else currentNumberPanel.setText(currentNumberPanel.getText().concat("."));
				isDouble = true;
				cleanable = false;
			}
		}
		
		if(e.getSource() == addButton) {
			lastoperator = operator;
			operator = '+';
			actionPerformedIfExpression();
		}
		if(e.getSource() == subButton) {
			lastoperator = operator;
			operator = '-';
			actionPerformedIfExpression();
		}
		if(e.getSource() == mulButton) {
			lastoperator = operator;
			operator = 'x';
			actionPerformedIfExpression();
		}
		if(e.getSource() == divButton) {
			lastoperator = operator;
			operator = '÷';
			actionPerformedIfExpression();
		}
		if(e.getSource() == sqrPowerButton) {
			lastoperator = operator;
			operator = 'p';
			actionPerformedIfExpression();
			equButt();
		}
		if(e.getSource() == sqrRootButton) {
			lastoperator = operator;
			operator = 'r';
			actionPerformedIfExpression();
			equButt();
		}
		if(e.getSource() == invButton) {
			lastoperator = operator;
			operator = 'i';
			actionPerformedIfExpression();
			equButt();
		}
		if(e.getSource() == percentButton) {
			lastoperator = operator;
			operator = '%';
			actionPerformedIfExpression();
			equButt();
		}
		
		if(e.getSource() == clrUnitButton) {
			if(currentNumberPanel.getText().length() == 1) currentNumberPanel.setText("0");
			else currentNumberPanel.setText(currentNumberPanel.getText().
											substring(0, currentNumberPanel.getText().length() - 1));
		}
		
		if(e.getSource() == clrElementButton) {
			currentNumberPanel.setText("0");
			isDouble = false;
			cleanable = true;
			isResult = false;
		}
		
		if(e.getSource() == clrFullButton) {
			currentNumberPanel.setText("0");
			pastNumberPanel.setText("");
			operator = 'f';
			lastnumber = 0;
			currentnumber = 0;
			isDouble = false;
			isExpression = false;
			cleanable = true;
			isResult = false;
		}
		
		if(e.getSource() == equButton) {
			equButt();
		}

		
	}

}
