package utils;

public class TopicNameNormalizer {
    public static String normalize(String topicName) {
        if (topicName.equalsIgnoreCase("Creating Linked List")) {
            return "Creating Linked LIst"; // match actual DOM typo
        }
        if (topicName.toLowerCase().contains("practice")) {
            return "Practice Questions";
        }
        return topicName.trim();
    }
}

