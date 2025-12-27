
package  com.india.railway.sms;
import jakarta.annotation.PostConstruct;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TwilioSmsSender {

    private static final String ACCOUNT_SID = "";
    private static final String AUTH_TOKEN = "";

    // need to get from twilio account already tested and working fine

    // need to login to twilio and get these credentials using akash922.g@gmail.com
    //

   //@PostConstruct
    public static void sendSms() {
        try {
            String apiUrl =
                    "https://api.twilio.com/2010-04-01/Accounts/" +
                            ACCOUNT_SID + "/Messages.json";

            String body =
                    "Dear Parent,\n" +
                            "Report card for Akash is ready.\n" +
                            "Marks: 450/500\n" +
                            "Grade: A\n" +
                            "Please review the full report in the school app.\n" +
                            "- ABC School";

            String data =
                    "To=" + URLEncoder.encode("+917075459707", "UTF-8") +
                            "&From=" + URLEncoder.encode("+14067976388", "UTF-8") +
                            "&Body=" + URLEncoder.encode(body, "UTF-8");

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            String auth =
                    ACCOUNT_SID + ":" + AUTH_TOKEN;
            String encodedAuth =
                    Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

            connection.setRequestProperty("Authorization", "Basic " + encodedAuth);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = connection.getOutputStream()) {
                os.write(data.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
