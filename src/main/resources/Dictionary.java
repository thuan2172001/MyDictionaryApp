package sample;

import java.io.*;

import static com.sun.tools.javac.util.StringUtils.toLowerCase;

public class Dictionary {
    int words_Available;
    public static final String pathOfAmount = "B:\\dictionaryFX\\src\\main\\java\\sample\\number.txt";
    public static final String path = "B:\\dictionaryFX\\src\\main\\java\\sample\\edited.txt";
    public static final String pathAfterDelete = "B:\\dictionaryFX\\src\\main\\java\\sample\\dictionariesTemp.txt";

    public void CopyFile() {
        FileInputStream instream = null;
        FileOutputStream outstream = null;
        try {
            File infile = new File(pathAfterDelete);
            File outfile = new File(path);
            instream = new FileInputStream(infile);
            outstream = new FileOutputStream(outfile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = instream.read(buffer)) > 0) {
                outstream.write(buffer, 0, length);
            }
            instream.close();
            outstream.close();
            System.out.println("Copy thành công!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // Lấy phần tử từ file number.txt
    public void setAmount() {
        try {
            FileInputStream fs = new FileInputStream(pathOfAmount);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
                String temp = br.readLine();
                int foo = Integer.parseInt(temp);
                words_Available = foo;
                br.close();
                fs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Ghi số lượng phần tử vào file number.txt
    public void setAmountToFile() {
        BufferedWriter buffer = null;
        FileWriter writer = null;

        try {
            writer = new FileWriter(pathOfAmount);
            buffer = new BufferedWriter(writer);
            String temp = String.valueOf(words_Available);
            buffer.write(temp);
            System.out.println("Add number into file successful");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (buffer != null)
                    buffer.close();
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        setAmount();
    }

    Dictionary() {
        setAmount();
    }

    //Thêm từ vào file
    public void add_Word(Word word, String PathFile) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            String data = word.enWord + " " + word.type + "AAA"
                    + word.pronunciation + "BBB " + word.vnWord;
            File file = new File(PathFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data + '\n');
            if (PathFile.equals(path)) { words_Available++;
            setAmountToFile(); }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void delete_Word(String word) {
        word = toLowerCase(word);
        try {
            FileInputStream fs = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            for (int j = 0; j < words_Available; j++) {
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
                Word word1 = new Word(enTemp, vnTemp, type, pronunciation);
                if (word.equals(enTemp)) {
                    words_Available--;
                    setAmountToFile();
                } else {
                    add_Word(word1, pathAfterDelete);
                }
            }
            fs.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CopyFile();
        try {
            File file = new File(pathAfterDelete);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
