package utils;

import model.TripleLong;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tom on 26.03.2017.
 */
public class SmallParser {

    public static TripleLong parse(String stringToParse) throws InvalidStringException {
        String line = stringToParse.replaceAll("\"", "");
        int count = StringUtils.countMatches(line, ";");
        List<String> items = new ArrayList<>();
        items.addAll(Arrays.asList(line.split(";")));
        if (items.size() < 3 && count == 2) {
            switch (items.size()) {
                case 0:
                    items.add("");
                    items.add("");
                    items.add("");
                    break;
                case 1:
                    items.add("");
                    items.add("");
                    break;
                case 2:
                    items.add("");
                    break;
            }
        }
        Long a=null, b=null, c=null;
        if (items.size() == 3) {
            String s1 = items.get(0);
            String s2 = items.get(1);
            String s3 = items.get(2);

            if (s1.equals("")){}
                //a = 0l;
            else {
                try {
                    a = Long.valueOf(s1);
                } catch (Exception e) {
                    throw new InvalidStringException(line);
                }
            }

            if (s2.equals("")){}
                //b = 0l;
            else {
                try {
                    b = Long.valueOf(s2);
                } catch (Exception e) {
                    throw new InvalidStringException(line);
                }
            }

            if (s3.equals("")){}
                //c = 0l;
            else {
                try {
                    c = Long.valueOf(s3);
                } catch (Exception e) {
                    throw new InvalidStringException(line);
                }
            }
            return new TripleLong(a, b, c);
        } else
            throw new InvalidStringException(line);
    }

}
