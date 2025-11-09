package com.india.railway.service.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.india.railway.model.mysql.PasswordResetToken;
import com.india.railway.model.mysql.User;
import com.india.railway.repository.mysql.PasswordResetTokenRepository;
import com.india.railway.repository.mysql.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.search.FlagTerm;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Session sessionsession;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public String greetUser(String name) {
        return "Hello, " + name;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public String generateResetToken() {
        return UUID.randomUUID().toString();
    }

    public void processForgotPassword(String email) {
        // Check if email exists
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            // Generate token
            String token = generateResetToken();
            PasswordResetToken passwordResetToken = new PasswordResetToken(token, userOptional.get());
            passwordResetTokenRepository.save(passwordResetToken);

            // Send email
            sendResetEmail(userOptional.get(), token);
        }
    }
    // yhlynqtvepkqwpas

    private void sendResetEmail(User user, String token) {
        String resetLink = "http://localhost:3000/reset-new-password?token=" + token
                + "&newPassword=akashgandham";

        try {

            MimeMessage message = new MimeMessage(sessionsession);
            message.setFrom(new InternetAddress("akash922.g@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("akash544.g@gmail.com"));
            message.setSubject("Password Reset Request");
            message.setText("To reset your password, click the link below:\n" + resetLink);

            // Sending the message
            Transport.send(message);
            // autoreply();

        } catch (Exception e) {
            System.out.println("error sending email");
        }
        System.out.println("sent email");
    }

    public void resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> resetTokenOptional = passwordResetTokenRepository.findByToken(token);

        if (resetTokenOptional.isPresent()) {
            PasswordResetToken resetToken = resetTokenOptional.get();
            if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
                throw new IllegalStateException("Token has expired");
            }

            User user = resetToken.getUser();
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));

            // user.setPassword(passwordEncoder.encode(newPassword)); // Use
            // BCryptPasswordEncoder or similar
            userRepository.save(user);

            // Optionally delete the token after reset
            passwordResetTokenRepository.delete(resetToken);

            // passwordResetTokenRepository.deleteById(resetToken.getId());
        } else {
            throw new IllegalStateException("Invalid token");
        }
    }

    public void autoreply() {

        String host = "imap.gmail.com"; // IMAP host
        String username = "akashglobalconnection@gmail.com"; // your email
        String password = "yhlynqtvepkqwpas"; // your password

        // Set properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", "993");

        try {
            // Create a mail session
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect(host, username, password);

            // Open the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            // Fetch unread messages
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            for (Message message : messages) {
                System.out.println("Email Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);

                // message.getAllRecipients();
                // message.getReceivedDate();

                // Create a reply message
                // Message replyMessage = new MimeMessage(emailSession);
                // replyMessage = (MimeMessage) message.reply(false);
                // replyMessage.setFrom(new InternetAddress(username));
                // replyMessage.setText("This is an automated reply.");

                MimeMessage replyMessage = new MimeMessage(sessionsession);
                replyMessage.setFrom(new InternetAddress("akashglobalconnection@gmail.com"));
                replyMessage.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(message.getFrom()[0].toString()));
                replyMessage.setSubject("This is an automated reply.");
                replyMessage.setText("This is an automated reply.");

                // Send the reply
                Transport.send(replyMessage);
                System.out.println("Replied to email: " + message.getSubject());
            }

            // Close connections
            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
