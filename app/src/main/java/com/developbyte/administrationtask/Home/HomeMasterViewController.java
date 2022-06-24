package com.developbyte.administrationtask.Home;

import android.os.Bundle;
import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.developbyte.administrationtask.Abstract.AbstractViewController;
import com.developbyte.administrationtask.Interfaces.IMasterViewController;
import com.developbyte.administrationtask.App.InjectionManager;
import com.developbyte.administrationtask.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;

public class HomeMasterViewController extends AppCompatActivity implements IMasterViewController {

    private HashMap<Integer, AbstractViewController> fragments;
    private ArrayList<Integer> tags;

    public static final int HOME_CONTROLLER = 0;
    public static final int LISTTASK_CONTROLLER = 1;
    public static final int NEWPROJECT_CONTROLLER = 2;
    public static final int INFOPROJECT_CONTROLLER = 3;


    @IntDef({HOME_CONTROLLER,LISTTASK_CONTROLLER,NEWPROJECT_CONTROLLER,INFOPROJECT_CONTROLLER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProfileControllers{}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragments = new HashMap<>();
        tags = new ArrayList<>();

        FragmentManager fr = getSupportFragmentManager();
        if (getSupportFragmentManager().getFragments() != null) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                if (fragment != null) {
                    fr.beginTransaction().remove(fragment).commit();
                    getSupportFragmentManager().executePendingTransactions();
                }
            }
        }

        InjectionManager.getInstance().startHome(this);

    }
    @Override
    public void addFragment(AbstractViewController fr) {
        fragments.put(fr.tag, fr);
    }

    @Override
    public void presetFragment(@ProfileControllers int tag) {
        tags.add(0,tag);
        Fragment fragment = fragments.get(tag);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.homeMasterView, fragment)
                .addToBackStack(null)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    @Override
    public boolean presetFragment2(@ProfileControllers int tag) {
        tags.add(0, tag);
        Fragment fragment = fragments.get(tag);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.homeMasterView, fragment)
                .addToBackStack(null)
                .commit();

        return getSupportFragmentManager().executePendingTransactions();
    }


    @Override
    public void presentMenu(int tag) {

    }

    @Override
    public void onBackPressed() {
        int index = tags.remove(0);
        fragments.get(index).onBackPressed();
    }

    @Override
    public void finishThis() {
        finish();
    }

}