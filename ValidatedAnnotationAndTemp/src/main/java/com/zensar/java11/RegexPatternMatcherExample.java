package com.zensar.java11;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatternMatcherExample {

    public static void main(String[] args) {

        // Example 1: Extract Email from Text
        String text = "Contact us at support@example.com or admin@my@domain.org.in bb";
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Matcher emailMatcher = emailPattern.matcher(text);

        System.out.println("📧 Found Emails:"+emailMatcher.matches());
        while (emailMatcher.find()) {
            System.out.println(" - " + emailMatcher.group());
        }

        // Example 2: Validate Phone Number (10 digits)
        String phone = "9876543210";
        Pattern phonePattern = Pattern.compile("\\d{10}");
        Matcher phoneMatcher = phonePattern.matcher(phone);

        System.out.println("\n📱 Phone is valid: " + phoneMatcher.matches());

        // Example 3: Extract all Words from a Sentence
        String sentence = "Java 17 is super-powerful & modern!";
        Pattern wordPattern = Pattern.compile("[a-zA-Z]+");
        Matcher wordMatcher = wordPattern.matcher(sentence);

        System.out.println("\n🔠 Words Found:");
        while (wordMatcher.find()) {
            System.out.println(" - " + wordMatcher.group());
        }

        // Example 4: Check if a string contains only letters and digits
        String username = "12user_123mm";
        Pattern alphanumeric = Pattern.compile("^[a-zA-Z0-9_]+$");
        Matcher usernameMatcher = alphanumeric.matcher(username);

        System.out.println("\n👤 Username is valid: " + usernameMatcher.matches());
        
        // Invalid characters
        Pattern p = Pattern.compile("[^a-zA-Z0-9_]+");
        Matcher m = p.matcher("Hello@123!");
        while (m.find()) {
            System.out.println("Invalid characters: " + m.group());
        }
    }
}
