import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class SignUp_Page {

        public static void SignUpBuyerAccount(ArrayList<Account> buyersAccountsDataBaseArrayList,
                        HashMap<String, String> usernameAndPasswordsOfBuyerAccountsHashMap)
                        throws NoSuchAlgorithmException {

                String name = JOptionPane.showInputDialog(null, "Please Enter Your Full Name: ",
                                "BuyerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE);

                String phoneNumber = JOptionPane.showInputDialog(null, "Please Enter Your PhoneNumber: ",
                                "BuyerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE);

                String username = JOptionPane.showInputDialog(null, "Please Enter a Username for Your Account: ",
                                "BuyerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE).toLowerCase();

                while (usernameAndPasswordsOfBuyerAccountsHashMap.containsKey(username)) {
                        JOptionPane.showMessageDialog(null, "Entered Username Was Already Used By Another Buyer.",
                                        "BuyerAccount SignUp Page", JOptionPane.ERROR_MESSAGE);

                        username = JOptionPane
                                        .showInputDialog(null, "Please Enter an UnUsed Username for Your Account: ",
                                                        "BuyerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE)
                                        .toLowerCase();
                }

                String password = JOptionPane.showInputDialog(null, "Please Enter a Password: ",
                                "BuyerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE);

                String passwordCheck = JOptionPane.showInputDialog(null,
                                "Please ReEnter Your Password to Double Check:", "BuyerAccount SignUp Page",
                                JOptionPane.QUESTION_MESSAGE);

                if (password.equals(passwordCheck)) {
                        JOptionPane.showMessageDialog(null,
                                        "SignUp Process Succeeded.\nYour Account Was Successfully Registered.\n",
                                        "BuyerAccount SignUp Page", JOptionPane.INFORMATION_MESSAGE);

                        Account newAccount = new Account(name, phoneNumber, username,
                                        PasswordSecurity.toHexString(PasswordSecurity.getSHA(password)),
                                        AccountType.BUYER);

                        buyersAccountsDataBaseArrayList.add(newAccount);
                        usernameAndPasswordsOfBuyerAccountsHashMap.put(username,
                                        PasswordSecurity.toHexString(PasswordSecurity.getSHA(password)));
                } else
                        JOptionPane.showMessageDialog(null,
                                        "Second Entered Password Doesn't Math the First Entered Password.\nAccount Registration Failed!",
                                        "BuyerAccount SignUp Page", JOptionPane.ERROR_MESSAGE);

        }

        public static void SignUpSellerAccount(ArrayList<Account> sellersAccountsDataBaseArrayList,
                        HashMap<String, String> usernameAndPasswordsOfSellerAccountsHashMap)
                        throws NoSuchAlgorithmException {

                String name = JOptionPane.showInputDialog(null, "Please Enter Your Full Name: ",
                                "SellerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE);

                String phoneNumber = JOptionPane.showInputDialog(null, "Please Enter Your PhoneNumber: ",
                                "SellerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE);

                String username = JOptionPane
                                .showInputDialog(null, "Please Enter a Username for Your Account: ",
                                                "SellerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE)
                                .toLowerCase();

                while (usernameAndPasswordsOfSellerAccountsHashMap.containsKey(username)) {
                        JOptionPane.showMessageDialog(null,
                                        "Entered Username Was Already Used By Another Seller. Try Again.\n",
                                        "SellerAccount SignUp Page", JOptionPane.ERROR_MESSAGE);

                        username = JOptionPane
                                        .showInputDialog(null, "Please Enter an UnUsed Username for Your Account: ",
                                                        "SellerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE)
                                        .toLowerCase();
                }

                String password = JOptionPane.showInputDialog(null, "Please Enter a Password: ",
                                "SellerAccount SignUp Page", JOptionPane.QUESTION_MESSAGE);

                String passwordCheck = JOptionPane.showInputDialog(null,
                                "Please ReEnter Your Password to Double Check:", "SellerAccount SignUp Page",
                                JOptionPane.QUESTION_MESSAGE);

                if (password.equals(passwordCheck)) {
                        JOptionPane.showMessageDialog(null,
                                        "SignUp Process Succeeded.\nYour Account Was Successfully Registered.\n",
                                        "SellerAccount SignUp Page", JOptionPane.INFORMATION_MESSAGE);

                        Account newAccount = new Account(name, phoneNumber, username,
                                        PasswordSecurity.toHexString(PasswordSecurity.getSHA(password)),
                                        AccountType.BUYER);

                        sellersAccountsDataBaseArrayList.add(newAccount);
                        usernameAndPasswordsOfSellerAccountsHashMap.put(username,
                                        PasswordSecurity.toHexString(PasswordSecurity.getSHA(password)));
                } else
                        JOptionPane.showMessageDialog(null,
                                        "Second Entered Password Doesn't Math the First Entered Password.\nAccount Registration Failed!",
                                        "SellerAccount SignUp Page", JOptionPane.ERROR_MESSAGE);
        }

}