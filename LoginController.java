package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button btn_close_signup;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup_inlogin;

    @FXML
    private Button btn_signup_insignup;

    @FXML
    private AnchorPane login_pane;

    @FXML
    private AnchorPane signup_pane;

    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_pass_in;

    @FXML
    private TextField txt_pass_up;

    @FXML
    private TextField txt_username_in;

    @FXML
    private TextField txt_username_up;


    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    public void LoginpaneShow(){

        login_pane.setVisible(true);
        signup_pane.setVisible(false);
    }

    public void SignuppaneShow(){

        login_pane.setVisible(false);
        signup_pane.setVisible(true);
    }
    @FXML
    private void Login (ActionEvent event) throws Exception{
        conn = mysqlconnect.ConnectDB();
        String sql = "Select * from USER where USERNAME = ? and PASSWORD = ? ";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username_in.getText());
            pst.setString(2, txt_pass_in.getText());

            rs = pst.executeQuery();

            if(rs.next()){
                JOptionPane.showMessageDialog(null, "Username And Password is Correct");

                btn_login.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();

            }else
                JOptionPane.showMessageDialog(null, "Invalid Username Or Password");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        clearTextFields();

    }

    public void add_users(ActionEvent event){
        conn = mysqlconnect.ConnectDB();
        String sql = "insert into user (username,password,email) values (?,?,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_username_up.getText());
            pst.setString(2, txt_pass_up.getText());
            pst.setString(3, txt_email.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Saved");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        clearTextFields();
    }

    public void clearTextFields() {
        txt_email.clear();
        txt_pass_in.clear();
        txt_pass_up.clear();
        txt_username_in.clear();
        txt_username_up.clear();
    }

    @Override
    public void initialize (URL url, ResourceBundle rb){
    	conn = mysqlconnect.ConnectDB();
    }
}