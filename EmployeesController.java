package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EmployeesController implements Initializable {

    @FXML
    private Button AddEmployeeButton;

    @FXML
    private Button AddPhoneNumberButton;
    
    @FXML
    private Button updEmployeephoneButton1;

    @FXML
    private Button EmployeephoneDeleteButton1;
    
    @FXML
    private Button updEmployeephoneButton;
    
    @FXML
    private TableColumn<employees, Integer> EidCol;

    @FXML
    private TableColumn<employees, Integer> EidColMain;

    @FXML
    private TextField EmployeeAddressData;

    @FXML
    private Button EmployeeClearAllButton;

    @FXML
    private Button EmployeeDeleteButton;

    @FXML
    private TextField EmployeeIdData;

    @FXML
    private TextField EmployeeIdData1;

    @FXML
    private TextField EmployeeNameData;

    @FXML
    private TextField EmployeePhoneNumberData;

    @FXML
    private TextField EmployeeSalary;

    @FXML
    private TextField EmployeeSearch;
    
    @FXML
    private TextField newphone;
    
    @FXML
    private AnchorPane EmployeeWindow;

    @FXML
    private TableView<employeePhones> EmployeesPhoneNumberTable;

    @FXML
    private BorderPane ViewSupplierWindow;

    @FXML
    private ComboBox<String> attributeSelection;

    @FXML
    private Button carsButton;

    @FXML
    private Button clientButton;

    @FXML
    private Button dashBoardButton;

    @FXML
    private TableColumn<employees, String> employeeAddressCol;

    @FXML
    private TableColumn<employees, String> employeeNameCol;

    @FXML
    private TableColumn<employees, String> employeePhoneNumberCol;

    @FXML
    private TableColumn<employees, Integer> employeeSalaryCol;

    @FXML
    private TableView<employees> employeeTable;

    @FXML
    private Button employeesButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button tranButton;

    @FXML
    private Button suppliersButton;

    @FXML
    private Button updEmployeeButton;

    @FXML
    private Button warehousesButton;
    
    ObservableList<employees> listM;
    ObservableList<employeePhones> listP;

    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    @FXML
    void AddEmployeeButton(ActionEvent event) {

    	 conn = mysqlconnect.ConnectDB(); // Corrected method name for ConnectDb
         String sql = "INSERT INTO employee (name, address, salary) VALUES (?, ?, ?)";
         try {
             pst = conn.prepareStatement(sql);
             pst.setString(1, EmployeeNameData.getText());
             pst.setString(2, EmployeeAddressData.getText());
             pst.setString(3, EmployeeSalary.getText());
             pst.execute();
             JOptionPane.showMessageDialog(null, "Customer added successfully");
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
         }
         UpdateTable();
       
    }

    @FXML
    void AddPhoneNumberButton(ActionEvent event) {
    	
		conn = mysqlconnect.ConnectDB(); // Corrected method name for ConnectDb
		String sql = "INSERT INTO Employeephone (phoneNumber, Eid) VALUES (?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, EmployeePhoneNumberData.getText());
			pst.setString(2, EmployeeIdData1.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Phone number added successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		 UpdateTable();
    }

    @FXML
    void EmployeeClearAllButton(ActionEvent event) {

		EmployeeNameData.clear();
		EmployeeAddressData.clear();
		EmployeeSalary.clear();
		EmployeePhoneNumberData.clear();
		EmployeeIdData1.clear();
    }
    
    @FXML
    void EmployeephoneDeleteButton(ActionEvent event) {
    	
    	conn = mysqlconnect.ConnectDB(); // Corrected method name for ConnectD 	
    	String sql = "DELETE FROM Employeephone WHERE Eid = ? and phoneNumber = ?";
    	try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, EmployeeIdData1.getText());
			pst.setString(2, EmployeePhoneNumberData.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Phone number deleted successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
    	}
    	UpdateTable();
    	                
    
    }

    @FXML
    void EmployeeDeleteButton(ActionEvent event) {
    	
    	conn = mysqlconnect.ConnectDB(); // Corrected method name for ConnectDb
    	String sql = "DELETE FROM employee WHERE Eid = ?";
    	try {
    	pst = conn.prepareStatement(sql);
    	pst.setString(1, EmployeeIdData.getText());
    	pst.execute();
    	JOptionPane.showMessageDialog(null, "Employee deleted successfully");
    		            
		} catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage());
		}
    	
    	sql = "DELETE FROM Employeephone WHERE Eid = ? and phoneNumber = ?";
    	try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, EmployeeIdData1.getText());
			pst.setString(2, EmployeePhoneNumberData.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Phone number deleted successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
    	}
    	UpdateTable();
    	                
    
    }

    @FXML
    void Search(KeyEvent event) {
    	
        conn = mysqlconnect.ConnectDB(); // Corrected method name for ConnectDb
    	String selection = attributeSelection.getValue();
    	String sql = "SELECT * FROM employee WHERE "+selection+" LIKE ?";
    	try {
    	pst = conn.prepareStatement(sql);
    	pst.setString(1, "%"+EmployeeSearch.getText()+"%");
    	rs = pst.executeQuery();
    	ObservableList<employees> list = FXCollections.observableArrayList();
    	while (rs.next()) {
        list.add(new employees(rs.getInt("Eid"),  rs.getInt("salary"),rs.getString("name"), rs.getString("address")));
    		      }
    		 employeeTable.setItems(list);
      } catch (Exception e) {
    	  System.out.println(e.getMessage());
	}
    	
    	
    	//Search for phone number and id
    	sql = "SELECT * FROM Employeephone WHERE "+selection+" LIKE ?";
    	try {
    		pst = conn.prepareStatement(sql);
    		pst.setString(1, "%"+EmployeeSearch.getText()+"%");
    		rs = pst.executeQuery();
    		ObservableList<employeePhones> list = FXCollections.observableArrayList();
    		while (rs.next()) {
    			list.add(new employeePhones(rs.getInt("Eid"), rs.getString("phoneNumber")));
    		}
    		EmployeesPhoneNumberTable.setItems(list);
    	}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    	}


		
		@FXML
		void switchForm(ActionEvent event) throws IOException {
		    Map<Button, String> buttonToFXMLMap = new HashMap<>();
		    buttonToFXMLMap.put(homeButton, "Home.fxml");
		    buttonToFXMLMap.put(dashBoardButton, "Dashboard.fxml");
		    buttonToFXMLMap.put(warehousesButton, "Warehouses.fxml");
		    buttonToFXMLMap.put(clientButton, "Customers.fxml");
		    buttonToFXMLMap.put(carsButton, "Cars.fxml");
		    buttonToFXMLMap.put(employeesButton, "Employees.fxml");
		    buttonToFXMLMap.put(tranButton, "Transaction.fxml");
		    buttonToFXMLMap.put(suppliersButton, "Suppliers.fxml");
		    buttonToFXMLMap.put(exitButton, "Login.fxml");
		
		    Button sourceButton = (Button) event.getSource();
		    String fxmlFile = buttonToFXMLMap.get(sourceButton);
		
		    if (fxmlFile != null) {
		        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
		        Scene scene = new Scene(root);
		        Stage regStage = new Stage();
		        regStage.setScene(scene);
		        regStage.show();
		        sourceButton.getScene().getWindow().hide();
		    }
		}

    private void UpdateTable() {
    	
    	EidColMain.setCellValueFactory(new PropertyValueFactory<>("eid"));
    	employeeNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	employeeAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
    	employeeSalaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
    	employeePhoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    	EidCol.setCellValueFactory(new PropertyValueFactory<>("eid"));
    		
        listM = mysqlconnect.getDataEmployee();
        if (listM != null && !listM.isEmpty()) {
            System.out.println("Data loaded successfully.");
        } else {
            System.out.println("No data retrieved.");
        }
        listP = mysqlconnect.getDataEmployeephone();
		if (listP != null && !listP.isEmpty()) {
			System.out.println("Data loaded successfully.");
		} else {
			System.out.println("No data retrieved.");
		}
		
		
        employeeTable.setItems(listM);
        EmployeesPhoneNumberTable.setItems(listP);
    }

    
    
    @FXML
    void updEmployeeButton(ActionEvent event) {
    	
    	conn = mysqlconnect.ConnectDB(); // Corrected method name for ConnectDb
    	String sql = "UPDATE employee SET name = ?, address = ?, salary = ? WHERE Eid = ?";
    	try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, EmployeeNameData.getText());
			pst.setString(2, EmployeeAddressData.getText());
			pst.setString(3, EmployeeSalary.getText());
			pst.setString(4, EmployeeIdData.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Employee updated successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
    	}
    	 UpdateTable();
    	
    }
    
    @FXML
    private void getSelected(MouseEvent event) {
    	
    	index = employeeTable.getSelectionModel().getSelectedIndex();
    	if(index <= -1) {
    		return;
    	}
    	EmployeeIdData.setText(EidColMain.getCellData(index).toString());
    	EmployeeNameData.setText(employeeNameCol.getCellData(index).toString());
    	EmployeeAddressData.setText(employeeAddressCol.getCellData(index).toString());
    	EmployeeSalary.setText(employeeSalaryCol.getCellData(index).toString());

    }
    
    @FXML
    private void updEmployeephoneButton(ActionEvent event) {

		conn = mysqlconnect.ConnectDB(); // Corrected method name for ConnectDb
		String sql = "UPDATE Employeephone SET phoneNumber = ? WHERE Eid = ? and phoneNumber = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, newphone.getText());
			pst.setString(2, EmployeeIdData1.getText());
			pst.setString(3, EmployeePhoneNumberData.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Phone number updated successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		 UpdateTable();

    }
    
    @FXML
    private void getSelectedphone(MouseEvent event) {
    	index = EmployeesPhoneNumberTable.getSelectionModel().getSelectedIndex();
		if (index <= -1) {
			return;
		}
		EmployeeIdData1.setText(EidCol.getCellData(index).toString());
		EmployeePhoneNumberData.setText(employeePhoneNumberCol.getCellData(index).toString());
    }

    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	 attributeSelection.setItems(FXCollections.observableArrayList("Eid",  "Name", "Address","Salary", "phoneNumber"));
    	 UpdateTable();

	}
    
}
