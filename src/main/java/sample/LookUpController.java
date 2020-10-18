package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import static com.sun.tools.javac.util.StringUtils.toLowerCase;

public class LookUpController {
    @FXML private Label LabelEnWord;
    @FXML private Label LabelVnWord;
    @FXML private TextField EnWord;
    @FXML private Button SubmitButton;
    @FXML private Label pronunciationID;
    @FXML private Label typeID;
    DictionaryManagement management = new DictionaryManagement();


    public void Submit (ActionEvent actionEvent) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lookup.fxml"));
            Parent root = loader.load();
            LookUpController controller = loader.getController();
            controller.setLabel(toLowerCase(EnWord.getText()));
            Word word = management.dictionaryLookupWord(EnWord.getText());
            controller.setLabelPronunciation(word.pronunciation);
            System.out.println(word.type);
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

    public void setLabel(String text) {
        LabelEnWord.setText(text);
    }

    public void setLabelVn(String text) {
        LabelVnWord.setText(text);
    }

    public void SoundOn() {
        management.pronunciation(LabelEnWord.getText()); // null
        System.out.println(LabelEnWord.getText());
    }

    public void setLabelType(String text) {
        typeID.setText(text);
    }

    public void setLabelPronunciation(String text) {
        pronunciationID.setText(text);
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

    public void mouseclick() {
        TextFields.bindAutoCompletion(EnWord, management.dictionarySearcher());
    }
}