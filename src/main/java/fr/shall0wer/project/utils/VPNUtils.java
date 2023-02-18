package fr.shall0wer.project.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class VPNUtils {

    public static Boolean isVPN(InetAddress ip) throws IOException, IOException {
        URL url = new URL("http://ip-api.com/json/" + ip.getHostName() + "?fields=66846719");
        BufferedReader stream = new BufferedReader(new InputStreamReader(
                url.openStream()));
        StringBuilder entirePage = new StringBuilder();
        String inputLine;
        while ((inputLine = stream.readLine()) != null) entirePage.append(inputLine);
        stream.close();
        if (!(entirePage.toString().contains("\"proxy\":"))) {
            return null;
        }
        return entirePage.toString().split("\"proxy\":")[1].split(",\"")[0].equals("true") || entirePage.toString().split("\"hosting\":")[1].split(",\"")[0].equals("true");
    }

}
