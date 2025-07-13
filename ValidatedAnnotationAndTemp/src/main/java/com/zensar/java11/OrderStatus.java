package com.zensar.java11;
interface Permission {
    String getAccessLevel();
    String getName();
}

enum UserRole implements Permission {
    ADMIN("ALL","Nizam"),
    EDITOR("WRITE","Shahab"),
    VIEWER("READ","Kalam");

    private final String accessLevel;
    private final String name;

    UserRole(String accessLevel, String name) {
        this.accessLevel = accessLevel;
        this.name = name;
    }

    @Override
    public String getAccessLevel() {
        return accessLevel;
    }
    @Override
    public String getName() {
    	return name;
    }
}

public enum OrderStatus {
    PENDING(0, "Order received"),
    PROCESSING(1, "In process"),
    SHIPPED(2, "Shipped"),
    DELIVERED(3, "Delivered"),
    CANCELLED(-1, "Cancelled");

    private final int code;
    private final String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() { return code; }

    public String getDescription() { return description; }

    // Lookup by code
    public static OrderStatus fromCode(int code) {
        for (OrderStatus status : values()) {
            if (status.getCode() == code) return status;
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
    public static void main(String[] args) {
    	OrderStatus fromCode = OrderStatus.fromCode(2);
    	System.out.println(fromCode+":"+fromCode.description+":"+fromCode.code);
    	
    	System.out.println(OrderStatus.PROCESSING.getDescription()); // In process
    	System.out.println(OrderStatus.fromCode(3)); // DELIVERED
    	
    	UserRole role = UserRole.EDITOR;
    	System.out.println(role.getAccessLevel()); // WRITE
    	System.out.println(role.getName()); 
    	
	}
}
