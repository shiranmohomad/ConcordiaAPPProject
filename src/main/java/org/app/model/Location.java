package org.app.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.*;
import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


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

    public void handleUpdateLocaltime_epoch() throws SQLException {
        String query = "UPDATE Weather SET localtime_epoch='"+localtime_epoch+"' WHERE name='"+name+"';";
        Statement statement= jdbcConnection.createStatement();
        statement.execute(query);
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
    public List<Location> handleAllSelectLocation() throws SQLException {
        String query = "SELECT * FROM Location LIMIT 5;";
        Statement statement= jdbcConnection.createStatement();
        ResultSet results = statement.executeQuery(query);
        //System.out.println(results.toString());

        List<Location> locationList=new ArrayList<Location>();
        while(results.next()){
            Location locationObj= new Location();
            System.out.println("results.next()"+results.getString("name"));
            locationObj.setName(results.getString("name"));
            locationObj.setCountry(results.getString("country"));
            locationObj.setRegion(results.getString("region"));
            locationObj.setTimezone_id(results.getString("timezone_id"));
            locationObj.setLocaltime_epoch(results.getString("localtime_epoch"));
            locationObj.setUtc_offset(results.getString("utc_offset"));
            //System.out.println(locationObj.toString());
            locationList.add(locationObj);
        }
        for(int i=0;i<locationList.size();i++){
            System.out.println("Method :"+locationList.get(i).getName().toString());
        }
        return locationList;
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

    public void deleteLocation(String locationName) throws SQLException {
        String query = "DELETE FROM Location WHERE name='"+locationName+"'";
        Statement statement= jdbcConnection.createStatement();
        statement.execute(query);
    }
    public Location invokeApi(String city) throws SQLException {
        URL url;
        Location location=null;
        try {
            //url = new URL("https://run.mocky.io/v3/19fa89b0-d9f9-4a82-8f95-0a9048d3a4af");
            //url = new URL("https://radio-world-50-000-radios-stations.p.rapidapi.com/v1/radios/getTopByCountry/{query:'fr'}");
            //url = new URL("https://api.geekflare.com/dnsrecord");

            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            url = new URL("http://api.weatherstack.com/current?access_key=cf3006e8186f805ea13f07dc593b5f34&query="+city);
            //url = new URL("https://run.mocky.io/v3/10758310-05ab-4338-bed7-28392f1b9d30"+"?access_key=cf3006e8186f805ea13f07dc593b5f34&query="+city);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("access_key","cf3006e8186f805ea13f07dc593b5f34");
            conn.setRequestProperty("accept","application/json");
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode= conn.getResponseCode();
            System.out.println("responseCode :"+responseCode);
            System.out.println("responseMessage :"+conn.getContent());
            StringBuilder response=new StringBuilder();
            String inline="";
            if(responseCode==200){
                System.out.println(conn.getResponseMessage());

                Scanner scanner=new Scanner(url.openStream());
                while(scanner.hasNext()){
                    inline+=scanner.nextLine();
                    //response.append(scanner.nextLine());
                }
                scanner.close();
                System.out.println(response);
                JSONParser parse = new JSONParser();
                JSONObject jobj = (JSONObject)parse.parse(inline);
                JSONObject jobjLocation = (JSONObject)jobj.get("location");
                JSONObject jobjCurrent = (JSONObject)jobj.get("current");

                if(jobjLocation!=null && jobjCurrent!=null){
                    location = new Location(jobjLocation,jobjCurrent);
                }
            }
            else
                System.out.println("Error :"+conn.getResponseMessage());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return location;
    }
}
