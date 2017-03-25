import model.TripleLong;
import org.apache.commons.lang3.StringUtils;
import process.Processor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Tom on 25.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        Processor processor = new Processor();
        //Processor cmp = new Processor(new TripleLong(1l,2l,3l));
        int i = 1;
        //TripleLong tpl2 = new TripleLong(4l,4l,3l);
        //cmp.Add(tpl2);
        //TripleLong tpl3 = new TripleLong(5l,6l,7l);
        //cmp.Add(tpl3);
        //TripleLong tpl4 = new TripleLong(8l,8l,7l);
        //cmp.Add(tpl4);
        //TripleLong tp5 = new TripleLong(9l,6l,3l);
        //cmp.Add(tp5);
        ArrayList<Integer> invalidIndexes = new ArrayList<>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("Lng.csv"), "Cp1252"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = br.readLine()) != null) {
                line=line.replaceAll("\"","");
                int count = StringUtils.countMatches(line, ";");
                List<String> items=new ArrayList<>();
                items.addAll(Arrays.asList(line.split(";")));
                if (items.size() == 2 && count == 2)
                    items.add("");
                Long a,b,c;
                if (items.size() == 3){
                    String s1 = items.get(0);
                    String s2 = items.get(1);
                    String s3 = items.get(2);

                    if (s1.equals(""))
                        a=0l;
                    else {
                        try {
                            a = Long.valueOf(s1);
                        }
                        catch (Exception e){
                            i++;
                            invalidIndexes.add(i);
                            continue;
                        }
                    }

                    if (s2.equals(""))
                        b=0l;
                    else {
                        try {
                            b = Long.valueOf(s2);
                        }
                        catch (Exception e){
                            i++;
                            invalidIndexes.add(i);
                            continue;
                        }
                    }

                    if (s3.equals(""))
                        c=0l;
                    else {
                        try {
                            c = Long.valueOf(s3);
                        }
                        catch (Exception e){
                            i++;
                            invalidIndexes.add(i);
                            continue;
                        }
                    }

                    TripleLong trpLng = new TripleLong(a, b, c);
                    processor.Add(trpLng);
                    //System.out.println(i);
                    i++;
                }
                else {
                    i++;
                    invalidIndexes.add(i);
                    continue;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(processor.getListOfGroupedElements().getGroups(),
                (o1, o2) -> o2.getTripleLongArrayList().Size() - o1.getTripleLongArrayList().Size());
        long finish = System.currentTimeMillis();
        float timeConsumedSec = (finish - start)/1000F;
        System.out.println("Execution time: " + timeConsumedSec + " seconds");
    }
}
