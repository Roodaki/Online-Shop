// Written & Developed by AmirHossein Roodaki (Student No. 9935707)

import java.awt.HeadlessException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class OnlineShop {

	public static String getBuyerAccountsData(ArrayList<Account> buyersAccountsDataBaseArrayList) {

		String allBuyerAccountsData = "All Registered Buyer's Account:\n";

		int counterBuyerAccount = 1;
		for (Account buyerAccount : buyersAccountsDataBaseArrayList) {
			allBuyerAccountsData += counterBuyerAccount + "." + "\n\tFull Name: " + buyerAccount.getName()
					+ "\n\tPhone Number: " + buyerAccount.getPhoneNumber() + "\n\tUsername: "
					+ buyerAccount.getUsername() + "\n";
			counterBuyerAccount++;
		}

		return allBuyerAccountsData;
	}

	public static String getShopInventoryData(ArrayList<Product> productsAvailableInShopInventoryArrayList) {

		String allProductsInShopInventoryData = "\nAll Products Available at Shop:\n";

		int counterProducts = 1;
		for (Product product : productsAvailableInShopInventoryArrayList) {
			allProductsInShopInventoryData += counterProducts + ".\n" + "\tName: " + product.getName()
					+ "\n\tPrice After Discount: $" + product.getPrice() + "\n\tDiscount Amount: "
					+ product.getDiscount() + "%\n";
			counterProducts++;
		}

		return allProductsInShopInventoryData;
	}

	public static boolean haveFoundProductUsingName(String name,
			ArrayList<Product> productsAvailableInShopInventoryArrayList) {

		boolean haveFoundProduct = false;

		for (Product product : productsAvailableInShopInventoryArrayList)
			if (product.getName().equals(name)) {
				haveFoundProduct = true;
				break;
			}

		return haveFoundProduct;

	}

	public static int getProductIndexUsingName(String name,
			ArrayList<Product> productsAvailableInShopInventoryArrayList) {
		int productIndex;

		for (productIndex = 0; productIndex < productsAvailableInShopInventoryArrayList.size(); productIndex++)
			if (productsAvailableInShopInventoryArrayList.get(productIndex).getName().equals(name))
				break;

		return productIndex;
	}

	public static void main(String[] args) throws HeadlessException, NoSuchAlgorithmException {
		// Define 2 ArrayLists to Store Sellers & Buyers Accounts
		ArrayList<Account> sellersAccountsDataBaseArrayList = new ArrayList<>();
		ArrayList<Account> buyersAccountsDataBaseArrayList = new ArrayList<>();
		// Define 2 HashMaps to Store Accounts's Usernames & Password
		HashMap<String, String> usernameAndPasswordsOfBuyerAccountsHashMap = new HashMap<>();
		HashMap<String, String> usernameAndPasswordsOfSellerAccountsHashMap = new HashMap<>();
		// Define an ArrayList to Store Available Products of The Shop
		ArrayList<Product> productsAvailableInShopInventoryArrayList = new ArrayList<>();

		// Welcome Note
		JOptionPane.showMessageDialog(null, "Welcome to the Online Shop Application", "Welcome Note",
				JOptionPane.INFORMATION_MESSAGE);

		boolean IsProgramRunning = true;
		while (IsProgramRunning) {

			// Divide Program Into 2 Parts: 1. Buyer-Side, 2. Seller-Side
			int chosenSideNumber = Integer.parseInt(JOptionPane.showInputDialog(null,
					"Which Side of the Program You'd Like to Use?\n1. Buyer\n2. Seller\n3. Exit the Program\n\nChosen Number: ",
					"Choose Side", JOptionPane.QUESTION_MESSAGE));

			switch (chosenSideNumber) {
				// Side 1: Buyer-Side of the Program
				case 1:

					Boolean isInBuyerAccountInSignInOrSignUpPage = true;
					while (isInBuyerAccountInSignInOrSignUpPage) {
						// Choose SignIn or SignUp Option
						int chosenSignInOrSignUpMethodNumberForBuyerAccount = Integer
								.parseInt(JOptionPane.showInputDialog(
										null,
										"How Would You Like to Access Your Buyer Account?\n1. Sign In\n2. Sing Up (Create New Buyer Account)\n3. Back to Select-Side Page\n\nChosen Number: ",
										"BuyerAccount SignIn/SignUp Page", JOptionPane.QUESTION_MESSAGE));

						switch (chosenSignInOrSignUpMethodNumberForBuyerAccount) {
							// Option 1: SignIn
							case 1:
								if (usernameAndPasswordsOfBuyerAccountsHashMap.isEmpty())
									JOptionPane.showMessageDialog(null,
											"No BuyerAccount Was Found in Program's DataBase.\nYou Have to Sign Up First.",
											"BuyerAccount SignIn Page", JOptionPane.ERROR_MESSAGE);
								else {

									String buyerAccountUsername = JOptionPane
											.showInputDialog(null, "Please Enter Your Username: ",
													"BuyerAccount SignIn Page",
													JOptionPane.QUESTION_MESSAGE)
											.toLowerCase();

									while (!usernameAndPasswordsOfBuyerAccountsHashMap
											.containsKey(buyerAccountUsername)) {
										JOptionPane.showMessageDialog(null,
												"Entered Username Was Not Found in BuyerAccounts's DataBase.",
												"BuyerAccount SignIn Page", JOptionPane.ERROR_MESSAGE);

										buyerAccountUsername = JOptionPane
												.showInputDialog(null, "Please Enter A Registered Username: ",
														"BuyerAccount SignIn Page", JOptionPane.QUESTION_MESSAGE)
												.toLowerCase();
									}

									String password = JOptionPane.showInputDialog(null, "Please Enter Your Password: ",
											"BuyerAccount SignIn Page", JOptionPane.QUESTION_MESSAGE);

									boolean haveNotEnteredCorrectPassword = true;
									int wrongPasswordAttemptLeft = 3;
									while (haveNotEnteredCorrectPassword && wrongPasswordAttemptLeft > 0) {
										if (usernameAndPasswordsOfBuyerAccountsHashMap.get(buyerAccountUsername)
												.equals(PasswordSecurity
														.toHexString(PasswordSecurity.getSHA(password)))) {
											haveNotEnteredCorrectPassword = false;
											break;
										} else {
											JOptionPane.showMessageDialog(null,
													"Entered Password Is Not Correct.\nWrong Password Attempts Left: "
															+ wrongPasswordAttemptLeft,
													"BuyerAccount SignIn Page", JOptionPane.ERROR_MESSAGE);
											wrongPasswordAttemptLeft--;

											password = JOptionPane.showInputDialog(null, "Please Enter Your Password: ",
													"BuyerAccount SignIn Page", JOptionPane.QUESTION_MESSAGE);
										}
									}

									if (haveNotEnteredCorrectPassword)
										JOptionPane.showMessageDialog(null, "SignIn Operation Failed!\n",
												"BuyerAccount SignIn Page", JOptionPane.ERROR_MESSAGE);
									else {
										JOptionPane.showMessageDialog(null,
												"You Are Successfully Signed In To Your Account.\n",
												"BuyerAccount SignIn Page", JOptionPane.INFORMATION_MESSAGE);

										Boolean buyerAccountIsSignedIn = true;
										while (buyerAccountIsSignedIn) {

											int chosenBuyerTaskNumber = Integer.parseInt(JOptionPane.showInputDialog(
													null,
													"Choose a Buyer-Side Task:\n1. Buy New ShoppingList\n2. Watch Purchased ShopLists's History\n3. Search a ShoppingList\n4. View Account's Info\n5. Delete Account\n6. SignOut\n\nChosen Task's Number: ",
													"Choose Buyer-Side Task", JOptionPane.QUESTION_MESSAGE));

											switch (chosenBuyerTaskNumber) {
												// Task 1: Buy New ShoppingList
												case 1:

													if (productsAvailableInShopInventoryArrayList.isEmpty())
														JOptionPane.showMessageDialog(null,
																"No Product is Available at Shop Currently.\nWait For Sellers to Add New Products to the Shop.",
																"Buy New ShoppingList", JOptionPane.ERROR_MESSAGE);
													else {
														ArrayList<Product> newProductArrayList = new ArrayList<>();

														boolean haveFinishedBuying = false;
														while (!haveFinishedBuying) {

															if (productsAvailableInShopInventoryArrayList.isEmpty()) {
																JOptionPane.showMessageDialog(null,
																		"No More Products is Left in Shop's Inventory.\nWait For Sellers to Add New Products to the Shop.",
																		"Buy New ShoppingList",
																		JOptionPane.ERROR_MESSAGE);
																break;
															}

															String productName2Buy = JOptionPane.showInputDialog(
																	null, OnlineShop
																			.getShopInventoryData(
																					productsAvailableInShopInventoryArrayList)
																			+ "\nPlease Enter the Name of the Product You'd Like to Add to Your ShoppingList: ",
																	"Buy New ShoppingList",
																	JOptionPane.QUESTION_MESSAGE);

															boolean haveEnteredValidProductName = false;
															int productIndex1;
															for (productIndex1 = 0; productIndex1 < productsAvailableInShopInventoryArrayList
																	.size(); productIndex1++)
																if (productsAvailableInShopInventoryArrayList
																		.get(productIndex1)
																		.getName().equals(productName2Buy)) {
																	haveEnteredValidProductName = true;
																	break;
																}

															if (haveEnteredValidProductName) {
																JOptionPane.showMessageDialog(null,
																		"Product '" + productName2Buy
																				+ "' Was Successfully Added to Your ShoppingList.",
																		"Buy New ShoppingList",
																		JOptionPane.INFORMATION_MESSAGE);
																newProductArrayList
																		.add(productsAvailableInShopInventoryArrayList
																				.get(productIndex1));
																productsAvailableInShopInventoryArrayList
																		.remove(productIndex1);
															} else {
																JOptionPane.showMessageDialog(null,
																		"No Such Product Was Found in the Shop.",
																		"Buy New ShoppingList",
																		JOptionPane.ERROR_MESSAGE);
																continue;
															}

															int haveFinishedBuyingChoice = JOptionPane
																	.showConfirmDialog(null,
																			"Have You Finished Your Shopping?\n",
																			"Buy New ShoppingList",
																			JOptionPane.YES_NO_OPTION);

															switch (haveFinishedBuyingChoice) {
																case JOptionPane.YES_OPTION:
																	haveFinishedBuying = true;
																	break;

																case JOptionPane.NO_OPTION:
																	break;
															}
														}
														ShoppingList newShoppingList = new ShoppingList(
																newProductArrayList);

														JOptionPane.showMessageDialog(null,
																"Purchased ShoppingList Was Successfully Added to Your Account.\n"
																		+ "\nPurchased Products:\n"
																		+ newShoppingList.getPurchasedProductsData()
																		+ "\n\nShoppingList's ID: "
																		+ (ShoppingList.numCreatedIDs - 1)
																		+ "\n[Write Down Your ShoppingList's ID for Later Searches.]",
																"Buy New ShoppingList",
																JOptionPane.INFORMATION_MESSAGE);

														buyersAccountsDataBaseArrayList
																.get(Account.getAccountIndexUsingUsername(
																		buyerAccountUsername,
																		buyersAccountsDataBaseArrayList))
																.getPurchasedShoppingListsArrayList()
																.add(newShoppingList);

													}
													continue;

												// Task 2: Watch Purchased ShopLists's History
												case 2:

													if (buyersAccountsDataBaseArrayList
															.get(Account.getAccountIndexUsingUsername(
																	buyerAccountUsername,
																	buyersAccountsDataBaseArrayList))
															.getPurchasedShoppingListsArrayList().isEmpty())
														JOptionPane.showMessageDialog(null,
																"Your History is Empty.\nNo ShoppingList is Ever Bough Using This Account.\n",
																"Watch Purchased ShopLists's History",
																JOptionPane.ERROR_MESSAGE);
													else {
														JOptionPane.showMessageDialog(
																null, buyersAccountsDataBaseArrayList
																		.get(Account.getAccountIndexUsingUsername(
																				buyerAccountUsername,
																				buyersAccountsDataBaseArrayList))
																		.getPurchasedShoppingListsData(),
																"Watch Purchased ShopLists's History",
																JOptionPane.INFORMATION_MESSAGE);
													}
													continue;

												// Task 3: Search a ShoppingList
												case 3:
													if (buyersAccountsDataBaseArrayList
															.get(Account.getAccountIndexUsingUsername(
																	buyerAccountUsername,
																	buyersAccountsDataBaseArrayList))
															.getPurchasedShoppingListsArrayList().size() == 0)
														JOptionPane.showMessageDialog(null,
																"No ShoppingList is Ever Bough in This Account.\n",
																"Search a ShoppingList", JOptionPane.ERROR_MESSAGE);
													else {
														int shoppingListID = Integer
																.parseInt(JOptionPane.showInputDialog(null,
																		"Please Enter the ID of ShoppingList You'd Like to Find: ",
																		"Search a ShoppingList",
																		JOptionPane.QUESTION_MESSAGE));

														Boolean haveFoundShoppingListID = false;
														for (ShoppingList shoppingList : buyersAccountsDataBaseArrayList
																.get(Account.getAccountIndexUsingUsername(
																		buyerAccountUsername,
																		buyersAccountsDataBaseArrayList))
																.getPurchasedShoppingListsArrayList())
															if (shoppingList.getID() == shoppingListID) {
																haveFoundShoppingListID = true;

																JOptionPane.showMessageDialog(null,
																		shoppingList.getPurchasedProductsData(),
																		"Search a ShoppingList",
																		JOptionPane.INFORMATION_MESSAGE);

																break;
															}

														if (!haveFoundShoppingListID)
															JOptionPane.showMessageDialog(null,
																	"No Such ShoppingList With This ID Was Found.\n",
																	"Search a ShoppingList", JOptionPane.ERROR_MESSAGE);
													}
													continue;

												// Task 4: View Account's Info
												case 4:
													JOptionPane.showMessageDialog(null,
															Account.getAccountData(buyerAccountUsername,
																	buyersAccountsDataBaseArrayList),
															"View Account's Info", JOptionPane.INFORMATION_MESSAGE);
													continue;

												// Task 5: Delete Account
												case 5:
													JOptionPane.showMessageDialog(null,
															"Warning: All Data of Account Will be Deleted & Can NOT be ReStored Later!",
															"Delete Account", JOptionPane.WARNING_MESSAGE);
													String passwordForConfirmationToDeleteAccount = JOptionPane
															.showInputDialog(
																	null,
																	"If You Are Completely Sure About Deleting Your Account, Enter Your Password For Confirmation: ",
																	"Delete Account", JOptionPane.QUESTION_MESSAGE);

													if (usernameAndPasswordsOfBuyerAccountsHashMap
															.get(buyerAccountUsername)
															.equals(PasswordSecurity.toHexString(PasswordSecurity
																	.getSHA(passwordForConfirmationToDeleteAccount)))) {
														JOptionPane.showMessageDialog(null,
																"Your Account Was Successfully Deleted!\n",
																"Delete Account",
																JOptionPane.INFORMATION_MESSAGE);
														buyersAccountsDataBaseArrayList
																.remove(Account.getAccountIndexUsingUsername(
																		buyerAccountUsername,
																		buyersAccountsDataBaseArrayList));
														usernameAndPasswordsOfBuyerAccountsHashMap
																.remove(buyerAccountUsername);
													} else
														JOptionPane.showMessageDialog(null,
																"Entered Password Was Not Correct.\n\nAccount Deletion Failed!\n",
																"Delete Account", JOptionPane.ERROR_MESSAGE);
													break;

												// Task 6: SignOut & Go Back to Select-Side Page
												case 6:
													buyerAccountIsSignedIn = false;
													break;

												// Other Task Are Invalid & the Program Will Throw Error
												default:
													JOptionPane.showMessageDialog(null,
															"Invalid Input. Input Must Be an Integer Between 1 and 6.",
															"Choose Buyer-Side Task", JOptionPane.ERROR_MESSAGE);
													continue;
											}
											break;
										}
										continue;
									}
								}
								break;

							// Option 2: SingUp/Create New Buyer Account
							case 2:
								SignUp_Page.SignUpBuyerAccount(buyersAccountsDataBaseArrayList,
										usernameAndPasswordsOfBuyerAccountsHashMap);
								break;

							case 3:
								isInBuyerAccountInSignInOrSignUpPage = false;
								break;

							default:
								JOptionPane.showMessageDialog(null,
										"Invalid Input. Input Must Be an Integer Equal to 1, 2 or 3.",
										"BuyerAccount SignIn/SignUp Page", JOptionPane.ERROR_MESSAGE);
								break;
						}
					}
					continue;

				// Side 2: Seller-Side of the Program
				case 2:

					Boolean isInSellerAccountSignInOrSignUpPage = true;
					while (isInSellerAccountSignInOrSignUpPage) {
						int chosenSignInSignUpMethodNumberForSellerAccount = Integer
								.parseInt(JOptionPane.showInputDialog(
										null,
										"How Would You Like to Access Your Account?\n1. SignIn\n2. SingUp (Create New Seller Account)\n3. Back to Select-Side Page\n\nChosen Number: ",
										"SellerAccount SignIn/SignUp Page", JOptionPane.QUESTION_MESSAGE));

						switch (chosenSignInSignUpMethodNumberForSellerAccount) {
							// Option 1: SignIn to Seller Account
							case 1:
								if (usernameAndPasswordsOfSellerAccountsHashMap.isEmpty())
									JOptionPane.showMessageDialog(null,
											"No SellerAccount Was Found. You Have to SignUp First.\n",
											"SellerAccount SignIn Page", JOptionPane.ERROR_MESSAGE);
								else {
									String SellerAccountUsername = JOptionPane.showInputDialog(null,
											"Please Enter Your Username: ", "SignIn Page", JOptionPane.QUESTION_MESSAGE)
											.toLowerCase();

									while (!usernameAndPasswordsOfSellerAccountsHashMap
											.containsKey(SellerAccountUsername)) {
										JOptionPane.showMessageDialog(null,
												"Entered Username Was Not Found in SellerAccounts's DataBase.",
												"SellerAccount SignIn Page", JOptionPane.ERROR_MESSAGE);

										SellerAccountUsername = JOptionPane
												.showInputDialog(null, "Please Enter A Registered Username: ",
														"SellerAccount SignIn Page", JOptionPane.QUESTION_MESSAGE)
												.toLowerCase();
									}

									String password = JOptionPane.showInputDialog(null, "Please Enter Your Password: ",
											"SellerAccount SignIn Page", JOptionPane.QUESTION_MESSAGE);

									boolean haveNotEnteredCorrectPassword = true;
									int wrongPasswordAttemptLeft = 3;
									while (haveNotEnteredCorrectPassword && wrongPasswordAttemptLeft > 0) {
										if (usernameAndPasswordsOfSellerAccountsHashMap.get(SellerAccountUsername)
												.equals(PasswordSecurity
														.toHexString(PasswordSecurity.getSHA(password)))) {
											haveNotEnteredCorrectPassword = false;
											break;
										} else {
											JOptionPane.showMessageDialog(null,
													"Wrong Password. Try Again.\nWrong Password Attempts Left: "
															+ wrongPasswordAttemptLeft,
													"SellerAccount SignIn Page", JOptionPane.ERROR_MESSAGE);
											wrongPasswordAttemptLeft--;

											password = JOptionPane.showInputDialog(null, "Please Enter Your Password: ",
													"SellerAccount SignIn Page", JOptionPane.QUESTION_MESSAGE);
										}
									}

									if (haveNotEnteredCorrectPassword)
										JOptionPane.showMessageDialog(null, "SignIn Operation Failed!\n",
												"SellerAccount SignIn Page", JOptionPane.ERROR_MESSAGE);
									else {
										JOptionPane.showMessageDialog(null,
												"You Are Successfully Signed In To Your Account.",
												"SellerAccount SignIn Page", JOptionPane.INFORMATION_MESSAGE);

										Boolean isSellerAccountSignedIn = true;
										while (isSellerAccountSignedIn) {
											int chosenSellerTaskNumber = Integer.parseInt(JOptionPane.showInputDialog(
													null,
													"Choose A Seller-Side Task:\n1. Monitor Shop's Products\n2. Add New Product\n3. Remove Product\n4. Set Discount For Products\n5. Monitor Buyer's Accounts\n6. Ban a Buyer's Account\n7. Set Shipping Status for Buyer's Purchased ShoppingLists\n8. View Account's Info\n9. Delete Account\n10. SignOut\n\nChosen Task's Number: ",
													"Choose Seller-Side Task", JOptionPane.QUESTION_MESSAGE));

											switch (chosenSellerTaskNumber) {
												// Task 1: Monitor Shop's Products
												case 1:
													if (productsAvailableInShopInventoryArrayList.isEmpty())
														JOptionPane.showMessageDialog(null,
																"No Products Was Found in Shop's Inventory.\nSellers Must Add New Products.",
																"Monitor Shop's Products", JOptionPane.ERROR_MESSAGE);
													else {
														JOptionPane.showMessageDialog(null,
																OnlineShop.getShopInventoryData(
																		productsAvailableInShopInventoryArrayList),
																"Monitor Shop's Products",
																JOptionPane.INFORMATION_MESSAGE);
													}
													continue;

												// Task 2: Add New Product
												case 2:
													String newProductName = JOptionPane.showInputDialog(null,
															"Please Enter the Name of the New Product: ",
															"Add New Product",
															JOptionPane.QUESTION_MESSAGE);

													double newProductPrice = Double
															.parseDouble(JOptionPane.showInputDialog(null,
																	"Please Enter the Price of the New Product in Dollar: ",
																	"Add New Product", JOptionPane.QUESTION_MESSAGE));

													JOptionPane.showMessageDialog(null,
															"New Product Was Successfully Added to the Shop.",
															"Add New Product",
															JOptionPane.INFORMATION_MESSAGE);
													Product newProduct = new Product(newProductName, newProductPrice);

													productsAvailableInShopInventoryArrayList.add(newProduct);

													continue;

												// Task 3: Remove Product
												case 3:
													if (productsAvailableInShopInventoryArrayList.isEmpty())
														JOptionPane.showMessageDialog(null,
																"No Product is Available in Shop Currently.\n",
																"Remove Product",
																JOptionPane.ERROR_MESSAGE);
													else {

														String nameOfProductWantToRemove = JOptionPane.showInputDialog(
																null,
																OnlineShop.getShopInventoryData(
																		productsAvailableInShopInventoryArrayList)
																		+ "\nPlease Enter the Name of the Product You Want to Remove From the Shop: ",
																"Remove Product", JOptionPane.QUESTION_MESSAGE);

														if (OnlineShop.haveFoundProductUsingName(
																nameOfProductWantToRemove,
																productsAvailableInShopInventoryArrayList)) {
															JOptionPane.showMessageDialog(null,
																	"Product '" + nameOfProductWantToRemove
																			+ "' Was Successfully Removed From the Shop.",
																	"Remove Product", JOptionPane.INFORMATION_MESSAGE);
															productsAvailableInShopInventoryArrayList.remove(
																	OnlineShop.getProductIndexUsingName(
																			nameOfProductWantToRemove,
																			productsAvailableInShopInventoryArrayList));
														} else
															JOptionPane.showMessageDialog(null,
																	"Operation Failed!\nNo Such Name Was Found in Shop.",
																	"Remove Product", JOptionPane.ERROR_MESSAGE);
													}
													continue;

												// Task 4: Set Discount For Products
												case 4:
													if (productsAvailableInShopInventoryArrayList.isEmpty())
														JOptionPane.showMessageDialog(null,
																"No Product is Available in Shop Currently",
																"Set Discount For Products", JOptionPane.ERROR_MESSAGE);
													else {
														String productNameToSetDiscount = JOptionPane.showInputDialog(
																null,
																OnlineShop.getShopInventoryData(
																		productsAvailableInShopInventoryArrayList)
																		+ "\nPlease Enter the Name of the Product You'd Like to Set Discount For: ",
																"Set Discount For Products",
																JOptionPane.QUESTION_MESSAGE);

														if (OnlineShop.haveFoundProductUsingName(
																productNameToSetDiscount,
																productsAvailableInShopInventoryArrayList)) {
															Double discountAmount = Double
																	.parseDouble(JOptionPane.showInputDialog(
																			null,
																			"Please Enter Amount of Discount You Want to Set for '"
																					+ productNameToSetDiscount
																					+ "' in Percentage:",
																			"Set Discount For Products",
																			JOptionPane.QUESTION_MESSAGE));

															JOptionPane.showMessageDialog(null,
																	discountAmount.toString()
																			+ "% Discount Was Added to the Product '"
																			+ productNameToSetDiscount + "'.",
																	"Set Discount For Products",
																	JOptionPane.INFORMATION_MESSAGE);

															productsAvailableInShopInventoryArrayList
																	.get(OnlineShop.getProductIndexUsingName(
																			productNameToSetDiscount,
																			productsAvailableInShopInventoryArrayList))
																	.setDiscount(discountAmount);

														} else
															JOptionPane.showMessageDialog(null,
																	"Operation Failed!\nNo Such Product Was Found in Shop's Inventory.",
																	"Set Discount For Products",
																	JOptionPane.ERROR_MESSAGE);

													}
													continue;

												// Task 5: Monitor Buyer's Accounts
												case 5:
													if (buyersAccountsDataBaseArrayList.isEmpty())
														JOptionPane.showMessageDialog(null,
																"BuyerAccounts's DataBase is Currently Empty.",
																"Monitor Buyer's Accounts", JOptionPane.ERROR_MESSAGE);
													else {
														JOptionPane.showMessageDialog(null,
																OnlineShop.getBuyerAccountsData(
																		buyersAccountsDataBaseArrayList),
																"Monitor Buyer's Accounts",
																JOptionPane.INFORMATION_MESSAGE);
													}
													continue;

												// Task 6: Ban a Buyer's Account
												case 6:
													if (buyersAccountsDataBaseArrayList.isEmpty())
														JOptionPane.showMessageDialog(null,
																"No BuyerAccount Was Found in DataBases.",
																"Ban a Buyer's Account",
																JOptionPane.ERROR_MESSAGE);
													else {
														String usernameOfBuyerAccountToBan = JOptionPane
																.showInputDialog(null,
																		OnlineShop.getBuyerAccountsData(
																				buyersAccountsDataBaseArrayList)
																				+ "\nPlease Enter the Username of the Buyer You Want to Ban: ",
																		"Ban a Buyer's Account",
																		JOptionPane.QUESTION_MESSAGE)
																.toLowerCase();

														Boolean haveFoundUsernameOfBuyerAccountToBan = false;
														int BuyerAccountIndex;
														for (BuyerAccountIndex = 0; BuyerAccountIndex < buyersAccountsDataBaseArrayList
																.size(); BuyerAccountIndex++)
															if (buyersAccountsDataBaseArrayList.get(BuyerAccountIndex)
																	.getUsername()
																	.equals(usernameOfBuyerAccountToBan)) {
																haveFoundUsernameOfBuyerAccountToBan = true;
																break;
															}

														if (haveFoundUsernameOfBuyerAccountToBan) {
															JOptionPane.showMessageDialog(null,
																	"Buyer Account With Username '"
																			+ usernameOfBuyerAccountToBan
																			+ "' Was Successfully Banned.",
																	"Ban a Buyer's Account",
																	JOptionPane.INFORMATION_MESSAGE);
															buyersAccountsDataBaseArrayList.remove(BuyerAccountIndex);
															usernameAndPasswordsOfBuyerAccountsHashMap
																	.remove(usernameOfBuyerAccountToBan);
														} else
															JOptionPane.showMessageDialog(null,
																	"No Such Username Found in BuyerAccounts's DataBase.",
																	"Ban a Buyer's Account", JOptionPane.ERROR_MESSAGE);
													}
													continue;

												// Task 7: Set Shipping Status for Buyer's Purchased ShoppingLists
												case 7:
													String usernameOfBuyerAccountWantToSetShippingStatus = JOptionPane
															.showInputDialog(null,
																	"Please Enter the Username of the BuyerAccount Which You Want to Set ShippingStatus for It's Purchased ShoppingLists: ",
																	"Set Shipping Status for Buyer's Purchased ShoppingLists",
																	JOptionPane.QUESTION_MESSAGE)
															.toLowerCase();

													if (usernameAndPasswordsOfBuyerAccountsHashMap
															.containsKey(
																	usernameOfBuyerAccountWantToSetShippingStatus)) {
														if (buyersAccountsDataBaseArrayList
																.get(Account.getAccountIndexUsingUsername(
																		usernameOfBuyerAccountWantToSetShippingStatus,
																		buyersAccountsDataBaseArrayList))
																.getPurchasedShoppingListsArrayList().isEmpty())
															JOptionPane.showMessageDialog(null,
																	"No ShoppingLists Was Found in This Account.",
																	"Set Shipping Status for Buyer's Purchased ShoppingLists",
																	JOptionPane.ERROR_MESSAGE);
														else {
															for (ShoppingList shoppingList : buyersAccountsDataBaseArrayList
																	.get(Account.getAccountIndexUsingUsername(
																			usernameOfBuyerAccountWantToSetShippingStatus,
																			buyersAccountsDataBaseArrayList))
																	.getPurchasedShoppingListsArrayList()) {

																int chosenShippingStatusNumber = Integer
																		.parseInt(JOptionPane.showInputDialog(null,
																				shoppingList
																						.getPurchasedProductsData()
																						+ "\nWhich Of the Following Shipping Status You'd Like to Set For This ShoppingList?\n1. PROCESSING\n2. SENDING\n3. DELIVERED\n\nChosen ShippingStatus Number: ",
																				"Set Shipping Status for Buyer's Purchased ShoppingLists",
																				JOptionPane.QUESTION_MESSAGE));

																switch (chosenShippingStatusNumber) {
																	case 1:
																		shoppingList.setShippingStatus(
																				ShippingStatus.PROCESSING);
																		break;

																	case 2:
																		shoppingList.setShippingStatus(
																				ShippingStatus.SENDING);
																		break;

																	case 3:
																		shoppingList.setShippingStatus(
																				ShippingStatus.DELIVERED);
																		break;

																	default:
																		JOptionPane.showMessageDialog(null,
																				"Invalid Input.\nInput Must an Integer Equal to 1, 2 or 3.",
																				"Set Shipping Status for Buyer's Purchased ShoppingLists",
																				JOptionPane.ERROR_MESSAGE);
																		break;
																}
															}

														}
													} else
														JOptionPane.showMessageDialog(null,
																"No Such Username Was Found in BuyerAccounts's DataBase.",
																"Set Shipping Status for Buyer's Purchased ShoppingLists",
																JOptionPane.ERROR_MESSAGE);

													continue;

												// Task 8: View Account's Info
												case 8:
													JOptionPane.showMessageDialog(null,
															Account.getAccountData(SellerAccountUsername,
																	sellersAccountsDataBaseArrayList),
															"View Account's Info", JOptionPane.INFORMATION_MESSAGE);
													continue;

												// Task 9: Delete Account
												case 9:
													JOptionPane.showMessageDialog(null,
															"Warning: All Data of Account Will be Deleted & Can NOT be ReStored Later!\n",
															"Delete Account", JOptionPane.WARNING_MESSAGE);
													String passwordForConfirmationToDeleteAccount = JOptionPane
															.showInputDialog(
																	null,
																	"If You Are Completely Sure About Deleting Your Account, Enter Your Password For Confirmation: ",
																	"Delete Account", JOptionPane.QUESTION_MESSAGE);

													if (usernameAndPasswordsOfSellerAccountsHashMap
															.get(SellerAccountUsername)
															.equals(PasswordSecurity.toHexString(PasswordSecurity
																	.getSHA(passwordForConfirmationToDeleteAccount)))) {
														JOptionPane.showMessageDialog(null,
																"Your Account Was Successfully Deleted!\n",
																"Delete Account",
																JOptionPane.INFORMATION_MESSAGE);
														sellersAccountsDataBaseArrayList
																.remove(Account.getAccountIndexUsingUsername(
																		SellerAccountUsername,
																		sellersAccountsDataBaseArrayList));
														usernameAndPasswordsOfSellerAccountsHashMap
																.remove(SellerAccountUsername);
													} else
														JOptionPane.showMessageDialog(null,
																"Entered Password Was Not Correct.\n\nAccount Deletion Failed!\n",
																"Delete Account", JOptionPane.ERROR_MESSAGE);
													break;

												// Task 10: SignOut & Go Back to Select-Side Page
												case 10:
													isSellerAccountSignedIn = false;
													break;

												// Other Tasks Are Invalid & Program Will Throw Errors
												default:
													JOptionPane.showMessageDialog(null,
															"Invalid Input. Input Must an Integer Between 1 & 10.",
															"Choose Seller-Side Task", JOptionPane.ERROR_MESSAGE);
													continue;
											}
											break;
										}
										continue;
									}
								}
								break;

							// Option 2: SignUp/Create New Seller Account
							case 2:
								SignUp_Page.SignUpSellerAccount(sellersAccountsDataBaseArrayList,
										usernameAndPasswordsOfSellerAccountsHashMap);
								break;

							case 3:
								isInSellerAccountSignInOrSignUpPage = false;
								break;

							default:
								JOptionPane.showMessageDialog(null,
										"Invalid Input.\nInput Must an Integer Equal to 1, 2 or 3.",
										"SellerAccount SignIn/SignUp Page", JOptionPane.ERROR_MESSAGE);
								break;
						}
						// continue;
					}
					continue;

				// Side 3: Exit the Program
				case 3:
					IsProgramRunning = false;
					break;

				// Other Entered Inputs Are Invalid & Program Will Throw Errors
				default:
					JOptionPane.showMessageDialog(null, "Invalid Input.\nInput Must an Integer Equal to 1, 2 or 3.",
							"Choose Side", JOptionPane.ERROR_MESSAGE);
					continue;
			}
		}

		// Good-Bye Note & Credit
		JOptionPane.showMessageDialog(null,
				"This Program Was Written & Developed by AmirHossein Roodaki.\nHope You've Enjoyed.\n", "Good-Bye Note",
				JOptionPane.INFORMATION_MESSAGE);

	}
}