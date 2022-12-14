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

public final class FragmentSceltaPauseBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText secondiGetMotivation;

  @NonNull
  public final EditText secondiRun;

  @NonNull
  public final MaterialButton setModifiche;

  @NonNull
  public final LinearLayout setMotivation;

  @NonNull
  public final LinearLayout setRun;

  @NonNull
  public final MaterialTextView testoScelta;

  private FragmentSceltaPauseBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText secondiGetMotivation, @NonNull EditText secondiRun,
      @NonNull MaterialButton setModifiche, @NonNull LinearLayout setMotivation,
      @NonNull LinearLayout setRun, @NonNull MaterialTextView testoScelta) {
    this.rootView = rootView;
    this.secondiGetMotivation = secondiGetMotivation;
    this.secondiRun = secondiRun;
    this.setModifiche = setModifiche;
    this.setMotivation = setMotivation;
    this.setRun = setRun;
    this.testoScelta = testoScelta;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSceltaPauseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSceltaPauseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_scelta_pause, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSceltaPauseBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.secondiGetMotivation;
      EditText secondiGetMotivation = ViewBindings.findChildViewById(rootView, id);
      if (secondiGetMotivation == null) {
        break missingId;
      }

      id = R.id.secondiRun;
      EditText secondiRun = ViewBindings.findChildViewById(rootView, id);
      if (secondiRun == null) {
        break missingId;
      }

      id = R.id.setModifiche;
      MaterialButton setModifiche = ViewBindings.findChildViewById(rootView, id);
      if (setModifiche == null) {
        break missingId;
      }

      id = R.id.setMotivation;
      LinearLayout setMotivation = ViewBindings.findChildViewById(rootView, id);
      if (setMotivation == null) {
        break missingId;
      }

      id = R.id.setRun;
      LinearLayout setRun = ViewBindings.findChildViewById(rootView, id);
      if (setRun == null) {
        break missingId;
      }

      id = R.id.testoScelta;
      MaterialTextView testoScelta = ViewBindings.findChildViewById(rootView, id);
      if (testoScelta == null) {
        break missingId;
      }

      return new FragmentSceltaPauseBinding((ConstraintLayout) rootView, secondiGetMotivation,
          secondiRun, setModifiche, setMotivation, setRun, testoScelta);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
