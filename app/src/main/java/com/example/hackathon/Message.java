package com.example.hackathon;

public class Message {
    private String userId; // ID de l'utilisateur Firebase Authentication
    private String messageText; // Texte du message
    private long timestamp; // Horodatage du message

    // Constructeur vide requis pour Firebase
    public Message() {
    }

    // Constructeur avec paramètres
    public Message(String userId, String messageText, long timestamp) {
        this.userId = userId;
        this.messageText = messageText;
        this.timestamp = timestamp;
    }

    // Méthodes Getter et Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

