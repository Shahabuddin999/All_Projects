package com.zensar.java11;
import lombok.Value;

@Value
class Person {
	int id;
    String name;
}

//public class GetSingleton {
//
//	public static void main(String[] args) {
//		Person p = new Person(10, "Shahab");
//		System.out.println(p.getName()+" : "+p.getId());
//	}
//}

/*
	What @Value does
	The @Value annotation in Lombok is a shortcut to make an immutable class. Here's what it generates automatically behind the scenes:
	
	| Feature                     | Description                                                |
	| --------------------------- | ---------------------------------------------------------- |
	| `private final` fields      | Makes all fields `private` and `final`                     |
	| `AllArgsConstructor`        | A constructor with all fields as parameters                |
	| Getters                     | Getter method for every field                              |
	| `toString()`                | A readable string representation                           |
	| `equals()` and `hashCode()` | Field-based implementations                                |
	| `final` class               | The class itself is marked as `final` (cannot be extended) |

	
	@Value annotation making Person class as a immutable class internally its converting like below code 
	
	public final class Person {
    private final int id;
    private final String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        // auto-generated equals logic
    }

    @Override
    public int hashCode() {
        // auto-generated hashCode logic
    }

    @Override
    public String toString() {
        return "Person(id=" + id + ", name=" + name + ")";
    }
}

*/