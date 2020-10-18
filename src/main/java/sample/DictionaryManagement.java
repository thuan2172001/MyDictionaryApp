package sample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static com.sun.tools.javac.util.StringUtils.toLowerCase;


public class DictionaryManagement extends Translator{
    public Dictionary dictionary;
    public static final int numMaxCanPrint = 100;
    public static final String path = "B:\\dictionaryFX\\src\\main\\java\\sample\\edited.txt";
    DictionaryManagement() {
        dictionary = new Dictionary();
    }

    //insert từ commandline vào file


    /*
    public void insertFromFile() {
        try {
            FileInputStream fs = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int j = 0; j < numMaxCanPrint; j++) {
                String line = br.readLine();
                String vnTemp = "";
                String enTemp = "";
                boolean check = false;
                for (int i = 0; i < line.length(); i++) {
                    if (!check) {
                        enTemp += line.charAt(i);
                        if (line.charAt(i) == '\t') {
                            check = true;
                            i = i + 1;
                        }
                    }
                    if (check) {
                        vnTemp += line.charAt(i);
                    }
                }
                Word temp = new Word(enTemp, vnTemp);
                dictionary.add_Word(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    // Tra cứu từ file
    public String dictionaryLookup(String word) {
        word = toLowerCase(word);
        try {
            FileInputStream fs = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int j = 0; j < dictionary.words_Available; j++) {
                String line = br.readLine();
                String vnTemp = "";
                String enTemp = "";
                String pronunciation = "";
                String type = "";
                boolean checkType = false;
                boolean checkPronun = false;
                boolean checkVn = false;
                for (int i = 0; i < line.length(); i++) {
                    if (!checkType) {
                        if (line.charAt(i) != ' ') enTemp += line.charAt(i);
                        if (line.charAt(i) == ' ') {
                            checkType = true;
                            i = i + 1;
                        }
                    }
                    if (checkType && !checkPronun) {
                        if (line.charAt(i) != 'A') type += line.charAt(i);
                        if (line.charAt(i) == 'A') {
                            checkPronun = true;
                            i = i + 3;
                        }
                    }
                    if (checkPronun && !checkVn) {
                        if (line.charAt(i) != 'B') pronunciation += line.charAt(i);
                        if (line.charAt(i) == 'B') {
                            checkVn = true;
                            i = i + 4;
                        }
                    }
                    if(checkVn) {
                        vnTemp += line.charAt(i);
                    }
                }
                if (word.equals(enTemp)) return vnTemp;
            }
            fs.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không tìm thấy từ này!";
    }

    public Word dictionaryLookupWord(String word) {
        word = toLowerCase(word);
        Word temp1 = new Word(word, "Không tìm thấy từ này", " ", " ");
        try {
            FileInputStream fs = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int j = 0; j < dictionary.words_Available; j++) {
                String line = br.readLine();
                String vnTemp = "";
                String enTemp = "";
                String pronunciation = "";
                String type = "";
                boolean checkType = false;
                boolean checkPronun = false;
                boolean checkVn = false;
                for (int i = 0; i < line.length(); i++) {
                    if (!checkType) {
                        if (line.charAt(i) != ' ') enTemp += line.charAt(i);
                        if (line.charAt(i) == ' ') {
                            checkType = true;
                            i = i + 1;
                        }
                    }
                    if (checkType && !checkPronun) {
                        if (line.charAt(i) != 'A') type += line.charAt(i);
                        if (line.charAt(i) == 'A') {
                            checkPronun = true;
                            i = i + 3;
                        }
                    }
                    if (checkPronun && !checkVn) {
                        if (line.charAt(i) != 'B') pronunciation += line.charAt(i);
                        if (line.charAt(i) == 'B') {
                            checkVn = true;
                            i = i + 4;
                        }
                    }
                    if(checkVn) {
                        vnTemp += line.charAt(i);
                    }
                }
                if (word.equals(enTemp)) {
                    Word temp = new Word(enTemp, vnTemp, type, pronunciation);
                    return temp;
                }
            }
            fs.close();
            br.close();
        } catch (Exception e) {
            Word temp = new Word("Không tìm thấy từ này", "Không tìm thấy từ này", " ", " ");
            return temp;
        }
        return temp1;
    }
    // Search recommend từ file
    public String[] dictionarySearcher() {
        int count = 0;
        int number = dictionary.words_Available;
        String[] WordSearched = new String[number];
        try {
            FileInputStream fs = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int j = 0; j < dictionary.words_Available; j++) {
                String line = br.readLine();
                String enTemp = "";
                boolean check = false;
                for (int i = 0; i < line.length(); i++) {
                    if (!check) {
                        if (line.charAt(i) != ' ') enTemp += line.charAt(i);
                        if (line.charAt(i) == ' ') {
                            check = true;
                            i = i + 1;
                        }
                    }
                    if (check) {
                        break;
                    }
                }
                WordSearched[count] = enTemp;
                count++;
                if (count == number) break;
            }
            fs.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WordSearched;
    }

    public String[] similiarSearcher(String word) {
        int count = 0;
        String[] WordSearched = new String[8];
        try {
            FileInputStream fs = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int j = 0; j < dictionary.words_Available; j++) {
                String line = br.readLine();
                String vnTemp = "";
                String enTemp = "";
                String pronunciation = "";
                String type = "";
                boolean checkType = false;
                boolean checkPronun = false;
                boolean checkVn = false;
                for (int i = 0; i < line.length(); i++) {
                    if (!checkType) {
                        if (line.charAt(i) != ' ') enTemp += line.charAt(i);
                        if (line.charAt(i) == ' ') {
                            checkType = true;
                            i = i + 1;
                        }
                    }
                    if (checkType && !checkPronun) {
                        if (line.charAt(i) != 'A') type += line.charAt(i);
                        if (line.charAt(i) == 'A') {
                            checkPronun = true;
                            i = i + 3;
                        }
                    }
                    if (checkPronun && !checkVn) {
                        if (line.charAt(i) != 'B') pronunciation += line.charAt(i);
                        if (line.charAt(i) == 'B') {
                            checkVn = true;
                            i = i + 4;
                        }
                    }
                    if (checkVn) {
                        vnTemp += line.charAt(i);
                    }
                }
                if (word.equals(enTemp)) {
                    WordSearched[count] = enTemp;
                    count++;
                }
            }
        }
         catch (Exception e) {
            e.printStackTrace();
        }
        return WordSearched;
    }
}
