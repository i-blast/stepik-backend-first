package resourceServer;

import resources.TestResource;

public interface IResourceServer {

    TestResource getResource();

    void saveResource(String resourcePath);
}
