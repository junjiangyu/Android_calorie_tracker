package com.example.fit5046_a3;

import java.util.Date;

public class User {
        Integer id;
        String name;
        String surname;
        String email;
        Date dob;
        String height;
        String weight;
        String gender;
        String address;
        String postcode;
        String levelOfActivity;
        String stepsPerMile;

    public User(String name, String surname, String email, Date dob, String height, String weight, String gender, String address, String postcode, String loa, String spm) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.levelOfActivity = loa;
        this.stepsPerMile = spm;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Date getDob() {
            return dob;
        }

        public void setDob(Date dob) {
            this.dob = dob;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getLevelOfActivity() {
            return levelOfActivity;
        }

        public void setLevelOfActivity(String levelOfActivity) {
            this.levelOfActivity = levelOfActivity;
        }

        public String getStepsPerMile() {
            return stepsPerMile;
        }

        public void setStepsPerMile(String stepsPerMile) {
            this.stepsPerMile = stepsPerMile;
        }





}
