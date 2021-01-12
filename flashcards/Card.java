package flashcards;

import java.util.Scanner;

public class Card {
    final private String term;
    final private String def;

    public Card(String term, String def) {
        this.term = term;
        this.def = def;
    }



    String getTerm() {
        return term;
    }

    String getDef() {
        return def;
    }


}