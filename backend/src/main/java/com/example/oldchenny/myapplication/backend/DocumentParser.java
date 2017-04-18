package com.example.oldchenny.myapplication.backend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;

/**
 * Created by Drew on 4/18/2017.
 */

public class DocumentParser {

    public static String[] retrievePresentationText(String site) throws ServletException, java.io.IOException {
        String[] docText = null;
        if(site.contains("/presentation/")){
            Document doc = Jsoup.connect(site).get();
            String temp, cut;
            Elements table;
            Iterator iter;
            ArrayList<String> output = new ArrayList<String>();

            table = doc.getElementsByTag("script");
            iter = table.iterator();
            boolean newPage = true;
            while (iter.hasNext()) {
                String line = iter.next().toString();
                //if(line.contains("available_themes") || line.contains("bodyPlaceholderListEntity"))
                //  continue;
                int start = line.indexOf("DOCS_modelChunk = ");
                //if(start < end)
                int end = line.indexOf("\"],[");
                while(start < end && end >= 0){
                    while (start >= 0) {
                        int next = line.indexOf("\"", start + 1);//bodyPlaceholderListEntity
                        if (next >= end)
                            break;
                        else
                            start = next;
                    }
                    int section = 0, limit = 0, tableOffset = 0, beforeContentMark = end-1;
                    int inSubBracket = 0;
                    int sections = 0;

                    for(int i = start; i > start-2000 && i > 0; i--){
                        if(line.charAt(i) == ',' && inSubBracket == 0){
                            sections++;
                        }
                        //if(beforeContentMark == end-1 && line.charAt(i)== ',')
                        //  beforeContentMark = i;
                        if(line.charAt(i) == ']') {
                            inSubBracket++;
                            //limit++;
                            //tableOffset++;
                        }
                        if(line.substring(i-3, i).equals(",\"#"))
                            break;
                        if(line.charAt(i) == '['){
                            if(inSubBracket > 0) {
                                inSubBracket--;
                                continue;
                            }

                            //if(limit == 0) {
                            section = line.substring(i, end).split(",").length;
                            System.out.println(line.substring(i, end));
                            break;
                            //}else
                            //  limit--;
                        }

                    }
                    String content = line.substring(start + 1, end);

                    if(sections == 4){//*5+tableOffset*//*) {
                        //System.out.println(content + " : " + sections);
                        //String content = line.substring(start + 1, end);
                        if (content.length() > 5 && !content.contains("\\n\\n\\n\\n\\n\\n\\n\\n") && !(content.charAt(0) == '#' && content.length() == 7) && !content.equals("bodyPlaceholderListEntity")){
                            String[] subcontent = content.split("\\\\n");
                            for(int i = 0; i < subcontent.length; i++){
                                if(newPage){
                                    newPage = false;
                                    output.add("~~**??");
                                }
                                output.add(subcontent[i]);
                            }
                        }
                    }

                    start = end;
                    end = line.indexOf("\"],[", end+4);
                }
                newPage = true;

            }
            docText = new String[output.size()];
            for(int i = 0; i < output.size(); i++) {
                docText[i] = output.get(i);
                System.out.println( output.get(i));
            }
        }

        return docText;
    }
}
