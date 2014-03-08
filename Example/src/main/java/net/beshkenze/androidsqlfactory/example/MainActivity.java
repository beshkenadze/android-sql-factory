package net.beshkenze.androidsqlfactory.example;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import net.beshkenze.androidsqlfactory.example.model.UserModel;
import net.beshkenze.androidsqlfactory.library.SqlBuilder;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        workWithDb();
    }

    private void workWithDb() {
        new SqlBuilder()
                .from(UserModel.TABLE_NAME)
                .select("_id", "sex", "age")
                .select("count(sex)", "count_sex")
                .leftJoin(new String[]{"phone", "user_phone"}, "user_id", "_id")
                .leftJoin("address", "user_id", "_id")
//                .eq("_id", 1)
//                .like("name", "Alex")
//                .gt("age", 19)
//                .gte("age", 19)
//                .lt("age", 25)
//                .lte("age", 25)
//                .ne("age", 21)
//                .is("age", 19)
//                .isNot("age", 18)
//                .in("age", 18, 12, 13, 14, 14)
//                .notIn("age", 18, 12, 13, 14, 14)
//                .beetween("age", 18, 16)
//                .where("age", "beetwe")
                .one();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
