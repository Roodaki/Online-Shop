import java.util.ArrayList;

public class Account {
    private String name;
    private String phoneNumber;
    private String username;
    private String password;
    private AccountType accountType;
    private ArrayList<ShoppingList> purchasedShoppingListsArrayList;

    public Account(String name, String phoneNumber, String username, String password, AccountType accountType) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setUsername(username);
        setPassword(password);
        setAccountType(accountType);
        setPurchasedShoppingListsArrayList(new ArrayList<>());
    }

    public String getPurchasedShoppingListsData() {

        String allPurchasedShoppingListsData = "All Purchased ShoppingLists of This Account:";

        int counterShoppingLists = 1;
        for (ShoppingList shoppingList : getPurchasedShoppingListsArrayList()) {
            allPurchasedShoppingListsData += "\n" + counterShoppingLists + ".\n" + "\tID: " + shoppingList.getID()
                    + "\n\tNumber of Purchased Products: " + shoppingList.getPurchasedProductsCount()
                    + "\n\tTotal Price: " + shoppingList.getTotalPrice() + "\n\tShipping Status: "
                    + shoppingList.getShippingStatus() + "\n\tPurchased Products\n{\n";

            int counterProducts = 1;
            for (Product product : shoppingList.getPurchasedProductsArrayList()) {
                allPurchasedShoppingListsData += "\t" + counterProducts + "." + "\n\tName: " + product.getName()
                        + "\n\tPrice: " + product.getPrice() + "\n";
                counterProducts++;
            }

            allPurchasedShoppingListsData += "}\n";

            counterShoppingLists++;
        }

        return allPurchasedShoppingListsData;
    }

    public void AddNewShopingListToPurchasedShoppingListsArrayList(ShoppingList newShoppingList) {
        getPurchasedShoppingListsArrayList().add(newShoppingList);
    }

    public static int getAccountIndexUsingUsername(String username, ArrayList<Account> AccountsDataBaseArrayList) {

        int accountIndex;

        for (accountIndex = 0; accountIndex < AccountsDataBaseArrayList.size(); accountIndex++)
            if (AccountsDataBaseArrayList.get(accountIndex).getUsername().equals(username))
                return accountIndex;

        return accountIndex;
    }

    public static String getAccountData(String username, ArrayList<Account> AccountsDataBaseArrayList) {
        int accountIndex = Account.getAccountIndexUsingUsername(username, AccountsDataBaseArrayList);
        return "Name: " + AccountsDataBaseArrayList.get(accountIndex).getName() + "\nPhoneNumber: "
                + AccountsDataBaseArrayList.get(accountIndex).getPhoneNumber() + "\nUsername: "
                + AccountsDataBaseArrayList.get(accountIndex).getUsername();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setPurchasedShoppingListsArrayList(ArrayList<ShoppingList> purchasedShoppingListsArrayList) {
        this.purchasedShoppingListsArrayList = purchasedShoppingListsArrayList;
    }

    public ArrayList<ShoppingList> getPurchasedShoppingListsArrayList() {
        return purchasedShoppingListsArrayList;
    }

}