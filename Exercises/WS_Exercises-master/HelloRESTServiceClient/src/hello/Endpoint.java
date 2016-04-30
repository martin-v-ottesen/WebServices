
package hello;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import javax.activation.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.jvnet.ws.wadl.util.DSDispatcher;
import org.jvnet.ws.wadl.util.JAXBDispatcher;
import org.jvnet.ws.wadl.util.UriBuilder;

public class Endpoint {


    public static class Hello {

        private JAXBDispatcher _jaxbDispatcher;
        private DSDispatcher _dsDispatcher;
        private UriBuilder _uriBuilder;
        private JAXBContext _jc;
        private HashMap<String, Object> _templateAndMatrixParameterValues;

        /**
         * Create new instance
         * 
         */
        public Hello()
            throws JAXBException
        {
            _dsDispatcher = new DSDispatcher();
            _uriBuilder = new UriBuilder();
            List<String> _matrixParamSet;
            _matrixParamSet = _uriBuilder.addPathSegment("http://localhost:8080/Hello/resources");
            _matrixParamSet = _uriBuilder.addPathSegment("hello");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        public DataSource getAsTextPlain()
            throws IOException, MalformedURLException
        {
            HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
            HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
            String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
            DataSource _retVal = _dsDispatcher.doGET(_url, _headerParameterValues, "text/plain");
            return _retVal;
        }

        public DataSource postAsTextPlain(DataSource input)
            throws IOException, MalformedURLException
        {
            HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
            HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
            String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
            DataSource _retVal = _dsDispatcher.doPOST(input, "text/plain", _url, _headerParameterValues, "text/plain");
            return _retVal;
        }

    }

}
