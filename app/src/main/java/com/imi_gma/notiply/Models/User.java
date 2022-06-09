package com.imi_gma.notiply.Models;

public class User {
    private static User instance;
    private String idMAC;
    private String userName;

    /**
     * Singleton constructor method
     * @return  isntance of this class
     */
    public static User getInstance(){
        if(instance == null) {
            instance = new User();
        }
        return instance;
    }

    private User() {}

    /**
     * Gets users current phone's MAC address
     * @return
     */
    public String getIdMAC() {
        return idMAC;
    }

    /**
     * Gets users current name
     * @return
     */
    public String getName() {
        return userName;
    }

    /**
     * Sets user's MAC address to a new input value
     * @param newMAC
     */
    public void setIdMAC(String newMAC) {
        idMAC = newMAC;
    }

    /**
     * Sets user's name to a new input value
     * @param newName
     */
    public void setName(String newName) {
        userName = newName;
    }
}
