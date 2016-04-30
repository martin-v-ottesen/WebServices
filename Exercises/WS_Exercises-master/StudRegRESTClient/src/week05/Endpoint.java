
package week05;

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


/**
 * 
 * 
 */
public class Endpoint {


    public static class Students {

        private JAXBDispatcher _jaxbDispatcher;
        private DSDispatcher _dsDispatcher;
        private UriBuilder _uriBuilder;
        private JAXBContext _jc;
        private HashMap<String, Object> _templateAndMatrixParameterValues;

        /**
         * Create new instance
         * 
         */
        public Students()
            throws JAXBException
        {
            _dsDispatcher = new DSDispatcher();
            _uriBuilder = new UriBuilder();
            List<String> _matrixParamSet;
            _matrixParamSet = _uriBuilder.addPathSegment("http://localhost:8080/sr/resources/");
            _matrixParamSet = _uriBuilder.addPathSegment("students");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
        }

        public DataSource postAs(DataSource input)
            throws IOException, MalformedURLException
        {
            HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
            HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
            String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
            DataSource _retVal = _dsDispatcher.doPOST(input, "*/*", _url, _headerParameterValues, "*/*");
            return _retVal;
        }

        public DataSource getAs()
            throws IOException, MalformedURLException
        {
            HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
            HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
            String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
            DataSource _retVal = _dsDispatcher.doGET(_url, _headerParameterValues, "*/*");
            return _retVal;
        }

        public DataSource getAs(String name)
            throws IOException, MalformedURLException
        {
            HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
            HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
            _queryParameterValues.put("name", name);
            String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
            DataSource _retVal = _dsDispatcher.doGET(_url, _headerParameterValues, "*/*");
            return _retVal;
        }

    }

    public static class StudentsId {

        private JAXBDispatcher _jaxbDispatcher;
        private DSDispatcher _dsDispatcher;
        private UriBuilder _uriBuilder;
        private JAXBContext _jc;
        private HashMap<String, Object> _templateAndMatrixParameterValues;

        /**
         * Create new instance
         * 
         */
        public StudentsId(String id)
            throws JAXBException
        {
            _dsDispatcher = new DSDispatcher();
            _uriBuilder = new UriBuilder();
            List<String> _matrixParamSet;
            _matrixParamSet = _uriBuilder.addPathSegment("http://localhost:8080/sr/resources/");
            _matrixParamSet = _uriBuilder.addPathSegment("students/{id}");
            _templateAndMatrixParameterValues = new HashMap<String, Object>();
            _templateAndMatrixParameterValues.put("id", id);
        }

        /**
         * Get id
         * 
         */
        public String getId() {
            return ((String) _templateAndMatrixParameterValues.get("id"));
        }

        /**
         * Set id
         * 
         */
        public void setId(String id) {
            _templateAndMatrixParameterValues.put("id", id);
        }

        public DataSource getAs()
            throws IOException, MalformedURLException
        {
            HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
            HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
            String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
            DataSource _retVal = _dsDispatcher.doGET(_url, _headerParameterValues, "*/*");
            return _retVal;
        }

        public DataSource putAs(DataSource input)
            throws IOException, MalformedURLException
        {
            HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
            HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
            String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
            DataSource _retVal = _dsDispatcher.doPUT(input, "*/*", _url, _headerParameterValues, "*/*");
            return _retVal;
        }

        public DataSource deleteAs()
            throws IOException, MalformedURLException
        {
            HashMap<String, Object> _queryParameterValues = new HashMap<String, Object>();
            HashMap<String, Object> _headerParameterValues = new HashMap<String, Object>();
            String _url = _uriBuilder.buildUri(_templateAndMatrixParameterValues, _queryParameterValues);
            DataSource _retVal = _dsDispatcher.doDELETE(_url, _headerParameterValues, "*/*");
            return _retVal;
        }

    }

}
