package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SampleController implements Initializable {
	@FXML
    private TextField EmailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;
    
    Connection conn = null;

    @FXML
    void add_users() {
		conn = mysqlconnect.ConnectDB();
		String sql = "insert into user (username, email, password) values (?, ?, ?)";
		try {
			// Validate inputs
			if (usernameField.getText().isEmpty() || EmailField.getText().isEmpty()
					|| passwordField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill in all fields");
				return;
			}
			// Validate email
			if (!EmailField.getText().contains("@")) {
				JOptionPane.showMessageDialog(null, "Invalid email");
				return;
			}
			// Validate password
			if (passwordField.getText().length() < 5) {
				JOptionPane.showMessageDialog(null, "Password must be at least 5 characters long");
				return;
			}
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, usernameField.getText());
			st.setString(2, EmailField.getText());
			st.setString(3, passwordField.getText());
			st.execute();
			JOptionPane.showMessageDialog(null, "Data inserted successfully");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		conn = mysqlconnect.ConnectDB();
		
	}
	
}
