package com.zensar.temp;

final public class Singleton {

	private static Singleton instance;

	private String configValue; // parameter/variable

	// private constructor with parameter
	private Singleton(String configValue) {
		this.configValue = configValue;
		System.out.println("Initialized with: " + configValue);
	}

// 	  parameterized getInstance method
    public static Singleton getInstance(String configValue) {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton(configValue);
                }
            }
        }
        return instance;
    }

//	public static synchronized Singleton getInstance(String configValue) {
//		if (instance == null) {
//			instance = new Singleton(configValue);
//		}
//		return instance;
//	}

	// getter
	public String getConfigValue() {
		return configValue;
	}

	// main method for testing
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance("FirstConfig");
		System.out.println(s1.getConfigValue());

		Singleton s2 = Singleton.getInstance("SecondConfig"); // Ignored
		System.out.println(s2.getConfigValue()); // Still "FirstConfig"

		System.out.println(s1 == s2); // true
	}
}
