package controller;

public class SellingController {
    private PrintItemController printItemController;
    public SellingController(){
        printItemController = new PrintItemController();
    }
    public void startSale(){
        printItemController.fetchAndPrintGroups();

    }
}
