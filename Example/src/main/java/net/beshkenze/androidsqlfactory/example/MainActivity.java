package net.beshkenze.androidsqlfactory.example;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import net.beshkenze.androidsqlfactory.example.model.PhoneModel;
import net.beshkenze.androidsqlfactory.example.model.SmsModel;
import net.beshkenze.androidsqlfactory.library.SqlBuilder;
import net.beshkenze.androidsqlfactory.library.helper.Where;
import timber.log.Timber;


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
        String sql = new SqlBuilder()
                .from(PhoneModel.TABLE_NAME)
                .select(PhoneModel._ID, PhoneModel.NUMBER, PhoneModel.CREATED_AT)
                .count(PhoneModel._ID, "count")
                .innerJoin(new String[]{SmsModel.TABLE_NAME, "user_sms"}, "user_sms.number", PhoneModel.NUMBER)
                .eq(PhoneModel._ID, "100")
                .like(PhoneModel.NUMBER, "79211234567")
                .contains(PhoneModel.NUMBER, "7921")
                .gt(PhoneModel.CREATED_AT, "2014-01-31 12:48:37.0")
                .gte(PhoneModel.CREATED_AT, "2014-01-31 12:48:37.0")
                .lt(PhoneModel.CREATED_AT, "2014-01-31 12:48:37.0")
                .lte(PhoneModel.CREATED_AT, "2014-01-31 12:48:37.0")
                .ne(PhoneModel.CREATED_AT, "2014-01-31 12:48:37.0")
                .is(PhoneModel.NUMBER, "79211234567")
                .isNot(PhoneModel.NUMBER, "79211234561")
                .where(PhoneModel.NUMBER, Where.EQUAL, "79211234567")
                .one();

        Timber.tag("SQL");
        Timber.i(sql);
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
