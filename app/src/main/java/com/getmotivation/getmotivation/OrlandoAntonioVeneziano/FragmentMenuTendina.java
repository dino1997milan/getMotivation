package com.getmotivation.getmotivation.OrlandoAntonioVeneziano;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentMenuTendina extends Fragment {
    String mail;
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_tendina_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        DrawerLayout drawerLayout = view.findViewById(R.id.drawerLayout);
        view.findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = view.findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        //attraverso questo metodo della classe NavigationView è possibile impostare i colori delle icone del menu di navigazione
        NavController navController = Navigation.findNavController(getActivity(),R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView,navController);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.mailUtenteLoggato);
        Log.d("FragmentMenuTendina",mail);
        sharedPreferences = getActivity().getSharedPreferences("save",MODE_PRIVATE);
        boolean salta = sharedPreferences.getBoolean("salta",false);

        if(salta){
            navUsername.setText("account di prova");
        }
        else{
            navUsername.setText(mail);}
    }

}
