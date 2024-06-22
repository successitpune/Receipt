package com.Receipt.Receipt;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import com.Receipt.Receipt.Entity.Receipt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
public class ReceiptApplication {

	private static JPanel mainPanel;
	private static JTable table; // Declare table as a class-level variable

	public static void main(String[] args) {
		SpringApplication.run(ReceiptApplication.class, args);
		System.setProperty("java.awt.headless", "false");

		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Receipt Application");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);

		// Create the menu bar
		JMenuBar menuBar = new JMenuBar();

		// Create menus
		JMenu menu = new JMenu("Menu");

		// Create menu items
		JMenuItem formMenuItem = new JMenuItem("Form");
		JMenuItem receiptMenuItem = new JMenuItem("Receipt");
		JMenuItem dataTableMenuItem = new JMenuItem("Data Table");

		// Add menu items to menu
		menu.add(formMenuItem);
		menu.add(receiptMenuItem);
		menu.add(dataTableMenuItem);

		// Add menu to menu bar
		menuBar.add(menu);

		// Set the menu bar for the frame
		frame.setJMenuBar(menuBar);

		// Main panel with CardLayout
		CardLayout cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		// Form panel
		JPanel formPanel = new JPanel();
		placeFormComponents(formPanel);
		mainPanel.add(formPanel, "Form");

		// Receipt panel
		JPanel receiptPanel = new JPanel();
		placeReceiptComponents(receiptPanel);
		mainPanel.add(receiptPanel, "Receipt");

		// Data Table panel
		JPanel dataTablePanel = new JPanel();
		placeDataTableComponents(dataTablePanel);
		mainPanel.add(dataTablePanel, "Data Table");

		// Add action listeners for menu items
		formMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Form"));
		receiptMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Receipt"));
		dataTableMenuItem.addActionListener(e -> cardLayout.show(mainPanel, "Data Table"));

		frame.add(mainPanel);
		frame.setVisible(true);
	}

	private static void placeFormComponents(JPanel panel) {
		panel.setLayout(null);

		JLabel CustomernameLabel = new JLabel("Customer Name:");
		CustomernameLabel.setBounds(10, 20, 160, 25);
		panel.add(CustomernameLabel);

		JTextField CustomernameText = new JTextField(20);
		CustomernameText.setBounds(180, 20, 160, 25);
		panel.add(CustomernameText);

		JLabel ContactnoLabel = new JLabel("Contact No:");
		ContactnoLabel.setBounds(10, 50, 160, 25);
		panel.add(ContactnoLabel);

		JTextField ContactnoText = new JTextField(20);
		ContactnoText.setBounds(180, 50, 160, 25);
		panel.add(ContactnoText);

		JLabel AddressLabel = new JLabel("Address:");
		AddressLabel.setBounds(10, 80, 160, 25);
		panel.add(AddressLabel);

		JTextField AddressText = new JTextField(20);
		AddressText.setBounds(180, 80, 160, 25);
		panel.add(AddressText);

		JLabel CompanynameLabel = new JLabel("Company Name:");
		CompanynameLabel.setBounds(10, 110, 160, 25);
		panel.add(CompanynameLabel);

		JTextField CompanynameText = new JTextField(20);
		CompanynameText.setBounds(180, 110, 160, 25);
		panel.add(CompanynameText);

		JLabel ModelnoLabel = new JLabel("Model No:");
		ModelnoLabel.setBounds(10, 140, 160, 25);
		panel.add(ModelnoLabel);

		JTextField ModelnoText = new JTextField(20);
		ModelnoText.setBounds(180, 140, 160, 25);
		panel.add(ModelnoText);

		JLabel IEI1Label = new JLabel("IMEI 1:");
		IEI1Label.setBounds(10, 170, 160, 25);
		panel.add(IEI1Label);

		JTextField IMEI1Text = new JTextField(20);
		IMEI1Text.setBounds(180, 170, 160, 25);
		panel.add(IMEI1Text);

		JLabel IMEI2Label = new JLabel("IMEI 2:");
		IMEI2Label.setBounds(10, 200, 160, 25);
		panel.add(IMEI2Label);

		JTextField IMEI2Text = new JTextField(20);
		IMEI2Text.setBounds(180, 200, 160, 25);
		panel.add(IMEI2Text);

		JLabel PhysicalconditionLabel = new JLabel("Physical Condition:");
		PhysicalconditionLabel.setBounds(10, 230, 160, 25);
		panel.add(PhysicalconditionLabel);

		JTextField PhysicalconditionText = new JTextField(20);
		PhysicalconditionText.setBounds(180, 230, 160, 25);
		panel.add(PhysicalconditionText);

		JLabel EstimatedpriceLabel = new JLabel("Estimated Price:");
		EstimatedpriceLabel.setBounds(10, 260, 160, 25);
		panel.add(EstimatedpriceLabel);

		JTextField EstimatedpriceText = new JTextField(20);
		EstimatedpriceText.setBounds(180, 260, 160, 25);
		panel.add(EstimatedpriceText);

		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(10, 290, 160, 25);
		panel.add(submitButton);

		// Add action listener for the submit button (logic to send data to REST API)
		submitButton.addActionListener(e -> {
			try {
				// Create the form data object
				Receipt formData = new Receipt();
				formData.setCustomername(CustomernameText.getText());
				formData.setContactno(Long.parseLong(ContactnoText.getText()));
				formData.setAddress(AddressText.getText());
				formData.setCompanyname(CompanynameText.getText());
				formData.setModelno(ModelnoText.getText());
				formData.setIMEI1(Long.parseLong(IMEI1Text.getText()));
				formData.setIMEI2(Long.parseLong(IMEI2Text.getText()));
				formData.setPhysicalcondition(PhysicalconditionText.getText());
				formData.setEstimatedprice(Long.parseLong(EstimatedpriceText.getText()));

				// Convert form data to JSON
				ObjectMapper objectMapper = new ObjectMapper();
				String jsonFormData = objectMapper.writeValueAsString(formData);
				System.out.println("JSON Form Data: " + jsonFormData);
				// Send POST request to the REST API
				URL url = new URL("http://localhost:8080/receipts");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setDoOutput(true);

				// Send JSON data
				try (OutputStream outputStream = connection.getOutputStream()) {
					outputStream.write(jsonFormData.getBytes());
					outputStream.flush();
				}

				// Check the response code
				int responseCode = connection.getResponseCode();
				System.out.println("Response Code: " + responseCode);

				StringBuilder responseBuilder = new StringBuilder();
				try (BufferedReader br = new BufferedReader(new InputStreamReader(
						responseCode >= 400 ? connection.getErrorStream() : connection.getInputStream()))) {
					String responseLine;
					while ((responseLine = br.readLine()) != null) {
						responseBuilder.append(responseLine.trim());
					}
				}

				String responseMessage = responseBuilder.toString();
				System.out.println("Response: " + responseMessage);

				if (responseCode == HttpURLConnection.HTTP_CREATED) {
					// Form submitted successfully
					JOptionPane.showMessageDialog(null, "Form submitted successfully!");

					// Refresh the table data
					refreshTableData(); // Call the method to refresh table data
				} else {
					// Failed to submit form
					JOptionPane.showMessageDialog(null, "Failed to submit form. Response Code: " + responseCode + ". Response: " + responseMessage);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
			}
		});
	}
	private static void placeReceiptComponents(JPanel panel) {
		panel.setLayout(null);
		addCompanyLogoAndName(panel);

		JLabel receiptNoLabel = new JLabel("Receipt No:");
		receiptNoLabel.setBounds(10, 20, 160, 25);
		panel.add(receiptNoLabel);

		JTextField receiptNoText = new JTextField(20);
		receiptNoText.setBounds(180, 20, 160, 25);
		panel.add(receiptNoText);

		JButton retrieveButton = new JButton("Retrieve");
		retrieveButton.setBounds(10, 50, 160, 25);
		panel.add(retrieveButton);

		JTextArea receiptArea = new JTextArea();
		receiptArea.setBounds(10, 80, 560, 460);
		panel.add(receiptArea);

		// Add action listener for the retrieve button
		retrieveButton.addActionListener(e -> {
			try {
				// Get the receipt number entered by the user
				String receiptNo = receiptNoText.getText();

				// Send a request to the server to retrieve data based on the receipt number
				String receiptData = retrieveReceiptDataFromServer(receiptNo);

				// Update the text area with the retrieved receipt data
				receiptArea.setText(receiptData);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
			}
		});

		// Print button remains as it is
		JButton printButton = new JButton("Print");
		printButton.setBounds(10, 550, 160, 25);
		panel.add(printButton);

		// Add action listener for the print button
		printButton.addActionListener(e -> {
			// Implement print functionality here
		});
	}






	private static String retrieveReceiptDataFromServer(String receiptNo) {
		try {
			// Send a request to the server to retrieve data based on the receipt number
			// Example:
			URL url = new URL("http://localhost:8080/receipts/" + receiptNo);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// Convert JSON response to an object
				ObjectMapper objectMapper = new ObjectMapper();
				Receipt receipt = objectMapper.readValue(response.toString(), Receipt.class);

				// Format receipt data into a table
				String formattedData = String.format("%-15s %s\n", "Receipt No:", receipt.getReceiptno());
				formattedData += String.format("%-15s %s\n", "Customer Name:", receipt.getCustomername());
				formattedData += String.format("%-15s %s\n", "Contact No:", receipt.getContactno());
				formattedData += String.format("%-15s %s\n", "Address:", receipt.getAddress());
				formattedData += String.format("%-15s %s\n", "Company Name:", receipt.getCompanyname());
				formattedData += String.format("%-15s %s\n", "Model No:", receipt.getModelno());
				formattedData += String.format("%-15s %s\n", "IMEI 1:", receipt.getIMEI1());
				formattedData += String.format("%-15s %s\n", "IMEI 2:", receipt.getIMEI2());
				formattedData += String.format("%-15s %s\n", "Physical Condition:", receipt.getPhysicalcondition());
				formattedData += String.format("%-15s %s\n", "Estimated Price:", receipt.getEstimatedprice());

				return formattedData;
			} else {
				JOptionPane.showMessageDialog(null, "Failed to retrieve data. Response Code: " + responseCode);
				return "";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "An error occurred while retrieving receipt data: " + ex.getMessage());
			return "";
		}
	}
	private static void addCompanyLogoAndName(JPanel panel) {
		// Load the company logo
		ImageIcon logoIcon = new ImageIcon(ReceiptApplication.class.getResource("/ISPL_PNG_Logo.png"));
		Image logoImage = logoIcon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT); // Adjust size here
		ImageIcon scaledLogoIcon = new ImageIcon(logoImage);

		JLabel logoLabel = new JLabel(scaledLogoIcon);
		int logoWidth = scaledLogoIcon.getIconWidth();
		int logoHeight = scaledLogoIcon.getIconHeight();
		logoLabel.setBounds(10, 90, logoWidth, logoHeight); // Adjust position and size here
		panel.add(logoLabel);

		// Add the company name label
		JLabel companyNameLabel = new JLabel("Infyvaritaas Softech Private Limited");
		companyNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
		companyNameLabel.setBounds(100, 90, 500, 50);
		panel.add(companyNameLabel);
	}




	private static void placeDataTableComponents(JPanel panel) {
		panel.setLayout(new BorderLayout());

		// Create table model and JTable
		String[] columnNames = {"Receiptno", "Customername", "Contactno", "Address", "Companyname", "Modelno", "IMEI1", "IMEI2", "Physicalcondition", "Estimatedprice"};
		Object[][] data = {}; // Initially empty, will be filled with data from the database

		table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);

		// Load data from the database
		loadDataIntoTable(); // Call method to load data
	}

	private static void loadDataIntoTable() {
		// Send GET request to the REST API to retrieve all data
		try {
			URL url = new URL("http://localhost:8080/receipts");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// Convert JSON response to an array of Receipts
				ObjectMapper objectMapper = new ObjectMapper();
				Receipt[] receipts = objectMapper.readValue(response.toString(), Receipt[].class);

				// Convert array of Receipts to a 2D Object array for the table model
				Object[][] data = new Object[receipts.length][10];
				for (int i = 0; i < receipts.length; i++) {
					data[i][0] = receipts[i].getReceiptno();
					data[i][1] = receipts[i].getCustomername();
					data[i][2] = receipts[i].getContactno();
					data[i][3] = receipts[i].getAddress();
					data[i][4] = receipts[i].getCompanyname();
					data[i][5] = receipts[i].getModelno();
					data[i][6] = receipts[i].getIMEI1();
					data[i][7] = receipts[i].getIMEI2();
					data[i][8] = receipts[i].getPhysicalcondition();
					data[i][9] = receipts[i].getEstimatedprice();
				}

				// Update the table model
				String[] columnNames = {"Receiptno", "Customername", "Contactno", "Address", "Companyname", "Modelno", "IMEI1", "IMEI2", "Physicalcondition", "Estimatedprice"};
				table.setModel(new DefaultTableModel(data, columnNames));
			} else {
				JOptionPane.showMessageDialog(null, "Failed to retrieve data. Response Code: " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "An error occurred while fetching data: " + e.getMessage());
		}
	}

	private static void refreshTableData() {
		// Send GET request to the REST API to retrieve all data
		try {
			URL url = new URL("http://localhost:8080/receipts");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// Convert JSON response to an array of Receipts
				ObjectMapper objectMapper = new ObjectMapper();
				Receipt[] receipts = objectMapper.readValue(response.toString(), Receipt[].class);

				// Convert array of Receipts to a 2D Object array for the table model
				Object[][] data = new Object[receipts.length][10];
				for (int i = 0; i < receipts.length; i++) {
					data[i][0] = receipts[i].getReceiptno();
					data[i][1] = receipts[i].getCustomername();
					data[i][2] = receipts[i].getContactno();
					data[i][3] = receipts[i].getAddress();
					data[i][4] = receipts[i].getCompanyname();
					data[i][5] = receipts[i].getModelno();
					data[i][6] = receipts[i].getIMEI1();
					data[i][7] = receipts[i].getIMEI2();
					data[i][8] = receipts[i].getPhysicalcondition();
					data[i][9] = receipts[i].getEstimatedprice();
				}

				// Update the table model
				String[] columnNames = {"Receiptno", "Customername", "Contactno", "Address", "Companyname", "Modelno", "IMEI1", "IMEI2", "Physicalcondition", "Estimatedprice"};
				table.setModel(new DefaultTableModel(data, columnNames));
			} else {
				JOptionPane.showMessageDialog(null, "Failed to retrieve data. Response Code: " + responseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "An error occurred while fetching data: " + e.getMessage());
		}
	}
}
