package com.example.shoppingapp2;

public class Items {
    private int id;
    private String korean;
    private String english;

    public Items(int id, String korean, String english) {
        this.id = id;
        this.korean = korean;
        this.english = english;
    }

    public Items() {

    }

    @Override
    public String toString() {
        return english + " / " + korean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKorean() {
        return korean;
    }

    public void setKorean(String korean) {
        this.korean = korean;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
