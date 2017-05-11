package layout;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.smarthomes.smarthomes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfilesFragment } interface
 * to handle interaction events.
 * Use the {@link ProfilesFragment newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilesFragment extends Fragment {

    private List<ToggleButton> toggles = new ArrayList<>();
    private int previousIndex = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("onActivityCreated!");
        return inflater.inflate(R.layout.fragment_profiles, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        System.out.println("onActivityCreated!");
        super.onActivityCreated(savedInstanceState);
        toggles.clear();

        toggles.add((ToggleButton) getView().findViewById(R.id.homeToggle));
        toggles.add((ToggleButton) getView().findViewById(R.id.energySavingToggle));
        toggles.add((ToggleButton) getView().findViewById(R.id.absentToggle));
        toggles.add((ToggleButton) getView().findViewById(R.id.arrivingToggle));
        toggles.add((ToggleButton) getView().findViewById(R.id.safeModeToggle));
        toggles.add((ToggleButton) getView().findViewById(R.id.lightsOnToggle));

        for (ToggleButton toggle : toggles) {
            if (toggle == null) {
                System.out.println("Null ToggleButton: " + toggle.getId());
                return;
            }
        }

        toggles.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkToggleStates(0);
            }
        });
        toggles.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkToggleStates(1);
            }
        });
        toggles.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkToggleStates(2);
            }
        });
        toggles.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkToggleStates(3);
            }
        });
        toggles.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkToggleStates(4);
            }
        });
        toggles.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkToggleStates(5);
            }
        });

        toggles.get(0).callOnClick();
        System.out.println("ToggleListeners Added!");
    }

    private void checkToggleStates(int index) {
        if (previousIndex > -1 && index != previousIndex) {
            uncheckToggle(toggles.get(previousIndex));
        }
        checkToggle(toggles.get(index));
        previousIndex = index;
    }

    private void checkToggle(ToggleButton toggle) {
        toggle.setChecked(true);
        toggle.setBackground(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.profilesDark, null)));
    }

    private void uncheckToggle(ToggleButton toggle) {
        toggle.setChecked(false);
        toggle.setBackground(new ColorDrawable(ResourcesCompat.getColor(getResources(), R.color.profilesPrimary, null)));
    }
}
