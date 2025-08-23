import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField textField;
    private String operator = "";
    private double num1, num2, result;

    public Calculator() {
        // Frame setup
        setTitle("Calculator");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 
        getContentPane().setBackground(new Color(240, 248, 255)); 

       
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 28));
        textField.setPreferredSize(new Dimension(350, 70)); 
        textField.setBackground(new Color(255, 228, 225));
        textField.setHorizontalAlignment(JTextField.RIGHT); 
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true)); 
        displayPanel.add(textField, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 12, 12));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "reset", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.setBackground(new Color(224, 255, 255));
            button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
            button.setFocusPainted(false); 
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            textField.setText(textField.getText() + command);
        } else if (command.equals("reset")) {
            textField.setText("");
            operator = "";
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/":
                    if (num2 != 0) result = num1 / num2;
                    else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }
            textField.setText(String.valueOf(result));
            operator = "";
        } else { // operator (+ - * /)
            num1 = Double.parseDouble(textField.getText());
            operator = command;
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}

