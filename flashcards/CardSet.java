package flashcards;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.io.File;

public class CardSet {
    private final Scanner sc = new Scanner(System.in);
    private LinkedHashMap<String, String> cards = new LinkedHashMap<>();
    private HashMap<String, Integer> hardest = new HashMap<>();
    private Log log;

    public CardSet(Log log) {
        this.log = log;
    }


    public static String getKey(LinkedHashMap<String, String> map , String def) {
        for (var entry : map.entrySet()) {
            if (entry.getValue().equals(def)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void add() {
        String term;
        String def;
        boolean flag = true;

        String s = "The card:";
        System.out.println(s);
        log.saveLog(s);
        term = sc.nextLine();
        log.saveLog(term);



        if (cards.containsKey(term)) {
            s = "The card " + "\"" + term + "\"" + " already exists.";
            System.out.println(s);
            log.saveLog(s);
            flag = false;
        }

        if (flag) {
            s = "The definition of the card:";
            System.out.println(s);
            log.saveLog(s);
            def = sc.nextLine();
            log.saveLog(def);
            if (cards.containsValue(def)) {
                s = "The definition " +  "\"" + def + "\"" +" already exists.";
            } else {
                cards.put(term, def);
                hardest.put(term, 0);
                s = String.format("The pair (\"%s\":\"%s\") has been added.\n",term,def);
            }
            System.out.println(s);
            log.saveLog(s);
        }

    }

    public void remove() {
        String term;

        String s = "Which card?";
        System.out.println(s);
        log.saveLog(s);
        term = sc.nextLine();
        log.saveLog(term);

        if (cards.remove(term) == null) {
            s = String.format("Can't remove \"%s\": there is no such card.\n", term);
        } else {
            hardest.remove(term);
            s = "The card has been removed.";
        }
        System.out.println(s);
        log.saveLog(s);
    }

    public void imp(String fileName) {
        String term;
        String def;
        String mistakes;
        int count = 0;
        String s;
        File file = new File(fileName);
        try(Scanner reader = new Scanner(file)) {
            while (reader.hasNext()) {
                term = reader.nextLine();
                def = reader.nextLine();
                mistakes = reader.nextLine();
                System.out.println(mistakes);
                cards.put(term, def);
                try {
                    hardest.put(term, Integer.parseInt(mistakes));
                } catch (NumberFormatException e) {
                    hardest.put(term, 0);
                }

                count++;
            }
            s = String.format("%d cards have been loaded.\n",count);
            System.out.println(s);
            log.saveLog(s);
        } catch (FileNotFoundException e) {
            s = "File not found.";
            System.out.println(s);
            log.saveLog(s);
        }
    }

    public void export(String fileName) {
        int count = 0;
        String s;
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try( FileWriter writer = new FileWriter(file)) {
            for (var entry : cards.entrySet()) {
                writer.write(entry.getKey());
                writer.write("\n");
                writer.write(entry.getValue());
                writer.write("\n");
                int x = hardest.get(entry.getKey());
                writer.write(String.valueOf(x));
                writer.write("\n");
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        s = String.format("%d cards have been saved.\n",count);
        System.out.println(s);
        log.saveLog(s);
    }


    public void imp() {
        String term;
        String def;
        String mistakes;
        int count = 0;
        String s = "File name:";
        System.out.println(s);
        log.saveLog(s);
        String fileName = sc.nextLine();
        log.saveLog(fileName);
        File file = new File(fileName);
        try(Scanner reader = new Scanner(file)) {
            while (reader.hasNext()) {
                term = reader.nextLine();
                def = reader.nextLine();
                mistakes = reader.nextLine();
                System.out.println(mistakes);
                cards.put(term, def);
                try {
                    hardest.put(term, Integer.parseInt(mistakes));
                } catch (NumberFormatException e) {
                    hardest.put(term, 0);
                }

                count++;
            }
            s = String.format("%d cards have been loaded.\n",count);
            System.out.println(s);
            log.saveLog(s);
        } catch (FileNotFoundException e) {
            s = "File not found.";
            System.out.println(s);
            log.saveLog(s);
        }
    }

    public void export() {
        int count = 0;
        String s = "File name:";
        System.out.println(s);
        log.saveLog(s);
        String fileName = sc.nextLine();
        log.saveLog(fileName);
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try( FileWriter writer = new FileWriter(file)) {
            for (var entry : cards.entrySet()) {
                writer.write(entry.getKey());
                writer.write("\n");
                writer.write(entry.getValue());
                writer.write("\n");
                int x = hardest.get(entry.getKey());
                writer.write(String.valueOf(x));
                writer.write("\n");
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        s = String.format("%d cards have been saved.\n",count);
        System.out.println(s);
        log.saveLog(s);
    }

    public void ask() {
        new Ask(cards, hardest, log);
    }

    public void close() {
        sc.close();
    }

    public void reset() {
        hardest.clear();
        for (var entry : cards.entrySet()) {
            hardest.put(entry.getKey(), 0);
        }
        String s = "Card statistics have been reset.";
        System.out.println(s);
        log.saveLog(s);
    }

    public void hardestCard() {
        int count = 0;
        int max = Integer.MIN_VALUE;
        ArrayList<String> hardestCards = new ArrayList<>();

        for (var entry : hardest.entrySet()) {
            if (entry.getValue() >= max) {
                max = entry.getValue();
            }
        }

        for (var entry : hardest.entrySet()) {
            if (entry.getValue() == max) {
                count++;
                hardestCards.add(entry.getKey());
            }
        }

        if (max == 0) {
            count = 0;
        }
        String s;
        switch (count) {
            case 0 :
                s = "There are no cards with errors.";
                System.out.println(s);
                log.saveLog(s);
                break;

            case 1 :
                s = String.format("The hardest card is \"%s\". You have %d errors answering it.", hardestCards.get(0), max);
                System.out.println(s);
                log.saveLog(s);
                break;

            default:
                String cards = "";
                for (int i = 0; i < count ; i++) {
                    if (i <  count - 1 ) {
                        cards += "\"" + hardestCards.get(i) + "\", ";
                    } else {
                        cards += "\"" + hardestCards.get(i) + "\". ";
                    }
                }

                s = "The hardest cards are " + cards + "You have " + max + " errors answering them.";
                System.out.println(s);
                log.saveLog(s);

        }

    }
}
