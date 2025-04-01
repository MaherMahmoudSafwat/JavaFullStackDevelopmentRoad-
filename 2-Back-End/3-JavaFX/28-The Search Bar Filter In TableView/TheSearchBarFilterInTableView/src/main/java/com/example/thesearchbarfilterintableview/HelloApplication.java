// 6. Create a FilteredList to wrap the original ObservableList.
// 6.1 The FilteredList allows filtering the data based on a Predicate.
// 6.2 Initially, show all data by setting the Predicate to always return true.
FilteredList<Account> filteredData = new FilteredList<>(AccountList, p -> true);

// Explanation:
// - FilteredList<Account>: This creates a FilteredList, a dynamic view of an ObservableList.
// - AccountList: This is the original ObservableList that contains the Account data.
// - p -> true: This is a lambda expression representing a Predicate.
//   - p: Represents a single Account object from AccountList.
//   - -> true: This means "for every Account object (p), return true."
//     - Initially, this Predicate returns true for all Accounts, so no filtering is applied.
//     - The FilteredList starts by displaying all the data from AccountList.

// 7. Add a listener to the searchField's textProperty.
// 7.1 This listener will be triggered whenever the text in the searchField changes.
searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        // Explanation:
        // - searchField.textProperty(): This gets the StringProperty representing the text in the TextField.
        // - .addListener(...): This adds a ChangeListener to the StringProperty.
        //   - The listener will be executed every time the text in the TextField changes.
        // - (observable, oldValue, newValue) -> { ... }: This is a lambda expression representing the listener.
        //   - observable: The StringProperty that changed.
        //   - oldValue: The previous text value.
        //   - newValue: The new text value.
        //   - The code inside the curly braces is executed when the text changes.

        // 8. Set the Predicate for the FilteredList.
        filteredData.setPredicate(account -> {
        // Explanation:
        // - filteredData.setPredicate(...): This sets a new Predicate for the FilteredList.
        // - account -> { ... }: This is a lambda expression representing the new Predicate.
        //   - account: Represents a single Account object from the original AccountList.
        //   - The code inside the curly braces defines the filtering logic.

        // 9. If the search field is empty, display all accounts.
        if (newValue == null || newValue.isEmpty()) {
        return true;
        }

// Explanation:
// - This checks if the search field is empty (null or empty string).
// - If it's empty, it returns true, meaning all Accounts should be displayed.

// 10. Convert the search query to lowercase for case-insensitive search.
String lowerCaseFilter = newValue.toLowerCase();

// Explanation:
// - newValue.toLowerCase(): This converts the search query to lowercase.
// - lowerCaseFilter: This stores the lowercase search query.
// - This is done to make the search case-insensitive.

// 11. Check if the search query matches any of the Account properties.
// 11.1 Check if the UserName property contains the search query.
        if (account.getUserName().toLowerCase().contains(lowerCaseFilter)) {
        return true; // Filter matches username.
        }
        // Explanation:
        // - account.getUserName().toLowerCase(): This gets the UserName property of the Account object and converts it to lowercase.
        // - .contains(lowerCaseFilter): This checks if the UserName contains the lowercase search query.
        // - If it does, it returns true, meaning the Account should be included in the FilteredList.

        // 11.2 Check if the Password property contains the search query.
        else if (account.getPassword().toLowerCase().contains(lowerCaseFilter)) {
        return true; // Filter matches password.
        }
        // Explanation:
        // - Same logic as 11.1, but for the Password property.

        // 11.3 Check if the Type property contains the search query.
        else if (account.getType().toLowerCase().contains(lowerCaseFilter)) {
        return true; // Filter matches type.
        }
        // Explanation:
        // - Same logic as 11.1, but for the Type property.

        // 12. If none of the properties match, return false to exclude the account.
        return false; // Does not match.
        // Explanation:
        // - If none of the Account's properties match the search query, this returns false.
        // - This means the Account will be excluded from the FilteredList.
        });
        });

// 13. Set the items of the TableView to the FilteredList.
// 13.1 This will display only the filtered accounts in the TableView.
        View.setItems(filteredData);

// Explanation:
// - View.setItems(filteredData): This sets the items of the TableView to the FilteredList.
// - This means the TableView will display the data from the FilteredList, which is dynamically filtered based on the search query.
// - The TableView will only show the Account objects that match the current Predicate in the FilteredList.
// - Therefore, as the user types in the search field, the Predicate will change, and the TableView will update to show only the matching Accounts.