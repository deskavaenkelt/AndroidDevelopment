package se.dsve.top10downloader;
/*
  __
 /\ \
 \_\ \    ____  __  __     __
 /'_` \  /',__\/\ \/\ \  /'__`\
/\ \L\ \/\__, `\ \ \_/ |/\  __/
\ \___,_\/\____/\ \___/ \ \____\
 \/__,_ /\/___/  \/__/   \/____/
       https://dsve.se/
*/
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
/**
 * @author Lars Strömberg
 * @version 1.0
 * @since 2019-07-06
 * https://github.com/deskavaenkelt/AndroidDevelopment/tree/master/Top10Downloader
 */

class ParseApplications {
    private static final String TAG = "ParseApplications";
    private ArrayList<FeedEntry> applications;

    ParseApplications() {
        this.applications = new ArrayList<>();
    }

    ArrayList<FeedEntry> getApplications() {
        return applications;
    }

    boolean parse(String xmlData) {
        boolean status = true;
        FeedEntry currentRecord = null;
        boolean inEntry = false;
        boolean gotWantedImageSize = false; // optional
        String textValue = "";

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance(); // setting upp parser to handle xml
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
//                        Log.d(TAG, "parse: Starting tag for " + tagName);
                        if ("entry".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            currentRecord = new FeedEntry();
                        } else if (("image".equalsIgnoreCase(tagName)) && inEntry) {    // optional
                            String imageResolution = xpp.getAttributeValue(null, "height");
                            if (imageResolution != null) {
                                gotWantedImageSize = "170".equalsIgnoreCase(imageResolution);
                            }
                        }
                        break;

                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
//                        Log.d(TAG, "parse: Ending tag for " + tagName);
                        if (inEntry) {
                            if ("entry".equalsIgnoreCase(tagName)) {
                                applications.add(currentRecord);
                                inEntry = false;
                            } else if ("name".equalsIgnoreCase(tagName)) {
                                currentRecord.setName(textValue);
                            } else if ("artist".equalsIgnoreCase(tagName)) {
                                currentRecord.setArtist(textValue);
                            } else if ("releaseDate".equalsIgnoreCase(tagName)) {
                                currentRecord.setReleaseDate(textValue);
                            } else if ("price".equalsIgnoreCase(tagName)) {
                                currentRecord.setSummary(textValue);
                            } else if ("image".equalsIgnoreCase(tagName)) {
                                if (gotWantedImageSize) {   // optional
                                    currentRecord.setImageURL(textValue);   // required
                                }
                            }
                        }
                        break;

                    default:
                        // nothing else to do.
                }
                eventType = xpp.next();

            }
//            for (FeedEntry app : applications) {
//                Log.d(TAG, "******************");
//                Log.d(TAG, app.toString());
//            }

        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }

        return status;
    }
}
