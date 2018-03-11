/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spml_assignment1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jasper
 */
public class GMLParser {
    private final List<String> vertices;
    private Graph graph;
    private Scanner scan;

    public GMLParser(String fileName) {
        try {
            scan = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        vertices = new ArrayList<>();
    }

    public Graph getGraph() {
        if(scan.next().equals("graph")) {
            scan.skip(squareBracket());

            while(!scan.hasNext("edge")) {
                if(scan.next().equals("node")) {
                    constructNode();
                } else {
                    skipLine();
                }
            }
            graph = new Graph(vertices.size());

            while(!scan.hasNext(squareBracket())) {
                if(scan.next().equals("edge")) {
                    constructEdge();
                } else {
                    skipLine();
                }
            }

            scan.close();
            return graph;
        }
        return null;
    }

    private void constructNode() {
        scan.skip(squareBracket());

        while(!scan.hasNext(squareBracket())) {
            if(scan.next().equals("id")) {
                vertices.add(scan.next());
            } else {
                skipLine();
            }
        }

        scan.skip(squareBracket());
    }

    private void constructEdge() {
        scan.skip(squareBracket());

        int sourceIndex = 0;
        int targetIndex = 0;
        double weight = 0.0d;
        while(!scan.hasNext(squareBracket())) {
            switch(scan.next()) {
                case "source":
                    String sourceIdent = scan.next();
                    sourceIndex = vertices.indexOf(sourceIdent);
                    break;
                case "target":
                    String sourceTarget = scan.next();
                    targetIndex = vertices.indexOf(sourceTarget);
                    break;
                case "weight":
                    weight = scan.nextDouble();
                    break;
                default:
                    skipLine();
                    break;
            }
        }
        graph.setCost(sourceIndex, targetIndex, weight);

        scan.skip(squareBracket());
    }

    private void skipLine() {
        if(scan.hasNext("\\p{javaWhitespace}*\".*\"")) {
            scan.skip("\\p{javaWhitespace}*\".*\"");
        } else {
            scan.skip("\\p{javaWhitespace}*[^\\p{javaWhitespace}]+");
        }
    }

    private String squareBracket() {
        return "\\p{javaWhitespace}*[\\[\\]]";
    }
}
