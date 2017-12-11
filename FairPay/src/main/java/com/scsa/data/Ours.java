package com.scsa.data;

public class Ours {

    private String myId;
    private String yourId;

    public Ours() { super(); }
    
    public Ours(String myId, String yourId) {
        this.myId = myId;
        this.yourId = yourId;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getYourId() {
        return yourId;
    }

    public void setYourId(String yourId) {
        this.yourId = yourId;
    }
}
