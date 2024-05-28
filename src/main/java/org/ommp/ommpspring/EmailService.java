package org.ommp.ommpspring;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}