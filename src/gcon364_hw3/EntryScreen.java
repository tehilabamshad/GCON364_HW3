package gcon364_hw3;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public  class EntryScreen extends JFrame {

    private final JComboBox salutationField;
    private final JTextField fNameField, lNameField, addressField, cityField, 
            stateField, zipField, phoneNumField, ssNumField;
    private final JRadioButton employee, customer;
    private final ButtonGroup group;
    private final JLabel salutationLabel, fNameLabel, lNameLabel, addressLabel, cityLabel, 
            stateLabel, zipLabel, phoneNumLabel, ssNumLabel, typeLabel;
    private final JTextArea area;
    private final JButton button;


    public EntryScreen() {
        super("Data Entry Screen");
        setLayout(new FlowLayout());
        
        salutationLabel = new JLabel("Salutation");
        add(salutationLabel);
        String salutations[] = {"Mr.", "Mrs.", "Ms.", "Miss", "Dr."};
        salutationField = new JComboBox(salutations);
        add(salutationField);

        fNameLabel = new JLabel("First Name");
        add(fNameLabel);
        fNameField = new JTextField(10);
        add(fNameField);
        
        lNameLabel = new JLabel("Last Name");
        add(lNameLabel);
        lNameField = new JTextField(10);
        add(lNameField);
        
        addressLabel = new JLabel("Address");
        add(addressLabel);
        addressField = new JTextField(10);
        add(addressField);
        
        cityLabel = new JLabel("City");
        add(cityLabel);
        cityField = new JTextField(10);
        add(cityField);
        
        stateLabel = new JLabel("State");
        add(stateLabel);
        stateField = new JTextField(10);
        add(stateField);
        
        zipLabel = new JLabel("Zip Code");
        add(zipLabel);
        zipField = new JTextField(10);
        add(zipField);
        
        phoneNumLabel = new JLabel("Phone Number");
        add(phoneNumLabel);
        phoneNumField = new JTextField(10);
        add(phoneNumField);
        
        ssNumLabel = new JLabel("SS Number");
        add(ssNumLabel);
        ssNumField = new JTextField(10);
        add(ssNumField);
        
        typeLabel = new JLabel("Type");
        add(typeLabel);
        employee = new JRadioButton("Employee");
        customer = new JRadioButton("Customer");
        group = new ButtonGroup();
        group.add(employee);
        group.add(customer);
        add(employee);
        add(customer);
        
        button = new JButton("Done");
        add(button);
        
        area = new JTextArea();
        area.setEditable(false);
        add(area);
        
        registerHandlers();
    }

    private void registerHandlers() {
        // Register event handlers
        TextFieldHandler handler = new TextFieldHandler();
        fNameField.addActionListener(handler);
        lNameField.addActionListener(handler);
        addressField.addActionListener(handler);
        cityField.addActionListener(handler);
        stateField.addActionListener(handler);
        zipField.addActionListener(handler);
        phoneNumField.addActionListener(handler);
        ssNumField.addActionListener(handler);
        
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                buttonPressed();
                            }

            private void buttonPressed() {
                String type, errMess = "";
                if(employee.isSelected()){
                    type = "Employee";
                } else {
                    type = "Customer";
                }
                
                if(!(validateLetters(fNameField.getText()))){
                    errMess+="Invalid First Name. Please enter letters only\n";
                }
                if(!(validateLetters(lNameField.getText()))){
                    errMess+="Invalid Last Name. Please enter letters only\n";
                }     
                if(!(validateLetters(cityField.getText()))){
                    errMess+="Invalid City. Please enter letters only\n";
                }  
                if(!(validateLetters(stateField.getText()))){
                    errMess+="Invalid State. Please enter letters only\n";
                } 
                if(!(validateDigit(zipField.getText()))){
                    errMess+="Invalid Zip Code. Please enter digits only\n";
                }
                if(!(validateDigit(phoneNumField.getText()))){
                    errMess+="Invalid Phone Number. Please enter digits only\n";
                }
                if(!(validateDigit(ssNumField.getText()))){
                    errMess+="Invalid SS Number. Please enter digits only\n";
                }
                
                if(errMess.equals("")){
                    try {
                  FileWriter myWriter = new FileWriter("test.txt", true);
                   myWriter.append("Name: " + salutationField.getSelectedItem() + " " 
                        + fNameField.getText() + " " + lNameField.getText() + 
                        "\tAddress: " + addressField.getText() + " " 
                        + cityField.getText() + ", " + stateField.getText() 
                        + "  " + zipField.getText() 
                        + " Phone Number: " + phoneNumField.getText() 
                        + " SS Num: " + ssNumField.getText() 
                        + " Type: " + type );
                   myWriter.append("\n");
                   myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                         } 
                //area.append("\n");
                resetFields(); 
                } else {
                  JOptionPane.showMessageDialog(null, errMess);
                }
            }           
        });      
    }

    private boolean validateDigit(String input) {
        return input.chars().allMatch(Character::isDigit);
    }
           
    private boolean validateLetters(String input){
        return input.chars().allMatch(Character::isLetter);
    }
    
    private void resetFields() {
        salutationField.setSelectedIndex(0);
        fNameField.setText("");
        lNameField.setText("");
        addressField.setText("");
        cityField.setText("");
        zipField.setText("");
        stateField.setText("");
        phoneNumField.setText("");
        ssNumField.setText("");
        group.clearSelection();
    }

    private class TextFieldHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String string = "";
            // User pressed enter in textField1
            if (event.getSource() == fNameField) {
                string = String.format("First Name: %s", event.getActionCommand());
            } // User pressed enter in textField2
            else if (event.getSource() == lNameField) {
                string = String.format("Last Name: %s", event.getActionCommand());
            } // User pressed enter in textField3
            else if (event.getSource() == addressField) {
                string = String.format("Address: %s", event.getActionCommand());
            }
            else if (event.getSource() == cityField) {
                string = String.format("City: %s", event.getActionCommand());
            }
            else if (event.getSource() == stateField) {
                string = String.format("State: %s", event.getActionCommand());
            }
            else if (event.getSource() == zipField) {
                string = String.format("Zip Code: %s", event.getActionCommand());
            }
            else if (event.getSource() == phoneNumField) {
                string = String.format("Phone Number: %s", event.getActionCommand());
            }
            else if (event.getSource() == ssNumField) {
                string = String.format("SS Number: %s", event.getActionCommand());
            }
            
            // display JtextField content
            JOptionPane.showMessageDialog(null, string);
        }

    }
}
