package org.app.view;

import org.app.model.Location;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.net.MalformedURLException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class MainFormTest {

    @Test
    void createsubLocation() throws SQLException, MalformedURLException, ParseException {
        MainForm testForm = new MainForm();
        JSONParser parser = new JSONParser();
        JSONObject jsonLocation = (JSONObject) parser.parse("{\"name\":\"New York\",\"country\":\"United States of America\",\n" +
                "\"region\":\"New York\",\"lat\":\"40.714\",\"lon\":\"-74.006\",\n" +
                "\"timezone_id\":\"America\\/New_York\",\"localtime\":\"2022-11-10 01:24\",\n" +
                "\"localtime_epoch\":1668043440,\n" +
                "\"utc_offset\":\"-5.0\"}");
        JSONObject jsonWeather = (JSONObject) parser.parse("{\"observation_time\":\"06:24 AM\",\n" +
                "\"temperature\":5,\"weather_code\":113,\n" +
                "\"weather_icons\":[\"https:\\/\\/cdn.worldweatheronline.com\\/images\\/wsymbols01_png_64\\/wsymbol_0008_clear_sky_night.png\"],\n" +
                "\"weather_descriptions\":[\"Clear\"],\n" +
                "\"wind_speed\":4,\"wind_degree\":10,\n" +
                "\"wind_dir\":\"N\",\"pressure\":1032,\n" +
                "\"precip\":0,\"humidity\":86,\"cloudcover\":0,\"feelslike\":2,\"uv_index\":1,\"visibility\":16,\"is_day\":\"no\"}");
        //JSONObject json=new JSONObject();
        Location testLocation = new Location(jsonLocation,jsonWeather);
        JPanel testJpanel= testForm.createsubLocation(testLocation);
        //System.out.println("Testing createsubLocation :"+testJpanel.getComponent(0).getName());
        Assertions.assertEquals(JLabel.class,testJpanel.getComponent(0).getClass());
    }

    @Test
    void invokeApi() throws SQLException {
        MainForm mainFormTest = new MainForm();
        Location testLocation = mainFormTest.invokeApi("New%20York");
        Assertions.assertEquals("New York",testLocation.getName());
    }
}