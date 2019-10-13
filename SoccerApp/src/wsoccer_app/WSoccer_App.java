/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsoccer_app;

/**
 *
 * @author Anika
 */
import java.util.*;
import java.io.*;

public class WSoccer_App {

    public static void main(String[] args) throws Exception {
      //  File file = new File("F:\\Code-Colleg\\Java\\soccer.txt");
      File file = new File("C:\\Users\\Annika\\Documents\\NetBeansProjects\\SoccerApp\\src\\wsoccer_app\\txts\\soccer.txt");
        Map<String, Integer> soccerTree = new TreeMap();
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        ArrayList<String> dubsName = new ArrayList();

        String[] myDelimiterLine, myDelimiterTeamLeft, myDelimiterTeamRight;
        String line, tryig;

        while ((line = buffer.readLine()) != null) {
            // System.out.println(line); //testing
            myDelimiterLine = line.split(",");
            String team1, team2, scoreStr1, scoreStr2, side1, side2;
            int score1, score2, points1 = 0, points2 = 0;
            side1 = myDelimiterLine[0];
            side2 = myDelimiterLine[1];

            // System.out.println("Side 1 "+ side1 +"\nSide 2 " + side2);    //testing
            //  System.out.println(" "); //testing
            myDelimiterTeamLeft = side1.split("#");
            myDelimiterTeamRight = side2.split("#");

            team1 = myDelimiterTeamLeft[0];
            team2 = myDelimiterTeamRight[0];

            scoreStr1 = myDelimiterTeamLeft[1];
            scoreStr2 = myDelimiterTeamRight[1];

            //System.out.println("team 1 " + team1 +" VS "+ "Team 2 " + team2 +" || Final Score:"+ scoreStr1+"/"+ scoreStr2);
            score1 = Integer.parseInt(scoreStr1);
            score2 = Integer.parseInt(scoreStr2);

            //System.out.println(score1 +" "+ score2);
            //System.out.println(points2);
            boolean testdubname1 = dubsName.contains(team1),
                    testdubname2 = dubsName.contains(team2);

            if (testdubname1 == false && testdubname2 == false) {
                dubsName.add(team1);
                dubsName.add(team2);
                soccerTree.put(team1, 0);
                soccerTree.put(team2, 0);

            } else if (testdubname1 == false && testdubname2 == true) {
                dubsName.add(team1);
                soccerTree.put(team1, 0);

            } else if (testdubname1 == true && testdubname2 == false) {
                dubsName.add(team2);
                soccerTree.put(team2, 0);

            } else if (testdubname1 && testdubname2) {

            } else {
                System.out.println("I ain't doin' it, cuz it ain't exist!");
            }

            if (soccerTree.containsKey(team1)) {
                points1 = soccerTree.get(team1);
            }
            if (soccerTree.containsKey(team2)) {
                points2 = soccerTree.get(team2);
            }
            if (score1 == score2) {
                soccerTree.replace(team1, points1 + 1);
                soccerTree.replace(team2, points1 + 1);
            } else if (score1 > score2) {
                soccerTree.replace(team1, points1 + 3);
                soccerTree.replace(team2, points2 + 0);
            } else if (score1 < score2) {
                soccerTree.replace(team1, points1 + 0);
                soccerTree.replace(team2, points2 + 3);
            } else {
                System.out.println("I told ya it doesn't exist!");
            }

        }

        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

        soccerTree.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        Set set = reverseSortedMap.entrySet();

        // Get an iterator
        Iterator it = set.iterator();

        // Display elements
        while (it.hasNext()) {
            Map.Entry me = (Map.Entry) it.next();
            System.out.print(me.getKey() + " " + me.getValue() + "\n");

        }

    }
}
