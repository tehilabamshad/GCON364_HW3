
package gcon364_hw3;

import javax.swing.JFrame;

public class GCON364_HW3 {

    public static void main(String[] args) {
        EntryScreen textFieldClass = new EntryScreen();               // Instantiate the MyLabelClass
        textFieldClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Include a close operation
        textFieldClass.setSize(1100, 200);                               // Size of the window
        textFieldClass.setVisible(true);     
    }
    
}
