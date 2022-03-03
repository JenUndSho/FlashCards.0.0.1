import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static java.lang.String.format;

public final class Constants {
    public static final String BYE = "Bye bye!";
    public static final String THE_CARD = "The card:";
    public static final String FILE_NAME = "File name:";
    public static final String MENU = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
    public static final UnaryOperator<String> CARD_ALREADY_EXIST = a -> format("The card \"%s\" already exists.\n", a);
    public static final String THE_DEFINITION = "The definition of the card";
    public static final UnaryOperator<String> DEFINITION_ALREADY_EXIST = a -> format("The definition \"%s\" already exists.\n", a);
    public static final BinaryOperator<String> PAIR_ADDED = (a, b) -> format("The pair (\"%s\":\"%s\") has been added.\n", a, b);
    public static final String WHICH_CARD = "Which card?";
    public static final UnaryOperator<String> CANT_REMOVE = a -> format("Can't remove \"%s\": there is no such card.\n", a);
    public static final String CARD_REMOVED = "The card has been removed.\n";
    public static final Function<Integer, String> CARDS_LOADED = a -> format("%d cards have been loaded.\n", a);
    public static final String FILE_NOT_FOUND = "File not found.\n";
    public static final Function<Integer, String> CARDS_SAVED = a -> format("%d cards have been saved.\n", a);
    public static final String HOW_MANY_TIMES_TO_ASK = "How many times to ask?";
    public static final UnaryOperator<String> PRINT_DEFINITION_OF = a -> format("Print the definition of \"%s\":", a);
    public static final String CORRECT = "Correct!";
    public static final BinaryOperator<String> WRONG_THE_RIGHT_ANSWER_IS_AND_YOUR_ANSWER_IS_RIGHT_FOR = (a, b) -> format("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".", a, b);
    public static final UnaryOperator<String> WRONG_THE_RIGHT_ANSWER_IS = a -> format("Wrong. The right answer is \"%s\".", a);
    public static final String LOG_SAVED = "The log has been saved.\n";
    public static final String STATISTICS_RESET = "Card statistics have been reset.\n";
    public static final String NO_CARDS_WITH_ERRORS = "There are no cards with errors.\n";
    public static final BiFunction<String, Integer, String> HARDEST_CARD = (a, b) -> format("The hardest card is \"%s\". You have %d errors answering it.\n", a, b);
}
