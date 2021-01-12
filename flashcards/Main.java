package flashcards;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input;
        Log log = new Log();
        CardSet cards = new CardSet(log);
        boolean loop  = true;
        String exportFileName = " ";

        if (args.length == 2) {
            if (args[0].equals("-import")) {
                cards.imp(args[1]);
            } else {
                exportFileName = args[1];
            }
        } else if (args.length == 4) {
            if (args[0].equals("-import")) {
                cards.imp(args[1]);
                exportFileName = args[3];
            } else {
                cards.imp(args[3]);
                exportFileName = args[1];
            }
        }
        String s = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
        while (loop) {
            System.out.println(s);
            log.saveLog(s);
            input = sc.nextLine();
            log.saveLog(input);

            switch (input) {
                case "add" :
                    cards.add();
                    break;
                case "remove" :
                    cards.remove();
                    break;
                case "import" :
                    cards.imp();
                    break;
                case "export" :
                    cards.export();
                    break;


                case "ask" :
                    cards.ask();
                    break;
                case "exit" :
                    System.out.println("Bye bye!");
                    loop = false;
                    sc.close();
                    cards.close();
                    if (!exportFileName.equals(" ")){
                        cards.export(exportFileName);
                    }

                    break;
                case "log" :
                    log.saveLogToFile();
                    break;
                case "hardest card" :
                    cards.hardestCard();
                    break;
                case "reset stats" :
                    cards.reset();
                    break;
                default:
                    break;

            }

        }


    }
       

}




