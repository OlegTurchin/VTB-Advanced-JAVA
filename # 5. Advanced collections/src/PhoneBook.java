import java.util.ArrayList;
public class PhoneBook {
    ArrayList<Contact> phoneBook = new ArrayList<>();

    void addContact(String name, String phoneNumber){
        phoneBook.add(new Contact(name, phoneNumber));
    }

    ArrayList<Contact> findNameByNumber(String number){
        ArrayList<Contact> targetArray = new ArrayList<>();
        for (Contact str: phoneBook) {
            if (str.phoneNumber.equals(number)) targetArray.add(new Contact(str.name, str.phoneNumber));
        } return targetArray;
    }
    ArrayList<Contact> findNumberByName(String name){
        ArrayList<Contact> targetArray = new ArrayList<>();
        for (Contact str: phoneBook) {
            if (str.name.equals(name)) targetArray.add(new Contact(str.name, str.phoneNumber));
        } return targetArray;
    }
}

class Contact{
    String name;
    String phoneNumber;

    Contact(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString(){
        return (this.name + ": " + this.phoneNumber);
    }
}

//public class PhoneBook {
//    HashMap<String, String> phoneBook = new HashMap<>();
//
//    void addContact(String name, String number) {
//        phoneBook.put(number, name);
//    }
//
//    HashMap<String, String> findNameByNumber(String number) {
//        HashMap<String, String> targetHashMap = new HashMap<>();
//        for (String key : phoneBook.keySet()) {
//            if (key.equals(number)) targetHashMap.put(key, phoneBook.get(key));
//        } return targetHashMap;
//    }
//
//    private HashMap<String, String> findNameByNumber(String number, HashMap<String, String> incomeHashMap) {
//        HashMap<String, String> targetHashMap = new HashMap<>();
//        for (String key : incomeHashMap.keySet()) {
//            if (key.equals(number)) targetHashMap.put(key, incomeHashMap.get(key));
//        } return targetHashMap;
//    }
//
//    HashMap<String, String> findNumberByName(String name) {
//        HashMap<String, String> invertedHashMap = new HashMap<>();
//        for (String key : phoneBook.keySet()) {
//            invertedHashMap.put(phoneBook.get(key), key);
//        } return findNameByNumber(name, invertedHashMap);
//    }
//}