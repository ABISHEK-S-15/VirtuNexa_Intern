import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    private Hotel hotel = new Hotel();

    private JTextField nameField, dateField, cancelIdField, availDateField;
    private JComboBox<String> roomBox, availRoomBox;
    private JTextArea outputArea;

    public MainFrame() {
        setTitle("Hotel Booking System");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createTaskSelectionPanel(), "task");
        mainPanel.add(createBookingPanel(), "booking");
        mainPanel.add(createCancelPanel(), "cancel");
        mainPanel.add(createAvailabilityPanel(), "availability");
        mainPanel.add(createViewPanel(), "view");

        add(mainPanel);
        cardLayout.show(mainPanel, "task");

        setVisible(true);
    }

    private JPanel createTaskSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Select Task", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(4, 1));
        ButtonGroup group = new ButtonGroup();
        JRadioButton book = new JRadioButton("Book Room");
        JRadioButton cancel = new JRadioButton("Cancel Reservation");
        JRadioButton check = new JRadioButton("Check Availability");
        JRadioButton view = new JRadioButton("View Reservations");

        group.add(book);
        group.add(cancel);
        group.add(check);
        group.add(view);

        center.add(book);
        center.add(cancel);
        center.add(check);
        center.add(view);

        panel.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton ok = new JButton("OK");
        JButton exit = new JButton("Exit");

        ok.addActionListener(e -> {
            if (book.isSelected()) cardLayout.show(mainPanel, "booking");
            else if (cancel.isSelected()) cardLayout.show(mainPanel, "cancel");
            else if (check.isSelected()) cardLayout.show(mainPanel, "availability");
            else if (view.isSelected()) cardLayout.show(mainPanel, "view");
        });

        exit.addActionListener(e -> System.exit(0));
        bottom.add(ok);
        bottom.add(exit);

        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createBookingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Room Booking", SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(3, 2));
        nameField = new JTextField();
        dateField = new JTextField();
        roomBox = new JComboBox<>(new String[]{"Single", "Double", "Suite"});

        form.add(new JLabel("Name:"));
        form.add(nameField);
        form.add(new JLabel("Date (YYYY-MM-DD):"));
        form.add(dateField);
        form.add(new JLabel("Room Type:"));
        form.add(roomBox);

        panel.add(form, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton ok = new JButton("OK");
        JButton back = new JButton("Back");
        JButton exit = new JButton("Exit");

        ok.addActionListener(e -> {
            String name = nameField.getText().trim();
            String date = dateField.getText().trim();
            String type = roomBox.getSelectedItem().toString();
            if (name.isEmpty() || date.isEmpty()) {
                showMessage("Name and Date are required.");
                return;
            }
            Reservation r = hotel.bookRoom(name, date, type);
            showMessage(r != null ? "Booked Successfully!\n" + r : "Room not available.");
        });

        back.addActionListener(e -> cardLayout.show(mainPanel, "task"));
        exit.addActionListener(e -> System.exit(0));

        bottom.add(ok);
        bottom.add(back);
        bottom.add(exit);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createCancelPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Cancel Reservation", SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(1, 2));
        cancelIdField = new JTextField();

        form.add(new JLabel("Reservation ID:"));
        form.add(cancelIdField);
        panel.add(form, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton ok = new JButton("OK");
        JButton back = new JButton("Back");
        JButton exit = new JButton("Exit");

        ok.addActionListener(e -> {
            try {
                int id = Integer.parseInt(cancelIdField.getText().trim());
                boolean success = hotel.cancelReservation(id);
                showMessage(success ? "Reservation canceled." : "Reservation not found.");
            } catch (NumberFormatException ex) {
                showMessage("Invalid ID.");
            }
        });

        back.addActionListener(e -> cardLayout.show(mainPanel, "task"));
        exit.addActionListener(e -> System.exit(0));

        bottom.add(ok);
        bottom.add(back);
        bottom.add(exit);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createAvailabilityPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Check Availability", SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2, 2));
        availDateField = new JTextField();
        availRoomBox = new JComboBox<>(new String[]{"Single", "Double", "Suite"});

        form.add(new JLabel("Date (YYYY-MM-DD):"));
        form.add(availDateField);
        form.add(new JLabel("Room Type:"));
        form.add(availRoomBox);
        panel.add(form, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton ok = new JButton("OK");
        JButton back = new JButton("Back");
        JButton exit = new JButton("Exit");

        ok.addActionListener(e -> {
            String date = availDateField.getText().trim();
            String type = availRoomBox.getSelectedItem().toString();
            boolean available = hotel.checkAvailability(date, type);
            showMessage(available ? "Room available." : "Room not available.");
        });

        back.addActionListener(e -> cardLayout.show(mainPanel, "task"));
        exit.addActionListener(e -> System.exit(0));

        bottom.add(ok);
        bottom.add(back);
        bottom.add(exit);
        panel.add(bottom, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Reservations", SwingConstants.CENTER), BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton back = new JButton("Back");
        JButton exit = new JButton("Exit");

        back.addActionListener(e -> {
            updateReservationList();
            cardLayout.show(mainPanel, "task");
        });

        exit.addActionListener(e -> System.exit(0));

        bottom.add(back);
        bottom.add(exit);
        panel.add(bottom, BorderLayout.SOUTH);

        return panel;
    }

    private void updateReservationList() {
        StringBuilder sb = new StringBuilder();
        List<Reservation> list = hotel.getReservations();
        if (list.isEmpty()) sb.append("No reservations found.");
        else for (Reservation r : list) sb.append(r).append("\n");
        outputArea.setText(sb.toString());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
