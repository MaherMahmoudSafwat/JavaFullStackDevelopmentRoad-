package org.example;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Simple servlet that handles HTTP GET requests and returns an HTML response.
 */
public class ServiceServlet extends HttpServlet {

    /**
     * Handles HTTP GET requests to this servlet
     * @param req The HttpServletRequest object containing client request data
     * @param resp The HttpServletResponse object for sending responses
     * @throws IOException If an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // 1. Set the response content type to HTML
        // This tells browsers to interpret the response as HTML
        resp.setContentType("text/html");

        // 2. Get the PrintWriter object for sending text responses
        // This is our channel to write data back to the client
        PrintWriter out = resp.getWriter();

        // 3. Build and send the HTML response
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hello World!</h1>");  // Main heading
        out.println("</body>");
        out.println("</html>");

        // 4. The PrintWriter is automatically flushed and closed
        // by the container after this method completes
    }
}
