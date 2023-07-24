package view.terminal;

public class SellingView {
    public void chooseItems(){
        System.out.print(
                """
                            Gib '<Getränk> <Menge>' ein, die verkauft werden soll gefolgt von 'Enter'
                            Wähle die Artikel Gruppe neu mit 'gruppen'
                            Wenn du fertig bist, schreibe 'fertig' um den Einkauf zu beenden\s
                            """
        );
    }
}
