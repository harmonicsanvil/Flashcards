package flashcards;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Ask {
    public Ask(LinkedHashMap<String, String> cards, HashMap<String, Integer> hardest, Log log) {
        Scanner sc = new Scanner(System.in);
        String input;
        String tempKey;
        String s = "How many times to ask:";
        System.out.println(s);
        log.saveLog(s);
        int noOfCards = Integer.parseInt(sc.nextLine());
        log.saveLog(String.format("%d", noOfCards));
        int count = 1;

        for (var entry : cards.entrySet()) {
            s = "Print the definition of " + "\"" + entry.getKey() + "\"" + ":";
            System.out.println(s);
            log.saveLog(s);
            input = sc.nextLine();
            log.saveLog(input);
            if (input.equals(entry.getValue())) {
                s = "Correct!";
            } else {
                tempKey = CardSet.getKey(cards, input);
                if (tempKey != null) {
                    s = "Wrong. The right answer is " + "\"" + entry.getValue() + "\", but your definition is" +
                            " correct for " + "\"" + tempKey + "\".";

                } else {
                    s = "Wrong. The right answer is " + "\"" + entry.getValue() + "\".";
                }
                int x = hardest.get(entry.getKey());
                hardest.put(entry.getKey(), x + 1);
            }
            System.out.println(s);
            log.saveLog(s);
            if (count == noOfCards) {
                break;
            } else {
                count++;
            }
        }
    }

}

