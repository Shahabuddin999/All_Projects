package com.zensar.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.constraints.NotNull;
public class Java11 {
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		List<String> list = List.of("Shahabuddin","Ansari","Koraon");
		list.stream().forEach((var name)->System.out.println(name));
		list.stream().forEach((@NotNull var name)->System.out.println(name));
		list.stream().forEach((final var name)->System.out.println(name));
		String[] array = list.toArray(String[]::new);
		
		List<String> copy = List.copyOf(list);
		System.out.println("Copied list : "+copy);
		System.out.println(" list : "+list);
		
		System.out.println("  hello  ".isBlank());         // false
		System.out.println("java\nrocks".lines().count());         // 3 // Stream of "java", "rocks"
		System.out.println("foo".repeat(3));               // "foofoofoo"
		System.out.println("  trim  ".strip());            // Better Unicode-aware trim
		System.out.println("  trim  ".trim()); 
		System.out.println("  left ".stripLeading());      // "left "
		System.out.println(" right ".stripTrailing());     // " right"
		
		
		//trim() sirf characters ≤ \u0020 (space, tab, newline etc.) remove karta hai.
		//👉 Agar string me Unicode spaces ho (jaise non-breaking space), to trim() fail ho sakta hai.
		
		String s = "\u2003Hello\u2003"; // EM SPACE (Unicode)

		System.out.println(s.trim());  // ❌ will not remove
		System.out.println(s.strip()); // ✅ removes properly
		String normal = " Hello ";
		String unicode = "\u2003Hello\u2003";

		System.out.println(normal.trim().length());  // works
		System.out.println(unicode.trim().length()); // will fail
		System.out.println(unicode.strip().length()); // correct
		
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
		    .uri(URI.create("https://api.github.com"))
		    .build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		
		CompletableFuture<HttpResponse<String>> asyncResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(asyncResponse.get().body());
		
		// POST request
		String json = "{\"title\":\"Hello\",\"body\":\"World\",\"userId\":1}";

		HttpRequest request2 = HttpRequest.newBuilder()
		        .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
		        .header("Content-Type", "application/json")
		        .POST(HttpRequest.BodyPublishers.ofString(json))
		        .build();

		HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());

		System.out.println("Status: " + response2.statusCode());
		System.out.println("Response:\n" + response2.body());
		
		Optional<String> name = Optional.empty();
		if (name.isEmpty()) {
		    System.out.println("No name");
		}
		String filePath = "D:\\OLX-Apps\\OLX-Updated.zip\\ValidatedAnnotationAndTemp\\src\\main\\java\\com\\zensar\\java11\\file.txt";
		String content = Files.readString(Path.of(filePath));
		System.out.println(content);
		Files.writeString(Path.of(filePath), "Hello Java 11 how are you...");
		String readData = Files.readString(Path.of(filePath));
		System.out.println(readData);
		// You can run .java files directly without compiling, for example : java Hello.java
		// Java 11 removed some older features: like (Applets, Java EE modules (like javax.xml.bind), JavaFX (now separate from JDK))
	
		// HttpClient, Files.readString()/writeString(), optional.isEmpty(), List.of(), list.copyOf()
		// String "".isBlank(), strip() "".lines().count(), "".repeat(3), "".stripLeading(),"".stripTrailing()
	}
}
