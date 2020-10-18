package sample;

public class Word {
    public String enWord;
    public String vnWord;
    public String type;
    public String pronunciation;
    //public String audio;
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
