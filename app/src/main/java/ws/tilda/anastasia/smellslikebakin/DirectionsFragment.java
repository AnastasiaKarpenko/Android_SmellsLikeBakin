package ws.tilda.anastasia.smellslikebakin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;


public class DirectionsFragment extends Fragment {
    private static final String KEY_CHECKED_BOXES = "key_checked_boxes" ;
    private CheckBox[] mCheckboxes;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        View view = inflater.inflate(R.layout.fragment_directions, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.directionsLayout);
        String[] directions = Recipes.directions[index].split("`");
        mCheckboxes = new CheckBox[directions.length];
        boolean[] checkedBoxes = new boolean[mCheckboxes.length];
        // To check if there is an savedInstanceState and if it contains an array
        if(savedInstanceState != null
                && savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES) != null) {
            checkedBoxes = savedInstanceState.getBooleanArray(KEY_CHECKED_BOXES);

        }
        setUpCheckBoxes(directions, linearLayout, checkedBoxes);

        return view;

    }

    private void setUpCheckBoxes(String[] directions, ViewGroup container, boolean[] checkedBoxes) {
        int i = 0;
        for (String direction : directions) {
            mCheckboxes[i] = new CheckBox(getActivity());
            mCheckboxes[i].setPadding(8, 16, 8, 16);
            mCheckboxes[i].setTextSize(20f);
            //checkBox.setButtonDrawable(R.drawable.checkbox_colors);
            mCheckboxes[i].setText(direction);
            container.addView(mCheckboxes[i]);
            if(checkedBoxes[i]) {
                mCheckboxes[i].toggle();
            }
            i++;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //to store the state of each checkbox
        boolean[] stateOfCheckboxes = new boolean[mCheckboxes.length];
        int i = 0;
        for(CheckBox checkBox : mCheckboxes) {
            stateOfCheckboxes[i] = checkBox.isChecked();
            i++;
        }
        //put it in the bundle
        outState.putBooleanArray(KEY_CHECKED_BOXES, stateOfCheckboxes);
        super.onSaveInstanceState(outState);

    }
}
