import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ParkingLotGUI {
    private ParkingLot lot;

    public ParkingLotGUI() {
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter Parking Lot Capacity:"));
        lot = new ParkingLot(capacity);

        // Frame setup
        JFrame frame = new JFrame("Parking Lot Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField carNumField = new JTextField();
        JTextField ownerField = new JTextField();

        inputPanel.add(new JLabel("Car Number:"));
        inputPanel.add(carNumField);
        inputPanel.add(new JLabel("Owner Name:"));
        inputPanel.add(ownerField);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        JButton parkBtn = new JButton("Park Car");
        JButton removeBtn = new JButton("Remove Car");
        JButton showForwardBtn = new JButton("Show (Front → End)");
        JButton showBackwardBtn = new JButton("Show (End → Front)");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(parkBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(showForwardBtn);
        buttonPanel.add(showBackwardBtn);
        buttonPanel.add(exitBtn);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Output area
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        frame.add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        // Button Actions
        parkBtn.addActionListener(e -> {
            String carNum = carNumField.getText();
            String owner = ownerField.getText();
            lot.parkCar(carNum, owner);
            displayArea.setText("Car " + carNum + " parked.\n");
            carNumField.setText("");
            ownerField.setText("");
        });

        removeBtn.addActionListener(e -> {
            String carNum = carNumField.getText();
            lot.removeCar(carNum);
            displayArea.setText("Car " + carNum + " removed.\n");
            carNumField.setText("");
        });

        showForwardBtn.addActionListener(e -> {
            displayArea.setText("");
            displayArea.append("Cars in Parking Lot (Front → End):\n");
            Car current = lot.head;
            while (current != null) {
                displayArea.append("Car No: " + current.carNumber + " | Owner: " + current.ownerName + "\n");
                current = current.next;
            }
        });

        showBackwardBtn.addActionListener(e -> {
            displayArea.setText("");
            displayArea.append("Cars in Parking Lot (End → Front):\n");
            Car current = lot.tail;
            while (current != null) {
                displayArea.append("Car No: " + current.carNumber + " | Owner: " + current.ownerName + "\n");
                current = current.prev;
            }
        });

        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ParkingLotGUI();
    }
}
