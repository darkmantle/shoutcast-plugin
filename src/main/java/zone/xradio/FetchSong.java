package zone.xradio;

import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class FetchSong {

    public static Document loadShoutcastXml(String url) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new URL(url).openStream());
    }

    public static String getSongString(String url) {
        try {

            Document doc = FetchSong.loadShoutcastXml(url);
            String song = doc.getElementsByTagName("SONGTITLE").item(0).getTextContent();
            
            return song;

        } catch (Exception e) {
            return null;
        }
    }
}
