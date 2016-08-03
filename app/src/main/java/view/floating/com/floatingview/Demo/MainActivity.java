package view.floating.com.floatingview.Demo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import view.floating.com.floatingview.R;
import view.floating.com.floatingview.Widget.FloatingView;


public class MainActivity extends ActionBarActivity {

    private FloatingView floatingView;
    private Button btn_clickshow,btn_clickdismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initIcon();

        btn_clickshow = (Button) findViewById(R.id.btn_clickshow);
        btn_clickshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingView.show(R.mipmap.demo);
            }
        });

        btn_clickdismiss = (Button) findViewById(R.id.btn_clickdismiss);
        btn_clickdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingView.stop();
            }
        });
    }

    public void initIcon(){
        floatingView = new FloatingView(MainActivity.this);
        floatingView.setclick_action("http://www.baidu.com");
        floatingView.setIcon_right(20);
        floatingView.setIcon_top(50);
        floatingView.setIcon_staty(10);
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
