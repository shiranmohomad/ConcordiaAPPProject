package org.app.model;

import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Weather {
    private String location = "";
    private String observation_time = "";
    private String temperature = "";
    private String weather_icons = "";
    private String weather_descriptions = "";
    private String wind_speed = "";
    private String wind_degree= "";
    private String wind_dir= "";
    private String pressure= "";
    private String precip= "";
    private String humidity= "";
    private String cloudcover= "";
    private String feelslike= "";
    private String uv_index= "";
    private String visibility= "";
    private String is_day= "";
    Connection jdbcConnection = JDBC.getInstance();

    public Weather(){
        /*try{
            createtable();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    public Weather(JSONObject jsonObj, String locationP){
        observation_time = jsonObj.get("observation_time").toString();
        temperature = jsonObj.get("temperature").toString();
        weather_icons = jsonObj.get("weather_icons").toString();
        weather_descriptions = jsonObj.get("weather_descriptions").toString();
        wind_speed = jsonObj.get("wind_speed").toString();
        wind_degree = jsonObj.get("wind_degree").toString();
        wind_dir = jsonObj.get("wind_dir").toString();
        pressure = jsonObj.get("pressure").toString();
        precip = jsonObj.get("precip").toString();
        humidity = jsonObj.get("humidity").toString();
        cloudcover = jsonObj.get("cloudcover").toString();
        feelslike = jsonObj.get("feelslike").toString();
        uv_index = jsonObj.get("uv_index").toString();
        visibility = jsonObj.get("visibility").toString();
        is_day = jsonObj.get("is_day").toString();
        location = locationP;

        /*try{
            //createtable();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setObservation_time(String observation_time) {
        this.observation_time = observation_time;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setWeather_icons(String weather_icons) {
        this.weather_icons = weather_icons;
    }

    public void setWeather_descriptions(String weather_descriptions) {
        this.weather_descriptions = weather_descriptions;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public void setWind_degree(String wind_degree) {
        this.wind_degree = wind_degree;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setCloudcover(String cloudcover) {
        this.cloudcover = cloudcover;
    }

    public void setFeelslike(String feelslike) {
        this.feelslike = feelslike;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void setIs_day(String is_day) {
        this.is_day = is_day;
    }

    public String getObservation_time() {
        return observation_time;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWeather_icons() {
        return weather_icons;
    }

    public String getWeather_descriptions() {
        return weather_descriptions;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getWind_degree() {
        return wind_degree;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public String getPressure() {
        return pressure;
    }

    public String getPrecip() {
        return precip;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getCloudcover() {
        return cloudcover;
    }

    public String getFeelslike() {
        return feelslike;
    }

    public String getUv_index() {
        return uv_index;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getIs_day() {
        return is_day;
    }
    public void handleInsert() throws SQLException {
        String query = "INSERT INTO Weather (observation_time,temperature,weather_icons,weather_descriptions,wind_speed," +
                "wind_degree,wind_dir,pressure,precip,humidity,cloudcover,feelslike,uv_index,visibility," +
                "is_day,name) VALUES('"+observation_time+"', '"
                +temperature+"', '"
                +weather_icons +"', '"+weather_descriptions+"', '"+wind_speed
                +"', '"+wind_degree+"', '"+wind_dir +"', '"+pressure
                +"', '"+precip+"', '"+humidity+"', '"+cloudcover
                +"', '"+feelslike+"', '"+uv_index+"', '"+visibility
                +"', '"+is_day+"','"+location+"');";
        Statement statement= jdbcConnection.createStatement();
        statement.execute(query);

    }
    public void handleInsert(Weather weatherObj) throws SQLException {
        String query = "INSERT INTO Weather VALUES('"+weatherObj.getObservation_time()+"', '"+weatherObj.getTemperature()+"', '"
                +weatherObj.getWeather_icons() +"', '"+weatherObj.getWeather_descriptions()+"', '"+weatherObj.getWind_speed()
                +"', '"+weatherObj.getWind_degree()+"', '"+weatherObj.getWind_dir() +"', '"+weatherObj.getPressure()
                +"', '"+weatherObj.getPrecip()+"', '"+weatherObj.getHumidity()+"', '"+weatherObj.getCloudcover()
                +"', '"+weatherObj.getFeelslike()+"', '"+weatherObj.getUv_index()+"', '"+weatherObj.getVisibility()
                +"', '"+weatherObj.getIs_day()+"','"+weatherObj.getLocation()+"');";
        Statement statement= jdbcConnection.createStatement();
        ResultSet results = statement.executeQuery(query);
    }

    public Weather handleSelectLocation(String locationName) throws SQLException {
        String query = "SELECT * FROM Weather WHERE name='"+locationName+"';";
        Statement statement= jdbcConnection.createStatement();
        ResultSet results = statement.executeQuery(query);
        //System.out.println(results.toString());
        Weather weatherObj= new Weather();
        while(results.next()){
            //System.out.println(results.toString());
            weatherObj.setObservation_time(results.getString("observation_time"));
            weatherObj.setTemperature(results.getString("temperature"));
            weatherObj.setWeather_icons(results.getString("weather_icons"));
            weatherObj.setWeather_descriptions(results.getString("weather_descriptions"));
            weatherObj.setWind_speed(results.getString("wind_speed"));
            weatherObj.setWind_degree(results.getString("wind_degree"));
            weatherObj.setWind_dir(results.getString("wind_dir"));
            weatherObj.setPressure(results.getString("pressure"));
            weatherObj.setPrecip(results.getString("precip"));
            weatherObj.setHumidity(results.getString("humidity"));
            weatherObj.setCloudcover(results.getString("cloudcover"));
            weatherObj.setFeelslike(results.getString("feelslike"));
            weatherObj.setUv_index(results.getString("uv_index"));
            weatherObj.setVisibility(results.getString("visibility"));
            weatherObj.setIs_day(results.getString("is_day"));
            weatherObj.setLocation(results.getString("name"));
            //locationObj.setN(results.getString("name"));

            //System.out.println("Name :"+Name +": SIN : "+SIN);
        }
        return weatherObj;
    }
    /*public void handleUpdate(Weather weatherObj){
        String query = "UPDATE Weather SET localtime_epoch='"+localtime_epoch+"' WHERE name='"+name+"';";
        String query = "INSERT INTO Weather VALUES('"+weatherObj.getObservation_time()+"', '"+weatherObj.getTemperature()+"', '"
                +weatherObj.getWeather_icons() +"', '"+weatherObj.getWeather_descriptions()+"', '"+weatherObj.getWind_speed()
                +"', '"+weatherObj.getWind_degree()+"', '"+weatherObj.getWind_dir() +"', '"+weatherObj.getPressure()
                +"', '"+weatherObj.getPrecip()+"', '"+weatherObj.getHumidity()+"', '"+weatherObj.getCloudcover()
                +"', '"+weatherObj.getFeelslike()+"', '"+weatherObj.getUv_index()+"', '"+weatherObj.getVisibility()
                +"', '"+weatherObj.getIs_day()+"');";
    }*/

    public void createtable() throws SQLException {
        String query = "CREATE TABLE Weather(WID integer primary key autoincrement, observation_time varchar(200), " +
                "temperature varchar(200), weather_icons varchar(200), weather_descriptions varchar(200)," +
                " wind_speed varchar(200), wind_degree varchar(200), wind_dir varchar(200)," +
                " pressure varchar(200), precip varchar(200),humidity varchar(200), cloudcover varchar(200), " +
                "feelslike varchar(200), uv_index varchar(200), visibility varchar(200), is_day varchar(200)," +
                "name varchar(200), foreign key (name) references Location (name))";
        Statement statement= jdbcConnection.createStatement();
        ResultSet results = statement.executeQuery(query);

    }
}
