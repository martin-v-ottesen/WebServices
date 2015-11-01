package dk.dtu.ws;

import net.webservicex.TemperatureUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConvertTemperatureTest {

    public ConvertTemperatureTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void convertFromCelsiusToKelvin() {
        double temp = 30;
        double convertion = 273.15;
        double result = convertTemp(temp, TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.KELVIN);
        assertEquals(temp+convertion, result, 0.1);
    }
    
    @Test
    public void convertFromKelvinToCelsius() {
        double temp = 30;
        double convertion = -273.15;
        double result = convertTemp(temp, TemperatureUnit.KELVIN, TemperatureUnit.DEGREE_CELSIUS);
        assertEquals(temp+convertion, result, 0.1);
    }
    
    @Test
    public void convertFromKelvinToFahfnheit() {
        double temp = 30;
        double convertion = -273.15;
        double result = convertTemp(temp, TemperatureUnit.KELVIN, TemperatureUnit.DEGREE_FAHRENHEIT);
        assertEquals(temp+convertion, result, 0.1);
    }

    private static double convertTemp(double temperature, net.webservicex.TemperatureUnit fromUnit, net.webservicex.TemperatureUnit toUnit) {
        net.webservicex.ConvertTemperature service = new net.webservicex.ConvertTemperature();
        net.webservicex.ConvertTemperatureSoap port = service.getConvertTemperatureSoap();
        return port.convertTemp(temperature, fromUnit, toUnit);
    }

    private static double convertTemp_12(double temperature, net.webservicex.TemperatureUnit fromUnit, net.webservicex.TemperatureUnit toUnit) {
        net.webservicex.ConvertTemperature service = new net.webservicex.ConvertTemperature();
        net.webservicex.ConvertTemperatureSoap port = service.getConvertTemperatureSoap12();
        return port.convertTemp(temperature, fromUnit, toUnit);
    }
}
