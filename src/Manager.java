
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.NumberStringConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.BlockSet.Member2.Item;

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
    private BookingManagement book_mg = new BookingManagement();

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
        room_table.setEditable(true);

        for (int t = 0; t < this.fetchRoom().size(); t++) {
            room_table.getItems().add(this.fetchRoom().get(t));
        }

        TableColumn room_tb_col1 = new TableColumn<>("ID");
        room_tb_col1.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        room_tb_col1.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col1.setEditable(true);

        TableColumn room_tb_col2 = new TableColumn<>("Room Number");
        room_tb_col2.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        room_tb_col2.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col2.setEditable(true);

        TableColumn room_tb_col3 = new TableColumn<>("Room Type");
        room_tb_col3.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        room_tb_col3.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col3.setEditable(true);

        TableColumn room_tb_col4 = new TableColumn<>("Room Class");
        room_tb_col4.setCellValueFactory(new PropertyValueFactory<>("roomClass"));
        room_tb_col4.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col4.setEditable(true);

        TableColumn room_tb_col5 = new TableColumn<>("Building");
        room_tb_col5.setCellValueFactory(new PropertyValueFactory<>("building"));
        room_tb_col5.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col5.setEditable(true);

        TableColumn room_tb_col6 = new TableColumn<>("Floor");
        room_tb_col6.setCellValueFactory(new PropertyValueFactory<>("floor"));
        room_tb_col6.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col6.setEditable(true);

        TableColumn room_tb_col10 = new TableColumn<>("Number of Bed");
        room_tb_col10.setCellValueFactory(new PropertyValueFactory<>("numBed"));
        room_tb_col10.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col10.setEditable(true);
        room_tb_col10.setVisible(false);

        TableColumn room_tb_col7 = new TableColumn<>("Base Price");
        room_tb_col7.setCellValueFactory(new PropertyValueFactory<Room, Double>("basePrice"));
        room_tb_col7.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        room_tb_col7.setEditable(true);
        room_tb_col7.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, Long>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, Long> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBasePrice(t.getNewValue());
            }
        });

        TableColumn room_tb_col8 = new TableColumn<>("Status");
        room_tb_col8.setCellValueFactory(new PropertyValueFactory<>("Status"));
        room_tb_col8.setCellFactory(ComboBoxTableCell.forTableColumn("Occupied", "Unoccupied"));
        room_tb_col8.setEditable(true);
        room_tb_col8.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Room, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Room, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setStatus(t.getNewValue());
            }
        });

        TableColumn room_tb_col9 = new TableColumn<>("Data Created");
        room_tb_col9.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        room_tb_col9.setCellFactory(TextFieldTableCell.forTableColumn());
        room_tb_col9.setEditable(true);

        room_table.getColumns().add(room_tb_col1);
        room_table.getColumns().add(room_tb_col2);
        room_table.getColumns().add(room_tb_col3);
        room_table.getColumns().add(room_tb_col4);
        room_table.getColumns().add(room_tb_col5);
        room_table.getColumns().add(room_tb_col6);
        room_table.getColumns().add(room_tb_col10);
        room_table.getColumns().add(room_tb_col7);
        room_table.getColumns().add(room_tb_col8);
        room_table.getColumns().add(room_tb_col9);

        //[Component] - Room Management - Save [Button]
        Button save_room = new Button("Save");
        save_room.setOnMouseClicked(eh -> {
            try {
                this.updateFile(room_table, "RoomStock");
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //[Component] - Room Management [All Center Area]
        VBox center_room = new VBox();
        center_room.setVisible(true);

        center_room.setMargin(room_man_label, new Insets(10, 10, 10, 10));
        center_room.setMargin(room_table, new Insets(10, 10, 10, 10));
        center_room.setMargin(save_room, new Insets(10, 10, 10, 10));

        center_room.getChildren().addAll(room_man_label, Search_bar, room_table, save_room);

        //[Component] - Booking Management  [Label]
        Label book_man_label = new Label("Booking Management");
        book_man_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));

        //[Component] - Booking Management - Search bar [Hbox]
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

        //[Component] - Booking Management Table [Table]
        TableView book_table = new TableView();

        book_table.setEditable(true);

        for (int t = 0; t < this.fetchBooking().size(); t++) {
            book_table.getItems().add(this.fetchBooking().get(t));
        }

        TableColumn book_tb_col1 = new TableColumn<>("Booking ID");
        book_tb_col1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        book_tb_col1.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col1.setEditable(true);

        TableColumn book_tb_col2 = new TableColumn<>("Customer name");
        book_tb_col2.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        book_tb_col2.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col2.setEditable(true);

        TableColumn book_tb_col3 = new TableColumn<>("Check-in Date");
        book_tb_col3.setCellValueFactory(new PropertyValueFactory<>("CheckInDate"));
        book_tb_col3.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col3.setEditable(true);

        TableColumn book_tb_col4 = new TableColumn<>("Check-out Date");
        book_tb_col4.setCellValueFactory(new PropertyValueFactory<>("CheckOutDate"));
        book_tb_col4.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col4.setEditable(true);

        TableColumn book_tb_col5 = new TableColumn<>("Room Type");
        book_tb_col5.setCellValueFactory(new PropertyValueFactory<>("RoomType"));
        book_tb_col5.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col5.setEditable(true);

        TableColumn book_tb_col6 = new TableColumn<>("Room Class");
        book_tb_col6.setCellValueFactory(new PropertyValueFactory<>("RoomClass"));
        book_tb_col6.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col6.setEditable(true);

        TableColumn book_tb_col7 = new TableColumn<>("Building");
        book_tb_col7.setCellValueFactory(new PropertyValueFactory<>("Building"));
        book_tb_col7.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col7.setEditable(true);

        TableColumn book_tb_col8 = new TableColumn<>("Floor");
        book_tb_col8.setCellValueFactory(new PropertyValueFactory<>("Floor"));
        book_tb_col8.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col8.setEditable(true);

        TableColumn book_tb_col9 = new TableColumn<>("Adult");
        book_tb_col9.setCellValueFactory(new PropertyValueFactory<>("Adult"));
        book_tb_col9.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col9.setEditable(true);

        TableColumn book_tb_col10 = new TableColumn<>("Young");
        book_tb_col10.setCellValueFactory(new PropertyValueFactory<>("Young"));
        book_tb_col10.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col10.setEditable(true);

        TableColumn book_tb_col11 = new TableColumn<>("Breakfast");
        book_tb_col11.setCellValueFactory(new PropertyValueFactory<Booking, String>("Breakfast"));
        book_tb_col11.setCellFactory(ComboBoxTableCell.forTableColumn("true", "false"));
        book_tb_col11.setEditable(true);

        TableColumn book_tb_col12 = new TableColumn<>("Dinner");
        book_tb_col12.setCellValueFactory(new PropertyValueFactory<>("Dinner"));
        book_tb_col12.setCellFactory(ComboBoxTableCell.forTableColumn("true", "false"));
        book_tb_col12.setEditable(true);

        TableColumn book_tb_col13 = new TableColumn<>("Total Price");
        book_tb_col13.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        book_tb_col13.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        book_tb_col13.setEditable(true);

        TableColumn book_tb_col14 = new TableColumn<>("Status");
        book_tb_col14.setCellValueFactory(new PropertyValueFactory<>("Status"));
        book_tb_col14.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col14.setEditable(true);

        TableColumn book_tb_col15 = new TableColumn<>("Data Created");
        book_tb_col15.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        book_tb_col15.setCellFactory(TextFieldTableCell.forTableColumn());
        book_tb_col15.setEditable(true);

        book_table.getColumns().add(book_tb_col1);
        book_table.getColumns().add(book_tb_col2);
        book_table.getColumns().add(book_tb_col3);
        book_table.getColumns().add(book_tb_col4);
        book_table.getColumns().add(book_tb_col5);
        book_table.getColumns().add(book_tb_col6);
        book_table.getColumns().add(book_tb_col7);
        book_table.getColumns().add(book_tb_col8);
        book_table.getColumns().add(book_tb_col9);
        book_table.getColumns().add(book_tb_col10);
        book_table.getColumns().add(book_tb_col11);
        book_table.getColumns().add(book_tb_col12);
        book_table.getColumns().add(book_tb_col13);
        book_table.getColumns().add(book_tb_col14);
        book_table.getColumns().add(book_tb_col15);

        //[Component] - Booking Management [All Center Area]
        VBox center_book = new VBox();
        center_book.setVisible(false);

        center_book.setMargin(book_man_label, new Insets(10, 10, 10, 10));
        center_book.setMargin(book_table, new Insets(10, 10, 10, 10));

        center_book.getChildren().addAll(book_man_label, book_Search_bar, book_table);

        //[Component] - Check-IN Management  [Label]
        Label check_in_label = new Label("Booking Management");
        check_in_label.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 24));

        //[Component] - Room Management - Search bar [Hbox]
        TextField checkin_search_box = new TextField();
        checkin_search_box.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));
        checkin_search_box.setStyle("-fx-min-width: 400; ");

        Button checkin_search_bt = new Button("ค้นหา");
        checkin_search_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 18));

        HBox checkin_Search_bar = new HBox(2);
        checkin_Search_bar.setMargin(checkin_search_box, new Insets(10, 10, 10, 10));
        checkin_Search_bar.setMargin(checkin_search_bt, new Insets(10, 10, 10, 10));
        checkin_Search_bar.setAlignment(Pos.BASELINE_RIGHT);
        checkin_Search_bar.getChildren().addAll(checkin_search_box, checkin_search_bt);

        //[Component] - Room Management Table [Table]
        TableView checkin_table = new TableView();

        TableColumn checkin_tb_col1 = new TableColumn<>("รายการ");
        checkin_tb_col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        checkin_table.getColumns().add(checkin_tb_col1);

        //[Component] - Booking Management [All Center Area]
        VBox center_checkin = new VBox();
        center_checkin.setVisible(false);

        center_checkin.setMargin(check_in_label, new Insets(10, 10, 10, 10));
        center_checkin.setMargin(checkin_table, new Insets(10, 10, 10, 10));

        center_checkin.getChildren().addAll(check_in_label, checkin_Search_bar, checkin_table);

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

            room_table.getItems().clear();

            try {
                for (int t = 0; t < this.fetchRoom().size(); t++) {
                    room_table.getItems().add(this.fetchRoom().get(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            border.setCenter(center_room);
            center_room.setVisible(true);
            center_book.setVisible(false);
            center_checkin.setVisible(false);

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

            book_table.getItems().clear();

            try {
                for (int t = 0; t < this.fetchBooking().size(); t++) {
                    book_table.getItems().add(this.fetchBooking().get(t));
                }
            } catch (IOException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }

            border.setCenter(center_book);
            center_room.setVisible(false);
            center_book.setVisible(true);
            center_checkin.setVisible(false);
        });
        //=======================================================================

        //============================ [Menu] Check-In [Button] ==========================          
        Button check_in_bt = new Button("CHECK-IN");
        check_in_bt.setFont(Font.loadFont(new FileInputStream("src/font/ThaiSansNeue-Bold.otf"), 26));
        check_in_bt.setStyle("-fx-background-radius: 2; -fx-background-color: #FFA34D; -fx-text-fill: #000000 ");
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

            border.setCenter(center_checkin);
            center_room.setVisible(false);
            center_book.setVisible(false);
            center_checkin.setVisible(true);
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

    private ObservableList fetchRoom() throws IOException {
        RoomManagement room_man = new RoomManagement();
        return FXCollections.observableArrayList(room_man.searchRoom(true));
    }

    private ArrayList fetchBooking() throws IOException {
        return book_mg.searchBooking("ALL");
    }

    private void updateFile(TableView<Room> table, String dbname) throws FileNotFoundException, IOException {

        File excelFile = new File("src/db/" + dbname + ".xlsx");

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(dbname);

        Row firstRow = sheet.createRow(0);

        ///set titles of columns
        for (int i = 0; i < table.getColumns().size(); i++) {
            firstRow.createCell(i).setCellValue(table.getColumns().get(i).getText());
        }

        for (int row = 0; row < table.getItems().size(); row++) {
            Row hssfRow = sheet.createRow(row + 1);
            for (int col = 0; col < table.getColumns().size(); col++) {
                Object celValue = table.getColumns().get(col).getCellObservableValue(row).getValue();
                try {
                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
                        hssfRow.createCell(col).setCellValue(Double.parseDouble(celValue.toString()));
                    }
                } catch (NumberFormatException e) {
                    hssfRow.createCell(col).setCellValue(celValue.toString());
                }
            }
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("src/db/" + dbname + ".xlsx");
        workbook.write(fileOut);
        fileOut.close();
    }

}
