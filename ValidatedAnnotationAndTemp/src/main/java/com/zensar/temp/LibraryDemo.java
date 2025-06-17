package com.zensar.temp;

public class LibraryDemo {
    public static void main(String[] args) {
        MagicalShelf librarianShelf = new MagicalShelf(2);
        librarianShelf.put(1, "Magic Spells");
        librarianShelf.put(2, "Ancient History");
        System.out.println(librarianShelf.get(1)); // Magic Spells
        librarianShelf.put(3, "Future Technologies"); // evicts book 2
        System.out.println(librarianShelf.get(2)); // -1
        librarianShelf.put(4, "Lost Civilizations"); // evicts book 1
        System.out.println(librarianShelf.get(1)); // -1
        System.out.println(librarianShelf.get(3)); // Future Technologies
        System.out.println(librarianShelf.get(4)); // Lost Civilizations
    }
}

