package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TopicUrlRegistry {

	private static final Map<String, String> topicToUrlMap = new HashMap<>();

	static {
		// Linked List topics
		topicToUrlMap.put("Linked List", "linked-list/");
		topicToUrlMap.put("Introduction", "linked-list/introduction/");
		topicToUrlMap.put("Creating Linked LIst", "linked-list/creating-linked-list/");
		topicToUrlMap.put("Types of Linked List", "linked-list/types-of-linked-list/");
		topicToUrlMap.put("Implement Linked List in Python", "linked-list/implement-linked-list-in-python/");
		topicToUrlMap.put("Traversal", "linked-list/traversal/");
		topicToUrlMap.put("Insertion", "linked-list/insertion-in-linked-list/");
		topicToUrlMap.put("Deletion", "linked-list/deletion-in-linked-list/");
		topicToUrlMap.put("Linked List Practice Questions", "linked-list/practice/");

		// Stack topics
		topicToUrlMap.put("Stack", "stack/");
		topicToUrlMap.put("Operations in Stack", "stack/operations-in-stack/");
		topicToUrlMap.put("Implementation", "stack/implementation/");
		topicToUrlMap.put("Applications", "stack/stack-applications/");
		topicToUrlMap.put("Stack Practice Questions", "stack/practice/");

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

//	private static final Map<String, String> stackTopics = Map.of("Operations in Stack", "operations-in-stack",
//			"Stack Applications", "stack-applications", "Implementation", "implementation");

//	public static String getStackPath(String topicPage) {
//		String path = stackTopics.get(topicPage.trim());
//		if (path == null) {
//			throw new IllegalArgumentException("Unknown topic page: " + topicPage);
//		}
//		return path;
//	}
//
//	public static Set<String> getLinkedListExpectedTopics() {
//		return Set.of("Introduction", "Creating Linked LIst", // matches site typo
//				"Types of Linked List", "Implement Linked List in Python", "Traversal", "Insertion", "Deletion"
//
//		);
//	}

}
