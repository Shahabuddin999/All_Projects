package com.zensar.temp;
class Geek {
	String name;
	int id;
	int age;
	Geek(String name, int id, int age) {
		this.age = age;
		this.name = name;
		this.id = id;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		Geek geek = (Geek) obj;
		return (geek.name == this.name && geek.id == this.id && this.age == geek.age);
	}
	@Override
	public int hashCode() {
		return this.id;
	}
}
