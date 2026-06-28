import javax.swing.*;
import java.awt.*;

public class MyForm extends JFrame {
    MyForm(){
        super("Belajar GUI");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.PINK);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        MyForm form = new MyForm();
    }
}