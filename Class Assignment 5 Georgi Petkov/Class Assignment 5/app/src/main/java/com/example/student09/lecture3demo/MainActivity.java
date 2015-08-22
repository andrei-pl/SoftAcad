package com.example.student09.lecture3demo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // GENERAL LAYOUT - The screen holder
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout);
        // For best results with the colours: Use a colour picker and a screenshot of the sample imaged
        // External easy to install colour picker: Paint .NET -> http://www.getpaint.net/
        ll.setBackgroundColor(Color.BLACK); // Doesn't matter as it is a hidden layer
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setWeightSum(2);

        LayoutParams lp = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        // We set weight to 1 for all inner boxes and manage the weightSum of their parents
        // The LayoutParams can be shared for all boxes in this case
        lp.weight = 1;

        // PART1 - vertical - this is the container for the 3 big squares on the left
        LinearLayout l1 = new LinearLayout(this);
        l1.setBackgroundColor(Color.GREEN); // Doesn't matter as it is a hidden layer
        l1.setOrientation(LinearLayout.VERTICAL);
        l1.setWeightSum(3);

        // l11 is the top left-most box
        LinearLayout l11 = new LinearLayout(this);
        l11.setBackgroundColor(Color.parseColor("#424E58"));
        
        // l12 is the 2nd big box on the left
        LinearLayout l12 = new LinearLayout(this);
        l12.setBackgroundColor(Color.GREEN); // Doesn't matter as it is a hidden layer
        l12.setWeightSum(2);

        // Here are the definitions of the inner boxes of the 2nd big box on the left
        // they have to be 4x4 squares, i.e. 2 vertical layouts, containing two horizontal all with weightSum = 2
        LinearLayout l121 = new LinearLayout(this);
        l121.setBackgroundColor(Color.parseColor("#424E58"));
        l121.setWeightSum(2);
        l121.setOrientation(LinearLayout.VERTICAL);

        // By default the orientation of a LinerLayout is horizontal
        // those are the two horizontal layouts belonging to vertical layout l121
        LinearLayout l1211 = new LinearLayout(this);
        l1211.setBackgroundColor(Color.parseColor("#B8886A"));

        LinearLayout l1212 = new LinearLayout(this);
        l1212.setBackgroundColor(Color.parseColor("#5F6972"));

        l121.addView(l1211, lp);
        l121.addView(l1212, lp);

        // The same logic applied here for vertical layout N:2 and it's two horizontal children
        LinearLayout l122 = new LinearLayout(this);
        l122.setBackgroundColor(Color.RED);
        l122.setWeightSum(2);
        l122.setOrientation(LinearLayout.VERTICAL);

        LinearLayout l1221 = new LinearLayout(this);
        l1221.setBackgroundColor(Color.parseColor("#6C868E"));

        LinearLayout l1222 = new LinearLayout(this);
        l1222.setBackgroundColor(Color.parseColor("#F5D57F"));

        // Add the horizontal children to the vertical layout 2 
        l122.addView(l1221, lp);
        l122.addView(l1222, lp);

        // Here we add the two vertical layouts to the l12 layout - they contain two horizontal layouts -> all together look like 4 squares
        l12.addView(l121, lp);
        l12.addView(l122, lp);

        // This is the 3rd big box on the left - it does not contain inner squares
        LinearLayout l13 = new LinearLayout(this);
        l13.setBackgroundColor(Color.parseColor("#C2CCCF"));

        l1.addView(l11, lp);
        l1.addView(l12, lp);
        l1.addView(l13, lp);

        ll.addView(l1, lp);

        // PART2 - vertical - this is the container for the 3 big squares on the right
        LinearLayout l2 = new LinearLayout(this);
        l2.setBackgroundColor(Color.RED);
        l2.setWeightSum(3);
        l2.setOrientation(LinearLayout.VERTICAL);

        // l21 is the first big box on the right - it must contain 4 inner squares
        // Again, this would be achieved by 2 vertical layouts, containing 2 horizontal
        LinearLayout l21 = new LinearLayout(this);
        l21.setBackgroundColor(Color.YELLOW);
        l21.setWeightSum(2); // Don't forget to set the weighed sum to 2

        // Here you could actually copy paste the code for square L12 form the left
        // And replace the first three symbols - l12 becomes l21 and the logic is exactly the same
        // No wonder - this is a pattern
        LinearLayout l211 = new LinearLayout(this);
        l211.setBackgroundColor(Color.parseColor("#424E58"));
        l211.setWeightSum(2);
        l211.setOrientation(LinearLayout.VERTICAL);

        LinearLayout l2111 = new LinearLayout(this);
        l2111.setBackgroundColor(Color.parseColor("#264C58"));

        LinearLayout l2112 = new LinearLayout(this);
        l2112.setBackgroundColor(Color.parseColor("#F5C543"));

        l211.addView(l2111, lp);
        l211.addView(l2112, lp);

        LinearLayout l212 = new LinearLayout(this);
        l212.setBackgroundColor(Color.parseColor("#E0952C"));
        l212.setWeightSum(2);
        l212.setOrientation(LinearLayout.VERTICAL);

        LinearLayout l2121 = new LinearLayout(this);
        l2121.setBackgroundColor(Color.parseColor("#E0952C"));

        LinearLayout l2122 = new LinearLayout(this);
        l2122.setBackgroundColor(Color.parseColor("#9A5325")); // Only the deepest level of layout colour matters as it is shown above the container

        l212.addView(l2121, lp);
        l212.addView(l2122, lp);
        
        l21.addView(l211, lp); // Very important to add them to the container layout
        l21.addView(l212, lp);

        LinearLayout l22 = new LinearLayout(this);
        l22.setBackgroundColor(Color.parseColor("#E7B56F"));
        
        // l23 is the big square at the bottom right corner - it also has 4 inner squares
        // Using the same pattern - 2 vertical containing 2 horizontal linear layouts
        LinearLayout l23 = new LinearLayout(this);
        l23.setBackgroundColor(Color.GRAY);
        l23.setWeightSum(2);
        
        LinearLayout l231 = new LinearLayout(this);
        l231.setBackgroundColor(Color.parseColor("#F8ECC9"));
        l231.setWeightSum(2);
        l231.setOrientation(LinearLayout.VERTICAL);

        LinearLayout l2311 = new LinearLayout(this);
        l2311.setBackgroundColor(Color.parseColor("#F8ECC9"));

        LinearLayout l2312 = new LinearLayout(this);
        l2312.setBackgroundColor(Color.parseColor("#BEC2C5"));

        l231.addView(l2311, lp);
        l231.addView(l2312, lp);

        LinearLayout l232 = new LinearLayout(this);
        l232.setBackgroundColor(Color.RED);
        l232.setWeightSum(2);
        l232.setOrientation(LinearLayout.VERTICAL);

        LinearLayout l2321 = new LinearLayout(this);
        l2321.setBackgroundColor(Color.parseColor("#44505A"));

        LinearLayout l2322 = new LinearLayout(this);
        l2322.setBackgroundColor(Color.parseColor("#264C58"));

        l232.addView(l2321, lp);
        l232.addView(l2322, lp);

        l23.addView(l231, lp);
        l23.addView(l232, lp);
        
        
        l2.addView(l21, lp);
        l2.addView(l22, lp);
        l2.addView(l23, lp);

        ll.addView(l2 ,lp);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
