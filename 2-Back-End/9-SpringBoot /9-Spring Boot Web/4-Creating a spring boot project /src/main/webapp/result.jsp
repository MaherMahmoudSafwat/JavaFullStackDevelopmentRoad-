<%@page language="java" %>
<!-- This declares this is a Java Server Page (JSP) -->

<html>
    <head>
        <!-- Link to an external CSS stylesheet -->
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <!--
            SECTION 1: Displaying result using plain Java (scriptlet)
            - <%= %> is called an expression tag
            - session.getAttribute("result") retrieves the value we stored in the controller
            - This is the older way of displaying data in JSP
        -->
        <h2>Result is: <%= session.getAttribute("result") %></h2>

        <!--
            SECTION 2: Displaying result using JSTL Expression Language (EL)
            - ${result} is the modern, preferred way in JSP
            - It automatically looks for "result" in page, request, session, and application scope
            - Cleaner syntax than scriptlets
        -->
        <h2>Result is: ${result} </h2>
    </body>
</html>



