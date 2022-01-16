import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GuiController {

    private Business business = new Business("business");
    private Health health = new Health("health");
    private Technology technology = new Technology("technology");
    private Science science = new Science("science_and_environment");
    private Entertainment entertainment = new Entertainment("entertainment_and_arts");

    private ArrayList<Label> newsLabels = new ArrayList<>();
    private Object category = null;

    @FXML
    private Button healthButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Label detailedText;

    @FXML
    private Button backButton;

    @FXML
    private VBox buttonsBox;

    @FXML
    private Label imageLabel;

    @FXML
    private VBox newsBox;

    @FXML
    private Button entertainmentButton;

    @FXML
    private Button scienceButton;

    @FXML
    private AnchorPane newsPane;

    @FXML
    private AnchorPane detailedTextPane;

    @FXML
    private AnchorPane newsDetailPane;

    @FXML
    private ScrollPane newsDetailScrollPane;

    @FXML
    private ScrollPane newsScrollPane;

    @FXML
    private Button technologyButton;

    @FXML
    private Button businessButton;

    @FXML
    void entertainmentButtonClicked(MouseEvent event) {

        if (this.newsPane.isVisible()) {
            this.newsPane.setVisible(false);
            this.clearLabels();
        }

        ArrayList<String> imageLinks = entertainment.getImages();
        ArrayList<String> newsTitles = entertainment.getTitles();
        this.setImageAndTitle(imageLinks, newsTitles);
        this.adjustSize(imageLinks.size());
        this.category = entertainment;
        this.newsPane.setVisible(true);
    }

    @FXML
    void healthButtonClicked(MouseEvent event) {

        if (this.newsPane.isVisible()) {
            this.newsPane.setVisible(false);
            this.clearLabels();
        }

        ArrayList<String> imageLinks = health.getImages();
        ArrayList<String> newsTitles = health.getTitles();
        this.setImageAndTitle(imageLinks, newsTitles);
        this.adjustSize(imageLinks.size());
        this.category = health;
        this.newsPane.setVisible(true);
    }

    @FXML
    void scienceButtonClicked(MouseEvent event) {

        if (this.newsPane.isVisible()) {
            this.newsPane.setVisible(false);
            this.clearLabels();
        }

        ArrayList<String> imageLinks = science.getImages();
        ArrayList<String> newsTitles = science.getTitles();
        this.setImageAndTitle(imageLinks, newsTitles);
        this.adjustSize(imageLinks.size());
        this.category = science;
        this.newsPane.setVisible(true);
    }

    @FXML
    void technologyButtonClicked(MouseEvent event) {

        if (this.newsPane.isVisible()) {
            this.newsPane.setVisible(false);
            this.clearLabels();
        }

        ArrayList<String> imageLinks = technology.getImages();
        ArrayList<String> newsTitles = technology.getTitles();
        this.setImageAndTitle(imageLinks, newsTitles);
        this.adjustSize(imageLinks.size());
        this.category = technology;
        this.newsPane.setVisible(true);
    }

    @FXML
    void businessButtonClicked(MouseEvent event) {

        if (this.newsPane.isVisible()) {
            this.newsPane.setVisible(false);
            this.clearLabels();
        }

        ArrayList<String> imageLinks = business.getImages();
        ArrayList<String> newsTitles = business.getTitles();
        this.setImageAndTitle(imageLinks, newsTitles);
        this.adjustSize(imageLinks.size());
        this.category = business;
        this.newsPane.setVisible(true);
    }

    @FXML
    void backButtonClicked(MouseEvent event) {

        this.newsDetailScrollPane.setVisible(false);
        this.newsPane.setVisible(true);
    }

    private void clearLabels() {

        for (int i = 0; i < this.newsLabels.size(); i++) {

            this.newsLabels.get(i).setText("");
            this.newsLabels.get(i).setGraphic(null);
        }
    }

    private void setImageAndTitle(ArrayList<String> imageLinks, ArrayList<String> newsTitles) {

        for (int i = 0; i < imageLinks.size(); i++) {

            String url = imageLinks.get(i);
            Image image = new Image(url);
            ImageView view = new ImageView(image);
            view.setFitHeight(190);
            view.setFitWidth(285);
            this.newsLabels.get(i).setGraphic(view);
            this.newsLabels.get(i).setContentDisplay(ContentDisplay.LEFT);
            this.newsLabels.get(i).setText(newsTitles.get(i));
            this.newsLabels.get(i).setGraphicTextGap(90);
        }
    }

    private void mouseEntered(MouseEvent event) {

        Label label = (Label) event.getSource();
        label.setTextFill(Color.rgb(26, 13, 190));
        label.setCursor(Cursor.HAND);

    }

    private void mouseExited(MouseEvent event) {

        Label label = (Label) event.getSource();
        label.setTextFill(Color.BLACK);
    }

    private void adjustSize(int newsCount) {

        this.newsPane.setPrefSize(1280, (newsCount * 200.0) + ((newsCount - 1) * 15));
    }

    private void labelClicked(MouseEvent event) {
        this.imageLabel.setGraphic(null);
        this.detailedText.setText("");
        this.titleLabel.setText("");

        Label label = (Label) event.getSource();
        String title = label.getText();
        int index = ((News)this.category).getTitles().indexOf(title);

        String url = ((News)this.category).getImages().get(index);
        Image image = new Image(url);
        ImageView view = new ImageView(image);
        view.setFitHeight(227);
        view.setFitWidth(354);

        String link = ((News)this.category).getLinks().get(index);
        String text = ((News)this.category).getDetailedText(link);

        this.imageLabel.setGraphic(view);
        this.detailedText.setText(text);
        this.titleLabel.setText(title);
        this.detailedTextPane.prefHeightProperty().bind(this.detailedText.prefHeightProperty());
        this.newsPane.setVisible(false);
        this.newsDetailScrollPane.setVisible(true);
        
    }

    @FXML
    void initialize() {

        this.newsPane.setVisible(false);
        this.newsDetailScrollPane.setVisible(false);

        for (Node child : newsBox.getChildren()) {

            Pane pane = (Pane) child;
            Label label = (Label) pane.getChildren().get(0);
            this.newsLabels.add(label);
            label.setOnMouseEntered(this::mouseEntered);
            label.setOnMouseExited(this::mouseExited);
            label.setOnMouseClicked(this::labelClicked);
        }
    }

}
