import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Thread_Project {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

			public void run() {
                JFrame f = new JFrame("Animation");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Panel panel = new Panel();
               Thread thread = new Thread(panel);
               thread.start();
                f.add(panel);
		        f.setSize(470,470);
		        f.setVisible(true);
			}
		});
    }
}