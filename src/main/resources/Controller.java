package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import static com.sun.tools.javac.util.StringUtils.toLowerCase;


public class Controller {
    @FXML private TextField EnWord;
    @FXML private TextField enWordAdded;
    @FXML private TextField vnWordAdded;
    @FXML private TextField enWordChange;
    @FXML private TextField vnWordChange;
    @FXML private TextField enWordDelete;
    @FXML private Button SubmitButton;
    @FXML private TextField EnParagraph;
    @FXML private Label VnParagraph;
    @FXML private Button SubmitButtonTranslate;
    @FXML private Label EnLabel;
    @FXML private Label VnLabel;
    private static String fromLang = "en";
    private static String toLang = "vi";
    private static Boolean EnToVn = true;

    /*
    @FXML private Label LookupVnWord;
     */
    DictionaryManagement management = new DictionaryManagement();

    public void Submit (ActionEvent actionEvent) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("lookup.fxml"));
            Parent root = loader.load();
            LookUpController controller = loader.getController();
            controller.setLabel(toLowerCase(EnWord.getText()));
            Word word = management.dictionaryLookupWord(EnWord.getText());
            controller.setLabelPronunciation(word.pronunciation);
            //
            if(word.type.equals("n.   ")) controller.setLabelType("Danh từ");
            else if(word.type.equals("v.   ")) controller.setLabelType("Động từ");
            else if(word.type.equals("adj.   ")) controller.setLabelType("Tính từ");
            else if(word.type.equals("adv.   ")) controller.setLabelType("Trạng từ");
            else if(word.type.equals("n, v.   ")) controller.setLabelType("Danh từ, Động từ");
            else if(word.type.equals("n., adj.   ")) controller.setLabelType("Danh từ, Tính từ");
            else if(word.type.equals("v., n.   ")) controller.setLabelType("Động từ, Danh từ");
            else if(word.type.equals("adj., adv.   ")) controller.setLabelType("Tính từ, Trạng từ");
            else if(word.type.equals("exclamation, n.   ")) controller.setLabelType("Thán từ, Danh từ");
            else if(word.type.equals("adj., n.  ")) controller.setLabelType("Tính từ, Danh từ");
            else if(word.type.equals("adv., n.  ")) controller.setLabelType("Trạng từ, Danh từ");
            else if(word.type.equals("adv., conj.   ")) controller.setLabelType("Trạng từ, Liên từ");
            else if(word.type.equals("adj., v.   ")) controller.setLabelType("Tính từ, Động từ");
            else if(word.type.equals("conj., adv.   ")) controller.setLabelType("Liên từ, Trạng từ");
            else if(word.type.equals("pron.   ")) controller.setLabelType("Đại từ");
            else if(word.type.equals("det.   ")) controller.setLabelType("Hạn định từ");
            else if(word.type.equals("adj., pron.   ")) controller.setLabelType("Tính từ, Đại từ");
            else if(word.type.equals("adv., prep.   ")) controller.setLabelType("Trạng từ, Giới từ");
            else if(word.type.equals("prep.   ")) controller.setLabelType("Giới từ");
            else if(word.type.equals("adj., adv., n., prep.   ")) controller.setLabelType("Tính từ, Trạng từ, Danh từ, Giới từ");
            else if(word.type.equals("exclamation, det.   ")) controller.setLabelType("Thán từ, Hạn định từ");
            else if(word.type.equals("prep., v., conj.   ")) controller.setLabelType("Giới từ, Động từ, Liên từ");
            else if(word.type.equals("prep., adj.   ")) controller.setLabelType("Giới từ, Tính từ");
            else if(word.type.equals("det., pron., adv.   ")) controller.setLabelType("Hạn định từ, Đại từ, Trạng từ");
            else controller.setLabelType(word.type); // sửa lại chi tiết phần này sau
            //
            controller.setLabelVn(word.vnWord);
            SubmitButton.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddCancel (javafx.event.ActionEvent actionEvent) throws Exception {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
            stage.setTitle("My dictionary app");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void DeleteCancel (javafx.event.ActionEvent actionEvent) throws Exception {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
            stage.setTitle("My dictionary app");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddOk () {
        if(enWordAdded.getLength() == 0 || vnWordAdded.getLength() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Chưa nhập từ hoặc nghĩa");
            alert.show();
        }
        else {
            String enWord = enWordAdded.getText();
            String vnWord = vnWordAdded.getText();
            enWord = toLowerCase(enWord);
            vnWord = toLowerCase(vnWord);
            Word word = new Word(enWord, vnWord);
            if (enWord.charAt(0) >= 'a' && enWord.charAt(0) <= 'z' && vnWord.charAt(0) != ' ') {
                management.dictionary.add_Word(word, Dictionary.path);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Thêm từ thành công");
                alert.show();
            } else if (vnWord.charAt(0) != ' ') {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Từ không hợp lệ");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Nghĩa không hợp lệ");
                alert.show();
            }
        }
    }

    public void DeleteOk () {
        if (enWordDelete.getLength() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Chưa nhập từ cần xóa");
            alert.show();
        } else {
            String enWord = enWordDelete.getText();
            if (management.dictionaryLookup(enWord).equals("Không tìm thấy từ này!")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Từ cần xóa không có trong từ điển");
                alert.show();
            } else {
                management.dictionary.delete_Word(enWord);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Xóa thành công");
                alert.show();
            }
        }
    }

    public void OkChange () {
        if(enWordChange.getLength() == 0 || vnWordChange.getLength() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Chưa nhập từ hoặc nghĩa");
            alert.show();
        }
        else {
            String enWord = enWordChange.getText();
            String vnWord = vnWordChange.getText();
            enWord = toLowerCase(enWord);
            vnWord = toLowerCase(vnWord);
            if (management.dictionaryLookup(enWord).equals("Không tìm thấy từ này!")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Từ cần sửa không có trong từ điển");
                alert.show();
            } else {
                Word word = new Word(enWord, vnWord);
                if (enWord.charAt(0) >= 'a' && enWord.charAt(0) <= 'z' && vnWord.charAt(0) != ' ') {
                    management.dictionary.delete_Word(enWord);
                    management.dictionary.add_Word(word, Dictionary.path);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Sửa từ thành công");
                    alert.show();
                } else if (vnWord.charAt(0) != ' ') {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Từ không hợp lệ");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Nghĩa không hợp lệ");
                    alert.show();
                }
            }
        }
    }

    public void AddAWord (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/addFunction.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void DeleteFunction (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/deleteFunction.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void keyPressLookUp(javafx.scene.input.KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/lookup.fxml"));
                Parent root = loader.load();
                LookUpController controller = loader.getController();
                controller.setLabel(toLowerCase(EnWord.getText()));
                Word word = management.dictionaryLookupWord(EnWord.getText());
                controller.setLabelPronunciation(word.pronunciation);
                //
                if(word.type.equals("n.   ")) controller.setLabelType("Danh từ");
                else if(word.type.equals("v.   ")) controller.setLabelType("Động từ");
                else if(word.type.equals("adj.   ")) controller.setLabelType("Tính từ");
                else if(word.type.equals("adv.   ")) controller.setLabelType("Trạng từ");
                else if(word.type.equals("n, v.   ")) controller.setLabelType("Danh từ, Động từ");
                else if(word.type.equals("n., adj.   ")) controller.setLabelType("Danh từ, Tính từ");
                else if(word.type.equals("v., n.   ")) controller.setLabelType("Động từ, Danh từ");
                else if(word.type.equals("adj., adv.   ")) controller.setLabelType("Tính từ, Trạng từ");
                else if(word.type.equals("exclamation, n.   ")) controller.setLabelType("Thán từ, Danh từ");
                else if(word.type.equals("adj., n.  ")) controller.setLabelType("Tính từ, Danh từ");
                else if(word.type.equals("adv., n.  ")) controller.setLabelType("Trạng từ, Danh từ");
                else if(word.type.equals("adv., conj.   ")) controller.setLabelType("Trạng từ, Liên từ");
                else if(word.type.equals("adj., v.   ")) controller.setLabelType("Tính từ, Động từ");
                else if(word.type.equals("conj., adv.   ")) controller.setLabelType("Liên từ, Trạng từ");
                else if(word.type.equals("pron.   ")) controller.setLabelType("Đại từ");
                else if(word.type.equals("det.   ")) controller.setLabelType("Hạn định từ");
                else if(word.type.equals("adj., pron.   ")) controller.setLabelType("Tính từ, Đại từ");
                else if(word.type.equals("adv., prep.   ")) controller.setLabelType("Trạng từ, Giới từ");
                else if(word.type.equals("prep.   ")) controller.setLabelType("Giới từ");
                else if(word.type.equals("adj., adv., n., prep.   ")) controller.setLabelType("Tính từ, Trạng từ, Danh từ, Giới từ");
                else if(word.type.equals("exclamation, det.   ")) controller.setLabelType("Thán từ, Hạn định từ");
                else if(word.type.equals("prep., v., conj.   ")) controller.setLabelType("Giới từ, Động từ, Liên từ");
                else if(word.type.equals("prep., adj.   ")) controller.setLabelType("Giới từ, Tính từ");
                else if(word.type.equals("det., pron., adv.   ")) controller.setLabelType("Hạn định từ, Đại từ, Trạng từ");
                else controller.setLabelType(word.type); // sửa lại chi tiết phần này sau
                //
                controller.setLabelVn(word.vnWord);
                SubmitButton.getScene().setRoot(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void ChangeFunction (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/changeFunction.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void About (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/about.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void TranslateFunc (ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/translateFunction.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/TranslatateCss.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void TranslateButton(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/translateFunction.fxml"));
            Parent root = loader.load();
            Controller controller = loader.getController();
            if (EnToVn) {
                //controller.setLabelPVn(EnParagraph.getText() + " đã dịch sang tiếng Việt"); // cái này để test mà k api đỡ phí
                controller.setPromtText(EnParagraph.getText());
                controller.setLabelPVn((management.TranslateAString(EnParagraph.getText(), fromLang, toLang))); // demo thì bật cái này
            }
            else {
                fromLang = "vi";
                toLang = "en";
                controller.setLabelEn("Tiếng việt");
                controller.setLabelVn("English");
                //controller.setLabelPVn(EnParagraph.getText() + " đã dịch sang tiếng Anh"); // cái này để test mà k api đỡ phí
                controller.setPromtText(EnParagraph.getText());
                controller.setLabelPVn((management.TranslateAString(EnParagraph.getText(), fromLang, toLang))); // demo thì bật cái này
            }
            System.out.println(EnParagraph.getText());
            System.out.println(fromLang);
            System.out.println(EnToVn);
            SubmitButtonTranslate.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPromtText(String text) {
        EnParagraph.setPromptText(text);
    }

    public void setLabelPVn(String text) {
        VnParagraph.setText(text);
    }

    public void SoundOn2() {
        management.pronunciation1(EnParagraph.getPromptText(), fromLang);
    }

    public void SoundOn3() {
        management.pronunciation1(VnParagraph.getText(), toLang);
    }

    public void ConvertLanguage() {
        if(fromLang.equals("en")) {
            fromLang = "vi";
            toLang = "en";
            setLabelEn("Tiếng việt");
            setLabelVn("English");
            setPromtText("Nhập đoạn văn bạn cần dịch");
            setLabelPVn("");
            EnToVn = false;
        }
        else {
            fromLang = "en";
            toLang = "vi";
            setLabelEn("English");
            setLabelVn("Tiếng việt");
            setPromtText("Nhập đoạn văn bạn cần dịch");
            setLabelPVn("");
            EnToVn = true;
        }
     }

    private void setLabelVn(String text) {
        VnLabel.setText(text);
    }

    private void setLabelEn(String text) {
        EnLabel.setText(text);
    }

}
