package com.example.danik.google_books_application.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.danik.google_books_application.Fragments.FavoritesFragment;
import com.example.danik.google_books_application.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.danik.google_books_application.Fragments.ListFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation_menu)
    protected BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationListener);

        setFragment(new ListFragment(), true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment=null;

                    switch (menuItem.getItemId()) {
                        case R.id.home_button:
                            selectedFragment=new ListFragment();
                            break;
                        case R.id.favorites_button:
                            selectedFragment=new FavoritesFragment();
                            break;
                    }
                    setFragment(selectedFragment, false);
                    return true;
                }
            };

    public void setFragment(final Fragment fragment, final boolean addToBackStack) {
        getSupportFragmentManager().beginTransaction();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();

    }

}
