package sample;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import us.monoid.web.BinaryResource;
import us.monoid.web.Resty;

import java.io.*;
import java.net.*;
import java.text.MessageFormat;

public class Translator {
    private static final String CLIENT_ID = "thuan2172001@gmail.com";
    private static final String CLIENT_SECRET = "075d7216dd2a4c23a8bafbbb5704ab83";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
    private static final String BASE_URL = "http://translate.google.com/translate_tts?ie=UTF-8&q={0}&tl={1}&prev=input&client=tw-ob";

    public static String TranslateAString(String text, String fromLang, String toLang) throws Exception {
        return Translator.translate(fromLang, toLang, text);
    }

    public static String translate(String fromLang, String toLang, String text) throws Exception {
        String jsonPayload = new StringBuilder()
                .append("{")
                .append("\"fromLang\":\"")
                .append(fromLang)
                .append("\",")
                .append("\"toLang\":\"")
                .append(toLang)
                .append("\",")
                .append("\"text\":\"")
                .append(text)
                .append("\"")
                .append("}")
                .toString();

        URL url = new URL(ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonPayload.getBytes());
        os.flush();
        os.close();

        int statusCode = conn.getResponseCode();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
        ));
        String res = "";
        String output;
        while ((output = br.readLine()) != null) {
            res += output;
        }
        conn.disconnect();
        return res;
    }

    public static void pronunciation(String text) {
        try {
            File f = new File("translate.mp3");
            String sentence = URLEncoder.encode(text, "UTF-8");
            String urlString = MessageFormat.format(BASE_URL, sentence, "en");
            BinaryResource res = new Resty().bytes(new URI(urlString));
            res.save(f);
            FileInputStream in = new FileInputStream(f);
            Player p = new Player(in);
            p.play();
            p.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void pronunciation1(String text, String lang) {
        try {
            File f = new File("translate.mp3");
            String sentence = URLEncoder.encode(text, "UTF-8");
            String urlString = MessageFormat.format(BASE_URL, sentence, lang);
            BinaryResource res = new Resty().bytes(new URI(urlString));
            res.save(f);
            FileInputStream in = new FileInputStream(f);
            Player p = new Player(in);
            p.play();
            p.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }
}