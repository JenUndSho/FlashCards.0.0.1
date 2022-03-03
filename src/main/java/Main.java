import java.util.*;


public class Main {
    public static void main(String[] args) {
        FlashCards fc = new FlashCards();
        Scanner scan = new Scanner(System.in);
        String fileOutput = null;

        for (int i = 0; i< args.length; i++) {
            if ("-import".equals(args[i])) {
                fc.importCards(args[i+1]);
            }

            if ("-export".equals(args[i])) {
                fileOutput = args[i+1];
            }

        }

        do {
            fc.write(Constants.MENU);
            String command = scan.nextLine();
            fc.logs.add(command);
            switch (command) {
                case "add":
                    fc.addCard();
                    break;
                case "remove":
                    fc.removeCard();
                    break;
                case "import":
                    fc.importCards();
                    break;
                case "export":
                    fc.exportCard();
                    break;
                case "ask":
                    fc.askCard();
                    break;
                case "log":
                    fc.log();
                    break;
                case "reset stats":
                    fc.resetStats();
                    break;
                case "hardest card":
                    fc.hardestCard();
                    break;
                case "exit":
                    if (fileOutput != null) {
                        fc.exportCard(fileOutput);
                    }
                    fc.write(Constants.BYE);
                    return;
            }
        } while (true);
    }
}



