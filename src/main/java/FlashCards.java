import com.sun.tools.internal.jxc.ap.Const;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FlashCards {
    private Map<String, String> cards;
    private Scanner s;
    public List<String> logs;
    private Map<String, Integer> mistakeCounter;

    public Map<String, String> getCards(){
        return this.cards;
    }

    public FlashCards() {
        cards = new LinkedHashMap<String, String>();
        s = new Scanner(System.in);
        logs = new ArrayList<>();
        mistakeCounter = new LinkedHashMap<String, Integer>();
    }

    public void addCard() {
        String term = null;
        String definition = null;

        System.out.println(Constants.THE_CARD);
        logs.add(Constants.THE_CARD);
        term = s.nextLine();
        logs.add(term);

        if (cards.containsKey(term)) {
            write(Constants.CARD_ALREADY_EXIST.apply(term));
            return;
        }

        write(Constants.THE_DEFINITION);
        definition = s.nextLine();
        logs.add(definition);

        if (cards.containsValue(definition)) {
            write(Constants.DEFINITION_ALREADY_EXIST.apply(definition));
            return;
        }

        write(Constants.PAIR_ADDED.apply(term, definition));
        cards.put(term, definition);
        mistakeCounter.put(term, 0);
    }

    public void removeCard() {
        write(Constants.WHICH_CARD);
        String term = s.nextLine();
        logs.add(term);

        if (!cards.containsKey(term)) {
            write(Constants.CANT_REMOVE.apply(term));
            return;
        }

        write(Constants.CARD_REMOVED);
        cards.remove(term);
        mistakeCounter.remove(term);
    }

    public void printAllCards() {
        System.out.println("______________");
        for (String name: cards.keySet()) {
            String key = name;
            String value = cards.get(name);
            System.out.println(key + " " + value);
        }
        System.out.println("______________");
    }

    public void printAllMistakes() {
        System.out.println("______________");
        for (String name: mistakeCounter.keySet()) {
            String key = name;
            Integer value = mistakeCounter.get(name);
            System.out.println(key + " " + value);
        }
        System.out.println("______________");
    }

    public void importCards() {
        System.out.println(Constants.FILE_NAME);
        logs.add(Constants.FILE_NAME);
        String fileName = s.nextLine();
        logs.add(fileName);
        String term = null;
        String definition = null;
        int mistakes = 0;
        int count = 0;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine())
            {

                term = sc.nextLine();
                definition = sc.nextLine();
                mistakes = Integer.parseInt(sc.nextLine());
                cards.put(term, definition);
                mistakeCounter.put(term, mistakes);
                count++;
            }

            write(Constants.CARDS_LOADED.apply(count));
            sc.close();
        }
        catch(IOException e) {
            write(Constants.FILE_NOT_FOUND);
        }
    }

    public void importCards(String fileName) {
        String term = null;
        String definition = null;
        int mistakes = 0;
        int count = 0;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine())
            {
                term = sc.nextLine();
                definition = sc.nextLine();
                mistakes = Integer.parseInt(sc.nextLine());
                cards.put(term, definition);
                mistakeCounter.put(term, mistakes);
                count++;
            }
            write(Constants.CARDS_LOADED.apply(count));
            sc.close();
        }
        catch(IOException e) {
            write(Constants.FILE_NOT_FOUND);
        }
    }

    public void exportCard() {
        System.out.println(Constants.FILE_NAME);
        logs.add(Constants.FILE_NAME);
        String fileName = s.nextLine();
        logs.add(fileName);

        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);

            for (String name: cards.keySet()) {
                writer.write(name + "\n");
                writer.write(cards.get(name) + "\n");
                writer.write(mistakeCounter.get(name) + "\n");
            }
            write(Constants.CARDS_SAVED.apply(cards.size()));
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void exportCard(String fileName) {
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);

            for (String name: cards.keySet()) {
                writer.write(name + "\n");
                writer.write(cards.get(name) + "\n");
                writer.write(mistakeCounter.get(name) + "\n");
            }
            write(Constants.CARDS_SAVED.apply(cards.size()));
            writer.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void askCard() {
        String answer = null;

        write(Constants.HOW_MANY_TIMES_TO_ASK);
        int n = Integer.parseInt(s.nextLine());
        logs.add(String.valueOf(n));
        int i = 0;
        while (true) {

            for (Map.Entry<String, String> e : cards.entrySet()) {
                i++;
                write(Constants.PRINT_DEFINITION_OF.apply(e.getKey()));
                answer = s.nextLine();
                logs.add(answer);
                if (answer.equals(e.getValue())) {
                    write(Constants.CORRECT);
                }
                else if (cards.containsValue(answer)) {
                    for (Map.Entry<String, String> localEntry : cards.entrySet()) {
                        if (answer.equals(localEntry.getValue())) {
                            write(Constants.WRONG_THE_RIGHT_ANSWER_IS_AND_YOUR_ANSWER_IS_RIGHT_FOR.apply(e.getValue(), localEntry.getKey()));
                            mistakeCounter.put(e.getKey(), mistakeCounter.get(e.getKey()) + 1);
                        }
                    }
                } else {
                    write(Constants.WRONG_THE_RIGHT_ANSWER_IS.apply(e.getValue()));
                    mistakeCounter.put(e.getKey(), mistakeCounter.get(e.getKey()) + 1);
                }

                if (i == n) break;
            }

            if (i == n) break;
        }

        System.out.println("\n");
        logs.add("\n");

    }

    public void log() {
        System.out.println(Constants.FILE_NAME);
        logs.add(Constants.FILE_NAME);
        String fileName = s.nextLine();
        logs.add(fileName);
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);

            for (String str: logs) {
                writer.write(str + "\n");
            }
            writer.write(Constants.LOG_SAVED);
//            System.out.println("The log has been saved.\n");
//            logs.add("The log has been saved.\n");
            write(Constants.LOG_SAVED);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetStats() {
        mistakeCounter.replaceAll((k, v) -> 0);
        write(Constants.STATISTICS_RESET);
    }

    public void hardestCard() {
        if (mistakeCounter.size() == 0) {
            write(Constants.NO_CARDS_WITH_ERRORS);
            return;
        }
        StringBuilder ss = new StringBuilder();
        int max = Collections.max(mistakeCounter.values());
        List<String> marks = mistakeCounter.entrySet().stream()
                .filter(x -> x.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (max == 0) {
            write(Constants.NO_CARDS_WITH_ERRORS);
        } else if (marks.size() == 1) {
            write(Constants.HARDEST_CARD.apply(marks.get(0), max));
        } else {
            for (int i = 0; i < marks.size(); i++) {
                ss.append("\"").append(marks.get(i)).append("\"");
                ss.append((i == marks.size()-1) ? "." : ",");
            }
            write(Constants.HARDEST_CARD.apply(marks.get(0), max));
        }
    }

    public void write(String message) {
        System.out.println(message);
        logs.add(message);
    }
}
