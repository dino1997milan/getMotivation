// Generated by view binder compiler. Do not edit!
package com.getmotivation.getmotivation.OrlandoAntonioVeneziano.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.getmotivation.getmotivation.OrlandoAntonioVeneziano.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegistrazioneBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ConstraintLayout activityRegistrazione;

  @NonNull
  public final TextView buttonLoginRegestrazioneXml;

  @NonNull
  public final TextView buttonPasswordDimenticataRegistrazioneXml;

  @NonNull
  public final MaterialButton buttonRegistrazioneRegXml;

  @NonNull
  public final TextInputEditText emailRegistrazione;

  @NonNull
  public final TextInputLayout emailRegistrazioneLayout;

  @NonNull
  public final ImageView imageReg;

  @NonNull
  public final LinearLayout linearLayoutReg;

  @NonNull
  public final TextInputEditText passwordRegistrazione;

  @NonNull
  public final TextInputLayout passwordRegistrazioneLayout;

  private ActivityRegistrazioneBinding(@NonNull ConstraintLayout rootView,
      @NonNull ConstraintLayout activityRegistrazione,
      @NonNull TextView buttonLoginRegestrazioneXml,
      @NonNull TextView buttonPasswordDimenticataRegistrazioneXml,
      @NonNull MaterialButton buttonRegistrazioneRegXml,
      @NonNull TextInputEditText emailRegistrazione,
      @NonNull TextInputLayout emailRegistrazioneLayout, @NonNull ImageView imageReg,
      @NonNull LinearLayout linearLayoutReg, @NonNull TextInputEditText passwordRegistrazione,
      @NonNull TextInputLayout passwordRegistrazioneLayout) {
    this.rootView = rootView;
    this.activityRegistrazione = activityRegistrazione;
    this.buttonLoginRegestrazioneXml = buttonLoginRegestrazioneXml;
    this.buttonPasswordDimenticataRegistrazioneXml = buttonPasswordDimenticataRegistrazioneXml;
    this.buttonRegistrazioneRegXml = buttonRegistrazioneRegXml;
    this.emailRegistrazione = emailRegistrazione;
    this.emailRegistrazioneLayout = emailRegistrazioneLayout;
    this.imageReg = imageReg;
    this.linearLayoutReg = linearLayoutReg;
    this.passwordRegistrazione = passwordRegistrazione;
    this.passwordRegistrazioneLayout = passwordRegistrazioneLayout;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegistrazioneBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegistrazioneBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_registrazione, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegistrazioneBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      ConstraintLayout activityRegistrazione = (ConstraintLayout) rootView;

      id = R.id.button_login_regestrazione_xml;
      TextView buttonLoginRegestrazioneXml = ViewBindings.findChildViewById(rootView, id);
      if (buttonLoginRegestrazioneXml == null) {
        break missingId;
      }

      id = R.id.button_password_dimenticata_registrazione_xml;
      TextView buttonPasswordDimenticataRegistrazioneXml = ViewBindings.findChildViewById(rootView, id);
      if (buttonPasswordDimenticataRegistrazioneXml == null) {
        break missingId;
      }

      id = R.id.button_registrazione_reg_xml;
      MaterialButton buttonRegistrazioneRegXml = ViewBindings.findChildViewById(rootView, id);
      if (buttonRegistrazioneRegXml == null) {
        break missingId;
      }

      id = R.id.email_registrazione;
      TextInputEditText emailRegistrazione = ViewBindings.findChildViewById(rootView, id);
      if (emailRegistrazione == null) {
        break missingId;
      }

      id = R.id.email_registrazione_layout;
      TextInputLayout emailRegistrazioneLayout = ViewBindings.findChildViewById(rootView, id);
      if (emailRegistrazioneLayout == null) {
        break missingId;
      }

      id = R.id.image_reg;
      ImageView imageReg = ViewBindings.findChildViewById(rootView, id);
      if (imageReg == null) {
        break missingId;
      }

      id = R.id.linear_layout_reg;
      LinearLayout linearLayoutReg = ViewBindings.findChildViewById(rootView, id);
      if (linearLayoutReg == null) {
        break missingId;
      }

      id = R.id.password_registrazione;
      TextInputEditText passwordRegistrazione = ViewBindings.findChildViewById(rootView, id);
      if (passwordRegistrazione == null) {
        break missingId;
      }

      id = R.id.password_registrazione_layout;
      TextInputLayout passwordRegistrazioneLayout = ViewBindings.findChildViewById(rootView, id);
      if (passwordRegistrazioneLayout == null) {
        break missingId;
      }

      return new ActivityRegistrazioneBinding((ConstraintLayout) rootView, activityRegistrazione,
          buttonLoginRegestrazioneXml, buttonPasswordDimenticataRegistrazioneXml,
          buttonRegistrazioneRegXml, emailRegistrazione, emailRegistrazioneLayout, imageReg,
          linearLayoutReg, passwordRegistrazione, passwordRegistrazioneLayout);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}