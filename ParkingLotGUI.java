import java.awt.*;
import javax.swing.*;

// GUI class for Parking Lot System
public class ParkingLotGUI {
    private ParkingLot lot;

    public ParkingLotGUI() {
        // Ask for parking capacity
        int capacity = Integer.parseInt(JOptionPane.showInputDialog("Enter Parking Lot Capacity:"));
        lot = new ParkingLot(capacity);

        // Create main frame
        JFrame frame = new JFrame("Parking Lot Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 500);
        frame.setLayout(new BorderLayout(10, 10));

        // Input panel (Car Number & Owner)
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JTextField carNumField = new JTextField();
        JTextField ownerField = new JTextField();

        inputPanel.add(new JLabel("Car Number:"));
        inputPanel.add(carNumField);
        inputPanel.add(new JLabel("Owner Name:"));
        inputPanel.add(ownerField);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 8, 8));
        JButton parkBtn = new JButton("Park Car");
        JButton removeBtn = new JButton("Remove Car");
        JButton showForwardBtn = new JButton("Show (Front → End)");
        JButton showBackwardBtn = new JButton("Show (End → Front)");
        JButton clearBtn = new JButton("Clear Display");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(parkBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(showForwardBtn);
        buttonPanel.add(showBackwardBtn);
        buttonPanel.add(clearBtn);
        buttonPanel.add(exitBtn);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Output display area
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.SOUTH);

        //Button Actions

        //Park Car
        parkBtn.addActionListener(e -> {
            String carNum = carNumField.getText().trim();
            String owner = ownerField.getText().trim();

            if (carNum.isEmpty() || owner.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both Car Number and Owner Name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            lot.parkCar(carNum, owner);
            displayArea.append("Car " + carNum + " parked successfully.\n");
            carNumField.setText("");
            ownerField.setText("");
        });

        // Remove Car
        removeBtn.addActionListener(e -> {
            String carNum = carNumField.getText().trim();

            if (carNum.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter Car Number to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            lot.removeCar(carNum);
            displayArea.append("Car " + carNum + " removed (if existed).\n");
            carNumField.setText("");
        });

        //Show Forward (Front → End)
        showForwardBtn.addActionListener(e -> {
            displayArea.append("\nCars in Parking Lot (Front → End):\n");
            Car current = lot.getHead();  // ✅ Fixed private access
            if (current == null) {
                displayArea.append("Parking Lot is empty.\n");
                return;
            }
            while (current != null) {
                displayArea.append("Car No: " + current.carNumber + " | Owner: " + current.ownerName + "\n");
                current = current.next;
            }
        });

        //Show Backward (End → Front)
        showBackwardBtn.addActionListener(e -> {
            displayArea.append("\nCars in Parking Lot (End → Front):\n");
            Car current = lot.getTail();  //Fixed private access
            if (current == null) {
                displayArea.append("Parking Lot is empty.\n");
                return;
            }
            while (current != null) {
                displayArea.append("Car No: " + current.carNumber + " | Owner: " + current.ownerName + "\n");
                current = current.prev;
            }
        });

        //Clear display
        clearBtn.addActionListener(e -> displayArea.setText(""));

        //Exit
        exitBtn.addActionListener(e -> System.exit(0));

        // Make frame visible
        frame.setVisible(true);
    }

    // Main method to run the GUI
    public static void main(String[] args) {
        new ParkingLotGUI();
    }
}
