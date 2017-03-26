package utils;

import model.TripleLong;
import model.TripleLongArrayList;
import model.groups.Group;
import model.groups.GroupedUniqueElements;
import process.Processor;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tom on 26.03.2017.
 */
public class BigBossLivesHere {
    public static void wakeUpBro() {
        System.out.println("Starting parsing and processing...");
        long start = System.currentTimeMillis();
        Processor processor = new Processor();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("Lng.csv"), "Cp1252"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        int i = 1;
        try {
            while ((line = br.readLine()) != null) {
                try {
                    TripleLong parsedLongs = SmallParser.parse(line);
                    processor.add(parsedLongs);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Invalid string index: "+i);
                }
                //System.out.println(i);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(processor.getGroupedUniqueElements().getGroups(),
                (o1, o2) -> o2.getTripleLongArrayList().size() - o1.getTripleLongArrayList().size());
        long finish = System.currentTimeMillis();
        float timeConsumedSec = (finish - start)/1000F;
        System.out.println("Parsing and process execution time: " + timeConsumedSec + " seconds");
        saveToFile(processor);
    }

    public static void saveToFile(Processor processor){
        System.out.println("Starting output...");
        long start = System.currentTimeMillis();
        try(PrintWriter out = new PrintWriter("processResult.txt")){
            int groupCounter = 1;
            GroupedUniqueElements groupedUniqueElements = processor.getGroupedUniqueElements();
            ArrayList<Group> groups = groupedUniqueElements.getGroups();
            out.println("Groups with more than one row "+ groupedUniqueElements.getGroups().size());
            for (int i=0;i<groups.size();i++){
                TripleLongArrayList tripleLongArrayList = groups.get(i).getTripleLongArrayList();
                out.println("Group "+groupCounter);
                for (int j=0;j<tripleLongArrayList.size();j++){
                    TripleLong tripleLong = tripleLongArrayList.get(j);
                    Long a = tripleLong.getA();
                    Long b = tripleLong.getB();
                    Long c = tripleLong.getC();
                    out.println(a+";"+b+";"+c);
                }
                groupCounter++;
                out.println("-------------------------------------------");
            }
            Group uniqueElementsGroup = processor.getUniqueElementsGroup();
            TripleLongArrayList tripleLongArrayList = uniqueElementsGroup.getTripleLongArrayList();
            for (int i=0;i<tripleLongArrayList.size();i++) {
                TripleLong tripleLong = tripleLongArrayList.get(i);
                out.println("Group "+groupCounter);
                out.println(tripleLong.getA()+";"+tripleLong.getB()+";"+tripleLong.getC());
                out.println("-------------------------------------------");
                groupCounter++;
            }
            //out.println("blag");

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        float timeConsumedSec = (finish - start)/1000F;
        System.out.println("Output execution time: " + timeConsumedSec + " seconds");
    }
}
