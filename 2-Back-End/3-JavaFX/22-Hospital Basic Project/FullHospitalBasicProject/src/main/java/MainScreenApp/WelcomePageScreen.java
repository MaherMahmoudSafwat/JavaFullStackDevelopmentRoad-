package MainScreenApp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WelcomePageScreen {
    private static final String HOSPITAL_NAME = "El-Tawoon Hospital";
    private static final String LOGO_PATH = "/images/ElTawoonHospitalLogo.jpg";
    private static final Color BACKGROUND_COLOR = Color.web("#f0f8ff");
    private static final Color PRIMARY_COLOR = Color.web("#1a6fc9");
    private static final Font TITLE_FONT = Font.font("Arial", 36);
    private static final Font BUTTON_FONT = Font.font("Arial", 18);

    public Scene createWelcomeScene() {
        // Create main content container
        VBox contentContainer = createContentContainer();

        // Wrap in ScrollPane for scrollable content
        ScrollPane rootContainer = new ScrollPane(contentContainer);
        configureScrollPane(rootContainer);

        // Create and return the scene
        return new Scene(rootContainer, 1000, 700);
    }

    private VBox createContentContainer() {
        VBox contentBox = new VBox(40);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setBackground(new Background(new BackgroundFill(BACKGROUND_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        contentBox.setPadding(new Insets(50));

        // Make content responsive
        contentBox.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        contentBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        // Add all components
        contentBox.getChildren().addAll(
                createLogo(),
                createWelcomeText(),
                createHospitalNameText(),
                createNextButton()
        );

        return contentBox;
    }

    private void configureScrollPane(ScrollPane scrollPane) {
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background: " + toHex(BACKGROUND_COLOR) + ";");
    }

    private ImageView createLogo() {
        try {
            Image logoImage = new Image(getClass().getResourceAsStream(LOGO_PATH));
            ImageView logoView = new ImageView(logoImage);
            logoView.setFitWidth(300);
            logoView.setPreserveRatio(true);
            return logoView;
        } catch (Exception e) {
            System.err.println("Error loading logo: " + e.getMessage());
            ImageView placeholder = new ImageView();
            placeholder.setFitWidth(200);
            placeholder.setPreserveRatio(true);
            placeholder.setStyle("-fx-background-color: lightgray;");
            return placeholder;
        }
    }

    private Text createWelcomeText() {
        Text text = new Text("Welcome To");
        text.setFont(TITLE_FONT);
        text.setFill(PRIMARY_COLOR);
        return text;
    }

    private Text createHospitalNameText() {
        Text text = new Text(HOSPITAL_NAME);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 42));
        text.setFill(PRIMARY_COLOR);
        return text;
    }

    private Button createNextButton() {
        Button button = new Button("Enter Hospital System");
        button.setFont(BUTTON_FONT);
        button.setStyle("-fx-background-color: " + toHex(PRIMARY_COLOR) + "; -fx-text-fill: white;");
        button.setPadding(new Insets(15, 40, 15, 40));
        button.setMaxWidth(Double.MAX_VALUE);
        return button;
    }

    private String toHex(Color color) {
        return String.format("#%02x%02x%02x",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255));
    }
}