
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Redzer0
 */
public class User extends Application {

    private String username;
    private String password;
    private Stage window;
    private iHotel main_page;

    public static void main(String[] args) {
        launch(args);
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void start(Stage stage) throws Exception {

        //[Inintialize] Stage
        window = stage;
        window.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));
        window.setTitle("[iHotel] - User");
        window.setResizable(false);

        //[Component] - Logo
        ImageView logo = new ImageView(new Image(new FileInputStream(new File("src/img/logo-s.png"))));
        logo.setScaleX(0.3);
        logo.setScaleY(0.3);

        //[Component] - FormBox
        Rectangle box = new Rectangle(400, 860);
        box.setFill(Color.web("#005082"));
        box.setEffect(new DropShadow());
        box.setOpacity(0.3);

        Rectangle box2 = new Rectangle(980, 860);
        box2.setFill(Color.web("#005082"));
        box2.setEffect(new DropShadow());
        box2.setOpacity(0.3);

        Rectangle info_bg = new Rectangle(960, 770);
        info_bg.setFill(Color.web("#00A8CC"));
        info_bg.setEffect(new DropShadow());
        info_bg.setVisible(false);

        Rectangle booking_bg = new Rectangle(960, 770);
        booking_bg.setFill(Color.web("#005082"));
        booking_bg.setEffect(new DropShadow());
        booking_bg.setVisible(true);

        Rectangle status_bg = new Rectangle(960, 770);
        status_bg.setFill(Color.web("#1EB2A6"));
        status_bg.setEffect(new DropShadow());
        status_bg.setVisible(false);

        //===================|  Logout [Button] setting |=========================
        Button logout_bt = new Button("ออกจากระบบ");
        logout_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        logout_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #F67575; -fx-text-fill: #000000");
        logout_bt.setDisable(false);
        //On mouse idle
        logout_bt.setOnMouseExited(e -> {
            logout_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #F67575; -fx-text-fill: #000000");
            logout_bt.setEffect(null);
        });
        //On mouse hover
        logout_bt.setOnMouseEntered(e -> {
            logout_bt.setStyle(" -fx-background-radius: 30; -fx-background-color: #FAACAC; -fx-text-fill: #ffffff");
            logout_bt.setEffect(new DropShadow());
        });
        //On mouse click
        logout_bt.setOnMouseClicked(e -> {
            logout_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #F67575; -fx-text-fill: #ffffff");
            window.close();
            iHotel main_page = new iHotel();
            try {
                main_page.start(window);
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //============================================================

        //===================|  Profile [Button] setting |=========================
        Button profile_bt = new Button("บัญชีผู้ใช้งาน");
        profile_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        profile_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
        profile_bt.setDisable(false);
        //On mouse idle
        profile_bt.setOnMouseExited(e -> {
            profile_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
            profile_bt.setEffect(null);
        });
        //On mouse hover
        profile_bt.setOnMouseEntered(e -> {
            profile_bt.setStyle(" -fx-background-radius: 30; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff");
            profile_bt.setEffect(new DropShadow());
        });
        //On mouse click
        profile_bt.setOnMouseClicked(e -> {
            profile_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #FFC997; -fx-text-fill: #ffffff");

        });

        //===================|  Booking [Button] setting |=========================
        Button booking_bt = new Button("จองห้องพัก");
        booking_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        booking_bt.setStyle("-fx-background-color: #005082; -fx-text-fill: #ffffff");
        booking_bt.setDisable(false);
        //On mouse idle
        booking_bt.setOnMouseExited(e -> {
            booking_bt.setStyle("-fx-background-color: #005082; -fx-text-fill: #ffffff");
            booking_bt.setEffect(null);

        });
        //On mouse hover
        booking_bt.setOnMouseEntered(e -> {
            booking_bt.setStyle(" -fx-background-color: #005082; -fx-text-fill: #fff000");
        });
        //On mouse click
        booking_bt.setOnMouseClicked(e -> {
            booking_bt.setStyle(" -fx-background-color: #005082; -fx-text-fill: #ffffff");
            booking_bg.setVisible(true);
            info_bg.setVisible(false);
            status_bg.setVisible(false);
            booking_bt.setEffect(new DropShadow());

        });
        //============================================================

        //===================|  Info [Button] setting |=========================
        Button info_bt = new Button("ข้อมูลห้องพัก");
        info_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        info_bt.setStyle("-fx-background-color: #00A8CC; -fx-text-fill: #ffffff");
        info_bt.setDisable(false);
        //On mouse idle
        info_bt.setOnMouseExited(e -> {
            info_bt.setStyle("-fx-background-color: #00A8CC; -fx-text-fill: #ffffff");
            info_bt.setEffect(null);

        });
        //On mouse hover
        info_bt.setOnMouseEntered(e -> {
            info_bt.setStyle(" -fx-background-color: #00A8CC; -fx-text-fill: #fff000");
        });
        //On mouse click
        info_bt.setOnMouseClicked(e -> {
            info_bt.setStyle(" -fx-background-color: #00A8CC; -fx-text-fill: #ffffff");
            booking_bg.setVisible(false);
            info_bg.setVisible(true);
            status_bg.setVisible(false);
            info_bt.setEffect(new DropShadow());
        });
        //============================================================

        //===================|  Status [Button] setting |=========================
        Button status_bt = new Button("สถานะการจอง");
        status_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        status_bt.setStyle("-fx-background-color: #1EB2A6; -fx-text-fill: #ffffff");
        status_bt.setDisable(false);
        //On mouse idle
        status_bt.setOnMouseExited(e -> {
            status_bt.setStyle("-fx-background-color: #1EB2A6; -fx-text-fill: #ffffff");
            status_bt.setEffect(null);

        });
        //On mouse hover
        status_bt.setOnMouseEntered(e -> {
            status_bt.setStyle(" -fx-background-color: #1EB2A6; -fx-text-fill: #fff000");
        });
        //On mouse click
        status_bt.setOnMouseClicked(e -> {
            status_bt.setStyle(" -fx-background-color: #1EB2A6; -fx-text-fill: #ffffff");
            booking_bg.setVisible(false);
            info_bg.setVisible(false);
            status_bg.setVisible(true);
            status_bt.setEffect(new DropShadow());
        });
        //============================================================

        //[Pane Setting]
        StackPane rootPane = new StackPane();

        //[HBox Pane] -- Initialize
        HBox user_page_layout = new HBox(2);
        user_page_layout.setPadding(new Insets(20));
        user_page_layout.setStyle("-fx-background-color: #00A8CC;");
        user_page_layout.setSpacing(20);
        user_page_layout.getChildren().addAll(box, box2);

        //[AnchorPane] -- Initialize
        AnchorPane menu_layout = new AnchorPane();

        //[AnchorPane] -- add component
        // Anchor to Logout [Button]
        AnchorPane.setTopAnchor(logout_bt, 880.0);
        AnchorPane.setLeftAnchor(logout_bt, 80.0);
        AnchorPane.setRightAnchor(logout_bt, 1080.0);
        AnchorPane.setBottomAnchor(logout_bt, 120.0);

        // Anchor to Profile [Button]
        AnchorPane.setTopAnchor(profile_bt, 810.0);
        AnchorPane.setLeftAnchor(profile_bt, 80.0);
        AnchorPane.setRightAnchor(profile_bt, 1080.0);
        AnchorPane.setBottomAnchor(profile_bt, 190.0);

        // Anchor to Booking_bg [Rectangle]
        AnchorPane.setTopAnchor(booking_bg, 110.0);
        AnchorPane.setLeftAnchor(booking_bg, 480.0);
        AnchorPane.setRightAnchor(booking_bg, 60.0);
        AnchorPane.setBottomAnchor(booking_bg, 100.0);

        // Anchor to Info_bg [Rectangle]
        AnchorPane.setTopAnchor(info_bg, 110.0);
        AnchorPane.setLeftAnchor(info_bg, 480.0);
        AnchorPane.setRightAnchor(info_bg, 60.0);
        AnchorPane.setBottomAnchor(info_bg, 100.0);

        // Anchor to Status_bg [Rectangle]
        AnchorPane.setTopAnchor(status_bg, 110.0);
        AnchorPane.setLeftAnchor(status_bg, 480.0);
        AnchorPane.setRightAnchor(status_bg, 60.0);
        AnchorPane.setBottomAnchor(status_bg, 100.0);

        // Anchor to Booking [Button]
        AnchorPane.setTopAnchor(booking_bt, 880.0);
        AnchorPane.setLeftAnchor(booking_bt, 480.0);
        AnchorPane.setRightAnchor(booking_bt, 780.0);
        AnchorPane.setBottomAnchor(booking_bt, 110.0);

        // Anchor to Info [Button]
        AnchorPane.setTopAnchor(info_bt, 880.0);
        AnchorPane.setLeftAnchor(info_bt, 720.0);
        AnchorPane.setRightAnchor(info_bt, 580.0);
        AnchorPane.setBottomAnchor(info_bt, 110.0);

        // Anchor to Status [Button]
        AnchorPane.setTopAnchor(status_bt, 880.0);
        AnchorPane.setLeftAnchor(status_bt, 920.0);
        AnchorPane.setRightAnchor(status_bt, 400.0);
        AnchorPane.setBottomAnchor(status_bt, 110.0);

        // Anchor to Logo [Image]
        AnchorPane.setTopAnchor(logo, 840.0);
        AnchorPane.setLeftAnchor(logo, 1100.0);
        AnchorPane.setRightAnchor(logo, 10.0);
        AnchorPane.setBottomAnchor(logo, 10.0);

        menu_layout.getChildren().addAll(logout_bt, profile_bt, booking_bg, info_bg, status_bg,
                status_bt, info_bt, booking_bt, logo);

        //[View] --show window
        rootPane.getChildren().addAll(user_page_layout, menu_layout);
        Scene user_page = new Scene(rootPane, 1440, 900);
        window.setScene(user_page);
        window.show();
    }

}
