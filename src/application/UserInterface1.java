package application;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.util.Random;


public class UserInterface1 extends Application {
	
	// Create dateField variable
	private TextField dateField;
	
	// Create colors array member variable
	final String[] colors = {"#e6ffee", "#ccffdd", "#99ffbb", "#4dff88", "#00e64d", "#009933", "#006622", "#003311"};
	
	// Create color member variable and assign it to an empty string so color is reset
	private String color = "";
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		// Create date field and initially hide it
		dateField = new TextField();
		dateField.setVisible(false);
		dateField.setEditable(false);
		
		// Set stage title
		primaryStage.setTitle("User Interface 1");
		
		// Create BoderPane layout instance
	    BorderPane root = new BorderPane();
	    
	    // Create MenuBar instance
	    MenuBar menuBar = new MenuBar();
	    
	    // Bind the width of the menu bar to the width of the stage
	    menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
	    
	    // User Interface menu - print, log, change color and exit
	    Menu userMenu = new Menu("User Interface");
	    MenuItem printMenuItem = new MenuItem("Print Date and Time");
	    MenuItem logMenuItem = new MenuItem("Write Date and Time to File");
	    MenuItem colorMenuItem = new MenuItem("Change Color");
	    MenuItem exitMenuItem = new MenuItem("Exit");
	    
	    // Add menu items to the userMenu
	    userMenu.getItems().addAll(printMenuItem, logMenuItem, colorMenuItem,
	        new SeparatorMenuItem(), exitMenuItem);
	    
	    // Print Date and Time
	    printMenuItem.setOnAction(actionEvent -> printCurrentDateTime());
	    
	    // Log Date and Time to a file
	    logMenuItem.setOnAction(actionEvent -> {
	    	printCurrentDateTime();
	    	
	    	// Get text from the date field
	    	String text = dateField.getText();
	    	
	    	// Create a file   	
			PrintWriter logFile;
			try {
				logFile = new PrintWriter("log.txt");
				// Write to the file
				logFile.println(text);
				logFile.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    });
	    
	    // Set color
	    colorMenuItem.setOnAction(actionEvent -> {
	    	// Only set color initially for a single execution
	    	if (color.equals("")) {
	    		Random random = new Random();
	    		int randomI = random.nextInt(colors.length);
	    		String randomColor = colors[randomI];
	    		color = randomColor;
	    		root.setStyle("-fx-background-color: " + randomColor + "; ");
	    	};
	    });
	    
	    // Exiting the program
	    exitMenuItem.setOnAction(actionEvent -> Platform.exit());
	    
	    // Add user menu to the menu bar
	    menuBar.getMenus().add(userMenu);
	    
	    // Set the menu bar at the top
	    root.setTop(menuBar);
	    
	    // Set date and time at the center
	    root.setCenter(dateField);
	    
	    // Create a scene with root and dimensions
	    Scene scene = new Scene(root, 400, 400);
	    
	    // Make scene active
	    primaryStage.setScene(scene);
	    
	    // Show the scene
	    primaryStage.show();		
		
	}
	
	// Format current date and time and print it in a text box
	private void printCurrentDateTime() {
    	LocalDateTime dateTime = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);
    	String formattedDateTime = dateTime.format(formatter);
    	dateField.setText(formattedDateTime);
    	dateField.setVisible(true);
    	
    }

	public static void main(String[] args) {
		// Launch the application
		launch(args);
	}
}
