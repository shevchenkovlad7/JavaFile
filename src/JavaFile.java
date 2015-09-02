/**
 * Created by Vlad on 02.09.2015.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JavaFile extends JFrame {

    public JavaFile() {
        super("JavaFile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        final JPanel rootContent = new JPanel();
        rootContent.setLayout(new BoxLayout(rootContent, BoxLayout.Y_AXIS));
        final JScrollPane rootContentScroll = new JScrollPane(rootContent);
        File[] roots = File.listRoots();
        final JList list = new JList(roots);
        list.setVisibleRowCount(5);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                rootContent.removeAll();
                File root = (File) list.getSelectedValue();
                File[] children = root.listFiles();
                if (children != null) {
                    for (int i = 0; i < children.length; i++) {
                        JLabel label = new JLabel(children[i].getName());
                        rootContent.add(label);
                    }
                }
                rootContent.repaint();
                rootContentScroll.revalidate();
            }
        });

        mainPanel.add(new JScrollPane(list), BorderLayout.NORTH);
        mainPanel.add(rootContentScroll, BorderLayout.CENTER);

        getContentPane().add(mainPanel);

        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new JavaFile();
            }
        });
    }
}