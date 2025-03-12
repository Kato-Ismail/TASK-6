import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiScreenApp {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    // Variables to store input data
    private String inputText = "";
    private String selectedOption = "";

    // Constructor to initialize the cardPanel and cardLayout
    public MultiScreenApp(JPanel cardPanel, CardLayout cardLayout) {
        this.cardPanel = cardPanel;
        this.cardLayout = cardLayout;
    }

    // Main page with buttons to navigate to other pages
    public JPanel createMainPage() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        JButton toInputPageButton = new JButton("Go to Input");
        JButton toDisplayPageButton = new JButton("Go to Display");
        JButton toUpdatePageButton = new JButton("Go to Update");
        JButton toInfoPageButton = new JButton("App Info");

        toInputPageButton.addActionListener(e -> cardLayout.show(cardPanel, "Input"));
        toDisplayPageButton.addActionListener(e -> cardLayout.show(cardPanel, "Display"));
        toUpdatePageButton.addActionListener(e -> cardLayout.show(cardPanel, "Update"));
        toInfoPageButton.addActionListener(e -> cardLayout.show(cardPanel, "Info"));

        mainPanel.add(toInputPageButton);
        mainPanel.add(toDisplayPageButton);
        mainPanel.add(toUpdatePageButton);
        mainPanel.add(toInfoPageButton);

        return mainPanel;
    }

    // Input page with combo box, text field, text area, radio buttons, tree, and navigation buttons
    public JPanel createInputPage() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(12, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextArea descriptionArea = new JTextArea();

        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Category 1", "Category 2", "Category 3"});

        JLabel optionsLabel = new JLabel("Options:");
        JRadioButton option1 = new JRadioButton("Option 1");
        JRadioButton option2 = new JRadioButton("Option 2");
        JRadioButton option3 = new JRadioButton("Option 3");

        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);

        JTree dataTree = new JTree(new DefaultTreeModel(new DefaultMutableTreeNode("Root")));

        JSeparator separator = new JSeparator();

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(e -> {
            inputText = nameField.getText() + " - " + descriptionArea.getText();
            selectedOption = (option1.isSelected() ? "Option 1" : "Option 2") + " - " + (String) categoryComboBox.getSelectedItem();
            cardLayout.show(cardPanel, "Display");
        });
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionArea);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryComboBox);
        inputPanel.add(optionsLabel);
        inputPanel.add(option1);
        inputPanel.add(new JLabel()); // Empty cell
        inputPanel.add(option2);
        inputPanel.add(option3);
        inputPanel.add(new JLabel("Data Tree:"));
        inputPanel.add(new JScrollPane(dataTree));
        inputPanel.add(separator);
        inputPanel.add(backButton);
        inputPanel.add(nextButton);

        return inputPanel;
    }

    // Display page with stored information
    public JPanel createDisplayPage() {
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());

        JTextArea displayArea = new JTextArea();
        displayArea.setText("Stored Input:\n" + inputText + "\nSelected Option: " + selectedOption);
        displayArea.setEditable(false);

        JSeparator separator = new JSeparator();

        JButton backButton = new JButton("Back");
        JButton nextButton = new JButton("Next");

        nextButton.addActionListener(e -> cardLayout.show(cardPanel, "Update"));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Input"));

        displayPanel.add(displayArea, BorderLayout.CENTER);
        displayPanel.add(separator, BorderLayout.SOUTH);

        JPanel navigationPanel = new JPanel();
        navigationPanel.add(backButton);
        navigationPanel.add(nextButton);

        displayPanel.add(navigationPanel, BorderLayout.SOUTH);

        return displayPanel;
    }

    // Update page where users can modify entered data
    public JPanel createUpdatePage() {
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout(12, 2));

        JLabel updateLabel = new JLabel("Update Name:");
        JTextField updateField = new JTextField(inputText);

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");

        saveButton.addActionListener(e -> {
            inputText = updateField.getText();
            cardLayout.show(cardPanel, "Display");
        });
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Display"));

        updatePanel.add(updateLabel);
        updatePanel.add(updateField);
        updatePanel.add(saveButton);
        updatePanel.add(backButton);

        return updatePanel;
    }

    // Info page providing details about the app
    public JPanel createInfoPage() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());

        JTextArea infoArea = new JTextArea();
        infoArea.setText("This is a simple Java Swing application demonstrating smooth navigation "
                + "between multiple pages. The app allows users to input, update, and display data "
                + "while navigating through different pages. Enjoy using it!");
        infoArea.setEditable(false);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));

        infoPanel.add(infoArea, BorderLayout.CENTER);
        infoPanel.add(backButton, BorderLayout.SOUTH);

        return infoPanel;
    }
}

