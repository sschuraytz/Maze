import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;

public class MazeGUI extends JFrame {
    private MazeComponent mazeComponent = new MazeComponent();
    private JButton solutionButton = new JButton("Show Solution");

    public MazeGUI() {
        setTitle("a-MAZE-ing");
        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());

        JLabel top = new JLabel("  ");
        JLabel left = new JLabel("  ");

        root.add(top, BorderLayout.NORTH);
        root.add(left, BorderLayout.WEST);
        root.add(solutionButton, BorderLayout.SOUTH);
        root.add(mazeComponent, BorderLayout.CENTER);

        setContentPane(root);
        root.setBackground(Color.BLACK);
        showSolution();
        root.setVisible(true);
    }

    public void showSolution() {
        solutionButton.addActionListener(event -> {
            mazeComponent.solutionIsVisible = true;
            repaint();
        });
    }
}