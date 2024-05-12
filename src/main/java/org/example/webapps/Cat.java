package org.example.webapps;

import java.io.Serializable;

// Class to represent a Cat
public class Cat implements Serializable {
    private int petId;
    private String petName;
    private int age;
    private String imageUrl;
    private String description;

    public Cat(int petId, String petName, int age, String imageUrl, String description) {
        this.petId = petId;
        this.petName = petName;
        this.age = age;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    // Getters
    public int getPetId() {
        return petId;
    }

    public String getPetName() {
        return petName;
    }

    public int getAge() {
        return age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }
}

