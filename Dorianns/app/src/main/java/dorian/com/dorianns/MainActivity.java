package dorian.com.dorianns;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickProduce(View view) {
        TextView content = (TextView) findViewById(R.id.content_pane);

        content.setText("1, 3, 4, 5, 8");
    }
}
