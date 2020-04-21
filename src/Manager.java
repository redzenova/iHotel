
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
public class Manager extends Application {

    private String username;
    private String password;
    private Stage window;
    private iHotel main_page;

    public static void main(String[] args) {
        launch(args);
    }

    public Manager() {
    }

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void start(Stage stage) throws Exception {
        //[Inintialize] Stage
        window = stage;
        window.getIcons().add(new Image(new FileInputStream(new File("src/img/icon.png"))));
        window.setTitle("[iHotel] - Manager");
        window.setResizable(false);

        //[Pane Setting]
        StackPane rootPane = new StackPane();
        BorderPane border = new BorderPane();

        //[Component] - Logo
        ImageView logo = new ImageView(new Image(new FileInputStream(new File("src/img/logo-s.png"))));
        logo.setFitHeight(73);
        logo.setFitWidth(180);

        //[Component]
        Button logout_bt = new Button("Logout");
        logout_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        logout_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #F67575; -fx-text-fill: #000000");
        //On mouse idle
        logout_bt.setOnMouseExited(e -> {
            logout_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #F67575; -fx-text-fill: #000000");
            logout_bt.setEffect(null);
        });
        //On mouse hover
        logout_bt.setOnMouseEntered(e -> {
            logout_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FAACAC; -fx-text-fill: #ffffff");
            logout_bt.setEffect(new DropShadow());
        });
        //On mouse click
        logout_bt.setOnMouseClicked(e -> {
            logout_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #F67575; -fx-text-fill: #000000");
            main_page = new iHotel();
            try {
                main_page.start(window);
            } catch (Exception ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //[Component] - Room Management  [Label]
        Label room_man_label = new Label("Room Management");
        room_man_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));

        //[Component] - Room Management - Search bar [Hbox]
        TextField search_box = new TextField();
        search_box.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        search_box.setStyle("-fx-min-width: 400; ");

        Button search_bt = new Button("ค้นหา");
        search_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));

        HBox Search_bar = new HBox(2);
        Search_bar.setMargin(search_box, new Insets(10, 10, 10, 10));
        Search_bar.setMargin(search_bt, new Insets(10, 10, 10, 10));
        Search_bar.setAlignment(Pos.BASELINE_RIGHT);
        Search_bar.getChildren().addAll(search_box, search_bt);

        //[Component] - Room Management Table [Table]
        TableView room_table = new TableView();

        TableColumn room_tb_col1 = new TableColumn<>("รายการ");
        room_tb_col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn room_tb_col2 = new TableColumn<>("ลักษณะห้องพัก");
        room_tb_col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn room_tb_col3 = new TableColumn<>("รูปแบบ");
        room_tb_col3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn room_tb_col4 = new TableColumn<>("ราตา ต่อห้อง ต่อคืน");
        room_tb_col4.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn room_tb_col5 = new TableColumn<>("ห้อง");
        room_tb_col5.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn room_tb_col6 = new TableColumn<>("จองเลย");

        room_table.getColumns().add(room_tb_col1);
        room_table.getColumns().add(room_tb_col2);
        room_table.getColumns().add(room_tb_col3);
        room_table.getColumns().add(room_tb_col4);
        room_table.getColumns().add(room_tb_col5);
        room_table.getColumns().add(room_tb_col6);

        //[Component] - Room Management [All Center Area]
        VBox center_room = new VBox();
        center_room.setVisible(true);

        center_room.setMargin(room_man_label, new Insets(10, 10, 10, 10));
        center_room.setMargin(room_table, new Insets(10, 10, 10, 10));

        center_room.getChildren().addAll(room_man_label, Search_bar, room_table);

        //[Component] - Booking Management  [Label]
        Label book_man_label = new Label("Booking Management");
        book_man_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));

        //[Component] - Room Management - Search bar [Hbox]
        TextField book_search_box = new TextField();
        book_search_box.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        book_search_box.setStyle("-fx-min-width: 400; ");

        Button book_search_bt = new Button("ค้นหา");
        book_search_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));

        HBox book_Search_bar = new HBox(2);
        book_Search_bar.setMargin(book_search_box, new Insets(10, 10, 10, 10));
        book_Search_bar.setMargin(book_search_bt, new Insets(10, 10, 10, 10));
        book_Search_bar.setAlignment(Pos.BASELINE_RIGHT);
        book_Search_bar.getChildren().addAll(book_search_box, book_search_bt);

        //[Component] - Room Management Table [Table]
        TableView book_table = new TableView();

        TableColumn book_tb_col1 = new TableColumn<>("รายการ");
        book_tb_col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn book_tb_col2 = new TableColumn<>("ลักษณะห้องพัก");
        book_tb_col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn book_tb_col3 = new TableColumn<>("รูปแบบ");
        book_tb_col3.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        book_table.getColumns().add(book_tb_col1);
        book_table.getColumns().add(book_tb_col2);
        book_table.getColumns().add(book_tb_col3);

        //[Component] - Room Management [All Center Area]
        VBox center_book = new VBox();
        center_book.setVisible(false);

        center_book.setMargin(book_man_label, new Insets(10, 10, 10, 10));
        center_book.setMargin(book_table, new Insets(10, 10, 10, 10));

        center_book.getChildren().addAll(book_man_label, book_Search_bar, book_table);

        //========================= [Menu] Room Management [Button] ========================
        Button room_man_bt = new Button("จัดการห้องพัก");
        room_man_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        room_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
        //On mouse idle
        room_man_bt.setOnMouseExited(e -> {
            room_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
            room_man_bt.setEffect(null);
        });
        //On mouse hover
        room_man_bt.setOnMouseEntered(e -> {
            room_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff");
            room_man_bt.setEffect(new DropShadow());
        });
        //On mouse click
        room_man_bt.setOnMouseClicked(e -> {
            room_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");

            border.setCenter(center_room);
            center_room.setVisible(true);
            center_book.setVisible(false);

        });
        //=======================================================================

        //======================== [Menu] Booking Management [Button] =======================        
        Button booking_man_bt = new Button("จัดการการจอง");
        booking_man_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        booking_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
        //On mouse idle
        booking_man_bt.setOnMouseExited(e -> {
            booking_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
            booking_man_bt.setEffect(null);
        });
        //On mouse hover
        booking_man_bt.setOnMouseEntered(e -> {
            booking_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff");
            booking_man_bt.setEffect(new DropShadow());
        });
        //On mouse click
        booking_man_bt.setOnMouseClicked(e -> {
            booking_man_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");

            border.setCenter(center_book);
            center_room.setVisible(false);
            center_book.setVisible(true);
        });
        //=======================================================================

        //============================ [Menu] Check-In [Button] ==========================          
        Button check_in_bt = new Button("CHECK-IN");
        check_in_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        check_in_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
        //On mouse idle
        check_in_bt.setOnMouseExited(e -> {
            check_in_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");
            check_in_bt.setEffect(null);
        });
        //On mouse hover
        check_in_bt.setOnMouseEntered(e -> {
            check_in_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #ffffff");
            check_in_bt.setEffect(new DropShadow());
        });
        //On mouse click
        check_in_bt.setOnMouseClicked(e -> {
            check_in_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000");

        });
        //=======================================================================

        VBox menu_bar = new VBox();
        //Setting the space between the nodes of a HBox pane 
        menu_bar.setSpacing(10);
        menu_bar.setAlignment(Pos.BASELINE_CENTER);
        menu_bar.setStyle("-fx-background-color: #00A8CC;");

        //Setting the margin to the nodes 
        menu_bar.setMargin(room_man_bt, new Insets(10, 10, 10, 10));
        menu_bar.setMargin(booking_man_bt, new Insets(10, 10, 10, 10));
        menu_bar.setMargin(check_in_bt, new Insets(10, 10, 10, 10));
        menu_bar.setMargin(logout_bt, new Insets(450, 10, 10, 10));

        menu_bar.getChildren().addAll(room_man_bt, booking_man_bt, check_in_bt, logout_bt);

        HBox info_bar = new HBox();
        //Setting the space between the nodes of a HBox pane 
        info_bar.setSpacing(10);
        info_bar.setAlignment(Pos.BASELINE_LEFT);
        info_bar.setStyle("-fx-background-color: #000839;");

        //Setting the margin to the nodes 
        info_bar.setMargin(logo, new Insets(10, 10, 10, 10));
        info_bar.getChildren().addAll(logo);

        //Pane - Proproties
        border.setTop(info_bar);
        border.setLeft(menu_bar);
        border.setCenter(center_room);

        //[View] --show window
        rootPane.getChildren().addAll(border);
        Scene user_page = new Scene(rootPane, 1440, 900);
        window.setScene(user_page);
        window.show();
    }

}
