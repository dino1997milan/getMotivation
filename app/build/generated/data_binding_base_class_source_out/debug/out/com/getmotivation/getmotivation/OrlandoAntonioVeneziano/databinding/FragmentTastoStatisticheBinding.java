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

public final class FragmentTastoStatisticheBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton dati;

  @NonNull
  public final MaterialTextView nomeDati;

  @NonNull
  public final MaterialButton stopRun;

  @NonNull
  public final ConstraintLayout tastiRun;

  private FragmentTastoStatisticheBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton dati, @NonNull MaterialTextView nomeDati,
      @NonNull MaterialButton stopRun, @NonNull ConstraintLayout tastiRun) {
    this.rootView = rootView;
    this.dati = dati;
    this.nomeDati = nomeDati;
    this.stopRun = stopRun;
    this.tastiRun = tastiRun;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentTastoStatisticheBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentTastoStatisticheBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_tasto_statistiche, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentTastoStatisticheBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dati;
      MaterialButton dati = ViewBindings.findChildViewById(rootView, id);
      if (dati == null) {
        break missingId;
      }

      id = R.id.nomeDati;
      MaterialTextView nomeDati = ViewBindings.findChildViewById(rootView, id);
      if (nomeDati == null) {
        break missingId;
      }

      id = R.id.stop_run;
      MaterialButton stopRun = ViewBindings.findChildViewById(rootView, id);
      if (stopRun == null) {
        break missingId;
      }

      ConstraintLayout tastiRun = (ConstraintLayout) rootView;

      return new FragmentTastoStatisticheBinding((ConstraintLayout) rootView, dati, nomeDati,
          stopRun, tastiRun);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
