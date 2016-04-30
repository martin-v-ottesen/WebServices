package ws.testtemperature;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestTemperature {

    public TestTemperature() {
    }

    @Test
    public void testConvertTemperatureSoap() {
        net.webservicex.ConvertTemperature service = new net.webservicex.ConvertTemperature();
        net.webservicex.ConvertTemperatureSoap port = service.getConvertTemperatureSoap();
        
        double temperature = 0.0d;
        net.webservicex.TemperatureUnit fromUnit = net.webservicex.TemperatureUnit.DEGREE_CELSIUS;
        net.webservicex.TemperatureUnit toUnit = net.webservicex.TemperatureUnit.DEGREE_FAHRENHEIT;
        
        double result = port.convertTemp(temperature, fromUnit, toUnit);
        assertEquals(temperature*(9.0d/5.0d)+32.0d, result, 0.0001);
    }

    @Test
    public void testConvertTemperatureSoap12() {
        net.webservicex.ConvertTemperature service = new net.webservicex.ConvertTemperature();
        net.webservicex.ConvertTemperatureSoap port = service.getConvertTemperatureSoap12();
        
        double temperature = 0.0d;
        net.webservicex.TemperatureUnit fromUnit = net.webservicex.TemperatureUnit.DEGREE_FAHRENHEIT;
        net.webservicex.TemperatureUnit toUnit = net.webservicex.TemperatureUnit.DEGREE_CELSIUS;
        
        double result = port.convertTemp(temperature, fromUnit, toUnit);
        assertEquals((temperature-32.0d)*(5.0d/9.0d), result, 0.0001);
    }
}
