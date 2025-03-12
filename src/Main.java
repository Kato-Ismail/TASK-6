import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Multi-Screen Navigation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.setLocationRelativeTo(null);

            // Setup card layout for the screens
            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            // Create an instance of MultiScreenApp
            MultiScreenApp multiScreenApp = new MultiScreenApp(cardPanel, cardLayout);

            // Add different screens as JPanel to cardPanel
            cardPanel.add(multiScreenApp.createMainPage(), "Main");
            cardPanel.add(multiScreenApp.createInputPage(), "Input");
            cardPanel.add(multiScreenApp.createDisplayPage(), "Display");
            cardPanel.add(multiScreenApp.createUpdatePage(), "Update");
            cardPanel.add(multiScreenApp.createInfoPage(), "Info");

            frame.add(cardPanel);
            frame.setVisible(true);
        });
    }
}
