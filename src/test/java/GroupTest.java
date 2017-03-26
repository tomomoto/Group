import com.opencsv.CSVReader;
import model.TripleLong;
import org.junit.Test;
import process.Processor;

import java.io.*;
import java.util.*;

public class GroupTest {

    @Test
    public void testContains() throws Exception {
        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader("Lng.csv"), ';');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] nextLine;
        Processor cmp = new Processor(new TripleLong(1l, 2l, 3l));
        int i = 0;
        TripleLong tpl2 = new TripleLong(4l, 4l, 3l);
        cmp.add(tpl2);
        TripleLong tpl3 = new TripleLong(5l, 6l, 7l);
        cmp.add(tpl3);
        TripleLong tpl4 = new TripleLong(8l, 8l, 7l);
        cmp.add(tpl4);
        TripleLong tp5 = new TripleLong(9l, 6l, 3l);
        cmp.add(tp5);
        try {
            while ((nextLine = reader.readNext()) != null) {
                if (i == 621218) {
                    System.out.print("aa");
                }
                Long a, b, c;

                String s1 = "";
                try {
                    s1 = nextLine[0];
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                String s2 = "";
                try {
                    s2 = nextLine[1];
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                String s3 = "";
                try {
                    s3 = nextLine[2];
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

                if (s1.equals("")) {
                    a = 0l;
                } else {
                    a = Long.valueOf(s1);
                }

                if (s2.equals("")) {
                    b = 0l;
                } else {
                    b = Long.valueOf(s2);
                }

                if (s3.equals("")) {
                    c = 0l;
                } else {
                    c = Long.valueOf(s3);
                }
                /*try {
                    a = Long.valueOf(nextLine[0]);
                } catch (Exception e) {
                    a=0l;
                }
                try {
                    b = Long.valueOf(nextLine[1]);
                } catch (Exception e) {
                    b=0l;
                }
                try {
                    c = Long.valueOf(nextLine[2]);
                } catch (Exception e) {
                    c=0l;
                }*/
                TripleLong trpLng = new TripleLong(a, b, c);
                cmp.add(trpLng);
                i++;
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(cmp.getGroupedUniqueElements().getGroups(),
                (o1, o2) -> o2.getTripleLongArrayList().size() - o1.getTripleLongArrayList().size());
        long finish = System.currentTimeMillis();
        float timeConsumedSec = (finish - start) / 1000F;
        System.out.println("Execution time: " + timeConsumedSec + " seconds");
    }
}