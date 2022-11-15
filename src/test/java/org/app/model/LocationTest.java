package org.app.model;

import org.app.view.MainForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    @Test
    void invokeApi() throws SQLException {
        MainForm mainFormTest = new MainForm();
        Location location=new Location();
        Location testLocation = location.invokeApi("New%20York");
        Assertions.assertEquals("New York",testLocation.getName());
    }

}