/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
        displayMessage(createOrderSummary());
    }

    /**
     * Create a summary of the order and add it as a text message in the end
     */
    private String createOrderSummary() {
        String userName = "Tiago";
        int price = calculatePrice();
        return  "Name: " + userName + "\n" +
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
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}