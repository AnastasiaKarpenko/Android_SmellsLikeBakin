package ws.tilda.anastasia.smellslikebakin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity
        implements ListFragment.OnRecipeSelectedInterface, GridFragment.OnRecipeSelectedInterface {
    public static final String LIST_FRAGMENT = "list_fragment ";
    public static final String VIEWPAGER_FRAGMENT = "viewpager_fragment ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isTablet = getResources().getBoolean(R.bool.is_tablet);
        if(!isTablet) {
            ListFragment savedFragment = (ListFragment) getFragmentManager().findFragmentByTag(LIST_FRAGMENT);
            if(savedFragment == null) {
                ListFragment fragment = new ListFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
                fragmentTransaction.commit();
            }

        } else {
            GridFragment savedFragment = (GridFragment) getFragmentManager().findFragmentByTag(LIST_FRAGMENT);
            if(savedFragment == null) {
                GridFragment fragment = new GridFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.placeHolder, fragment, LIST_FRAGMENT);
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    public void onListRecipeSelected(int index) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.placeHolder, fragment, VIEWPAGER_FRAGMENT);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onGridRecipeSelected(int index) {

    }
}
