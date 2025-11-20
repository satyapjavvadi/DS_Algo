package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TopicUrlRegistry {

	private static final Map<String, String> topicToUrlMap = new HashMap<>();

	static {
		// Linked List topics
		topicToUrlMap.put("Linked List", "/linked-list/introduction");
		topicToUrlMap.put("Introduction", "/linked-list/introduction/");
		topicToUrlMap.put("Creating Linked List", "/linked-list/creating-linked-list/");
		topicToUrlMap.put("Types of Linked List", "/linked-list/types-of-linked-list/");
		topicToUrlMap.put("Implement Linked List in Python", "/linked-list/implement-linked-list-in-python/");
		topicToUrlMap.put("Traversal", "/linked-list/traversal/");
		topicToUrlMap.put("Insertion", "/linked-list/insertion-in-linked-list/");
		topicToUrlMap.put("Deletion", "/linked-list/deletion-in-linked-list/");
		topicToUrlMap.put("Practice Questions", "/linked-list/practice");

		// Stack topics
		topicToUrlMap.put("Operations in Stack", "/stack/operations-in-stack");
		topicToUrlMap.put("Implementation", "/stack/implementation");
		topicToUrlMap.put("Applications", "/stack/stack-applications");
		topicToUrlMap.put("Practice Questions", "/stack/practice");

		// Add Queue, Tree, Graph, etc. as needed
	}

	public static String getUrlForTopic(String topicName) {
		String normalized = topicName.trim().toLowerCase();
		for (Map.Entry<String, String> entry : topicToUrlMap.entrySet()) {
			if (entry.getKey().toLowerCase().equals(normalized)) {
				return entry.getValue();
			}
		}
		throw new IllegalArgumentException("❌ No URL mapping found for topic: " + topicName);
	}

	public static boolean containsTopic(String topicName) {
		return topicToUrlMap.containsKey(topicName.trim());
	}

	public static Set<String> getAllTopics() {
		return topicToUrlMap.keySet();
	}
}
