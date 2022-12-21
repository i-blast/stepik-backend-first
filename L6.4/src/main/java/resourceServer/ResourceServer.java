package resourceServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import resources.TestResource;
import sax.ReadXMLFileSAX;

import java.util.Objects;

public class ResourceServer implements IResourceServer {

    static final Logger logger = LogManager.getLogger(ResourceServer.class.getName());

    private TestResource resource;

    private static final TestResource STUB = new TestResource();

    @Override
    public TestResource getResource() {
        return Objects.nonNull(this.resource) ? this.resource : STUB;
    }

    @Override
    public void saveResource(String resourcePath) {
        this.resource = buildResource(resourcePath);
    }

    private static TestResource buildResource(String resourcePath) {
        TestResource testResource = null;
        try {
            testResource = (TestResource) ReadXMLFileSAX.readXML(resourcePath);
        } catch (Exception exc) {
            logger.error("Error during resource deserializing.", exc);
        }
        return testResource;
    }
}
