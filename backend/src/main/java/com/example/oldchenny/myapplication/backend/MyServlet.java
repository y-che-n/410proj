/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.example.oldchenny.myapplication.backend;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Please use the form to POST to this url");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String docA = req.getParameter("DocumentA");
        String docB = req.getParameter("DocumentB");
        resp.setContentType("text/plain");
        if (docA == null) {
            resp.getWriter().println("Please enter Document A");
        }
        if (docB == null) {
            resp.getWriter().println("Please enter Document B");
        }

        try {
            DocumentParser.retrievePresentationText("https://docs.google.com/presentation/d/1yCLpPkldwILBOhnyQvmrkn4LFLAaltLzRQ5kXT_OeSg/edit?usp=sharing5");
        }catch(Exception e){}

        resp.getWriter().println("Hello " + docA);
        resp.getWriter().println("Hello " + docB);
        resp.setHeader("Access-Control-Allow-Origin", "*");
    }





}
