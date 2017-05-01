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
            //retrieve sample pres
            //DocumentParser.retrievePresentationText("https://docs.google.com/presentation/d/1yCLpPkldwILBOhnyQvmrkn4LFLAaltLzRQ5kXT_OeSg/edit?usp=sharing5");
        }catch(Exception e){}

        resp.getWriter().println("Doc A: " + docA);
        resp.getWriter().println("Doc B: " + docB);
        //comment

        String[] ALines = docA.split("\n");
        String[] BLines = docB.split("\n");
        String[][] A = new String[ALines.length][];
        for(int i = 0; i < ALines.length; i++){
            A[i] = ALines[i].split(" ");
        }
        String[][] B = new String[BLines.length][];
        for(int i = 0; i < BLines.length; i++){
            B[i] = BLines[i].split(" ");
        }

        Completeness complete = new Completeness();
        resp.getWriter().println("Word for word: " + WordForWord.wordForWord(A, B));
        resp.getWriter().println("Completeness: " + Completeness.compare(A, B));
        resp.getWriter().println("Negatory: " + Negatory.Negatory(A, B));
        resp.getWriter().println("Negatory2: " + Negatory.Negatory(B, A));

        resp.setHeader("Access-Control-Allow-Origin", "*");
    }





}
