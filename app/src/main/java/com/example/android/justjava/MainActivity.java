/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    // Initializes the variable Quantity by a default value
    // When changing this, change also the number on strings.xml -> quantity_text_view
    public int quantity = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */

    public void increment (View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
            return;
        } else {
            //Toast for incremental button. Shows message when trying to add more than 100 coffees
            //String pointing to strings.xml, sorry_increment string
            final Toast incrementToast = Toast.makeText(this, (R.string.sorry_increment), Toast.LENGTH_SHORT);
            incrementToast.show();
            // Added to make Toast be shorter and cancel last toast before adding a new one (if user presses many times)
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    incrementToast.cancel();
                }
            }, 1000);
        }
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
        // If no name is given, userName is updated to Final Consumer
        EditText editTextName = findViewById(R.id.name_text_view);
        String userName = editTextName.getText().toString();
        if (userName.matches("")) {
            userName = "Final Consumer";
        }
        //calculate the price
        calculatePrice(hasWhippedCream, hasChocolate);

//        // Replaced by Intent Email
//        // Display message when tapping on Submit Order button, 2 parameters need to be passed: hasWhippedCream and hasChocolate (boolean)
//       displayMessage(createOrderSummary(hasWhippedCream, hasChocolate, userName));

        // Replaced message.
        // Intent send e-mail with order
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:")); // only email apps should handle this
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"aniracsilva@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + userName);
        email.putExtra(Intent.EXTRA_TEXT, createOrderSummary(hasWhippedCream, hasChocolate, userName));

        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }


    }


    /**
     * Create a summary of the order and add it as a text message in the app
     */
    // hasWhippedCream is the variable that is updated with the state of the CheckBox with the id (check_whipped_cream)
    private String createOrderSummary(boolean hasWhippedCream, boolean hasChocolate, String userName) {
        int price = calculatePrice(hasWhippedCream, hasChocolate);
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
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        // Indicates the prices of cup
        int pricePerCup = 5;

        //if WhippedCream is selected, add the price of WhippedCream to the pricePerCup
        if (hasWhippedCream) {
            pricePerCup += 1;
        }
        //if Chocolate is selected, add the price of Chocolate to the pricePerCup
        if (hasChocolate) {
            pricePerCup += 2;
        }
        return (quantity * pricePerCup);

    }


    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement (View view) {
        if (quantity >= 2) {
            quantity = quantity - 1;
            displayQuantity(quantity);
            return;
        } else {
            //Toast for decremental button. Shows message when trying to add less than 1 coffee
            //String pointing to strings.xml, sorry_decrement string
            final Toast decrementToast = Toast.makeText(this, (R.string.sorry_decrement), Toast.LENGTH_SHORT);
            decrementToast.show();
            // Added to make Toast be shorter and cancel last toast before adding a new one (if user presses many times)
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    decrementToast.cancel();
                }
            }, 1000);
        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int displayQuantity) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + displayQuantity);
    }

//     Replaced by intent to send email
//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//    }
}



