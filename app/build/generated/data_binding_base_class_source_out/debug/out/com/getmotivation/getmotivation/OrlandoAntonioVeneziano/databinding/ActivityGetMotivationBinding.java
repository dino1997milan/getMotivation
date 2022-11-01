// Generated by view binder compiler. Do not edit!
package com.getmotivation.getmotivation.OrlandoAntonioVeneziano.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.getmotivation.getmotivation.OrlandoAntonioVeneziano.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityGetMotivationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton stopMotivation;

  @NonNull
  public final VideoView videoMotivation;

  private ActivityGetMotivationBinding(@NonNull ConstraintLayout rootView,
      @NonNull MaterialButton stopMotivation, @NonNull VideoView videoMotivation) {
    this.rootView = rootView;
    this.stopMotivation = stopMotivation;
    this.videoMotivation = videoMotivation;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGetMotivationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGetMotivationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_get_motivation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGetMotivationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.stop_motivation;
      MaterialButton stopMotivation = ViewBindings.findChildViewById(rootView, id);
      if (stopMotivation == null) {
        break missingId;
      }

      id = R.id.videoMotivation;
      VideoView videoMotivation = ViewBindings.findChildViewById(rootView, id);
      if (videoMotivation == null) {
        break missingId;
      }

      return new ActivityGetMotivationBinding((ConstraintLayout) rootView, stopMotivation,
          videoMotivation);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}