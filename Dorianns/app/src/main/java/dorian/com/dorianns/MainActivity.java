package dorian.com.dorianns;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_main_grid);
    }

    private List<Integer> createLuckyNumbers(int max, int count) {
        List<Integer> results = new ArrayList<>();
        Set<Integer> numbers = new HashSet<>();

        Random ran = new Random(System.currentTimeMillis());

        for (int i = 0; i < count; i++) {
            int num = 0;
            while (numbers.contains(num = ran.nextInt(max) + 1)) {
                num = 0;
            }
            numbers.add(new Integer(num));
            results.add(new Integer(num));
        }
        return results;
    }

    public void onClickProduceBalls(View view) {
        TextView red_ball_content = (TextView) findViewById(R.id.content_pane_red_balls);
        TextView blue_ball_content = (TextView) findViewById(R.id.content_pane_blue_balls);

        StringBuilder sb = new StringBuilder();

        List<Integer> redballs = createLuckyNumbers(33, 6);

        Collections.sort(redballs);

        for (int i = 0; i < 6; i++) {
            sb.append(redballs.get(i)).append(" ");
        }

        red_ball_content.setText(sb.toString());

        List<Integer> blueballs = createLuckyNumbers(16, 1);

        sb = new StringBuilder();

        sb.append(blueballs.get(0));

        blue_ball_content.setText(sb.toString());
    }

    public void onClickProduceLucks(View view) {
        TextView head_numbers_pane = (TextView) findViewById(R.id.content_pane_head_nums);

        TextView tail_numbers_pane = (TextView) findViewById(R.id.content_pane_tail_nums);

        StringBuilder sb = new StringBuilder();

        List<Integer> headnums = createLuckyNumbers(35, 5);
        Collections.sort(headnums);

        for (int i = 0; i < 5; i++) {
            sb.append(headnums.get(i)).append(" ");
        }

        head_numbers_pane.setText(sb.toString());

        List<Integer> tailnums = createLuckyNumbers(12, 2);
        Collections.sort(tailnums);
        sb = new StringBuilder();

        sb.append(tailnums.get(0)).append(" ").append(tailnums.get(1));

        tail_numbers_pane.setText(sb.toString());
    }

}
