package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * Main class to launch an embedded Tomcat server with a simple servlet.
 */
public class App {
    public static void main(String[] args) throws LifecycleException {
        // 1. Create a new instance of embedded Tomcat server
        // This will handle HTTP requests without needing a separate Tomcat installation
        Tomcat tomcat = new Tomcat();

        // 2. Configure the port Tomcat will listen on
        // Default port is 8080, but we're using 8081 to avoid conflicts
        tomcat.setPort(8081);

        // 3. Create a context (web application) with empty path
        // The empty string "" makes this the root context
        // Second parameter (null) means use default file base directory
        Context context = tomcat.addContext("", null);

        // 4. Register our servlet with Tomcat
        // Parameters:
        // - Context: The webapp this servlet belongs to
        // - Servlet name: Internal name used by Tomcat ("helloServlet")
        // - Servlet instance: The actual servlet object
        Tomcat.addServlet(context, "helloServlet", new ServiceServlet());

        // 5. Map the servlet to a URL pattern
        // This tells Tomcat to route "/hello" requests to our servlet
        // Must start with '/' and match the name used in addServlet()
        context.addServletMappingDecoded("/hello", "helloServlet");

        // 6. Initialize the HTTP connector
        // This critical step creates the actual network listener
        // Without this, Tomcat won't accept connections!
        tomcat.getConnector();

        // 7. Start the Tomcat server
        // This begins listening for HTTP requests
        tomcat.start();

        // 8. Keep the server running and log success
        System.out.println("Server running on http://localhost:8081/hello");

        // 9. Block the main thread to keep server alive
        // await() makes Tomcat wait for shutdown commands
        tomcat.getServer().await();
    }
}