package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ShowWord {
    public Scene MyDictionary() throws IOException {
        int totalWord = 0;
        ArrayList<String> arrayList = new ArrayList<String>();
        try (FileReader fr = new FileReader(Dictionary.path)) {
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            while ((s = br.readLine()) != null) {
                totalWord++;
                arrayList.add(s);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] randomWord = new String[100];
        Random rd = new Random();
        for (int i = 0; i < 100; i++) {
            int n = rd.nextInt(totalWord);
            randomWord[i] = arrayList.get(n);
        }


        TableView<Word> table = new TableView<Word>();
        // Tạo cột eWord (Kiểu dữ liệu String)
        TableColumn<Word, String> eWordCol
                = new TableColumn<Word, String>("Từ tiếng anh");

        // Tạo cột typeWord (Kiểu dữ liệu String)
        TableColumn<Word, String> typeWordCol
                = new TableColumn<Word, String>("Loại từ");

        // Tạo cột pronounce (Kiểu dữ liệu String)
        TableColumn<Word, String> pronounceCol
                = new TableColumn<Word, String>("Phát âm");

        // Tạo cột mean ( kiểu String)
        TableColumn<Word, String> meanCol
                = new TableColumn<Word, String>("Nghĩa");


        // Định nghĩa cách để lấy dữ liệu cho mỗi ô.
        // Lấy giá trị từ các thuộc tính của UserAccount.
        eWordCol.setCellValueFactory(new PropertyValueFactory<>("enWord"));
        typeWordCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        pronounceCol.setCellValueFactory(new PropertyValueFactory<>("pronunciation"));
        meanCol.setCellValueFactory(new PropertyValueFactory<>("vnWord"));

        Button buttonBack = new Button();
        buttonBack.setOnAction(event -> {
            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
                stage.setTitle("My dictionary app");
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Hiển thị các dòng dữ liệu
        ObservableList<Word> list = getUserList(randomWord);
        table.setItems(list);

        table.getColumns().addAll(eWordCol, typeWordCol, pronounceCol, meanCol);
        //eWordCol.setSortType(TableColumn.SortType.DESCENDING);
        //table.sort();
        VBox root = new VBox();
        Pane ble = new Pane();
        Label notification = null;
        try {
            InputStream input = getClass().getResourceAsStream("/image/dictionarymini.png");
            Image image = new Image(input);
            ImageView imageLabel = new ImageView(image);
            notification = new Label("Dưới đây là 100 từ ngẫu nhiên", imageLabel);
            notification.setFont(Font.font("serif", FontWeight.BOLD , 26));

            notification.setMinWidth(500);
            notification.setTextAlignment(TextAlignment.CENTER);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        root.getChildren().add(ble);
        root.getChildren().add(table);//
        table.setMinHeight(520);
        root.setSpacing(20);
        //buttonBack.setPrefSize(50, 50);
        table.setPadding(new Insets(0, 0, 0, 0));

        buttonBack.setText("");
        try {
            InputStream input = getClass().getResourceAsStream("/image/Untitled.png");
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            buttonBack.setGraphic(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        notification.setLayoutX(150);
        notification.setLayoutY(2);
        ble.getChildren().addAll(buttonBack, notification);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(table);
        // Luôn luôn hiển thị thanh kéo thẳng đứng
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        // Thanh kéo ngang chỉ hiển thị khi cần
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        Scene scene = new Scene(root, 700, 600);
        return scene;
    }


    public ObservableList<Word> getUserList(String[] ramdomWord) {
        ObservableList<Word> list = FXCollections.observableArrayList();
        for (int i = 0; i < 100; i++) {
            Word eword = new Word();
            String s = ramdomWord[i];
            int endWord = s.indexOf(" ");
            int endTypeWord = s.indexOf("A");
            int endpronounce = s.indexOf("B");

            eword.setEnWord(s.substring(0, endWord));

            // xóa dấu cách và dấu chấm
            String _typeWord = s.substring(endWord + 1, endTypeWord);
              /*for (int j = 0; j < _typeWord.length(); j++) {
                  if (_typeWord.charAt(j) == '.' || _typeWord.charAt(j) == ' ') {
                      _typeWord = _typeWord.substring(0,j)
                              + _typeWord.substring(j+1,_typeWord.length()-1);
                  }
              }*/
            eword.setType(_typeWord);

            String _pronounce = s.substring(endTypeWord + 3, endpronounce);

            // xóa dấu / và dấu cách
              /*for (int j = 0; j < _pronounce.length(); j++) {
                  if (_pronounce.charAt(j) == '/' || _pronounce.charAt(j) == ' ') {
                      _pronounce = _pronounce.substring(0,j)
                              + _pronounce.substring(j+1,_pronounce.length()-1);
                  }
              }*/
            eword.setPronunciation(_pronounce);
            eword.setVnWord(s.substring(endpronounce + 4, s.length()));
            list.add(eword);
        }

        return list;
    }

}

