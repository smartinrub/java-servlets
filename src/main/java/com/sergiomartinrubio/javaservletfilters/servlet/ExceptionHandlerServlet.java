package com.sergiomartinrubio.javaservletfilters.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/ExceptionHandler", name = "ErrorHandlerServlet")
public class ExceptionHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Class exceptionType = (Class) request.getAttribute("javax.servlet.error.exception_type");
        String exceptionMessage = (String) request.getAttribute("javax.servlet.error.message");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        if (servletName == null) {
            servletName = "Unknown";
        }
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        if (exceptionMessage == null) {
            exceptionMessage = "Unknown";
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Exception Information";
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n");

        if (throwable == null && statusCode == null) {
            out.println("<h2>Error information is missing</h2>");
        } else {
            out.println("<h2>Error information</h2>");
            out.println("Servlet Name : " + servletName + "</br></br>");
            out.println("Exception Type : " + exceptionType.getName() + "</br></br>");
            out.println("The request URI: " + requestUri + "<br><br>");
            out.println("The exception message: " + exceptionMessage);
        }
        out.println("</body>");
        out.println("</html>");

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
}
