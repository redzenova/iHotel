
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Redzer0
 */
public class iHotel extends Application {

    private String username;
    private String password;
    private String mem_username;
    private String mem_password;
    private boolean mem_status;
    
    private Stage window;
    private Database db = new Database();
    
    private User user_page;
    private Manager manager_page;
    private Admin admin_page;
    
    public iHotel() {
    }
    
    public static void main2(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        //[Inintialize] Stage
        window = stage;
        window.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));
        window.setTitle("[iHotel] - Hotel Administrator and Management");
        window.setResizable(false);
        
        
        //[Component] - ImageView
        ImageView main_scene_bg = new ImageView(new Image(new FileInputStream(new File("src/img/main_scene.png"))));
        main_scene_bg.setScaleX(1);
        main_scene_bg.setScaleY(1.2);
        main_scene_bg.setEffect(new DropShadow());
        
        //[Component] - Logo
        ImageView logo = new ImageView(new Image(new FileInputStream(new File("src/img/logo.png"))));
        logo.setScaleX(0.8);
        logo.setScaleY(0.8);
        
        //[Component] - FormBox
        Rectangle box = new Rectangle(420, 680); 
        box.setFill(Color.web("#005082"));
        box.setEffect(new DropShadow());
        
        //[Component] - CheckBox remember me
        CheckBox mem_me = new CheckBox("จดจำฉัน");
        mem_me.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        mem_me.setStyle("-fx-text-fill: #ffffff");
        mem_me.setScaleX(0.8);
        mem_me.setScaleY(0.8);
        
        // Error Form 
        //[Component] - Error --name
        Label username_error = new Label("รูปแบบชื่อผู้ใช้งานไม่ถูกต้อง");
        username_error.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        username_error.setStyle("-fx-text-fill: #FF1919");
        username_error.setVisible(false);
        
        //[Component] - Form Field 
        Label user_label = new Label("ชื่อผู้ใช้งาน");
        user_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        user_label.setStyle("-fx-text-fill: #ffffff");
        
        Label pass_label = new Label("รหัสผ่าน");
        pass_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        pass_label.setStyle("-fx-text-fill: #ffffff");
        
        TextField user_form = new TextField();
        user_form.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        user_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        user_form.setEffect(new DropShadow());
        user_form.setPromptText("อีเมลหรือเบอร์โทรศัพท์");
        user_form.setText("Redzenova");
        user_form.clear();
        
        PasswordField pass_form = new PasswordField();
        pass_form.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        pass_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        pass_form.setEffect(new DropShadow());
        pass_form.setPromptText("รหัสผ่าน");
        
        //====================|  Login [Button] setting |=========================
        Button login_bt = new Button("เข้าสู่ระบบ");
        login_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        login_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #1EB2A6; -fx-text-fill: #ffffff");
        login_bt.setDisable(false);

        //On mouse hover
        login_bt.setOnMouseEntered( e -> {
                login_bt.setStyle(" -fx-background-radius: 30; -fx-background-color: #1EB2A6; -fx-text-fill: #ffffff");        
                login_bt.setEffect(new DropShadow());
        });
        //On mouse click
        login_bt.setOnMouseClicked(e -> {
               login_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #6FE7DE; -fx-text-fill: #ffffff");
               this.username = user_form.getText();
               this.password = pass_form.getText();
               if(mem_me.isSelected()){
                   this.mem_username = this.username;
                   this.mem_password = this.password;
               }
               
            try {
                this.submit();
            } catch (IOException ex) {
                Logger.getLogger(iHotel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(iHotel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //On mouse idle
        login_bt.setOnMouseExited( e -> {
               login_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #1EB2A6; -fx-text-fill: #000000");
               login_bt.setEffect(null);
        });
         //============================================================
        
        //===================|  Regis [Button] setting |=========================
        Button regis_bt = new Button("ลงทะเบียน");
        regis_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        regis_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
        regis_bt.setDisable(false); 
        //On mouse idle
        regis_bt.setOnMouseExited( e -> {
               regis_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
               regis_bt.setEffect(null);
        });
        //On mouse hover
        regis_bt.setOnMouseEntered( e -> {
                regis_bt.setStyle(" -fx-background-radius: 30; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff"); 
                regis_bt.setEffect(new DropShadow());
        });
         //On mouse click
        regis_bt.setOnMouseClicked(e -> {
               regis_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #FFC997; -fx-text-fill: #ffffff");
               //window.close();
               Register register_page = new Register();
            try {
                register_page.start(window);
            } catch (Exception ex) {
                Logger.getLogger(iHotel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         //============================================================
         
         //===================|  Forgot [Button] setting |=========================
        Button forgot_bt = new Button("ลืมรหัสผ่าน?");
        forgot_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        forgot_bt.setStyle("-fx-background-color: transparent;  -fx-text-fill: #ffffff");
        forgot_bt.setDisable(false); 
        //On mouse idle
        forgot_bt.setOnMouseExited( e -> {
               forgot_bt.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff");
               forgot_bt.setEffect(null);
        });
        //On mouse hover
        forgot_bt.setOnMouseEntered( e -> {
                forgot_bt.setStyle(" -fx-background-color: transparent; -fx-text-fill: #FFFF00"); 
              
        });
         //On mouse click
        forgot_bt.setOnMouseClicked(e -> {
               forgot_bt.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFF00");  
               forgot_bt.setEffect(new DropShadow());
        });
         //============================================================
         
         
         //[Pane Setting]
        StackPane rootPane = new StackPane();
        
        //[GridPane] -- Initialize
        GridPane main_page_layout = new GridPane();      
        main_page_layout.setPadding(new Insets(20));
        main_page_layout.setHgap(25);
        main_page_layout.setVgap(15);
        main_page_layout.setStyle("-fx-background-color: #000839;");
        
        //[GridPane] -- add component
        main_page_layout.add(main_scene_bg, 0, 0);
        main_page_layout.add(box, 1, 0);
        
         //[AnchorPane] -- Initialize
        AnchorPane form_layout = new AnchorPane();
        
        //[AnchorPane] -- add component
        // Anchor to Login [Button]
        AnchorPane.setTopAnchor(login_bt, 660.0);
        AnchorPane.setLeftAnchor(login_bt, 950.0);
        AnchorPane.setRightAnchor(login_bt, 150.0);
        AnchorPane.setBottomAnchor(login_bt, 220.0);  
       
         // Anchor to Regis [Button]
        AnchorPane.setTopAnchor(regis_bt, 730.0);
        AnchorPane.setLeftAnchor(regis_bt, 950.0);
        AnchorPane.setRightAnchor(regis_bt, 150.0);
        AnchorPane.setBottomAnchor(regis_bt, 150.0); 
        
         // Anchor to logo [Picture]
        AnchorPane.setTopAnchor(logo, 40.0);
        AnchorPane.setLeftAnchor(logo, 880.0);
        AnchorPane.setRightAnchor(logo, 80.0);
        AnchorPane.setBottomAnchor(logo, 500.0); 
        
        // Anchor to User Field [TextField]
        AnchorPane.setTopAnchor(user_form, 405.0);
        AnchorPane.setLeftAnchor(user_form, 950.0);
        AnchorPane.setRightAnchor(user_form, 150.0);
        AnchorPane.setBottomAnchor(user_form, 485.0); 
        
        // Anchor to User Label [TextField]
        AnchorPane.setTopAnchor(user_label, 370.0);
        AnchorPane.setLeftAnchor(user_label, 950.0);
        AnchorPane.setRightAnchor(user_label, 250.0);
        AnchorPane.setBottomAnchor(user_label, 530.0); 
        
        // Anchor to Pass Field [TextField]
        AnchorPane.setTopAnchor(pass_form, 485.0);
        AnchorPane.setLeftAnchor(pass_form, 950.0);
        AnchorPane.setRightAnchor(pass_form, 150.0);
        AnchorPane.setBottomAnchor(pass_form, 405.0); 
        
        // Anchor to Pass Label [TextField]
        AnchorPane.setTopAnchor(pass_label, 435.0);
        AnchorPane.setLeftAnchor(pass_label, 950.0);
        AnchorPane.setRightAnchor(pass_label, 250.0);
        AnchorPane.setBottomAnchor(pass_label, 435.0);
        
        // Anchor to Remember [CheckBox]
        AnchorPane.setTopAnchor(mem_me, 540.0);
        AnchorPane.setLeftAnchor(mem_me, 930.0);
        AnchorPane.setRightAnchor(mem_me, 270.0);
        AnchorPane.setBottomAnchor(mem_me, 360.0);
        
         // Anchor to Forgot [Button]
        AnchorPane.setTopAnchor(forgot_bt, 540.0);
        AnchorPane.setLeftAnchor(forgot_bt, 1130.0);
        AnchorPane.setRightAnchor(forgot_bt, 80.0);
        AnchorPane.setBottomAnchor(forgot_bt, 360.0); 
        
        form_layout.getChildren().addAll(logo, regis_bt, login_bt, user_form, 
                pass_form, user_label, pass_label, mem_me, forgot_bt);
        
        
        //[View] --show window
        rootPane.getChildren().addAll(main_page_layout, form_layout);
        Scene main_page = new Scene(rootPane,1280,720);         
        window.setScene(main_page);
        window.show();
    }
   
    private void submit() throws IOException, Exception{
        Authentication auth = new Authentication();
        if(auth.isUser(this.username, this.password)){
            System.out.println("Welcome User;");
            window.close();
            user_page = new User(this.username,this.password);
            user_page.start(window);
        }else if(auth.isAdmin(this.username, this.password)){
            System.out.println("Welcome Admin");
            window.close();
            admin_page = new Admin(this.username,this.password);
            admin_page.start(window);
        }else System.out.println("Not have account !");
    }
}
