package com.zensar.java11;
// File: SolidPrinciplesDemo.java

// ===========================
// S - Single Responsibility
// ===========================
class ReportGenerator {
    public String generateReport() {
        return "Generated Report";
    }
}

class ReportSaver {
    public void saveReport(String data) {
        System.out.println("Saving report: " + data);
    }
}

// ===========================
// O - Open/Closed Principle
// ===========================
interface DiscountStrategy {
    double applyDiscount(double price);
}

class StudentDiscount implements DiscountStrategy {
    public double applyDiscount(double price) {
        return price * 0.9;
    }
}

class SeniorDiscount implements DiscountStrategy {
    public double applyDiscount(double price) {
        return price * 0.8;
    }
}

class PriceCalculator {
    public double calculatePrice(double price, DiscountStrategy discount) {
        return discount.applyDiscount(price);
    }
}

// ===========================
// L - Liskov Substitution
// ===========================
interface Bird {}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void fly() {
        System.out.println("Sparrow is flying");
    }
}

class Ostrich implements Bird {
    // Ostrich can't fly, but still a valid Bird
}

// ===========================
// I - Interface Segregation
// ===========================
interface Printer {
    void print();
}

interface Scanner {
    void scan();
}

class MultiFunctionPrinter implements Printer, Scanner {
    public void print() {
        System.out.println("Printing...");
    }

    public void scan() {
        System.out.println("Scanning...");
    }
}

class BasicPrinter implements Printer {
    public void print() {
        System.out.println("Basic printing...");
    }
}

// ===========================
// D - Dependency Inversion
// ===========================
interface Database {
    void save(String data);
}

class MySQLDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

class UserService {
    private final Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public void registerUser(String username) {
        database.save("User: " + username);
    }
}

// ===========================
// Main - Demo All Principles
// ===========================
public class SolidPrinciplesDemo {
    public static void main(String[] args) {

        // SRP
        ReportGenerator reportGenerator = new ReportGenerator();
        String report = reportGenerator.generateReport();
        ReportSaver reportSaver = new ReportSaver();
        reportSaver.saveReport(report);

        // OCP
        PriceCalculator calculator = new PriceCalculator();
        System.out.println("Student Discount: " + calculator.calculatePrice(100, new StudentDiscount()));
        System.out.println("Senior Discount: " + calculator.calculatePrice(100, new SeniorDiscount()));

        // LSP
        FlyingBird sparrow = new Sparrow();
        sparrow.fly();
        Bird ostrich = new Ostrich(); // no fly(), but still valid

        // ISP
        Printer basicPrinter = new BasicPrinter();
        basicPrinter.print();

        MultiFunctionPrinter mfp = new MultiFunctionPrinter();
        mfp.print();
        mfp.scan();

        // DIP
        Database db = new MySQLDatabase();
        UserService userService = new UserService(db);
        userService.registerUser("Shahabuddin");
    }
}
