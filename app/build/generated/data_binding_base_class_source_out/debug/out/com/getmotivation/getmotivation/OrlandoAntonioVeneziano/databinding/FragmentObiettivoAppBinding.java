// Generated by view binder compiler. Do not edit!
package com.getmotivation.getmotivation.OrlandoAntonioVeneziano.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public final class FragmentObiettivoAppBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton buttonBackObiettivoApp;

  @NonNull
  public final MaterialTextView testoObiettivoApp;

  @NonNull
  public final MaterialTextView titoloObbApp;

  private FragmentObiettivoAppBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton buttonBackObiettivoApp, @NonNull MaterialTextView testoObiettivoApp,
      @NonNull MaterialTextView titoloObbApp) {
    this.rootView = rootView;
    this.buttonBackObiettivoApp = buttonBackObiettivoApp;
    this.testoObiettivoApp = testoObiettivoApp;
    this.titoloObbApp = titoloObbApp;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentObiettivoAppBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentObiettivoAppBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_obiettivo_app, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentObiettivoAppBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonBackObiettivoApp;
      MaterialButton buttonBackObiettivoApp = ViewBindings.findChildViewById(rootView, id);
      if (buttonBackObiettivoApp == null) {
        break missingId;
      }

      id = R.id.testoObiettivoApp;
      MaterialTextView testoObiettivoApp = ViewBindings.findChildViewById(rootView, id);
      if (testoObiettivoApp == null) {
        break missingId;
      }

      id = R.id.titoloObbApp;
      MaterialTextView titoloObbApp = ViewBindings.findChildViewById(rootView, id);
      if (titoloObbApp == null) {
        break missingId;
      }

      return new FragmentObiettivoAppBinding((ConstraintLayout) rootView, buttonBackObiettivoApp,
          testoObiettivoApp, titoloObbApp);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
