package com.imi_gma.notiply.Models;

public class User {
    private String userName;
    // Array of Layers? User=1 & Host=#ofUsers

    /**
     * Gets users current name.
     * @return  String of users name.
     */
    public String getName() {
        return userName;
    }

    /**
     * Sets users name to a new input value.
     * @param newName   String of the new name to set.
     */
    public void setName(String newName) {
        userName = newName;
    }
}