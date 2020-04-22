
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private int numSelect = 0;
    private int numDeSelect = 0;
    private boolean isClick;
    private double breakfast_price = 800.0;
    private double dinner_price = 1750.0;
    private double summery_room_price = 0.0;
    private double vat_price = 0.0;
    private double summery_price = 0.0;
    private int NumberOfDay = 0;

    private Stage window;

    //Custom Class
    private iHotel main_page;
    private Room r1 = new Room();
    private Room r2 = new Room();
    private Account acc_temp;
    private Authentication auth = new Authentication();
    private Database db = new Database();

    public static void main(String[] args) {
        launch(args);
    }

    public User() {
    }

    public User(String username, String password) throws IOException {
        this.username = username;
        this.password = password;
        acc_temp = db.getAccount(this.username, this.password);
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

        Label welcome_label;

        welcome_label = new Label("ยินดีต้อนรับ คุณ   ");

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

        //[Component] - Profile editor
        Rectangle profile_bg = new Rectangle(400, 860);
        profile_bg.setFill(Color.web("#ffffff"));
        profile_bg.setVisible(false);

        Label profile_name_label = new Label("ชื่อ");
        profile_name_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_name_label.setStyle("-fx-text-fill: #000000");
        profile_name_label.setVisible(false);

        Label profile_lastname_label = new Label("นามสกุล");
        profile_lastname_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_lastname_label.setStyle("-fx-text-fill: #000000");
        profile_lastname_label.setVisible(false);

        Label profile_age_label = new Label("อายุ");
        profile_age_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_age_label.setStyle("-fx-text-fill: #000000");
        profile_age_label.setVisible(false);

        Label profile_gender_label = new Label("เพศ");
        profile_gender_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_gender_label.setStyle("-fx-text-fill: #000000");
        profile_gender_label.setVisible(false);

        Label profile_email_label = new Label("อีเมล");
        profile_email_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_email_label.setStyle("-fx-text-fill: #000000");
        profile_email_label.setVisible(false);

        Label profile_phone_label = new Label("เบอร์โทร");
        profile_phone_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_phone_label.setStyle("-fx-text-fill: #000000");
        profile_phone_label.setVisible(false);

        TextField profile_name = new TextField();
        profile_name.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_name.setStyle("-fx-text-fill: #000000");
        profile_name.setVisible(false);

        TextField profile_lastname = new TextField();
        profile_lastname.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_lastname.setStyle("-fx-text-fill: #000000");
        profile_lastname.setVisible(false);

        TextField profile_age = new TextField();
        profile_age.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_age.setStyle("-fx-text-fill: #000000");
        profile_age.setVisible(false);

        ChoiceBox<String> profile_gender = new ChoiceBox<>();
        profile_gender.setStyle("-fx-font-size: 22;");
        profile_gender.getItems().addAll("ชาย", "หญิง", "ไม่ระบุ");
        profile_gender.setVisible(false);

        TextField profile_email = new TextField();
        profile_email.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_email.setStyle("-fx-text-fill: #000000");
        profile_email.setVisible(false);

        TextField profile_phone = new TextField();
        profile_phone.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        profile_phone.setStyle("-fx-text-fill: #000000");
        profile_phone.setVisible(false);

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
        breakfast.setOnAction(eh -> {
            if (breakfast.isSelected()) {
                this.summery_price += this.breakfast_price * this.NumberOfDay;
                this.vat_price = this.summery_price * 0.07;
                breakfast.setText("บริการอาหารเช้า " + this.breakfast_price + " บาท/มื้อ");
                vat_price.setText("รวมภาษีมูลค่าเพิ่ม 7%    " + (int) this.vat_price + " บาท");
                summery_price.setText("ราคารวมสุทธิ       " + this.summery_price + " บาท");
            }
            if (!breakfast.isSelected()) {
                this.summery_price -= this.breakfast_price * this.NumberOfDay;
                this.vat_price = this.summery_price * 0.07;
                breakfast.setText("บริการอาหารเช้า " + this.breakfast_price + " บาท/มื้อ");
                vat_price.setText("รวมภาษีมูลค่าเพิ่ม 7%    " + (int) this.vat_price + " บาท");
                summery_price.setText("ราคารวมสุทธิ       " + this.summery_price + " บาท");
            }
        });

        CheckBox dinner = new CheckBox("บริการอาหารเย็น " + this.dinner_price + " บาท/มื้อ");
        dinner.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 22));
        dinner.setStyle("-fx-text-fill: #ffffff");
        dinner.setOnAction(eh -> {
            if (dinner.isSelected()) {
                this.summery_price += this.dinner_price * this.NumberOfDay;
                this.vat_price = this.summery_price * 0.07;
                dinner.setText("บริการอาหารเช้า " + this.dinner_price + " บาท/มื้อ");
                vat_price.setText("รวมภาษีมูลค่าเพิ่ม 7%    " + (int) this.vat_price + " บาท");
                summery_price.setText("ราคารวมสุทธิ       " + this.summery_price + " บาท");
            }
            if (!dinner.isSelected()) {
                this.summery_price -= this.dinner_price * this.NumberOfDay;
                this.vat_price = this.summery_price * 0.07;
                dinner.setText("บริการอาหารเช้า " + this.dinner_price + " บาท/มื้อ");
                vat_price.setText("รวมภาษีมูลค่าเพิ่ม 7%    " + (int) this.vat_price + " บาท");
                summery_price.setText("ราคารวมสุทธิ       " + this.summery_price + " บาท");
            }
        });

        DatePicker check_in_date = new DatePicker();
        check_in_date.setStyle("-fx-font-size: 20;");

        DatePicker check_out_date = new DatePicker();
        check_out_date.setStyle(" -fx-font-size: 20;");
        check_out_date.setOnAction(eh -> {
            this.NumberOfDay = check_out_date.getValue().getDayOfYear() - check_in_date.getValue().getDayOfYear();
            System.out.println("Num day" + this.NumberOfDay);
        });

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
        RoomManagement room_man;
        room_man = new RoomManagement();

        ArrayList<Room> roomList = room_man.searchRoom();

        TableColumn col1 = new TableColumn<>("ID");
        col1.setCellValueFactory(new PropertyValueFactory<Room, String>("roomID"));
        col1.setMinWidth(50);
        col1.setReorderable(false);
        col1.setResizable(false);
        col1.setStyle("-fx-alignment: CENTER;");

        TableColumn col2 = new TableColumn<>("Room Type");
        col2.setCellValueFactory(new PropertyValueFactory<Room, String>("roomType"));
        col2.setMinWidth(150);
        col2.setReorderable(false);
        col2.setResizable(false);
        col2.setStyle("-fx-alignment: CENTER;");

        TableColumn col3 = new TableColumn<>("Room Class");
        col3.setCellValueFactory(new PropertyValueFactory<Room, String>("roomClass"));
        col3.setMinWidth(150);
        col3.setReorderable(false);
        col3.setResizable(false);
        col3.setStyle("-fx-alignment: CENTER;");

        TableColumn col4 = new TableColumn<>("Price");
        col4.setCellValueFactory(new PropertyValueFactory<Room, String>("basePrice"));
        col4.setMinWidth(150);
        col4.setReorderable(false);
        col4.setResizable(false);
        col4.setStyle("-fx-alignment: CENTER;");

        TableColumn col5 = new TableColumn<>("Booked");
        col5.setCellValueFactory(new PropertyValueFactory<Room, CheckBox>("select"));
        col5.setMinWidth(300);
        col5.setReorderable(false);
        col5.setResizable(false);
        col5.setStyle("-fx-alignment: CENTER;");

        TableColumn col6 = new TableColumn<>("Floor");
        col3.setCellValueFactory(new PropertyValueFactory<Room, String>("floor"));
        col3.setMinWidth(150);
        col3.setReorderable(false);
        col3.setResizable(false);
        col3.setStyle("-fx-alignment: CENTER;");

        col1.setVisible(false);
        col2.setVisible(false);
        col3.setVisible(false);
        col4.setVisible(false);
        col5.setVisible(false);
        col6.setVisible(false);

        tableView.getColumns().add(col1);
        tableView.getColumns().add(col2);
        tableView.getColumns().add(col3);
        tableView.getColumns().add(col6);
        tableView.getColumns().add(col4);
        tableView.getColumns().add(col5);

        VBox searchRoom = new VBox(tableView);
        searchRoom.setVisible(true);

        //==============================================================
        //[Component] - Status Tab 
        //===========================[ Status ]===========================//
        Label history_booking = new Label("ประวัติการจอง");
        history_booking.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 28));
        history_booking.setStyle("-fx-text-fill: #ffffff");
        history_booking.setVisible(false);

        TableView tableView2 = new TableView();

        TableColumn C1 = new TableColumn<>("รายการ");
        C1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn C2 = new TableColumn<>("ลักษณะห้องพัก");
        C2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn C3 = new TableColumn<>("รูปแบบ");
        C3.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn C4 = new TableColumn<>("ราตา ต่อห้อง ต่อคืน");
        C4.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn C5 = new TableColumn<>("ห้อง");
        C5.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn C6 = new TableColumn<>("จองเลย");

        tableView2.getColumns().add(C1);
        tableView2.getColumns().add(C2);
        tableView2.getColumns().add(C3);
        tableView2.getColumns().add(C4);
        tableView2.getColumns().add(C5);
        tableView2.getColumns().add(C6);
        VBox booking_status = new VBox(tableView2);
        booking_status.setVisible(false);

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

            try {
                for (int t = 0; t < room_man.searchRoom().size(); t++) {
                    try {
                        tableView.getItems().add(room_man.searchRoom().get(t));
                    } catch (IOException ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }

            col1.setVisible(true);
            col2.setVisible(true);
            col3.setVisible(true);
            col4.setVisible(true);
            col5.setVisible(true);
            col6.setVisible(true);

        });

        for (int t = 0; t < room_man.searchRoom().size(); t++) {
            tableView.getItems().add(room_man.searchRoom().get(t));
        }

        for (int t = 0; t < room_man.searchRoom().size(); t++) {
            Room r = (Room) tableView.getItems().get(t);
            r.getSelect().setOnAction(e -> {
                if (r.getSelect().isSelected()) {
                    //System.out.println("is Select !");                    
                    this.numSelect++;
                    this.summery_room_price += r.getBasePrice() * this.NumberOfDay;
                    this.summery_price += this.summery_room_price;
                    summery_room_price.setText("ราคาห้องรวม           " + this.summery_room_price + " บาท");
                    summery_price.setText("ราคารวมสุทธิ       " + this.summery_price + " บาท");
                    this.vat_price = this.summery_price * 0.07;
                    vat_price.setText("รวมภาษีมูลค่าเพิ่ม 7%    " + (int) this.vat_price + " บาท");
                }
                if (!r.getSelect().isSelected()) {
                    //System.out.println(" un select !");
                    this.numSelect--;
                    this.summery_room_price -= r.getBasePrice() * this.NumberOfDay;
                    this.summery_price = this.summery_room_price;
                    summery_room_price.setText("ราคาห้องรวม           " + this.summery_room_price + " บาท");
                    summery_price.setText("ราคารวมสุทธิ       " + this.summery_price + " บาท");
                    this.vat_price = this.summery_price * 0.07;
                    vat_price.setText("รวมภาษีมูลค่าเพิ่ม 7%    " + (int) this.vat_price + " บาท");
                }

                if (this.numSelect == room_num.getValue()) {
                    try {
                        for (int h = 0; h < room_man.searchRoom().size(); h++) {
                            Room r2 = (Room) tableView.getItems().get(h);
                            if (!r2.getSelect().isSelected()) {
                                r2.getSelect().setDisable(true);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        for (int h = 0; h < room_man.searchRoom().size(); h++) {
                            Room r2 = (Room) tableView.getItems().get(h);
                            if (!r2.getSelect().isSelected()) {
                                r2.getSelect().setDisable(false);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

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

            ArrayList<Room> roomCheckList = new ArrayList();
            try {
                for (int ii = 0; ii < room_man.searchRoom().size(); ii++) {
                    Room roomCheck = (Room) tableView.getItems().get(ii);
                    if (roomCheck.isSelect()) {
                        roomCheckList.add(roomCheck);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }

            Booking booking = new Booking();
            for (int p = 0; p < roomCheckList.size(); p++) {
                try {
                    booking.addBooking(roomCheckList.get(p).getRoomID(), acc_temp.getFirstName() + "   " + acc_temp.getLastName(), check_in_date.getValue().toString(), check_out_date.getValue().toString(),
                            roomCheckList.get(p).getRoomType(), roomCheckList.get(p).getRoomClass(), roomCheckList.get(p).getBuilding(), roomCheckList.get(p).getFloor(),
                            adult_num.getValue().toString(), young_num.getValue().toString(),
                            String.valueOf(breakfast.isSelected()), String.valueOf(dinner.isSelected()), "Booked");
                } catch (IOException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            tableView.getItems().clear();
            roomList.clear();
            roomCheckList.clear();
            this.numSelect = 0;
            this.NumberOfDay = 0;
            this.summery_price = 0;
            this.vat_price = 0;
            this.summery_room_price = 0;
            summery_room_price.setText("ราคาห้องรวม           " + this.summery_room_price + " บาท");
            summery_price.setText("ราคารวมสุทธิ       " + this.summery_price + " บาท");
            vat_price.setText("รวมภาษีมูลค่าเพิ่ม 7%    " + (int) this.vat_price + " บาท");

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
            history_booking.setVisible(true);
            booking_status.setVisible(true);
            status_bg.setVisible(true);
            status_bt.setEffect(new DropShadow());
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

            history_booking.setVisible(false);
            booking_status.setVisible(false);
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

            history_booking.setVisible(false);
            booking_status.setVisible(false);

            booking_bg.setVisible(false);
            info_bg.setVisible(true);
            status_bg.setVisible(false);
            info_bt.setEffect(new DropShadow());
        });
        //============================================================

        //===================|  Save [Button] setting |=========================
        Button save_bt = new Button("บันทึก");
        save_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        save_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #1EB2A6; -fx-text-fill: #000000");
        save_bt.setVisible(false);
        //On mouse idle
        save_bt.setOnMouseExited(e -> {
            save_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #1EB2A6; -fx-text-fill: #000000");
            save_bt.setEffect(null);
        });
        //On mouse hover
        save_bt.setOnMouseEntered(e -> {
            save_bt.setStyle(" -fx-background-radius: 30; -fx-background-color: #30DCD0; -fx-text-fill: #ffffff");
            save_bt.setEffect(new DropShadow());
        });
        //On mouse click
        save_bt.setOnMouseClicked(e -> {
            save_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #1EB2A6; -fx-text-fill: #ffffff");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("แบบฟอร์มข้อมูลถูกตรวจสอบแล้ว");
            alert.setHeaderText("คุณต้องการบันทึกข้อมูลใช่หรือไม่ !");
            alert.setContentText("ถ้าต้องการกด OK ถ้าไม่กด Cancel ");

            Stage stage2 = (Stage) alert.getDialogPane().getScene().getWindow();
            try {
                stage2.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                acc_temp.setFirstName(profile_name.getText());
                acc_temp.setLastName(profile_lastname.getText());
                acc_temp.setAge(profile_age.getText());
                acc_temp.setGender(profile_gender.getValue());
                acc_temp.setEmail(profile_email.getText());
                acc_temp.setPhoneNumber(profile_phone.getText());
                try {
                    if (db.editAccount(acc_temp)) {
                        System.out.println("success !");
                    } else {
                        System.out.println("Fail !");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                alert.close();
            }
        });

        //===================|  Back Home [Button] setting |=========================
        Button back_bt = new Button("กลับ");
        back_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        back_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #F67575; -fx-text-fill: #000000");
        back_bt.setVisible(false);
        //On mouse idle
        back_bt.setOnMouseExited(e -> {
            back_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #F67575; -fx-text-fill: #000000");
            back_bt.setEffect(null);
        });
        //On mouse hover
        back_bt.setOnMouseEntered(e -> {
            back_bt.setStyle(" -fx-background-radius: 30; -fx-background-color: #FAACAC; -fx-text-fill: #ffffff");
            back_bt.setEffect(new DropShadow());
        });
        //On mouse click
        back_bt.setOnMouseClicked(e -> {
            back_bt.setStyle("-fx-background-radius: 30; -fx-background-color: #F67575; -fx-text-fill: #ffffff");
            back_bt.setVisible(false);
            profile_bg.setVisible(false);
            save_bt.setVisible(false);

            profile_name_label.setVisible(false);
            profile_name.setVisible(false);
            profile_lastname_label.setVisible(false);
            profile_lastname.setVisible(false);
            profile_age_label.setVisible(false);
            profile_age.setVisible(false);
            profile_gender_label.setVisible(false);
            profile_gender.setVisible(false);
            profile_email_label.setVisible(false);
            profile_email.setVisible(false);
            profile_phone_label.setVisible(false);
            profile_phone.setVisible(false);

        });
        //=============================================================

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
            back_bt.setVisible(true);
            profile_bg.setVisible(true);
            save_bt.setVisible(true);

            profile_name_label.setVisible(true);
            profile_name.setVisible(true);
            profile_lastname_label.setVisible(true);
            profile_lastname.setVisible(true);
            profile_age_label.setVisible(true);
            profile_age.setVisible(true);
            profile_gender_label.setVisible(true);
            profile_gender.setVisible(true);
            profile_email_label.setVisible(true);
            profile_email.setVisible(true);
            profile_phone_label.setVisible(true);
            profile_phone.setVisible(true);

            profile_name.setText(acc_temp.getFirstName());
            profile_lastname.setText(acc_temp.getLastName());
            profile_age.setText(acc_temp.getAge());
            profile_gender.setValue(acc_temp.getGender());
            profile_email.setText(acc_temp.getEmail());
            profile_phone.setText(acc_temp.getPhoneNumber());

        });

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

        // Anchor to Welcome [Label]
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

        // Anchor to Status Label [Status-TAB]
        AnchorPane.setTopAnchor(history_booking, 80.0);
        AnchorPane.setLeftAnchor(history_booking, 510.0);
        AnchorPane.setRightAnchor(history_booking, 500.0);
        AnchorPane.setBottomAnchor(history_booking, 850.0);

        // Anchor to Booking Status [Status-TAB]
        AnchorPane.setTopAnchor(booking_status, 180.0);
        AnchorPane.setLeftAnchor(booking_status, 500.0);
        AnchorPane.setRightAnchor(booking_status, 80.0);
        AnchorPane.setBottomAnchor(booking_status, 500.0);

        //[AnchorPane-Edit Profile] Profile - background
        AnchorPane.setTopAnchor(profile_bg, 100.0);
        AnchorPane.setLeftAnchor(profile_bg, 50.0);
        AnchorPane.setRightAnchor(profile_bg, 100.0);
        AnchorPane.setBottomAnchor(profile_bg, 100.0);

        //[AnchorPane-Edit Profile] Profile - back button
        AnchorPane.setTopAnchor(back_bt, 880.0);
        AnchorPane.setLeftAnchor(back_bt, 80.0);
        AnchorPane.setRightAnchor(back_bt, 1080.0);
        AnchorPane.setBottomAnchor(back_bt, 120.0);

        //[AnchorPane-Edit Profile] Profile - save button
        AnchorPane.setTopAnchor(save_bt, 810.0);
        AnchorPane.setLeftAnchor(save_bt, 80.0);
        AnchorPane.setRightAnchor(save_bt, 1080.0);
        AnchorPane.setBottomAnchor(save_bt, 190.0);

        //[AnchorPane-Edit Profile] Profile - profile_name_label
        AnchorPane.setTopAnchor(profile_name_label, 80.0);
        AnchorPane.setLeftAnchor(profile_name_label, 95.0);
        AnchorPane.setRightAnchor(profile_name_label, 800.0);
        AnchorPane.setBottomAnchor(profile_name_label, 850.0);

        //[AnchorPane-Edit Profile] Profile - profile_name
        AnchorPane.setTopAnchor(profile_name, 120.0);
        AnchorPane.setLeftAnchor(profile_name, 150.0);
        AnchorPane.setRightAnchor(profile_name, 1080.0);
        AnchorPane.setBottomAnchor(profile_name, 890.0);

        //[AnchorPane-Edit Profile] Profile - profile_lastname_label
        AnchorPane.setTopAnchor(profile_lastname_label, 190.0);
        AnchorPane.setLeftAnchor(profile_lastname_label, 80.0);
        AnchorPane.setRightAnchor(profile_lastname_label, 800.0);
        AnchorPane.setBottomAnchor(profile_lastname_label, 840.0);

        //[AnchorPane-Edit Profile] Profile - profile_lastname
        AnchorPane.setTopAnchor(profile_lastname, 180.0);
        AnchorPane.setLeftAnchor(profile_lastname, 150.0);
        AnchorPane.setRightAnchor(profile_lastname, 1080.0);
        AnchorPane.setBottomAnchor(profile_lastname, 830.0);

        //[AnchorPane-Edit Profile] Profile - profile_age_label
        AnchorPane.setTopAnchor(profile_age_label, 240.0);
        AnchorPane.setLeftAnchor(profile_age_label, 90.0);
        AnchorPane.setRightAnchor(profile_age_label, 800.0);
        AnchorPane.setBottomAnchor(profile_age_label, 760.0);

        //[AnchorPane-Edit Profile] Profile - profile_age
        AnchorPane.setTopAnchor(profile_age, 240.0);
        AnchorPane.setLeftAnchor(profile_age, 150.0);
        AnchorPane.setRightAnchor(profile_age, 1080.0);
        AnchorPane.setBottomAnchor(profile_age, 770.0);

        //[AnchorPane-Edit Profile] Profile - profile_gender_label
        AnchorPane.setTopAnchor(profile_gender_label, 300.0);
        AnchorPane.setLeftAnchor(profile_gender_label, 90.0);
        AnchorPane.setRightAnchor(profile_gender_label, 800.0);
        AnchorPane.setBottomAnchor(profile_gender_label, 710.0);

        //[AnchorPane-Edit Profile] Profile - profile_gender
        AnchorPane.setTopAnchor(profile_gender, 300.0);
        AnchorPane.setLeftAnchor(profile_gender, 150.0);
        AnchorPane.setRightAnchor(profile_gender, 1080.0);
        AnchorPane.setBottomAnchor(profile_gender, 710.0);

        //[AnchorPane-Edit Profile] Profile - profile_email_label
        AnchorPane.setTopAnchor(profile_email_label, 350.0);
        AnchorPane.setLeftAnchor(profile_email_label, 90.0);
        AnchorPane.setRightAnchor(profile_email_label, 800.0);
        AnchorPane.setBottomAnchor(profile_email_label, 640.0);

        //[AnchorPane-Edit Profile] Profile - profile_email
        AnchorPane.setTopAnchor(profile_email, 360.0);
        AnchorPane.setLeftAnchor(profile_email, 150.0);
        AnchorPane.setRightAnchor(profile_email, 1080.0);
        AnchorPane.setBottomAnchor(profile_email, 650.0);

        //[AnchorPane-Edit Profile] Profile - profile_phone_label
        AnchorPane.setTopAnchor(profile_phone_label, 420.0);
        AnchorPane.setLeftAnchor(profile_phone_label, 80.0);
        AnchorPane.setRightAnchor(profile_phone_label, 800.0);
        AnchorPane.setBottomAnchor(profile_phone_label, 590.0);

        //[AnchorPane-Edit Profile] Profile - profile_phone
        AnchorPane.setTopAnchor(profile_phone, 420.0);
        AnchorPane.setLeftAnchor(profile_phone, 150.0);
        AnchorPane.setRightAnchor(profile_phone, 1080.0);
        AnchorPane.setBottomAnchor(profile_phone, 590.0);

        menu_layout.getChildren().addAll(logout_bt, profile_bt, booking_bg, info_bg, status_bg, logo,
                status_bt, info_bt, booking_bt, welcome_label,
                searchRoom, serch_room, check_in_lable, check_in_date, check_out_lable, check_out_date,
                adult_num, young_num, adult_label, young_label, numRoom_label, room_num, Search_bt,
                add_on_label, breakfast, dinner, Booking_bt, summery_price, summery_room_price, vat_price,
                scrollPaneDetails, booking_status, history_booking,
                profile_bg, back_bt, save_bt,
                profile_name_label, profile_name, profile_lastname_label, profile_lastname, profile_age_label, profile_age,
                profile_gender_label, profile_gender, profile_email_label, profile_email, profile_phone_label, profile_phone);

        //[View] --show window
        rootPane.getChildren().addAll(user_page_layout, menu_layout);
        //rootPane.getChildren().addAll(scrollPaneDetails);
        Scene user_page = new Scene(rootPane, 1440, 900);
        window.setScene(user_page);
        window.show();
    }

}
