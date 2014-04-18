import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingExample8 extends JFrame {
  public SwingExample8() {
    this.getContentPane().setLayout(new FlowLayout());
    JButton btn = new JButton("Click Me");
    btn.setPreferredSize(new Dimension(400,200));
    add(btn);
    btn.addActionListener(e -> {
        btn.setText("Clicked");
        btn.setEnabled(false);
      }
    );
  }

  private static void createAndShowGUI() {
    JFrame frame = new SwingExample8();
    frame.pack();
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI(); 
      }
    });
  }
}
