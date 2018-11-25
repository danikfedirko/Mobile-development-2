package com.example.danik.google_books_application.FragmentNavigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.danik.google_books_application.Fragments.ListItemFragment;
import com.example.danik.google_books_application.R;

public class FragmentNavigation {
    FragmentManager fragmentManager;

    public FragmentNavigation(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void setFragment(final Fragment fragment, final boolean addToBackStack) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace( R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack( null );
        }
        transaction.commit();
    }
    public void showBookDetailsFragment() {
        setFragment(new ListItemFragment(), true);
    }

}
