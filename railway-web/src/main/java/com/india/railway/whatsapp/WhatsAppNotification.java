
package com.india.railway.whatsapp;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.math.BigDecimal;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class WhatsAppNotification {


        // Find your Account Sid and Token at twilio.com/console
        public static final String ACCOUNT_SID = "";
        public static final String AUTH_TOKEN = "[AuthToken]";

        @PostConstruct
        public void testWhatsApp() {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            String.valueOf(new PhoneNumber("whatsapp:+917075459707")),
                            new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                            "",
                            "Your message")
                    .create();

            System.out.println(message.getSid());
        }

}
