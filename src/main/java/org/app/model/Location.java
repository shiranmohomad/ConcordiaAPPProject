package org.app.model;

import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Location {
    private String name = "";
    private String country = "";

    private String region = "";
    private String timezone_id = "";
    private String localtime_epoch = "";
    private String utc_offset = "";

    private Weather weatherObj;

    private Connection jdbcConnection = JDBC.getInstance();

    public Location() throws SQLException {
        //createtable();
    }
    public Location(JSONObject jsonLocationObj,JSONObject jsonWeatherObj) throws SQLException {
        name = jsonLocationObj.get("name").toString();
        country = jsonLocationObj.get("country").toString();
        region = jsonLocationObj.get("region").toString();
        timezone_id = jsonLocationObj.get("timezone_id").toString();
        localtime_epoch = jsonLocationObj.get("localtime_epoch").toString();
        utc_offset = jsonLocationObj.get("utc_offset").toString();
        weatherObj = new Weather(jsonWeatherObj,jsonLocationObj.get("name").toString());
        System.out.println("name:"+name);
        //createtable();
    }

    public Weather getWeatherObj() {
        return weatherObj;
    }

    public void setWeatherObj(Weather weatherObj) {
        this.weatherObj = weatherObj;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setTimezone_id(String timezone_id) {
        this.timezone_id = timezone_id;
    }

    public void setLocaltime_epoch(String localtime_epoch) {
        this.localtime_epoch = localtime_epoch;
    }

    public void setUtc_offset(String utc_offset) {
        this.utc_offset = utc_offset;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getTimezone_id() {
        return timezone_id;
    }

    public String getLocaltime_epoch() {
        return localtime_epoch;
    }

    public String getUtc_offset() {
        return utc_offset;
    }
    public void handleInsert() throws SQLException {
        System.out.println("name:"+name);
        String query = "INSERT INTO Location VALUES('"+name+"', '"+country+"', '"
                +region +"', '"+timezone_id+"', '"+localtime_epoch
                +"', '"+utc_offset+"');";
        Statement statement= jdbcConnection.createStatement();
        statement.execute(query);
    }

    public void handleUpdateLocaltime_epoch(){
        String query = "UPDATE Weather SET localtime_epoch='"+localtime_epoch+"' WHERE name='"+name+"';";

    }
    public Location handleSelectLocation(String locationName) throws SQLException {
        String query = "SELECT * FROM Location WHERE name='"+locationName+"';";
        Statement statement= jdbcConnection.createStatement();
        ResultSet results = statement.executeQuery(query);
        //System.out.println(results.toString());
        Location locationObj= new Location();
        while(results.next()){
            //System.out.println(results.toString());
            locationObj.setName(results.getString("name"));
            locationObj.setCountry(results.getString("country"));
            locationObj.setRegion(results.getString("region"));
            locationObj.setTimezone_id(results.getString("timezone_id"));
            locationObj.setLocaltime_epoch(results.getString("localtime_epoch"));
            locationObj.setUtc_offset(results.getString("utc_offset"));
            //System.out.println("Name :"+Name +": SIN : "+SIN);
        }
        return locationObj;
    }

    public void handleInsert(Location locationObj){
        String query = "INSERT INTO Weather VALUES('"+locationObj.getName()+"', '"+locationObj.getCountry()+"', '"
                +locationObj.getRegion() +"', '"+locationObj.getTimezone_id()+"', '"+locationObj.getLocaltime_epoch()
                +"', '"+locationObj.getUtc_offset()+"');";
    }
    public void createtable() throws SQLException {
        String query = "CREATE TABLE Location(name varchar(200) primary key, " +
                "country varchar(200), region varchar(200), timezone_id varchar(200)," +
                " localtime_epoch varchar(200), utc_offset varchar(200))";

        Statement statement= jdbcConnection.createStatement();
        ResultSet results = statement.executeQuery(query);
    }
}
