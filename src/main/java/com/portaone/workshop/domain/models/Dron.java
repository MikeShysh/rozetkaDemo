package com.portaone.workshop.domain.models;

public class Dron {
    String name;
    int price;
    int reviews;
    int rating;
    boolean isAvailable;

    public Dron(String name, int price, int reviews, int rating, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.reviews = reviews;
        this.rating = rating;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dron)) return false;

        Dron dron = (Dron) o;

        if (price != dron.price) return false;
        if (rating != dron.rating) return false;
        if (reviews != dron.reviews) return false;
        if (isAvailable != dron.isAvailable) return false;
        return name.equals(dron.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price;
        result = 31 * result + rating;
        result = 31 * result + reviews;
        result = 31 * result + (isAvailable ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dron{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", reviews=" + reviews +
                ", rating=" + rating +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
