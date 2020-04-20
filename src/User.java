
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private double breakfast_price = 800.0;
    private double dinner_price = 1750.0;
    private double summery_room_price = 10000.0;
    private double vat_price = 1000.0;
    private double summery_price = 90000.0;

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

        //[Component] - Logo
        ImageView image = new ImageView(new Image(new FileInputStream(new File("src/img/ce club.jpg"))));

        Label welcome_label = new Label("ยินดีต้อนรับ คุณ ");
        welcome_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 28));
        welcome_label.setStyle("-fx-text-fill: #ffffff");
        welcome_label.setVisible(true);

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

        //[Component] - Info Tab 
        //===============================[ Info ]==============================//
        Label labelRoomStyle = new Label("รายละเอียดห้องต่างๆ");
        labelRoomStyle.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 32));

        //Superior Room
        Label labelSuperior = new Label("Superior Room");
        labelSuperior.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 30));
        Image imageSuperior1 = new Image("RoomStyle/Superior.jpg");
        ImageView imageViewSuperior1 = new ImageView(imageSuperior1);
        imageViewSuperior1.setFitWidth(800);
        imageViewSuperior1.setFitHeight(400);

        Image imageSuperior2 = new Image("RoomStyle/Superior2.jpg");
        ImageView imageViewSuperior2 = new ImageView(imageSuperior2);
        imageViewSuperior2.setFitWidth(800);
        imageViewSuperior2.setFitHeight(400);

        GridPane gpSuperior = new GridPane();
        gpSuperior.setHgap(10);
        gpSuperior.setVgap(10);
        gpSuperior.add(labelSuperior, 1, 0);
        gpSuperior.add(imageViewSuperior1, 1, 1);
        gpSuperior.add(imageViewSuperior2, 1, 2);
        gpSuperior.setAlignment(Pos.CENTER);

        //Delux Room
        Label labelDelux = new Label("Delux Room");
        labelDelux.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 30));
        Image imageDelux1 = new Image("RoomStyle/Delux.jpg");
        ImageView imageViewDelux1 = new ImageView(imageDelux1);
        imageViewDelux1.setFitWidth(800);
        imageViewDelux1.setFitHeight(400);

        Image imageDelux2 = new Image("RoomStyle/Delux2.jpg");
        ImageView imageViewDelux2 = new ImageView(imageDelux2);
        imageViewDelux2.setFitWidth(800);
        imageViewDelux2.setFitHeight(400);

        GridPane gpDelux = new GridPane();
        gpDelux.setHgap(10);
        gpDelux.setVgap(10);
        gpDelux.add(labelDelux, 1, 0);
        gpDelux.add(imageViewDelux1, 1, 1);
        gpDelux.add(imageViewDelux2, 1, 2);
        gpDelux.setAlignment(Pos.CENTER);

        //Junior Suite Room
        Label labelJuniorSuite = new Label("Junior Suite Room");
        labelJuniorSuite.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 30));
        Image imageJuniorSuite1 = new Image("RoomStyle/JuniorSuite.jpg");
        ImageView imageViewJuniorSuite1 = new ImageView(imageJuniorSuite1);
        imageViewJuniorSuite1.setFitWidth(800);
        imageViewJuniorSuite1.setFitHeight(400);

        Image imageJuniorImage2 = new Image("RoomStyle/JuniorSuite2.jpg");
        ImageView imageViewJuniorSuite2 = new ImageView(imageJuniorImage2);
        imageViewJuniorSuite2.setFitWidth(800);
        imageViewJuniorSuite2.setFitHeight(400);

        Image imageJuniorImage3 = new Image("RoomStyle/JuniorSuite3.jpg");
        ImageView imageViewJuniorSuite3 = new ImageView(imageJuniorImage3);
        imageViewJuniorSuite3.setFitWidth(800);
        imageViewJuniorSuite3.setFitHeight(400);

        GridPane gpJuniorSuite = new GridPane();
        gpJuniorSuite.setHgap(10);
        gpJuniorSuite.setVgap(10);
        gpJuniorSuite.add(labelJuniorSuite, 1, 0);
        gpJuniorSuite.add(imageViewJuniorSuite1, 1, 1);
        gpJuniorSuite.add(imageViewJuniorSuite2, 1, 2);
        gpJuniorSuite.add(imageViewJuniorSuite3, 1, 3);
        gpJuniorSuite.setAlignment(Pos.CENTER);

        //Royal Suite Room
        Label labelRoyalSuite = new Label("Royal Suite Room");
        labelRoyalSuite.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 30));
        Image imageRoyalSuite1 = new Image("RoomStyle/Royal Suite.jpg");
        ImageView imageViewRoyalSuite1 = new ImageView(imageRoyalSuite1);
        imageViewRoyalSuite1.setFitWidth(800);
        imageViewRoyalSuite1.setFitHeight(400);

        Image imageRoyalSuite2 = new Image("RoomStyle/Royal Suite 2.jpg");
        ImageView imageViewRoyalSuite2 = new ImageView(imageRoyalSuite2);
        imageViewRoyalSuite2.setFitWidth(800);
        imageViewRoyalSuite2.setFitHeight(400);

        Image imageRoyalSuite3 = new Image("RoomStyle/Royal Suite 3.jpg");
        ImageView imageViewRoyalSuite3 = new ImageView(imageRoyalSuite3);
        imageViewRoyalSuite3.setFitWidth(800);
        imageViewRoyalSuite3.setFitHeight(400);

        GridPane gpRoyalSuite = new GridPane();
        gpRoyalSuite.setHgap(10);
        gpRoyalSuite.setVgap(10);
        gpRoyalSuite.add(labelRoyalSuite, 1, 0);
        gpRoyalSuite.add(imageViewRoyalSuite1, 1, 1);
        gpRoyalSuite.add(imageViewRoyalSuite2, 1, 2);
        gpRoyalSuite.add(imageViewRoyalSuite3, 1, 3);
        gpRoyalSuite.setAlignment(Pos.CENTER);

        //---------------------------------------------------------Gym Details---------------------------------------------//
        Label labelGym = new Label("The Gym");
        labelGym.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 30));
        Image imageGym1 = new Image("RoomStyle/Gym.jpg");
        ImageView imageViewGym1 = new ImageView(imageGym1);
        imageViewGym1.setFitWidth(800);
        imageViewGym1.setFitHeight(400);

        Image imageGym2 = new Image("RoomStyle/Gym2.jpg");
        ImageView imageViewGym2 = new ImageView(imageGym2);
        imageViewGym2.setFitWidth(800);
        imageViewGym2.setFitHeight(400);

        GridPane gpGym = new GridPane();
        gpGym.setHgap(10);
        gpGym.setVgap(10);
        gpGym.add(labelGym, 1, 0);
        gpGym.add(imageViewGym1, 1, 1);
        gpGym.add(imageViewGym2, 1, 2);
        gpGym.setAlignment(Pos.CENTER);

        //-----------------------------------------------------------Restaurant Details---------------------------------------------//
        Label labelRestaurant = new Label("Restaurant Room");
        labelRestaurant.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 30));
        Image imageRestaurant1 = new Image("RoomStyle/Restaurant.jpg");
        ImageView imageViewRestaurant1 = new ImageView(imageRestaurant1);
        imageViewRestaurant1.setFitWidth(800);
        imageViewRestaurant1.setFitHeight(400);

        Image imageRestaurant2 = new Image("RoomStyle/Restaurant2.jpg");
        ImageView imageViewRestaurant2 = new ImageView(imageRestaurant2);
        imageViewRestaurant2.setFitWidth(800);
        imageViewRestaurant2.setFitHeight(400);

        Image imageRestaurant3 = new Image("RoomStyle/Restaurant3.jpg");
        ImageView imageViewRestaurant3 = new ImageView(imageRestaurant3);
        imageViewRestaurant3.setFitWidth(800);
        imageViewRestaurant3.setFitHeight(400);

        Image imageRestaurant4 = new Image("RoomStyle/Restaurant4.jpg");
        ImageView imageViewRestaurant4 = new ImageView(imageRestaurant4);
        imageViewRestaurant4.setFitWidth(800);
        imageViewRestaurant4.setFitHeight(400);

        GridPane gpRestaurant = new GridPane();
        gpRestaurant.setHgap(10);
        gpRestaurant.setVgap(10);
        gpRestaurant.add(labelRestaurant, 1, 0);
        gpRestaurant.add(imageViewRestaurant2, 1, 1);
        gpRestaurant.add(imageViewRestaurant3, 1, 2);
        gpRestaurant.add(imageViewRestaurant4, 1, 3);
        gpRestaurant.add(imageViewRestaurant1, 1, 4);
        gpRestaurant.setAlignment(Pos.CENTER);

        //------------------------------------------The view and other rest areas-----------------------------------------//
        Label labelTHeView = new Label("The View and Other rest areas");
        labelTHeView.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 30));
        Image imageTheView1 = new Image("RoomStyle/View1.jpg");
        ImageView imageViewTheView1 = new ImageView(imageTheView1);
        imageViewTheView1.setFitWidth(800);
        imageViewTheView1.setFitHeight(400);

        Image imageTheView2 = new Image("RoomStyle/View2.jpg");
        ImageView imageViewTheView2 = new ImageView(imageTheView2);
        imageViewTheView2.setFitWidth(800);
        imageViewTheView2.setFitHeight(400);

        Image imageTheView3 = new Image("RoomStyle/View3.jpg");
        ImageView imageViewTheView3 = new ImageView(imageTheView3);
        imageViewTheView3.setFitWidth(800);
        imageViewTheView3.setFitHeight(400);

        Image imageTheView4 = new Image("RoomStyle/View4.jpg");
        ImageView imageViewTheView4 = new ImageView(imageTheView4);
        imageViewTheView4.setFitWidth(800);
        imageViewTheView4.setFitHeight(400);

        Image imageTheView5 = new Image("RoomStyle/View5.jpg");
        ImageView imageViewTheView5 = new ImageView(imageTheView5);
        imageViewTheView5.setFitWidth(800);
        imageViewTheView5.setFitHeight(400);

        Image imageTheView6 = new Image("RoomStyle/View6.jpg");
        ImageView imageViewTheView6 = new ImageView(imageTheView6);
        imageViewTheView6.setFitWidth(800);
        imageViewTheView6.setFitHeight(400);

        Image imageTheView7 = new Image("RoomStyle/View7.jpg");
        ImageView imageViewTheView7 = new ImageView(imageTheView7);
        imageViewTheView7.setFitWidth(800);
        imageViewTheView7.setFitHeight(400);

        GridPane gpView = new GridPane();
        gpView.setHgap(10);
        gpView.setVgap(10);
        gpView.add(labelTHeView, 1, 0);
        gpView.add(imageViewTheView1, 1, 1);
        gpView.add(imageViewTheView2, 1, 2);
        gpView.add(imageViewTheView3, 1, 3);
        gpView.add(imageViewTheView4, 1, 4);
        gpView.add(imageViewTheView5, 1, 5);
        gpView.add(imageViewTheView6, 1, 6);
        gpView.add(imageViewTheView7, 1, 7);
        gpView.setAlignment(Pos.CENTER);

        VBox PackRoomDetailsAll = new VBox(20);
        PackRoomDetailsAll.getChildren().addAll(labelRoomStyle, gpSuperior, gpDelux, gpJuniorSuite, gpRoyalSuite, gpGym, gpRestaurant, gpView);
        PackRoomDetailsAll.setAlignment(Pos.CENTER);

        ScrollPane scrollPaneDetails = new ScrollPane();
        scrollPaneDetails.fitToWidthProperty().set(true);
        scrollPaneDetails.fitToHeightProperty().set(true);
        scrollPaneDetails.setContent(PackRoomDetailsAll);
        scrollPaneDetails.setVisible(false);

        //[Component] - Booking Tab 
        //===========================[ Booking ]===========================//
        Label serch_room = new Label("ค้นหาห้องพัก");
        serch_room.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 28));
        serch_room.setStyle("-fx-text-fill: #ffffff");
        serch_room.setVisible(true);

        Label check_in_lable = new Label("วันเริ่มต้นพัก (CHECK-IN)");
        check_in_lable.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        check_in_lable.setStyle("-fx-text-fill: #ffffff");
        check_in_lable.setVisible(true);

        Label check_out_lable = new Label("วันสิ้นสุดพัก (CHECK-OUT)");
        check_out_lable.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        check_out_lable.setStyle("-fx-text-fill: #ffffff");
        check_out_lable.setVisible(true);

        Label adult_label = new Label("ผู้ใหญ่");
        adult_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        adult_label.setStyle("-fx-text-fill: #ffffff");
        adult_label.setVisible(true);

        Label young_label = new Label("เด็ก");
        young_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        young_label.setStyle("-fx-text-fill: #ffffff");
        young_label.setVisible(true);

        Label numRoom_label = new Label("จำนวนห้อง");
        numRoom_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        numRoom_label.setStyle("-fx-text-fill: #ffffff");
        numRoom_label.setVisible(true);

        Label add_on_label = new Label("บริการเสริม");
        add_on_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        add_on_label.setStyle("-fx-text-fill: #ffffff");
        add_on_label.setUnderline(true);
        add_on_label.setVisible(true);

        Label summery_room_price = new Label("ราคาห้องรวม           " + this.summery_room_price + " บาท");
        summery_room_price.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        summery_room_price.setStyle("-fx-text-fill: #ffffff");
        summery_room_price.setVisible(true);

        Label vat_price = new Label("รวมภาษีมูลค่าเพิ่ม 7%    " + this.vat_price + " บาท");
        vat_price.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        vat_price.setStyle("-fx-text-fill: #ffffff");
        vat_price.setVisible(true);

        Label summery_price = new Label("ราคารวมสุทธิ       " + this.summery_price + " บาท");
        summery_price.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 28));
        summery_price.setStyle("-fx-text-fill: #fff000");
        summery_price.setVisible(true);

        CheckBox breakfast = new CheckBox("บริการอาหารเช้า " + this.breakfast_price + " บาท/มื้อ");
        breakfast.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        breakfast.setStyle("-fx-text-fill: #ffffff");

        CheckBox dinner = new CheckBox("บริการอาหารเย็น " + this.dinner_price + " บาท/มื้อ");
        dinner.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        dinner.setStyle("-fx-text-fill: #ffffff");

        DatePicker check_in_date = new DatePicker();
        check_in_date.setStyle("-fx-font-size: 20;");

        DatePicker check_out_date = new DatePicker();
        check_out_date.setStyle(" -fx-font-size: 20;");

        Spinner<Integer> adult_num = new Spinner<Integer>(0, 15, 1);
        adult_num.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        adult_num.setStyle("-fx-font-size: 20;");

        Spinner<Integer> young_num = new Spinner<Integer>(0, 15, 0);
        young_num.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        young_num.setStyle("13; -fx-font-size: 20;");

        Spinner<Integer> room_num = new Spinner<Integer>(1, 15, 1);
        room_num.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
        room_num.setStyle(" -fx-font-size: 20;");

        TableView tableView = new TableView();

        TableColumn col1 = new TableColumn<>("รายการ");
        col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn col2 = new TableColumn<>("ลักษณะห้องพัก");
        col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn col3 = new TableColumn<>("รูปแบบ");
        col3.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn col4 = new TableColumn<>("ราตา ต่อห้อง ต่อคืน");
        col4.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn col5 = new TableColumn<>("ห้อง");
        col4.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn col6 = new TableColumn<>("จองเลย");

        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col4);
        tableView.getColumns().add(col5);
        tableView.getColumns().add(col6);
        VBox searchRoom = new VBox(tableView);
        searchRoom.setVisible(true);

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
        //============================================================

        //===================|  Search [Button] setting |=========================
        Button Search_bt = new Button("ค้นหา");
        Search_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        Search_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
        Search_bt.setDisable(false);
        //On mouse idle
        Search_bt.setOnMouseExited(e -> {
            Search_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
            Search_bt.setEffect(null);
        });
        //On mouse hover
        Search_bt.setOnMouseEntered(e -> {
            Search_bt.setStyle(" -fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff");
            Search_bt.setEffect(new DropShadow());
        });
        //On mouse click
        Search_bt.setOnMouseClicked(e -> {
            Search_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFC997; -fx-text-fill: #ffffff");

        });
        //============================================================

        //===================|  Booking [Button] setting |=========================
        Button Booking_bt = new Button("จองเลยเดี๋ยวนี้");
        Booking_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        Booking_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
        Booking_bt.setDisable(false);
        //On mouse idle
        Booking_bt.setOnMouseExited(e -> {
            Booking_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
            Booking_bt.setEffect(null);
        });
        //On mouse hover
        Booking_bt.setOnMouseEntered(e -> {
            Booking_bt.setStyle(" -fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff");
            Booking_bt.setEffect(new DropShadow());
        });
        //On mouse click
        Booking_bt.setOnMouseClicked(e -> {
            Booking_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFC997; -fx-text-fill: #ffffff");

        });
        //============================================================

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
            booking_bt.setStyle(" -fx-background-color: #0081D0; -fx-text-fill: #fff000");
        });
        //On mouse click
        booking_bt.setOnMouseClicked(e -> {
            booking_bt.setStyle(" -fx-background-color: #005082; -fx-text-fill: #ffffff");

            searchRoom.setVisible(true);
            scrollPaneDetails.setVisible(false);

            searchRoom.setVisible(true);
            serch_room.setVisible(true);
            check_in_lable.setVisible(true);
            check_in_date.setVisible(true);
            check_out_lable.setVisible(true);
            check_out_date.setVisible(true);
            adult_num.setVisible(true);
            young_num.setVisible(true);
            adult_label.setVisible(true);
            young_label.setVisible(true);
            numRoom_label.setVisible(true);
            room_num.setVisible(true);
            Search_bt.setVisible(true);
            add_on_label.setVisible(true);
            breakfast.setVisible(true);
            dinner.setVisible(true);
            Booking_bt.setVisible(true);
            summery_price.setVisible(true);
            summery_room_price.setVisible(true);
            vat_price.setVisible(true);

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
            info_bt.setStyle(" -fx-background-color: #09D6FF; -fx-text-fill: #fff000");
        });
        //On mouse click
        info_bt.setOnMouseClicked(e -> {
            info_bt.setStyle(" -fx-background-color: #00A8CC; -fx-text-fill: #ffffff");

            searchRoom.setVisible(false);
            scrollPaneDetails.setVisible(true);
            scrollPaneDetails.setVvalue(0);

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
            status_bt.setStyle(" -fx-background-color: #30DCD0; -fx-text-fill: #fff000");
        });
        //On mouse click
        status_bt.setOnMouseClicked(e -> {
            status_bt.setStyle(" -fx-background-color: #1EB2A6; -fx-text-fill: #ffffff");

            searchRoom.setVisible(false);
            scrollPaneDetails.setVisible(false);

            searchRoom.setVisible(false);
            serch_room.setVisible(false);
            check_in_lable.setVisible(false);
            check_in_date.setVisible(false);
            check_out_lable.setVisible(false);
            check_out_date.setVisible(false);
            adult_num.setVisible(false);
            young_num.setVisible(false);
            adult_label.setVisible(false);
            young_label.setVisible(false);
            numRoom_label.setVisible(false);
            room_num.setVisible(false);
            Search_bt.setVisible(false);
            add_on_label.setVisible(false);
            breakfast.setVisible(false);
            dinner.setVisible(false);
            Booking_bt.setVisible(false);
            summery_price.setVisible(false);
            summery_room_price.setVisible(false);
            vat_price.setVisible(false);

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

        // Anchor to Welcime [Label]
        AnchorPane.setTopAnchor(welcome_label, 60.0);
        AnchorPane.setLeftAnchor(welcome_label, 80.0);
        AnchorPane.setRightAnchor(welcome_label, 1080.0);
        AnchorPane.setBottomAnchor(welcome_label, 820.0);

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

        // Anchor to ScollPane-Info [Pane]
        AnchorPane.setTopAnchor(scrollPaneDetails, 120.0);
        AnchorPane.setLeftAnchor(scrollPaneDetails, 500.0);
        AnchorPane.setRightAnchor(scrollPaneDetails, 80.0);
        AnchorPane.setBottomAnchor(scrollPaneDetails, 180.0);

        // Anchor to SerachTable [Booking-TAB]
        AnchorPane.setTopAnchor(searchRoom, 280.0);
        AnchorPane.setLeftAnchor(searchRoom, 500.0);
        AnchorPane.setRightAnchor(searchRoom, 80.0);
        AnchorPane.setBottomAnchor(searchRoom, 400.0);

        // Anchor to Serach Label [Booking-TAB]
        AnchorPane.setTopAnchor(serch_room, 80.0);
        AnchorPane.setLeftAnchor(serch_room, 510.0);
        AnchorPane.setRightAnchor(serch_room, 500.0);
        AnchorPane.setBottomAnchor(serch_room, 850.0);

        // Anchor to Check-In Label [Booking-TAB]
        AnchorPane.setTopAnchor(check_in_lable, 150.0);
        AnchorPane.setLeftAnchor(check_in_lable, 510.0);
        AnchorPane.setRightAnchor(check_in_lable, 500.0);
        AnchorPane.setBottomAnchor(check_in_lable, 850.0);

        // Anchor to Check-In Date [Booking-TAB]
        AnchorPane.setTopAnchor(check_in_date, 200.0);
        AnchorPane.setLeftAnchor(check_in_date, 510.0);
        AnchorPane.setRightAnchor(check_in_date, 820.0);
        AnchorPane.setBottomAnchor(check_in_date, 810.0);

        // Anchor to Check-Out Label [Booking-TAB]
        AnchorPane.setTopAnchor(check_out_lable, 150.0);
        AnchorPane.setLeftAnchor(check_out_lable, 720.0);
        AnchorPane.setRightAnchor(check_out_lable, 300.0);
        AnchorPane.setBottomAnchor(check_out_lable, 850.0);

        // Anchor to Check-Out Date [Booking-TAB]
        AnchorPane.setTopAnchor(check_out_date, 200.0);
        AnchorPane.setLeftAnchor(check_out_date, 730.0);
        AnchorPane.setRightAnchor(check_out_date, 600.0);
        AnchorPane.setBottomAnchor(check_out_date, 810.0);

        // Anchor to Adult-Number Label [Booking-TAB]
        AnchorPane.setTopAnchor(adult_label, 150.0);
        AnchorPane.setLeftAnchor(adult_label, 965.0);
        AnchorPane.setRightAnchor(adult_label, 460.0);
        AnchorPane.setBottomAnchor(adult_label, 850.0);

        // Anchor to Adult-Number [Booking-TAB]
        AnchorPane.setTopAnchor(adult_num, 200.0);
        AnchorPane.setLeftAnchor(adult_num, 930.0);
        AnchorPane.setRightAnchor(adult_num, 460.0);
        AnchorPane.setBottomAnchor(adult_num, 810.0);

        // Anchor to Young-Number Label [Booking-TAB]
        AnchorPane.setTopAnchor(young_label, 150.0);
        AnchorPane.setLeftAnchor(young_label, 1090.0);
        AnchorPane.setRightAnchor(young_label, 340.0);
        AnchorPane.setBottomAnchor(young_label, 850.0);

        // Anchor to Young-Number [Booking-TAB]
        AnchorPane.setTopAnchor(young_num, 200.0);
        AnchorPane.setLeftAnchor(young_num, 1050.0);
        AnchorPane.setRightAnchor(young_num, 340.0);
        AnchorPane.setBottomAnchor(young_num, 810.0);

        // Anchor to Num-Room Label [Booking-TAB]
        AnchorPane.setTopAnchor(numRoom_label, 150.0);
        AnchorPane.setLeftAnchor(numRoom_label, 1185.0);
        AnchorPane.setRightAnchor(numRoom_label, 240.0);
        AnchorPane.setBottomAnchor(numRoom_label, 850.0);

        // Anchor to Num-Room [Booking-TAB]
        AnchorPane.setTopAnchor(room_num, 200.0);
        AnchorPane.setLeftAnchor(room_num, 1170.0);
        AnchorPane.setRightAnchor(room_num, 220.0);
        AnchorPane.setBottomAnchor(room_num, 810.0);

        // Anchor to Search Button [Booking-TAB]
        AnchorPane.setTopAnchor(Search_bt, 180.0);
        AnchorPane.setLeftAnchor(Search_bt, 1300.0);
        AnchorPane.setRightAnchor(Search_bt, 100.0);
        AnchorPane.setBottomAnchor(Search_bt, 810.0);

        // Anchor to Add-on Label [Booking-TAB]
        AnchorPane.setTopAnchor(add_on_label, 600.0);
        AnchorPane.setLeftAnchor(add_on_label, 510.0);
        AnchorPane.setRightAnchor(add_on_label, 820.0);
        AnchorPane.setBottomAnchor(add_on_label, 280.0);

        // Anchor to Breakfast CheckBox [Booking-TAB]
        AnchorPane.setTopAnchor(breakfast, 680.0);
        AnchorPane.setLeftAnchor(breakfast, 510.0);
        AnchorPane.setRightAnchor(breakfast, 720.0);
        AnchorPane.setBottomAnchor(breakfast, 260.0);

        // Anchor to Dinner CheckBox [Booking-TAB]
        AnchorPane.setTopAnchor(dinner, 760.0);
        AnchorPane.setLeftAnchor(dinner, 510.0);
        AnchorPane.setRightAnchor(dinner, 720.0);
        AnchorPane.setBottomAnchor(dinner, 230.0);

        // Anchor to Booking Button [Booking-TAB]
        AnchorPane.setTopAnchor(Booking_bt, 800.0);
        AnchorPane.setLeftAnchor(Booking_bt, 1050.0);
        AnchorPane.setRightAnchor(Booking_bt, 100.0);
        AnchorPane.setBottomAnchor(Booking_bt, 200.0);

        // Anchor to Summery room price [Booking-TAB]
        AnchorPane.setTopAnchor(summery_room_price, 580.0);
        AnchorPane.setLeftAnchor(summery_room_price, 1050.0);
        AnchorPane.setRightAnchor(summery_room_price, 100.0);
        AnchorPane.setBottomAnchor(summery_room_price, 260.0);

        // Anchor to Vat price [Booking-TAB]
        AnchorPane.setTopAnchor(vat_price, 610.0);
        AnchorPane.setLeftAnchor(vat_price, 1050.0);
        AnchorPane.setRightAnchor(vat_price, 100.0);
        AnchorPane.setBottomAnchor(vat_price, 250.0);

        // Anchor to Summery  price [Booking-TAB]
        AnchorPane.setTopAnchor(summery_price, 680.0);
        AnchorPane.setLeftAnchor(summery_price, 1050.0);
        AnchorPane.setRightAnchor(summery_price, 100.0);
        AnchorPane.setBottomAnchor(summery_price, 240.0);

        menu_layout.getChildren().addAll(logout_bt, profile_bt, booking_bg, info_bg, status_bg, logo,
                status_bt, info_bt, booking_bt, welcome_label,
                searchRoom, serch_room, check_in_lable, check_in_date, check_out_lable, check_out_date,
                adult_num, young_num, adult_label, young_label, numRoom_label, room_num, Search_bt,
                add_on_label, breakfast, dinner, Booking_bt, summery_price, summery_room_price, vat_price,
                scrollPaneDetails);

        //[View] --show window
        rootPane.getChildren().addAll(user_page_layout, menu_layout);
        //rootPane.getChildren().addAll(scrollPaneDetails);
        Scene user_page = new Scene(rootPane, 1440, 900);
        window.setScene(user_page);
        window.show();
    }

}