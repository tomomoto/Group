package utils;

import model.TripleLong;
import model.TripleLongArrayList;
import model.groups.Group;
import model.groups.GroupedUniqueElements;
import process.Processor;

import java.io.*;
import java.util.*;

/**
 * Created by Tom on 26.03.2017.
 */
public class LittleChinaProgrammerLivesThere {

    public static void wakeUpAndWork() {
        System.out.println("Starting parsing and processing...");
        long start = System.currentTimeMillis();
        TreeMap<Integer,String> hashMapInvalidStrings = new TreeMap<>();
        Processor processor = getTunedProcessor(hashMapInvalidStrings);
        long finish = System.currentTimeMillis();
        float timeConsumedSec = (finish - start)/1000;
        System.out.println("Parsing and process execution time: " + timeConsumedSec + " seconds");
        saveToFile(processor);
        printStatistics(hashMapInvalidStrings);

    }

    private static void printStatistics(TreeMap<Integer, String> mapInvalidStrings) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Invalid string statistic with given format [index] = string");
        printMap(mapInvalidStrings);
        System.out.println("------------------------------------------------------------");
    }

    public static void printMap(Map mp) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println("["+pair.getKey()+"]" + " = " + pair.getValue());
            it.remove();
        }
    }

    private static Processor getTunedProcessor(Map<Integer,String> mapInvalidStrings) {
        Processor processor = new Processor();
        BufferedReader br = getBufferedReader();
        parsing(processor, br,mapInvalidStrings);
        bufferCloser(br);
        Collections.sort(processor.getGroupedUniqueElements().getGroups(),
                (o1, o2) -> o2.getTripleLongArrayList().size() - o1.getTripleLongArrayList().size());
        return processor;
    }

    private static void parsing(Processor processor, BufferedReader br, Map<Integer,String> mapInvalidStrings) {
        String line;
        int i = 1;
        try {
            while ((line = br.readLine()) != null) {
                try {
                    TripleLong parsedLongs = SmallParser.parse(line);
                    processor.add(parsedLongs);
                } catch (InvalidStringException e) {
                    mapInvalidStrings.put(i,line);
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bufferCloser(BufferedReader br) {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedReader getBufferedReader() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("Lng.csv"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }

    public static void saveToFile(Processor processor){
        System.out.println("Starting output...");
        long start = System.currentTimeMillis();
        try(PrintWriter out = new PrintWriter("processResult.txt")){
            Integer groupCounter = 1;
            groupCounter = saveGroupedUniqueElements(processor, out, groupCounter);
            saveUniqueElementsGroup(processor, out, groupCounter);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        float timeConsumedSec = (finish - start)/1000;
        System.out.println("Output execution time: " + timeConsumedSec + " seconds");
    }

    private static void saveUniqueElementsGroup(Processor processor, PrintWriter out, Integer groupCounter) {
        Group uniqueElementsGroup = processor.getUniqueElementsGroup();
        TripleLongArrayList tripleLongArrayList = uniqueElementsGroup.getTripleLongArrayList();
        for (int i=0;i<tripleLongArrayList.size();i++) {
            TripleLong tripleLong = tripleLongArrayList.get(i);
            out.println("Group "+groupCounter);
            Long a = tripleLong.getA();
            Long b = tripleLong.getB();
            Long c = tripleLong.getC();
            String aStr = (a == null)? "" : a.toString();
            String bStr = (b == null)? "" : b.toString();
            String cStr = (c == null)? "" : c.toString();
            out.println(aStr+";"+bStr+";"+cStr);
            out.println("-------------------------------------------");
            groupCounter++;
        }
    }

    private static int saveGroupedUniqueElements(Processor processor, PrintWriter out, Integer groupCounter) {
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
                String aStr = (a == null)? "" : a.toString();
                String bStr = (b == null)? "" : b.toString();
                String cStr = (c == null)? "" : c.toString();
                out.println(aStr+";"+bStr+";"+cStr);
            }
            groupCounter++;
            out.println("-------------------------------------------");
        }
        return groupCounter;
    }
}
