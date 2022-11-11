package org.app.control;

import org.app.model.JDBC;
//import org.json.*;
import org.app.model.Location;
import org.app.model.Weather;
import org.app.view.MainForm;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*\
const axios = require("axios");

const options = {
  method: 'GET',
  url: 'https://radio-world-50-000-radios-stations.p.rapidapi.com/v1/radios/getTopByCountry',
  params: {query: 'fr'},
  headers: {
    'X-RapidAPI-Key': '84af080ea7mshf92803645b1bac4p13f263jsne272f76f376f',
    'X-RapidAPI-Host': 'radio-world-50-000-radios-stations.p.rapidapi.com'
  }
};

axios.request(options).then(function (response) {
	console.log(response.data);
}).catch(function (error) {
	console.error(error);
});




https://api.geekflare.com/dnsrecord
{
  "url": "https://google.com"
}
curl -X GET "https://localhost:8243/pizzashack/1.0.0/menu" -H "accept: application/json" -H "Authorization: Bearer eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJhdXQiOiJBUFBMSUNBVElPTiIsImF1ZCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJuYmYiOjE2Njc4NzAyNzEsImF6cCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJzY29wZSI6ImFtX2FwcGxpY2F0aW9uX3Njb3BlIGRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvbG9jYWxob3N0Ojk0NDNcL29hdXRoMlwvdG9rZW4iLCJleHAiOjE2Njc4NzM4NzEsImlhdCI6MTY2Nzg3MDI3MSwianRpIjoiYzdkMDRmYjUtODE2Ni00OTcxLWE5YWEtYzcwMDViODViNDQxIn0.g1-0OXSuZrJumNIGzkkn5Er9ZMM0RRu0_CYPTjRpCck-3YI8q4w4mcWl1kh9Nq1wGkAZUNrrYMWJ8eto6UTl5v-abaqPqJmnp0dal3Gc-ncmuc6N8WiqkW4dem8dT1upwdKnXad72QSU3Wit6_uS_NPdubPnB_fVi0F4DhX1u_WqkbLWif2VV8DkANN0aj723nLcaf9UEwY0kqWarKv4tGUpEBROmGdJqiVVYQrynBzelbZOKCeddMASQUJZuw_3HdJZix5xuqpmsxSki0wlkmw4VfUoVH3BMiXTvmpu3T5eSKH-l38jg7A1NaLJSW8eWcLay5NY99mLT09wzxqddQ"

curl -X GET "https://localhost:8243/pizzashack/1.0.0/menu" -H "accept: application/json" -H "Authorization: Bearer eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJhdXQiOiJBUFBMSUNBVElPTiIsImF1ZCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJuYmYiOjE2Njc5NTg5OTksImF6cCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJzY29wZSI6ImFtX2FwcGxpY2F0aW9uX3Njb3BlIGRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvbG9jYWxob3N0Ojk0NDNcL29hdXRoMlwvdG9rZW4iLCJleHAiOjE2Njc5NjI1OTksImlhdCI6MTY2Nzk1ODk5OSwianRpIjoiMDliZWQ3OWEtNDQ0OC00Mjg2LWFiMTItMzFkMTg5YzM4YTNhIn0.sDz8tqibkf6Glxn4kfXCyjtjQEtKC6EA7crHg9OcbLzYcmwaBdvibbw6fo_h5wc2dpAO8OQIoPNgZ6yoJtLvBo8EvgMfY3ywoGS8KlYqDkwf4wLs012elOSPqonRBYApkVexx9C03HKKVC8GJKH12b42lG8c0f2zoVJDGmvynYFHqVj7HxpTEhMWkSH0ySsHMa_mel3G9rLcuX0HoB1qHWhX-uRLaf9nkcGw7wCjQsvwNnpAFNYrsV41Elst8lV2rkVP87vMhJCTQLLbB0g8dkIR8aWpEhRCWszuJBThFGZQ_vokmwq3DHleojo-fN_zcfifsFUnu-SPrATj9R_i-g"

curl -X POST "https://localhost:8243/pizzashack/1.0.0/order" -H "accept: application/json" -H "Content-Type: application/json" -H "Authorization: Bearer eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJhdXQiOiJBUFBMSUNBVElPTiIsImF1ZCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJuYmYiOjE2Njc5NjAyMDEsImF6cCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJzY29wZSI6ImFtX2FwcGxpY2F0aW9uX3Njb3BlIGRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvbG9jYWxob3N0Ojk0NDNcL29hdXRoMlwvdG9rZW4iLCJleHAiOjE2Njc5NjM4MDEsImlhdCI6MTY2Nzk2MDIwMSwianRpIjoiNDJlNDE3NjgtNGY4OC00MDE5LTk3YmYtZWM2MzdiNzgxZmIxIn0.EoufTrwONaAVUyyXFNidiR52FAO8SLHf_a6wlCJWsNK-auCoV76IRFUJuwlo6__SIV8VpKmiWu7D5I0t_GzHOJpQdiAbwzXBauZV3zNqjy-KPvSwnQSDEG29rCd8qWxStYf-m03yURRoqCWT6XO1QSCKp5QhvMTNEtUUgCS-MoxD1d2DoMIEpi3Q2nHBovGhkZWH0dSI_gmbCdNs-gHwl5-mKpQavC_F3Xy35bE0LLhCnsNIKfQULPexovctihJYLMuKK7VxcBP243Ay3AnAK9Dz67iA4kosHhTHF1JI93SdTCYy3AK3L6-meYhvfduZyU1hCN5gP79JxEgbtGtZFg" -d "{ \"customerName\": \"string\", \"delivered\": true, \"address\": \"string\", \"pizzaType\": \"string\", \"creditCardNumber\": \"string\", \"quantity\": 0, \"orderId\": \"string\"}"

curl -X POST "https://localhost:8243/test/1" -H "Authorization: Bearer eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJhdXQiOiJBUFBMSUNBVElPTiIsImF1ZCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJuYmYiOjE2NjgwMzIxMDYsImF6cCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJzY29wZSI6ImFtX2FwcGxpY2F0aW9uX3Njb3BlIGRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvbG9jYWxob3N0Ojk0NDNcL29hdXRoMlwvdG9rZW4iLCJleHAiOjE2NjgwMzU3MDYsImlhdCI6MTY2ODAzMjEwNiwianRpIjoiM2I0ZDVmZTAtNTA3Ny00ZDE2LTk4OWEtN2ZhZGU1NTUyNDQwIn0.xaHR1pndKida4wdFI7-HxBN8jCJ3mZGK9q3P6VHJPjnX2b9ESz0qYu7JtHUGfzfyRl5hTPO2qwZW-tjuXopn783AEitty4fmT01UkfEst5YWsK6r0wVrNSMkg_mVX77PEYbXe-ocryhCvh8NZ0XjxSalF_-KvSMqWWYA9frLhuVFHc2Nlt2QDRQ3UjArRyqfSEy38rnbZR-pzUegAyBPLRQhCybjFRNpAcn2Wj8iaHfa3FgEq55OIM7npb6KFvlzRJ9t533UvQGIwDr7J_RKUMCLJxVd9Ld3PFP12mzt1DZZmgndqgG7Cd8SNSnGXZHbk8xT6HzwB6K4qmVSft8m5A"

OK
{"request":{"type":"City","query":"New York, United States of America","language":"en","unit":"m"},"location":{"name":"New York","country":"United States of America","region":"New York","lat":"40.714","lon":"-74.006","timezone_id":"America\/New_York","localtime":"2022-11-10 01:24","localtime_epoch":1668043440,"utc_offset":"-5.0"},"current":{"observation_time":"06:24 AM","temperature":5,"weather_code":113,"weather_icons":["https:\/\/cdn.worldweatheronline.com\/images\/wsymbols01_png_64\/wsymbol_0008_clear_sky_night.png"],"weather_descriptions":["Clear"],"wind_speed":4,"wind_degree":10,"wind_dir":"N","pressure":1032,"precip":0,"humidity":86,"cloudcover":0,"feelslike":2,"uv_index":1,"visibility":16,"is_day":"no"}}

http://api.weatherstack.com/current
cf3006e8186f805ea13f07dc593b5f34

*/

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        MainForm mainForm = new MainForm();
        mainForm.setContentPane(mainForm.mainPanel);
        mainForm.setTitle("සිරා Weather");
        mainForm.setSize(400,700);
        mainForm.setVisible(true);
        long start = System.currentTimeMillis();
        Thread.sleep(20000);
        String jdbcUrl= "jdbc:sqlite:/Users/shiransilva/Repos/Aca/ConcordiaAPPProject/APP_Projact.db";
        try{


            System.out.println("Executing query");
            //Connection jdbcConnection= DriverManager.getConnection(jdbcUrl);
            Connection jdbcConnection = JDBC.getInstance();
            String sqlcreate = "create table User(SIN integer primary key, Name varchar(200))";
            String sqlInsert = "INSERT INTO User VALUES(2, 'Shiran')";
            String sql = " select * from User;";
            Statement statement= jdbcConnection.createStatement();
            //statement.executeQuery(sqlcreate);
            //statement.executeQuery(sqlInsert);
            ResultSet results = statement.executeQuery(sql);
            //System.out.println(results.toString());
            while(results.next()){
                //System.out.println(results.toString());
                String SIN = results.getString("SIN");
                String Name = results.getString("Name");

                System.out.println("Name :"+Name +": SIN : "+SIN);
            }

        } catch(Exception e){
            System.out.println(e);
        }
        URL url;
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
            //url = new URL("https://run.mocky.io/v3/19fa89b0-d9f9-4a82-8f95-0a9048d3a4af");
            //url = new URL("https://localhost:8243/pizzashack/1.0.0/menu");
            //url = new URL("http://api.weatherstack.com/current?access_key=cf3006e8186f805ea13f07dc593b5f34&query=New York");
            url = new URL("https://run.mocky.io/v3/10758310-05ab-4338-bed7-28392f1b9d30");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //conn.setRequestProperty("Authorization","Bearer eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJhdXQiOiJBUFBMSUNBVElPTiIsImF1ZCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJuYmYiOjE2NjgwMzIxMDYsImF6cCI6IllpT1ZESGFsb2k5Z2NGQXpXYlpHX1cyVGhHSWEiLCJzY29wZSI6ImFtX2FwcGxpY2F0aW9uX3Njb3BlIGRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvbG9jYWxob3N0Ojk0NDNcL29hdXRoMlwvdG9rZW4iLCJleHAiOjE2NjgwMzU3MDYsImlhdCI6MTY2ODAzMjEwNiwianRpIjoiM2I0ZDVmZTAtNTA3Ny00ZDE2LTk4OWEtN2ZhZGU1NTUyNDQwIn0.xaHR1pndKida4wdFI7-HxBN8jCJ3mZGK9q3P6VHJPjnX2b9ESz0qYu7JtHUGfzfyRl5hTPO2qwZW-tjuXopn783AEitty4fmT01UkfEst5YWsK6r0wVrNSMkg_mVX77PEYbXe-ocryhCvh8NZ0XjxSalF_-KvSMqWWYA9frLhuVFHc2Nlt2QDRQ3UjArRyqfSEy38rnbZR-pzUegAyBPLRQhCybjFRNpAcn2Wj8iaHfa3FgEq55OIM7npb6KFvlzRJ9t533UvQGIwDr7J_RKUMCLJxVd9Ld3PFP12mzt1DZZmgndqgG7Cd8SNSnGXZHbk8xT6HzwB6K4qmVSft8m5A");
            conn.setRequestProperty("access_key","cf3006e8186f805ea13f07dc593b5f34");
            conn.setRequestProperty("accept","application/json");

            //conn.setRequestProperty("X-RapidAPI-Key","84af080ea7mshf92803645b1bac4p13f263jsne272f76f376f");
            //conn.setRequestProperty("X-RapidAPI-Host", "radio-world-50-000-radios-stations.p.rapidapi.com");
            conn.setRequestMethod("GET");
            //conn.setDoOutput(true);
            String jsonInputString = "{\n" +
                    "  \"url\": \"https://google.com\"\n" +
                    "}";
            /*try(OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }*/
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
            }
            else
                System.out.println("Error :"+conn.getResponseMessage());
            JSONParser parse = new JSONParser();
            JSONObject jobj = (JSONObject)parse.parse(inline);
            JSONObject jobjLocation = (JSONObject)jobj.get("location");
            JSONObject jobjCurrent = (JSONObject)jobj.get("current");

            Location location = new Location(jobjLocation);
            String LocationExistName= location.handleSelectLocation(location.getName()).getName();
            if(LocationExistName ==""){
                location.handleInsert();
            }
            //boolean ifLocationExist = ;
            Weather weather =new Weather(jobjCurrent,location.getName());
            weather.handleInsert();
            Weather weatherSelectObj = weather.handleSelectLocation(location.getName());
            System.out.println(weatherSelectObj.getTemperature());

            System.out.println(jobjLocation.get("localtime"));
            System.out.println(jobj.toJSONString());
            //JSONObject obj = new JSONObject(response);

            System.out.println(jobj.get("location"));
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
        }


        //System.out.println("Hello world!");

    }
}