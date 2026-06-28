import java.awt.*;
import javax.swing.*;

public class MyForm4 extends JFrame{
    GridLayout gl = new GridLayout(3,3);

    MyForm4() {
        super("Belajar GUI");
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(400,400));
        getContentPane().setBackground(Color.GRAY);
        setLocationRelativeTo(null);
        setLayout(gl);
        add(new JButton("tombol 1"));
        add(new JButton("tombol 2"));
        add(new JButton("tombol 3"));
        add(new JButton("tombol 4"));
        add(new JButton("tombol 5"));
        add(new JButton("tombol 6"));
        add(new JButton("tombol 7"));
        add(new JButton("tombol 8"));
        setVisible(true);
    }

    public static void main(String[] args) {
        MyForm4 form = new MyForm4();
    }
}
