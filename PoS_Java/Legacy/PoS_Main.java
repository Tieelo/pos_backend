
import java.util.Scanner;
public class PoS_Main {

    public static final int maxDrinkItems = 16; //idealerweise wird vor Programm Beginn abgefragt wie viele Zeilen die Datenbank hat und anschließen der Wert aller Zeilen als Wert gesetzt
    public static final int boughtItems = 10; //willkürlich, kann erhöht werden
    static String name;
    static double amount;
    private static Items[] drinkItems = new Items[maxDrinkItems]; //array für das Inventar
    //private static Items[] drinkItems = new Items[drinkItems.length]; // Zwischenspeicher für später
    private static String[] kaufen = new String[boughtItems]; //array für den Einkauf/ dient zwischenzeitlich auch als Prüfung
    private static int totalDrinkItems = 0;
    static double totalPrice = 0;
    static int countOfItems = 0; //Laufvariable die zur Iteration durch das Array funktioniert und am Ende die Menge der Artikel selbst ausgibt

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        String input;

        PoS_Main inventory = new PoS_Main();
// hier sollten sich die Items aus der Datenbank geholt werden, anpassung der super klasse evtl. notwendig, vorerst manuelle Daten
        inventory.addDrinks(new Wein("Grauburgunder", "l", 5.50, 20));
        inventory.addDrinks(new Wein("Chardonnay", "l", 5.90, 25));
        inventory.addDrinks(new Wein("Muskateller", "l", 6.20, 10));
        inventory.addDrinks(new Alkoholfrei("Wasser", "l", 2.50, 126));
        inventory.addDrinks(new Alkoholfrei("Cola", "l", 2.90, 36));
        inventory.addDrinks(new Alkoholfrei("Apfelschorle", "l", 2.90, 84));

        char exit = 'j';// while Schleife um das Programm immer wieder von vorne starten zu lassen. Lässt sich sicher eleganter lösen, Lösung noch nicht bekannt
        while (exit == 'j') {
            System.out.printf("Gib '<Getränk> <Menge>' ein, die verkauft werden soll gefolgt von 'Enter'\n" +
                    "Wenn du fertig bist, schreibe 'fertig' um den Einkauf zu beenden \n");
            inventory.printInventory();

            countOfItems = 0; //Setzen der Laufvariable auf Ursprungswert
            while (!(input = in.nextLine()).equals("fertig")) {

                String[] tokens = input.split(" "); //Auftrennung des Strings in einzelne Strings zur weiterverarbeitung

                if (tokens.length > 1) {//Umgeht eine Exception bei Ausbruch durch 'fertig'
                    name = tokens[0];
                    tokens[1] = tokens[1].replaceAll(",", ".");
                    amount = Double.parseDouble(tokens[1]);
                    kaufen[countOfItems] = String.format("%-19s %.2f€ %-12s %.2f",name,inventory.drinkItems[countOfItems].getPrice(),"", amount );
                    //test ob das eingegebene sich im Inventar befindet
                }

                for (int count = 0; count < inventory.totalDrinkItems; count++) {

                    if (inventory.drinkItems[count].getName().equals(name)) {

                        inventory.drinkItems[count].decreaseStock(amount);
                        totalPrice += amount * inventory.drinkItems[count].getPrice();
                        break;
                    }
                }
                countOfItems++;

            }
            System.out.printf("Die Gesamtkosten betragen: %.2f \n", totalPrice);
            System.out.println("Wollen sie den Einkauf tätigen? (J/N) ");

            if (stringToChar() == 'j') {
                System.out.println("\n Inventar wurde aktualisiert"); // hier würde dann der neue Wert in die Datenbank geschrieben
                System.out.println("~~~~~~~~~~~~~~~~~");
                inventory.printInventory();

                System.out.println("\n Möchten sie eine Quittung haben? (j/n)");
                if (stringToChar() == 'j') {
                    printReceipt();
                }
            } else {
                System.out.println("Inventar wurde nicht belastet"); //hier müsste der gesamte Vorgang verworfen werden
            }
                System.out.println("Nächster kauf? (j/n)");
                exit = stringToChar();
            }
        }

    public static char stringToChar(){
        Scanner in = new Scanner(System.in);
        String charInput = in.next();
        charInput = charInput.toLowerCase();
        char cInput = charInput.charAt(0);
        return cInput;
    }
    public boolean addDrinks (Items d){
        if (totalDrinkItems >= maxDrinkItems) {
            return false;
        } else {
            drinkItems[totalDrinkItems] = d;
            totalDrinkItems++;
            return true;
        }
    }
    public static void printInventory () {
        for (int i = 0; i < totalDrinkItems; i++) {
            System.out.println(drinkItems[i].toString());
        }
    }
    public static void printReceipt(){
        System.out.println("\n\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("           		   Super Bar\n");
        System.out.println("                   Quittung");
        System.out.println("                  ~~~~~~~~~\n");
        System.out.println("Artikel        "+"Preis pro Einheit       "+"Menge");
        System.out.println("______________________________________________\n");
        for(int i = 0; i < countOfItems; i++) {
            System.out.println(kaufen[i]);
            }
        System.out.println("\nMenge der Artikel insgesamt : "+countOfItems);
        System.out.printf ("\nGesamtbetrag: %.2f€ \n", totalPrice);
        System.out.println("\n               Ende der Quittung");
        System.out.println("\nVielen Dank für Ihren Besuch");
        System.out.println("             Kommen Sie gerne wieder");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//    public static void printTemporaryInventory () {
//        for (int i = 0; i < totalDrinkItems; i++) {
//            System.out.println(tempItems[i].toString());
//        }
//    }
//    public static Drinks[] copyObjectArray(Drinks[] source){
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(source);
//        Drinks[] dest = gson.fromJson(jsonString, Drinks[].class);
//        return dest;
//    }
}

