package GraphicsInJava;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;

public class MyNote extends JFrame {
    MyNote(String title) {
        super(title);
        Container c = getContentPane(); // Функцыя базавага класа JFrame
        JTextField tf = new JTextField("Input text", 50); // 50 - max колькасць сімвалаў у радку
        c.add(tf, BorderLayout.NORTH);
        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        c.add(ta);
        JPanel p = new JPanel();
        c.add(p, BorderLayout.SOUTH);
        JButton b = new JButton("Transfer");
        p.add(b);
        tf.addActionListener(new TextMove(tf, ta)); // Дабаўляем апрацоўку паведамлення
        b.addActionListener(new TextMove(tf, ta));
        setSize(300, 200); // Памер аб’екта акна
        setVisible(true);
    }
    public static void main(String[] args) { // Старт з кансолі!
        JFrame f = new MyNote("ActionEvent");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Павінна быць у кожным спадчынніку JFrame
        // Функцыя азначае закрыць акно па патрэбе. Яна не выконваецца адразу.
    }
}

class TextMove implements ActionListener {
    private JTextField tf;
    private JTextArea ta;
    TextMove(JTextField tf, JTextArea ta) {
        this.tf = tf;
        this.ta = ta;
    }
    public void actionPerformed(ActionEvent ae) {
        ta.append(tf.getText() + "\n");
    }
}