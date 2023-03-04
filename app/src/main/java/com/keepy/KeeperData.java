package com.keepy;

import java.util.HashMap;


/*
 * This class is used to store the data of a keeper
 * It is used to store the keeper's fees and rating
 * We use this class to store the data in the database and
 * in a way that is easy to use in the app
 */
public class KeeperData {
    private HashMap<String, Integer> fees;

    private int ratingCount;
    private float rating;

    public KeeperData(HashMap<String,Integer> fees, int rating, int ratingCount) {
        this.fees = fees;
        this.rating = rating;
        this.ratingCount = ratingCount;
    }

    public float getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public KeeperData() { /* empty constructor for firebase */ }

    public HashMap<String, Integer> getFees() {
        return new HashMap<>(fees);
    } /* return a copy of the fees */

    public void setFees(HashMap<String, Integer> fees) {
        this.fees = fees;
    }
}
