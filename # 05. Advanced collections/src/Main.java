import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] wordSet = new String[]{"lady", "contribution", "physics", "employment", "lady", "efficiency", "beer", "river", "painting", "painting", "physics", "beer"};

        System.out.println(findUniqueWords(wordSet));
        System.out.println(wordOccurrenceCounter(wordSet));

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addContact("Hennie", "+1 458-572-7749");
        phoneBook.addContact("Justin", "+1 582-682-5867");
        phoneBook.addContact("Kristy", "+1 312-498-6216");
        phoneBook.addContact("Jeri-ca", "+1 225-210-0095");
        phoneBook.addContact("Justin", "+1 208-859-0848");

        System.out.println(phoneBook.findNameByNumber("+1 458-572-7749").toString());
        System.out.println(phoneBook.findNumberByName("Justin").toString());

    }

    static HashSet<String> findUniqueWords(String[] incomeArray) {
        return new HashSet<String>(Arrays.asList(incomeArray));
    }

    static HashMap<String, Integer> wordOccurrenceCounter(String[] incomeArray) {
        HashMap<String, Integer> targetHashMap = new HashMap<>();
        for (String str : new HashSet<String>(findUniqueWords(incomeArray))) {
            targetHashMap.put(str, Collections.frequency(List.of(incomeArray), str));
        }
        return targetHashMap;
    }
}
