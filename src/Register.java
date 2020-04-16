
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
public class Register extends Application {

    Stage window;

    //Class Variable
    private String firstName;
    private String lastName;
    private String age;
    private String gender;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isSame;

    private String dbName = "User";
    private String[] header = {"Id", "Firstname", "Lastname", "Age", "Gender", "Email", "Phone Number", "Password", "DataCreate"};

    Validate check = new Validate();
    AlertBox alert_box = new AlertBox();

    @Override
    public void start(Stage stage) throws Exception {

        Database db = new Database();
        db.create(dbName);
        db.addHeader(dbName, header);

        //[Inintialize] Stage
        window = stage;
        window.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));
        window.setTitle("[iHotel] - Registeration");
        window.setResizable(false);

        //[Component] - Logo
        ImageView logo = new ImageView(new Image(new FileInputStream(new File("src/img/logo-s.png"))));
        logo.setScaleX(0.8);
        logo.setScaleY(0.8);

        //[Component] - FormBox
        Rectangle box = new Rectangle(1200, 200);
        box.setFill(Color.web("#005082"));
        //box.setEffect(new DropShadow());
        Rectangle box2 = new Rectangle(1200, 200);
        box2.setFill(Color.web("#005082"));
        box2.setEffect(new DropShadow());

        // Error Form 
        //[Component] - Error --name
        Label name_error = new Label("ชื่อไม่ถูกต้อง");
        name_error.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        name_error.setStyle("-fx-text-fill: #FF1919");
        name_error.setVisible(false);

        //[Component] - Error --lastname
        Label lastname_error = new Label("นามสกุลไม่ถูกต้อง");
        lastname_error.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        lastname_error.setStyle("-fx-text-fill: #FF1919");
        lastname_error.setVisible(false);

        //[Component] - Error --age
        Label age_error = new Label("โปรดระบุอายุ");
        age_error.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        age_error.setStyle("-fx-text-fill: #FF1919");
        age_error.setVisible(false);

        //[Component] - Error --email--invalid
        Label email_invalid_error = new Label("รูปแบบอีเมลไม่ถูกต้อง");
        email_invalid_error.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        email_invalid_error.setStyle("-fx-text-fill: #FF1919");
        email_invalid_error.setVisible(false);

        //[Component] - Error --email--already-exit
        Label email_already_error = new Label("อีเมลนี้มีอยู่ในระบบอยู่แล้ว");
        email_already_error.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        email_already_error.setStyle("-fx-text-fill: #FF1919");
        email_already_error.setVisible(false);

        //[Component] - Error --phone
        Label phone_error = new Label("รูปแบบไม่ถูกต้อง โปรดกรอกเฉพาะตัวเลข");
        phone_error.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        phone_error.setStyle("-fx-text-fill: #FF1919");
        phone_error.setVisible(false);

        //[Component] - Error --Password-not-match
        Label pass_notmatch_error = new Label("รหัสผ่านไม่ตรงกัน");
        pass_notmatch_error.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        pass_notmatch_error.setStyle("-fx-text-fill: #FF1919");
        pass_notmatch_error.setVisible(false);

        //[Component] - Form Field 
        //[Component] - Form Field --Label-name
        Label name_label = new Label("ชื่อ");
        name_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        name_label.setStyle("-fx-text-fill: #ffffff");

        //[Component] - Form Field --Label-lastname
        Label lastname_label = new Label("นามสกุล");
        lastname_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        lastname_label.setStyle("-fx-text-fill: #ffffff");

        //[Component] - Form Field --Label-age
        Label age_label = new Label("อายุ");
        age_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        age_label.setStyle("-fx-text-fill: #ffffff");

        //[Component] - Form Field --Label-gender
        Label gender_label = new Label("เพศ");
        gender_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        gender_label.setStyle("-fx-text-fill: #ffffff");

        //[Component] - Form Field --Label-email
        Label email_label = new Label("อีเมล");
        email_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        email_label.setStyle("-fx-text-fill: #ffffff");

        //[Component] - Form Field --Label-phone
        Label phone_label = new Label("เบอร์โทร");
        phone_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        phone_label.setStyle("-fx-text-fill: #ffffff");

        //[Component] - Form Field --Label-pass
        Label pass_label = new Label("รหัสผ่าน");
        pass_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        pass_label.setStyle("-fx-text-fill: #ffffff");

        //[Component] - Form Field --Label-repass
        Label repass_label = new Label("รหัสผ่านอีกครั้ง");
        repass_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        repass_label.setStyle("-fx-text-fill: #ffffff");

        //[Component] - CheckBox Condition
        CheckBox condit_box = new CheckBox("ยอมรับและยินยอมตาม                        "
                + "ของทางโรงแรม");
        condit_box.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        condit_box.setStyle("-fx-text-fill: #ffffff;");
        condit_box.setScaleX(0.8);
        condit_box.setScaleY(0.8);

        //[Component] - Form Field --TextField-name
        TextField name_form = new TextField();
        name_form.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        name_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        name_form.setEffect(new DropShadow());

        //[Component] - Form Field --TextField-lastname
        TextField lastname_form = new TextField();
        lastname_form.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        lastname_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        lastname_form.setEffect(new DropShadow());

        //[Component] - Form Field --TextField-age
        TextField age_form = new TextField();
        age_form.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        age_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        age_form.setEffect(new DropShadow());

        //[Component] - Form Field --TextField-gender
        ChoiceBox<String> gender_form = new ChoiceBox<>();
        gender_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        gender_form.setEffect(new DropShadow());
        gender_form.getItems().addAll("ชาย", "หญิง", "ไม่ระบุ");

        //[Component] - Form Field --TextField-email
        TextField email_form = new TextField();
        email_form.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        email_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        email_form.setEffect(new DropShadow());
        email_form.setPromptText("john@example.com");

        //[Component] - Form Field --TextField-phone
        TextField phone_form = new TextField();
        phone_form.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        phone_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        phone_form.setEffect(new DropShadow());
        phone_form.setPromptText("0890012345");

        //[Component] - Form Field --PasswordField-pass
        PasswordField pass_form = new PasswordField();
        pass_form.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        pass_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        pass_form.setEffect(new DropShadow());

        //[Component] - Form Field --PasswordField-repass
        PasswordField repass_form = new PasswordField();
        repass_form.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));
        repass_form.setStyle("-fx-background-radius: 13; -fx-font-size: 22;");
        repass_form.setEffect(new DropShadow());
        repass_form.setOnKeyReleased(e -> {
            isSame = checkPass(pass_form, repass_form, pass_notmatch_error);
        });

        //====================|  Regis [Button] setting |=========================
        Button regis_bt = new Button("สมัคร");
        regis_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        regis_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #1EB2A6; -fx-text-fill: #ffffff");
        regis_bt.setDisable(false);

        //On mouse idle
        regis_bt.setOnMouseExited(e -> {
            regis_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #1EB2A6; -fx-text-fill: #000000");
            regis_bt.setEffect(null);
        });
        //On mouse hover
        regis_bt.setOnMouseEntered(e -> {
            regis_bt.setStyle(" -fx-background-radius: 30; -fx-background-color: #6FE7DE; -fx-text-fill: #ffffff");
            regis_bt.setEffect(new DropShadow());
        });
        //On mouse click
        regis_bt.setOnMouseClicked(e -> {
            regis_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #1EB2A6; -fx-text-fill: #ffffff");
            try {

                this.firstName = name_form.getText();
                this.lastName = lastname_form.getText();
                this.age = age_form.getText();
                this.gender = gender_form.getValue();
                this.email = email_form.getText();
                this.phoneNumber = phone_form.getText();
                this.password = pass_form.getText();

                this.Submit();
            } catch (IOException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //============================================================
        //===================|  Cancel [Button] setting |=========================
        Button cancel_bt = new Button("ยกเลิก");
        cancel_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        cancel_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #F67575; -fx-text-fill: #000000");
        cancel_bt.setDisable(false);
        //On mouse idle
        cancel_bt.setOnMouseExited(e -> {
            cancel_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #F67575; -fx-text-fill: #000000");
            cancel_bt.setEffect(null);
        });
        //On mouse hover
        cancel_bt.setOnMouseEntered(e -> {
            cancel_bt.setStyle(" -fx-background-radius: 30; -fx-background-color: #FAACAC; -fx-text-fill: #ffffff");
            cancel_bt.setEffect(new DropShadow());
        });
        //On mouse click
        cancel_bt.setOnMouseClicked(e -> {
            cancel_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #F67575; -fx-text-fill: #ffffff");
            window.close();
            iHotel main_page = new iHotel();
            try {
                main_page.start(window);
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //============================================================

        //===================|  Condition [Button] setting |=========================
        Button condit_bt = new Button("เงื่อนไขและข้อตกลง");
        condit_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 20));
        condit_bt.setStyle("-fx-background-color: transparent ;  -fx-text-fill: #ffffff");
        condit_bt.setDisable(false);
        //On mouse idle
        condit_bt.setOnMouseExited(e -> {
            condit_bt.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff");
            condit_bt.setEffect(null);
        });
        //On mouse hover
        condit_bt.setOnMouseEntered(e -> {
            condit_bt.setStyle(" -fx-background-color: transparent; -fx-text-fill: #FFFF00");

        });
        //On mouse click
        condit_bt.setOnMouseClicked(e -> {
            condit_bt.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFF00");
            condit_bt.setEffect(new DropShadow());
        });
        //============================================================       

        //[Pane Setting]
        StackPane rootPane = new StackPane();

        //[VBox Pane] -- Initialize
        VBox regis_page_layout = new VBox();
        regis_page_layout.setPadding(new Insets(20));
        regis_page_layout.setStyle("-fx-background-color: #000839;");
        regis_page_layout.setAlignment(Pos.TOP_CENTER);

        //[VBox Pane] -- add component
        regis_page_layout.getChildren().addAll(logo, box2, box);

        //[AnchorPane] -- Initialize
        AnchorPane form_layout = new AnchorPane();

        //[AnchorPane] -- add component
        // Anchor to Regis [Button]
        AnchorPane.setTopAnchor(regis_bt, 740.0);
        AnchorPane.setLeftAnchor(regis_bt, 350.0);
        AnchorPane.setRightAnchor(regis_bt, 680.0);
        AnchorPane.setBottomAnchor(regis_bt, 120.0);

        // Anchor to Cancel [Button]
        AnchorPane.setTopAnchor(cancel_bt, 740.0);
        AnchorPane.setLeftAnchor(cancel_bt, 680.0);
        AnchorPane.setRightAnchor(cancel_bt, 350.0);
        AnchorPane.setBottomAnchor(cancel_bt, 120.0);

        // Anchor to Name [TextField]
        AnchorPane.setTopAnchor(name_form, 300.0);
        AnchorPane.setLeftAnchor(name_form, 240.0);
        AnchorPane.setRightAnchor(name_form, 760.0);
        AnchorPane.setBottomAnchor(name_form, 560.0);

        // Anchor to Name [Label]
        AnchorPane.setTopAnchor(name_label, 300.0);
        AnchorPane.setLeftAnchor(name_label, 200.0);
        AnchorPane.setRightAnchor(name_label, 1000.0);
        AnchorPane.setBottomAnchor(name_label, 560.0);

        // Anchor to Name [Error]
        AnchorPane.setTopAnchor(name_error, 355.0);
        AnchorPane.setLeftAnchor(name_error, 250.0);
        AnchorPane.setRightAnchor(name_error, 920.0);
        AnchorPane.setBottomAnchor(name_error, 535.0);

        // Anchor to Lastname [TextField]
        AnchorPane.setTopAnchor(lastname_form, 300.0);
        AnchorPane.setLeftAnchor(lastname_form, 620.0);
        AnchorPane.setRightAnchor(lastname_form, 370.0);
        AnchorPane.setBottomAnchor(lastname_form, 560.0);

        // Anchor to Lastname [Label]
        AnchorPane.setTopAnchor(lastname_label, 300.0);
        AnchorPane.setLeftAnchor(lastname_label, 540.0);
        AnchorPane.setRightAnchor(lastname_label, 660.0);
        AnchorPane.setBottomAnchor(lastname_label, 560.0);

        // Anchor to Lastname [Error]
        AnchorPane.setTopAnchor(lastname_error, 355.0);
        AnchorPane.setLeftAnchor(lastname_error, 620.0);
        AnchorPane.setRightAnchor(lastname_error, 370.0);
        AnchorPane.setBottomAnchor(lastname_error, 535.0);

        // Anchor to Age [TextField]
        AnchorPane.setTopAnchor(age_form, 300.0);
        AnchorPane.setLeftAnchor(age_form, 980.0);
        AnchorPane.setRightAnchor(age_form, 220.0);
        AnchorPane.setBottomAnchor(age_form, 560.0);

        // Anchor to Age [Label]
        AnchorPane.setTopAnchor(age_label, 300.0);
        AnchorPane.setLeftAnchor(age_label, 930.0);
        AnchorPane.setRightAnchor(age_label, 300.0);
        AnchorPane.setBottomAnchor(age_label, 560.0);

        // Anchor to Age [Error]
        AnchorPane.setTopAnchor(age_error, 355.0);
        AnchorPane.setLeftAnchor(age_error, 980.0);
        AnchorPane.setRightAnchor(age_error, 100.0);
        AnchorPane.setBottomAnchor(age_error, 535.0);

        // Anchor to Gender [Choise]
        AnchorPane.setTopAnchor(gender_form, 380.0);
        AnchorPane.setLeftAnchor(gender_form, 260.0);
        AnchorPane.setRightAnchor(gender_form, 900.0);
        AnchorPane.setBottomAnchor(gender_form, 480.0);

        // Anchor to Gender [Label]
        AnchorPane.setTopAnchor(gender_label, 380.0);
        AnchorPane.setLeftAnchor(gender_label, 220.0);
        AnchorPane.setRightAnchor(gender_label, 1000.0);
        AnchorPane.setBottomAnchor(gender_label, 480.0);

        // Anchor to Email [TextField]
        AnchorPane.setTopAnchor(email_form, 380.0);
        AnchorPane.setLeftAnchor(email_form, 460.0);
        AnchorPane.setRightAnchor(email_form, 540.0);
        AnchorPane.setBottomAnchor(email_form, 480.0);

        // Anchor to Email [Label]
        AnchorPane.setTopAnchor(email_label, 380.0);
        AnchorPane.setLeftAnchor(email_label, 410.0);
        AnchorPane.setRightAnchor(email_label, 800.0);
        AnchorPane.setBottomAnchor(email_label, 480.0);

        // Anchor to Email Invalid [Error]
        AnchorPane.setTopAnchor(email_invalid_error, 430.0);
        AnchorPane.setLeftAnchor(email_invalid_error, 460.0);
        AnchorPane.setRightAnchor(email_invalid_error, 600.0);
        AnchorPane.setBottomAnchor(email_invalid_error, 440.0);

        // Anchor to Email Already [Error]
        AnchorPane.setTopAnchor(email_already_error, 430.0);
        AnchorPane.setLeftAnchor(email_already_error, 460.0);
        AnchorPane.setRightAnchor(email_already_error, 600.0);
        AnchorPane.setBottomAnchor(email_already_error, 440.0);

        // Anchor to Phone [TextField]
        AnchorPane.setTopAnchor(phone_form, 380.0);
        AnchorPane.setLeftAnchor(phone_form, 830.0);
        AnchorPane.setRightAnchor(phone_form, 270.0);
        AnchorPane.setBottomAnchor(phone_form, 480.0);

        // Anchor to Phone [Label]
        AnchorPane.setTopAnchor(phone_label, 380.0);
        AnchorPane.setLeftAnchor(phone_label, 760.0);
        AnchorPane.setRightAnchor(phone_label, 440.0);
        AnchorPane.setBottomAnchor(phone_label, 480.0);

        // Anchor to Phone [Error]
        AnchorPane.setTopAnchor(phone_error, 430.0);
        AnchorPane.setLeftAnchor(phone_error, 840.0);
        AnchorPane.setRightAnchor(phone_error, 100.0);
        AnchorPane.setBottomAnchor(phone_error, 440.0);

        // Anchor to Pass [PasswordField]
        AnchorPane.setTopAnchor(pass_form, 500.0);
        AnchorPane.setLeftAnchor(pass_form, 540.0);
        AnchorPane.setRightAnchor(pass_form, 480.0);
        AnchorPane.setBottomAnchor(pass_form, 360.0);

        // Anchor to Pass [Label]
        AnchorPane.setTopAnchor(pass_label, 500.0);
        AnchorPane.setLeftAnchor(pass_label, 440.0);
        AnchorPane.setRightAnchor(pass_label, 720.0);
        AnchorPane.setBottomAnchor(pass_label, 360.0);

        // Anchor to Repass [PasswordField]
        AnchorPane.setTopAnchor(repass_form, 570.0);
        AnchorPane.setLeftAnchor(repass_form, 540.0);
        AnchorPane.setRightAnchor(repass_form, 480.0);
        AnchorPane.setBottomAnchor(repass_form, 290.0);

        // Anchor to Repass [Label]
        AnchorPane.setTopAnchor(repass_label, 570.0);
        AnchorPane.setLeftAnchor(repass_label, 420.0);
        AnchorPane.setRightAnchor(repass_label, 720.0);
        AnchorPane.setBottomAnchor(repass_label, 290.0);

        // Anchor to Password Not Match [Error]
        AnchorPane.setTopAnchor(pass_notmatch_error, 630.0);
        AnchorPane.setLeftAnchor(pass_notmatch_error, 550.0);
        AnchorPane.setRightAnchor(pass_notmatch_error, 620.0);
        AnchorPane.setBottomAnchor(pass_notmatch_error, 250.0);

        // Anchor to Condition [CheckBox]
        AnchorPane.setTopAnchor(condit_box, 650.0);
        AnchorPane.setLeftAnchor(condit_box, 370.0);
        AnchorPane.setRightAnchor(condit_box, 400.0);
        AnchorPane.setBottomAnchor(condit_box, 180.0);

        // Anchor to Condition [Button]
        AnchorPane.setTopAnchor(condit_bt, 650.0);
        AnchorPane.setLeftAnchor(condit_bt, 565.0);
        AnchorPane.setRightAnchor(condit_bt, 565.0);
        AnchorPane.setBottomAnchor(condit_bt, 180.0);

        form_layout.getChildren().addAll(regis_bt, cancel_bt, name_form, lastname_form,
                age_form, gender_form, email_form, phone_form, name_label, lastname_label,
                age_label, gender_label, email_label, phone_label, pass_form, repass_form,
                pass_label, repass_label, condit_box, condit_bt,
                name_error, lastname_error, age_error, email_invalid_error, email_already_error, phone_error,
                pass_notmatch_error);

        //Validate Data
        checkName(name_form, name_error);
        checkName(lastname_form, lastname_error);
        checkPhone(phone_form, phone_error);
        checkPhone(age_form, age_error);
        checkEmail(email_form, email_invalid_error);

        //[View] --show window
        rootPane.getChildren().addAll(regis_page_layout, form_layout);
        Scene main_page = new Scene(rootPane, 1280, 720);
        window.setScene(main_page);
        window.show();
    }

    private void checkName(TextField data, Label error) {
        data.setOnKeyReleased(e -> {
            String s = data.getText();
            if (check.isName(s)) {
                data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #6FE7DE;");
            } else {
                error.setVisible(true);
            }
        });
        data.setOnMouseExited(e -> {
            String s = data.getText();
            if (check.isName(s)) {
                data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #6FE7DE;");
            } else {
                error.setVisible(true);
            }
        });
        data.setOnKeyTyped(e -> {
            error.setVisible(false);
            data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #FAACAC;");
        });
        data.setOnMouseClicked(e -> {
            error.setVisible(false);
            data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #FFFFFF;");
        });
    }

    private void checkPhone(TextField data, Label error) {
        data.setOnKeyReleased(e -> {
            String s = data.getText();
            if (check.isPhoneNum(s)) {
                data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #6FE7DE;");
            } else {
                error.setVisible(true);
            }
        });
        data.setOnMouseExited(e -> {
            String s = data.getText();
            if (check.isPhoneNum(s)) {
                data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #6FE7DE;");
            } else {
                error.setVisible(true);
            }
        });
        data.setOnKeyTyped(e -> {
            error.setVisible(false);
            data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #FAACAC;");
        });
        data.setOnMouseClicked(e -> {
            error.setVisible(false);
            data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #FFFFFF;");
        });
    }

    private void checkEmail(TextField data, Label error) {
        data.setOnKeyReleased(e -> {
            String s = data.getText();
            if (check.isValidEmail(s)) {
                data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #6FE7DE;");
            } else {
                error.setVisible(true);
            }
        });
        data.setOnMouseExited(e -> {
            String s = data.getText();
            if (check.isValidEmail(s)) {
                data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #6FE7DE;");
            } else {
                error.setVisible(true);
            }
        });
        data.setOnKeyTyped(e -> {
            error.setVisible(false);
            data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #FAACAC;");
        });
        data.setOnMouseClicked(e -> {
            error.setVisible(false);
            data.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #FFFFFF;");
        });
    }

    private boolean checkPass(PasswordField pass, PasswordField repass, Label error) {
        String repas = repass.getText();
        String pas = pass.getText();
        if (repas.equals(pas)) {
            error.setVisible(false);
            pass.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #6FE7DE;");
            repass.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #6FE7DE;");
            return true;
        } else {
            error.setVisible(true);
            pass.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #FAACAC;");
            repass.setStyle("-fx-background-radius: 13; -fx-font-size: 22; -fx-background-color: #FAACAC;");
            return false;
        }
    }

    private void Submit() throws IOException, Exception {
        if (check.isName(this.firstName)) {
            if (check.isName(this.lastName)) {
                if (check.isPhoneNum(this.age)) {
                    if (check.isValidEmail(this.email)) {
                        if (check.isPhoneNum(this.phoneNumber)) {
                            if (isSame) {
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("แบบฟอร์มข้อมูลถูกตรวจสอบแล้ว");
                                alert.setHeaderText("ข้อมูลของคุณถูกต้อง !");
                                alert.setContentText("ยืนยันการสมัคร ?");

                                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                                stage.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));

                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    Database db = new Database();
                                    Account acc = new Account(this.firstName, this.lastName, this.age, this.gender, this.email, this.phoneNumber, this.password);
                                    db.addAccount(dbName, header.length, acc);
                                    System.out.println("Add User Seccess !");
                                    db.toTextFile(dbName);

                                    iHotel ihotel_page = new iHotel();
                                    ihotel_page.start(window);

                                } else {
                                    alert.close();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
