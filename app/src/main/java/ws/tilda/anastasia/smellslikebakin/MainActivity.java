package ws.tilda.anastasia.smellslikebakin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ListFragment.OnRecipeSelectedInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment fragment = new ListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment);

        fragmentTransaction.commit();

    }

    @Override
    public void onListRecipeSelected(int index) {
        Toast.makeText(MainActivity.this, Recipes.names[index], Toast.LENGTH_SHORT).show();

    }
}
