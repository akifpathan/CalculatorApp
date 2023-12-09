import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Calculator implements ActionListener {
    JFrame frame;
    Container contentPane;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, eqButton, cButton, acButton, sgnButton, sqrtButton;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD, 20);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean flag = false;
    boolean overFlow = false;
    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);

        contentPane = frame.getContentPane();
        contentPane.setBackground(Color.lightGray);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 70);
        textField.setFont(new Font("Ink Free", Font.BOLD, 25));
        textField.setEditable(false);
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setBackground(Color.decode("#5c7c7f"));
        textField.setCaretColor(Color.decode("#5c7c7f"));
        textField.setForeground(Color.black);

        addButton = new JButton("+");
        subButton = new JButton("−");
        mulButton = new JButton("×");
        divButton = new JButton("÷");
        decButton = new JButton(".");
        eqButton = new JButton("=");
        cButton = new JButton("C");
        acButton = new JButton("AC");
        sgnButton = new JButton("±");
        sqrtButton = new JButton("√");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = eqButton;
        functionButtons[6] = cButton;
        functionButtons[7] = acButton;
        functionButtons[8] = sqrtButton;
        functionButtons[9] = sgnButton;

        for(int i=0;i<10;i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
            functionButtons[i].setBackground(Color.decode("#4f4e49"));
            functionButtons[i].setForeground(Color.white);
        }
        functionButtons[6].setBackground(Color.decode("#8b4000"));
        functionButtons[7].setBackground(Color.decode("#8b0000"));
        functionButtons[4].setBackground(Color.decode("#1c1c1c"));

        for(int i=0;i<10;i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(Color.decode("#1c1c1c"));
            numberButtons[i].setForeground(Color.lightGray);
        }



        panel = new JPanel();
        panel.setBounds(50, 120, 300, 300);
        panel.setLayout(new GridLayout(5,4,10, 10));
        panel.setBackground(Color.lightGray);

        panel.add(acButton);
        panel.add(cButton);
        panel.add(sqrtButton);
        panel.add(sgnButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(divButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(mulButton);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(subButton);

        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(eqButton);
        panel.add(addButton);

        frame.add(panel);
        frame.add(textField);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        for(int i=0;i<10;i++) {
            if(e.getSource() == numberButtons[i]) {
                if(flag || overFlow) {
                    textField.setText("");
                    overFlow = false;
                }
                if(textField.getText().length()<16) {
                    textField.setText(textField.getText().concat(String.valueOf(i)));
                }
                flag = false;
            }
        }
        if(e.getSource()==decButton) {
            if(flag || textField.getText().isEmpty() || overFlow) {
                textField.setText("0");
                overFlow = false;
            }
            if(!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }
            flag = false;
        }
        if(e.getSource()==addButton) {
            if(textField.getText().isEmpty() || overFlow) {
                textField.setText("0");
                overFlow = false;
            }
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            flag = true;
        }
        if(e.getSource()==subButton) {
            if(textField.getText().isEmpty() || overFlow) {
                textField.setText("0");
                overFlow = false;
            }
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            flag = true;
        }
        if(e.getSource()==mulButton) {
            if(textField.getText().isEmpty() || overFlow) {
                textField.setText("0");
                overFlow = false;
            }
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            flag = true;
        }
        if(e.getSource()==divButton) {
            if(textField.getText().isEmpty() || overFlow) {
                textField.setText("0");
                overFlow = false;
            }
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            flag = true;
        }
        if(e.getSource()==sqrtButton) {
            if(textField.getText().isEmpty() || overFlow) {
                textField.setText("0");
                overFlow = false;
            }
            num2 = Double.parseDouble(textField.getText());
            num2 = sqrt(num2);
            textField.setText(String.valueOf(num2));
            num1 = num2;
        }
        if(e.getSource()==eqButton) {
            if(textField.getText().isEmpty() || overFlow) {
                textField.setText("0");
                overFlow = false;
            }
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    operator = ' ';
                    break;
                case '-':
                    result = num1 - num2;
                    operator = ' ';
                    break;
                case '*':
                    result = num1 * num2;
                    operator = ' ';
                    break;
                case '/':
                    result = num1 / num2;
                    operator = ' ';
                    break;
                default:
                    result = num2;
            }
            if(abs(result) < 1e15) {
                StringBuilder sb = new StringBuilder(String.valueOf(result));
                while (sb.length() > 16) {
                    sb.deleteCharAt(sb.length()-1);
                }
                textField.setText(sb.toString());
                overFlow = false;
            }
            else {
                textField.setText("Error");
                overFlow = true;
                result = 0;
            }
            num1 = result;
        }
        if(e.getSource()==acButton) {
            textField.setText("");
        }
        if(e.getSource()==cButton) {
            if(overFlow) {
                overFlow = false;
                textField.setText("");
            }
            StringBuilder sb = new StringBuilder(textField.getText());
            if(!sb.isEmpty()) {
                sb.deleteCharAt(sb.length()-1);
            }
            textField.setText(sb.toString());
        }
        if(e.getSource()==sgnButton) {
            if(textField.getText().isEmpty() || overFlow) {
                textField.setText("0");
                overFlow = false;
            }
            StringBuilder sb = new StringBuilder(textField.getText());
            if(sb.charAt(0) == '-') {
                sb.deleteCharAt(0);
            }
            else {
                if(sb.length() != 1 || sb.charAt(0)!='0') {
                    sb.insert(0,'-');
                }
            }
            textField.setText(sb.toString());
        }

    }
}
