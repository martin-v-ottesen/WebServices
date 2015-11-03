package dk.dtu.ws;

import net.webservicex.TemperatureUnit;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConvertTemperatureTest {
    
    double temp = 100;

    @Test
    public void convertFromCelsiusToKelvin() {
        double exp = temp + 273.15;
        double result = convertTemp(temp, TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.KELVIN);
        assertEquals(exp, result, 0.1);
    }    
    @Test
    public void convertFromCelsiusToKelvin2() {
        double exp = temp + 273.15;
        double result = convertTemp2(temp, TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.KELVIN);
        assertEquals(exp, result, 0.1);
    }
    
    @Test
    public void convertFromKelvinToCelsius() {
        double exp = temp - 273.15;
        double result = convertTemp(temp, TemperatureUnit.KELVIN, TemperatureUnit.DEGREE_CELSIUS);
        assertEquals(exp, result, 0.1);
    }
    @Test
    public void convertFromKelvinToCelsius2() {
        double exp = temp - 273.15;
        double result = convertTemp2(temp, TemperatureUnit.KELVIN, TemperatureUnit.DEGREE_CELSIUS);
        assertEquals(exp, result, 0.1);
    }
    
    @Test
    public void convertFromCelsiusToFahfnheit() {
        double exp = 32 + temp * 9 / 5;
        double result = convertTemp(temp, TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.DEGREE_FAHRENHEIT);
        assertEquals(exp, result, 0.1);
    }
    @Test
    public void convertFromCelsiusToFahfnheit2() {
        double exp = 32 + temp * 9 / 5;
        double result = convertTemp2(temp, TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.DEGREE_FAHRENHEIT);
        assertEquals(exp, result, 0.1);
    }
    
    @Test
    public void convertFromFahfnheitToCelsius() {
        double exp = (temp - 32) / 9 * 5;
        double result = convertTemp(temp, TemperatureUnit.DEGREE_FAHRENHEIT, TemperatureUnit.DEGREE_CELSIUS);
        assertEquals(exp, result, 0.1);
    }
    @Test
    public void convertFromFahfnheitToCelsius2() {
        double exp = (temp - 32) / 9 * 5;
        double result = convertTemp2(temp, TemperatureUnit.DEGREE_FAHRENHEIT, TemperatureUnit.DEGREE_CELSIUS);
        assertEquals(exp, result, 0.1);
    }

    private static double convertTemp(double temperature, net.webservicex.TemperatureUnit fromUnit, net.webservicex.TemperatureUnit toUnit) {
        net.webservicex.ConvertTemperature service = new net.webservicex.ConvertTemperature();
        net.webservicex.ConvertTemperatureSoap port = service.getConvertTemperatureSoap();
        return port.convertTemp(temperature, fromUnit, toUnit);
    }

    private static double convertTemp2(double temperature, net.webservicex.TemperatureUnit fromUnit, net.webservicex.TemperatureUnit toUnit) {
        net.webservicex.ConvertTemperature service = new net.webservicex.ConvertTemperature();
        net.webservicex.ConvertTemperatureSoap port = service.getConvertTemperatureSoap12();
        return port.convertTemp(temperature, fromUnit, toUnit);
    }
}
