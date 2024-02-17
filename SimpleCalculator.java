import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class SimpleCalculator extends JFrame implements ActionListener 
{
 private JTextField textField;
 private double currentResult;
 private String currentOperator;
 SimpleCalculator() 
 {

 setTitle("Simple Calculator");
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setSize(300, 500); 
 setLayout(new BorderLayout());

 textField = new JTextField();
 textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
 add(textField, BorderLayout.NORTH);
 
 JPanel buttonPanel = new JPanel();
 buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
 
 String[] buttonLabels = {
 "7", "8", "9", "/",
 "4", "5", "6", "*",
 "1", "2", "3", "-",
 "0", ".", "=", "+"
 };
 
 for (String label : buttonLabels) 
 {
 JButton button = new JButton(label);
 button.addActionListener(this); 
 button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
 buttonPanel.add(button);
 }
 
 add(buttonPanel, BorderLayout.CENTER);

 currentResult = 0;
 currentOperator = "";
 }
 public void actionPerformed(ActionEvent e) 
 {
 JButton source = (JButton) e.getSource();
 String buttonText = source.getText();
 
 if (buttonText.equals("="))
 {
 try
 {
 evaluateExpression();
 }
 catch (ArithmeticException ex) 
 {
 textField.setText("Error: " + ex.getMessage());
 }
 } 
 else
 {

 if (Character.isDigit(buttonText.charAt(0)) || buttonText.equals(".")) 
 {

 textField.setText(textField.getText() + buttonText);
 }
 else 
 {

 if (!currentOperator.isEmpty())
 {
 
 try
 {
 evaluateExpression();
 } 
 catch (ArithmeticException ex)
 {
 textField.setText("Error: " + ex.getMessage());
 return;
 }
 }

 currentOperator = buttonText;
 currentResult = Double.parseDouble(textField.getText());
 textField.setText("");
 }
 }
 }
 private void evaluateExpression()
 {
 try
 {
 
 double operand2 = Double.parseDouble(textField.getText());

 switch (currentOperator) 
 {
 case "+":
 currentResult += operand2;
 break;
 case "-":
 currentResult -= operand2;
 break;
 case "*":
 currentResult *= operand2;
 break;
 case "/":
 if (operand2 == 0) 
 {
 throw new ArithmeticException("Divide by zero");
 }
 currentResult /= operand2;
 break;
 }

 textField.setText(Double.toString(currentResult));

 currentOperator = "";
 } 
 catch (NumberFormatException ex)
 {
 textField.setText("Error: Invalid input");
 }
 }
 public static void main(String[] args)
 {

 SimpleCalculator calculator = new SimpleCalculator();
 calculator.setVisible(true);
 }
}