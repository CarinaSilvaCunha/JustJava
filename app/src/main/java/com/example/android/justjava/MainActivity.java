/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */

    public void increment (View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox checkWhippedCream = findViewById(R.id.check_whipped_cream);
        // define checkbox has checkWhippedCream, and connect it to the id given on the XML (check_whipped_cream)
        boolean hasWhippedCream = checkWhippedCream.isChecked();
        // NoteToSelf: Could use have used: ** String hasWhippedCreamResult = hasWhippedCream ? "Yes" : "No"; ** and passed a string with the variable hasWhippedCreamResult already with the Yes No Version

        //define the variable @hasWhippedCream that is updated with the state of the checkWhippedCream CheckBox
        //call displayMessage and pass along the state of the variable it needs to build the message
        //Do the same for CheckBox Chocolate
        CheckBox checkChocolate = findViewById(R.id.check_chocolate);
        boolean hasChocolate = checkChocolate.isChecked();
        // Find Name entered in EditText to pass on the Order as a String
        EditText editTextName = findViewById(R.id.name_text_view);
        String userName = editTextName.getText().toString();

        // Display message when tapping on Submit Order button, 2 parameters need to be passed: hasWhippedCream and hasChocolate (boolean)
        displayMessage(createOrderSummary(hasWhippedCream, hasChocolate, userName));
    }


    /**
     * Create a summary of the order and add it as a text message in the
     */
    // hasWhippedCream is the variable that is updated with the state of the CheckBox with the id (check_whipped_cream)
    private String createOrderSummary(boolean hasWhippedCream, boolean hasChocolate, String userName) {
        int price = calculatePrice();
        return  "Name: " + userName + "\n" +
                "Add Whipped cream? " + (hasWhippedCream ? "Yes" : "No") + "\n" +
                "Add Chocolate? " + (hasChocolate ? "Yes" : "No") + "\n" +
                "Quantity: " + quantity + "\n" +
                "Total: " + price + "€" + "\n" +
                "Thank You!";
    }


    /**
     * Calculates the price of the order.
     */
    private int calculatePrice() {
        int pricePerCup = 5;
        return quantity * pricePerCup;

    }


    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement (View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int displayQuantity) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + displayQuantity);
    }


    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);


    }
}



