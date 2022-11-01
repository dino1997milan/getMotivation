// Generated by view binder compiler. Do not edit!
package com.getmotivation.getmotivation.OrlandoAntonioVeneziano.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.getmotivation.getmotivation.OrlandoAntonioVeneziano.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentObbiettiviUtenteBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialTextView inserisci;

  @NonNull
  public final LinearLayout kcal;

  @NonNull
  public final LinearLayout km;

  @NonNull
  public final LinearLayout passi;

  @NonNull
  public final MaterialButton setModificheObbiettivi;

  @NonNull
  public final EditText testoKcal;

  @NonNull
  public final EditText testoKm;

  @NonNull
  public final EditText testoPassi;

  private FragmentObbiettiviUtenteBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialTextView inserisci, @NonNull LinearLayout kcal, @NonNull LinearLayout km,
      @NonNull LinearLayout passi, @NonNull MaterialButton setModificheObbiettivi,
      @NonNull EditText testoKcal, @NonNull EditText testoKm, @NonNull EditText testoPassi) {
    this.rootView = rootView;
    this.inserisci = inserisci;
    this.kcal = kcal;
    this.km = km;
    this.passi = passi;
    this.setModificheObbiettivi = setModificheObbiettivi;
    this.testoKcal = testoKcal;
    this.testoKm = testoKm;
    this.testoPassi = testoPassi;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentObbiettiviUtenteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentObbiettiviUtenteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_obbiettivi_utente, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentObbiettiviUtenteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.inserisci;
      MaterialTextView inserisci = ViewBindings.findChildViewById(rootView, id);
      if (inserisci == null) {
        break missingId;
      }

      id = R.id.kcal;
      LinearLayout kcal = ViewBindings.findChildViewById(rootView, id);
      if (kcal == null) {
        break missingId;
      }

      id = R.id.km;
      LinearLayout km = ViewBindings.findChildViewById(rootView, id);
      if (km == null) {
        break missingId;
      }

      id = R.id.passi;
      LinearLayout passi = ViewBindings.findChildViewById(rootView, id);
      if (passi == null) {
        break missingId;
      }

      id = R.id.setModificheObbiettivi;
      MaterialButton setModificheObbiettivi = ViewBindings.findChildViewById(rootView, id);
      if (setModificheObbiettivi == null) {
        break missingId;
      }

      id = R.id.testoKcal;
      EditText testoKcal = ViewBindings.findChildViewById(rootView, id);
      if (testoKcal == null) {
        break missingId;
      }

      id = R.id.testoKm;
      EditText testoKm = ViewBindings.findChildViewById(rootView, id);
      if (testoKm == null) {
        break missingId;
      }

      id = R.id.testoPassi;
      EditText testoPassi = ViewBindings.findChildViewById(rootView, id);
      if (testoPassi == null) {
        break missingId;
      }

      return new FragmentObbiettiviUtenteBinding((ConstraintLayout) rootView, inserisci, kcal, km,
          passi, setModificheObbiettivi, testoKcal, testoKm, testoPassi);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}