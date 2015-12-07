package io.wavymav.topten;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * @ParseArtists - Will parse the xml artist data to String
 */
public class ParseArtists {
    private String xmlData;
    private ArrayList<Song> songs;

    public ParseArtists(String xmlData) {
        this.xmlData = xmlData;
        songs = new ArrayList<Song>();
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public boolean process() {
        Song currentRecord = null;
        boolean status = true;
        boolean inEntry = false;
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));

            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
//                        Log.d("ParseArtist", "Starting tag for " + tagName);

                        if (tagName.equalsIgnoreCase("entry")) {
                            inEntry = true;
                            currentRecord = new Song();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
//                        Log.d("ParseArtist", "Ending tag for " + tagName);

                        if (inEntry) {
                            if (tagName.equalsIgnoreCase("entry")) {
                                songs.add(currentRecord);
                                inEntry = false;
                            } else if (tagName.equalsIgnoreCase("name")){
                                currentRecord.setName(textValue);
                            } else if (tagName.equalsIgnoreCase("artist")){
                                currentRecord.setArtist(textValue);
                            } else if (tagName.equalsIgnoreCase("releaseDate")){
                                currentRecord.setReleaseDate(textValue);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }

        } catch (Exception err) {
            status = false;
            err.printStackTrace();
        }

        // Checking data
        for (Song song : songs) {
            Log.d("ParseArtist", "============================");
            Log.d("ParseArtist", "Name: " + song.getName());
            Log.d("ParseArtist", "Song: " + song.getArtist());
            Log.d("ParseArtist", "Release Date: " + song.getReleaseDate());
            Log.d("ParseArtist", "============================");
        }

        return true;
    }
}
