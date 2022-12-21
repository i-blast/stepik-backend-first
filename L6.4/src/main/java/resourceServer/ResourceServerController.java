package resourceServer;

public class ResourceServerController implements ResourceServerControllerMBean {

    private final IResourceServer resourceServer;

    public ResourceServerController(IResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    @Override
    public String getName() {
        return resourceServer.getResource().getName();
    }

    @Override
    public int getAge() {
        return resourceServer.getResource().getAge();
    }
}
