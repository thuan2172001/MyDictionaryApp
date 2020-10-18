package sample;

public class Word {
    public String enWord;

    public String getEnWord() {
        return enWord;
    }

    public void setEnWord(String enWord) {
        this.enWord = enWord;
    }

    public String getVnWord() {
        return vnWord;
    }

    public void setVnWord(String vnWord) {
        this.vnWord = vnWord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String vnWord;
    public String type;
    public String pronunciation;

    public Word() {
        enWord = "unknown";
        vnWord = "không xác định";
        type = "  ";
        pronunciation = "  ";
    }
    public Word(String enWord, String vnWord) {
        this.enWord = enWord;
        this.vnWord = vnWord;
        type = "  ";
        pronunciation = "  ";
    }
    public Word(String enWord, String vnWord, String type, String pronunciation) {
        this.enWord = enWord;
        this.vnWord = vnWord;
        this.type = type;
        this.pronunciation = pronunciation;
    }


}
