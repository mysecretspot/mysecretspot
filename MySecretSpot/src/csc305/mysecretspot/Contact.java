package csc305.mysecretspot;

public class Contact {
    
    //private variables
    int _id;
    double latitude;
    double longitude;
    String fishType;
    String baitType;
    double weight;
    double length;
     
    // Empty constructor
    public Contact(){
         
    }
    // constructor
    public Contact(int id, double latitude, double longitude, String fishType, String baitType, double weight, double length){
        this._id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.fishType = fishType;
        this.baitType = baitType;
        this.weight = weight;
        this.length = length;
    }
     
    // constructor
    public Contact(double latitude, double longitude, String fishType, String baitType, double weight, double length){
    	this.latitude = latitude;
        this.longitude = longitude;
        this.fishType = fishType;
        this.baitType = baitType;
        this.weight = weight;
        this.length = length;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public double getLatitude(){
        return this.latitude;
    }
     
    // setting name
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    
 // getting name
    public double getLongitude(){
        return this.longitude;
    }
     
    // setting name
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
     
    // getting phone number
    public String getFishType(){
        return this.fishType;
    }
     
    // setting phone number
    public void setFishType(String fishType){
        this.fishType = fishType;
    }
    
 // getting name
    public String getBaitType(){
        return this.baitType;
    }
     
    // setting name
    public void setBaitType(String baitType){
        this.baitType = baitType;
    }
    
 // getting name
    public double getWeight(){
        return this.weight;
    }
     
    // setting name
    public void setWeight(double weight){
        this.weight = weight;
    }
    
 // getting name
    public double getLength(){
        return this.length;
    }
     
    // setting name
    public void setLength(double length){
        this.length = length;
    }
}