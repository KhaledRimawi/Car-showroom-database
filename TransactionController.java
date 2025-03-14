package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TransactionController implements Initializable {

    @FXML
    private Button AddtranButton;

    @FXML
    private TextField EmpIDDATA;

    @FXML
    private AnchorPane EmployeeWindow;

    @FXML
    private TableColumn<Transaction, Integer> Transaction_Id;

    @FXML
    private BorderPane ViewSupplierWindow;

    @FXML
    private ComboBox<String> attributeSelection;

    @FXML
    private Button carsButton;

    @FXML
    private Button cleartranButton;

    @FXML
    private Button clientButton;

    @FXML
    private Button dashBoardButton;

    @FXML
    private Button deletetranButton;

    @FXML
    private TableView<Transaction> TransactionTable;

    @FXML
    private Button employeesButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button homeButton;

    @FXML
    private TableView<Transaction> price;

    @FXML
    private TextField priceDATA;

    @FXML
    private Button suppliersButton;

    @FXML
    private Button tranButton;

    @FXML
    private TextField tranSearch;

    @FXML
    private TableColumn<Transaction, LocalDate> transationDATECol;

    @FXML
    private TableColumn<Transaction, Double> transationDISCOUNTCol;

    @FXML
    private TableColumn<Transaction, Integer> transationEmpIDCol;

    @FXML
    private TableColumn<Transaction, String> transationPAYTYPECol;

    @FXML
    private TableColumn<Transaction, Double> transationPaidAmountCol;

    @FXML
    private TableColumn<Transaction, Double> transationREMAININGCol;

    @FXML
    private TableColumn<Transaction, Double> transationTotalpriceCol;

    @FXML
    private TableColumn<Transaction, Integer> transationcarIDCol;

    @FXML
    private TableColumn<Transaction, Integer> transationcustIDCol;

    @FXML
    private TableColumn<Transaction, Double> transationpriceCol;

    @FXML
    private TableColumn<Transaction, Integer> transationtranidCol;
    @FXML
    private TableColumn<Transaction, Double> transationPaidAmountCol2;
    

    @FXML
    private TextField txt_enter_carID;

    @FXML
    private TextField txt_enter_cusID;

    @FXML
    private TextField txt_enter_date;

    @FXML
    private TextField txt_Paid_Amount;
    
    @FXML
    private TextField txt_enter_discount;

    @FXML
    private Label txt_enter_empID;

    @FXML
    private TextField txt_enter_paytype;

    @FXML
    private Label txt_enter_price;

    @FXML
    private TextField txt_enter_tranID;

    @FXML
    private Button updtranButton;

    @FXML
    private Button warehousesButton;
    
    ObservableList<Transaction> listM;
    ObservableList<Transaction> listP;
    

    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void AddtranButton(ActionEvent event) {
    	
    	 conn = mysqlconnect.ConnectDB(); // Corrected method name for ConnectDb
         String sql = "INSERT INTO Transaction (Car_Id, Customer_Id, Emp_id, Date, Price, Pay_Type, Paid_Amount, Discount) VALUES(?,?,?,?,?,?,?,?)";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, txt_enter_carID.getText());
				pst.setString(2, txt_enter_cusID.getText());
				pst.setString(3, EmpIDDATA.getText());
				pst.setString(4, txt_enter_date.getText());
				pst.setString(5, priceDATA.getText());
				pst.setString(6, txt_enter_paytype.getText());
				pst.setString(7, txt_Paid_Amount.getText());
				pst.setString(8, txt_enter_discount.getText());
				pst.execute();
				JOptionPane.showMessageDialog(null, "Transaction Added");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
			UpdateTable();

    }

    @FXML
    void Search(KeyEvent event) {
    	conn = mysqlconnect.ConnectDB(); // Corrected method name for ConnectDb
    	String selection = attributeSelection.getValue();
    	String search = tranSearch.getText();
    	String sql = "SELECT * FROM Transaction WHERE "+selection+" LIKE ?";
    	
    	try {
              pst = conn.prepareStatement(sql);
              pst.setString(1, "%"+search +"%");
              rs = pst.executeQuery();
              ObservableList<Transaction> list = FXCollections.observableArrayList();
              while (rs.next()) {
            	  list.add(new Transaction(rs.getInt("Transaction_Id"), rs.getInt("Customer_Id"), rs.getInt("Car_Id"), 
            			  rs.getInt("Emp_id"), rs.getDate("Date").toLocalDate(),
            			  rs.getString("Pay_Type"),  rs.getDouble("Paid_Amount"), rs.getDouble("Price"),rs.getDouble("Discount") ));
              }
				TransactionTable.setItems(list);
			} catch (Exception e) {
				System.out.println(e);
    	}
    	

    	}
    
    @FXML
    void cleartranButton(ActionEvent event) {

    	
		txt_enter_carID.clear();
		txt_enter_cusID.clear();
		txt_enter_date.clear();
		txt_enter_discount.clear();
		EmpIDDATA.clear();
		txt_enter_paytype.clear();
		priceDATA.clear();
		txt_enter_tranID.clear();
		txt_Paid_Amount.clear();
		
		
		
    }

    @FXML
    void deletetranButton(ActionEvent event) {
    	    
        	conn = mysqlconnect.ConnectDB();
        	String sql = "DELETE FROM Transaction WHERE Transaction_Id = ?";
        	
        	try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, txt_enter_tranID.getText());
				pst.execute();
				JOptionPane.showMessageDialog(null, "Transaction Deleted");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
        	}
        	UpdateTable();

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
    
    @FXML
    private void UpdateTable() {
    	
    	transationtranidCol.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("Transaction_Id"));
    	transationcustIDCol.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("Customer_Id"));
    	transationcarIDCol.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("Car_Id"));
    	transationEmpIDCol.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("Emp_id"));
    	transationDATECol.setCellValueFactory(new PropertyValueFactory<Transaction, LocalDate>("Date"));
    	transationpriceCol.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("Price"));
    	transationPAYTYPECol.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Pay_Type"));
    	transationPaidAmountCol.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("Paid_Amount"));
    	transationDISCOUNTCol.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("Discount"));
    	transationTotalpriceCol.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("TotalPrice"));
    	transationPaidAmountCol2.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("Paid_Amount"));
    	transationREMAININGCol.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("Remaining"));
    	Transaction_Id.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("Transaction_Id"));
    	listM = mysqlconnect.getDataTransactionprice();
    	
    	if (listM.isEmpty()) {
    	System.out.println("No data in the database");
    	}
    	else {
			System.out.println("Data found");
		}
    	TransactionTable.setItems(listM);
    	price.setItems(listM);
    	
    	}
    
    @FXML
    void updtranButton(ActionEvent event) {
    
    	conn = mysqlconnect.ConnectDB();
    	String sql = "UPDATE Transaction SET Car_Id = ?, Customer_Id = ?, Emp_id = ?, Date = ?, Price = ?, Pay_Type = ?, Paid_Amount = ?, Discount = ? WHERE Transaction_Id = ?";
    	
    	try {
              pst = conn.prepareStatement(sql);
              pst.setString(1, txt_enter_carID.getText());
              pst.setString(2, txt_enter_cusID.getText());
              pst.setString(3, EmpIDDATA.getText());
              pst.setString(4, txt_enter_date.getText());
              pst.setString(5, priceDATA.getText());
              pst.setString(6, txt_enter_paytype.getText());
              pst.setString(7, txt_Paid_Amount.getText());
              pst.setString(8, txt_enter_discount.getText());
              pst.setString(9, txt_enter_tranID.getText());
              pst.execute();
              JOptionPane.showMessageDialog(null, "Transaction Updated");
              } catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
    		UpdateTable();
    }
    


    
    @FXML
    private void getSelected(MouseEvent event) {
    	
    	index = TransactionTable.getSelectionModel().getSelectedIndex();
    	
    	if (index <= -1) {
			return;
		}
    	
    	txt_enter_tranID.setText(transationtranidCol.getCellData(index).toString());
    	txt_enter_cusID.setText(transationcustIDCol.getCellData(index).toString());
    	txt_enter_carID.setText(transationcarIDCol.getCellData(index).toString());
    	EmpIDDATA.setText(transationEmpIDCol.getCellData(index).toString());
    	txt_enter_date.setText(transationDATECol.getCellData(index).toString());
    	priceDATA.setText(transationpriceCol.getCellData(index).toString());
    	txt_enter_paytype.setText(transationPAYTYPECol.getCellData(index).toString());
    	txt_Paid_Amount.setText(transationPaidAmountCol.getCellData(index).toString());
    	txt_enter_discount.setText(transationDISCOUNTCol.getCellData(index).toString());
    	
    	
    	}

    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
    	attributeSelection.setItems(FXCollections.observableArrayList("Transaction_Id", "Customer_Id", "Car_Id", "Emp_id", 
    			"Date", "Pay_Type", "Paid_Amount", "Price", "Discount"));

	}

}
