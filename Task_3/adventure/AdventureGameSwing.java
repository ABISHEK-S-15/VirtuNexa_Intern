package adventure;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AdventureGameSwing {
    private JFrame frame;
    private JTextArea storyArea;
    private JPanel choicesPanel;
    private Map<String, StoryNode> storyMap;
    private StoryNode currentNode;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdventureGameSwing().startGame());
    }

    public void startGame() {
        storyMap = StoryLoader.loadStory();
        currentNode = storyMap.get("start");
    
        frame = new JFrame("Chronicles of Aetherion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
    
        // Set background panel with image
        BackgroundPanel backgroundPanel = new BackgroundPanel("C:/python_projects/Java programs/virtunexa_intern/Task_3/adventure/resources/background.png");
        backgroundPanel.setLayout(new BorderLayout());
    
        storyArea = new JTextArea();
        storyArea.setEditable(false);
        storyArea.setLineWrap(true);
        storyArea.setWrapStyleWord(true);
        storyArea.setOpaque(false); // transparent background
        storyArea.setFont(new Font("Times new Roman", Font.PLAIN, 30));
        storyArea.setForeground(Color.WHITE); // contrast against background
    
        JScrollPane scrollPane = new JScrollPane(storyArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
    
        choicesPanel = new JPanel();
        choicesPanel.setOpaque(false); // transparent panel
        choicesPanel.setLayout(new GridLayout(0, 1));
    
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
        backgroundPanel.add(choicesPanel, BorderLayout.SOUTH);
    
        frame.setContentPane(backgroundPanel);
        updateStory();
        frame.setVisible(true);
    }

    private void updateStory() {
        storyArea.setText(currentNode.description);
        choicesPanel.removeAll();

        if (currentNode.choices.isEmpty()) {
            JButton endButton = new JButton("Exit Game");
            endButton.addActionListener(e -> System.exit(0));
            choicesPanel.add(endButton);
        } else {
            for (Map.Entry<Integer, Choice> entry : currentNode.choices.entrySet()) {
                Choice choice = entry.getValue();
                JButton button = new JButton(choice.text);
                button.setSize(200, 100);
                button.addActionListener(e -> {
                    currentNode = storyMap.get(choice.nextNodeId);
                    updateStory();
                });
                choicesPanel.add(button);
            }
        }

        choicesPanel.revalidate();
        choicesPanel.repaint();
    }
}