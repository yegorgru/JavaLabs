import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallRunner extends JFrame {
    JPanel panel = new JPanel();
    JButton btn = new JButton("Add point");

    public BallRunner() {
        setBounds(100, 200, 270, 350);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        btn.setBounds(50, 10, 160, 20);
        contentPane.add(btn);
        panel.setBounds(30, 40, 200, 200);
        panel.setBackground(Color.WHITE);
        contentPane.add(panel);

        btn.addActionListener(ev -> {
            new BallThread(panel).start();
            repaint();
        });
    }

    public static void main(String[] args) {
        BallRunner frame =
                new BallRunner();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}