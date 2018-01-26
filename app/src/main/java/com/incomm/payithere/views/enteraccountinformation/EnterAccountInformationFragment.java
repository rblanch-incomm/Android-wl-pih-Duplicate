package com.incomm.payithere.views.enteraccountinformation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.incomm.payithere.PayItHereApplication;
import com.incomm.payithere.R;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.response.Form;
import com.incomm.payithere.models.services.response.PaymentPost;
import com.incomm.payithere.repositories.EnterAccountInformationRepository;
import com.incomm.payithere.services.BillerByIdService;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.confirmAccountDetails.ConfirmAccountDetailsFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

/**
 * Created by rblanch on 11/6/17.
 */

public class EnterAccountInformationFragment extends ViewFragment implements EnterAccountInformationMVP.View {

    @BindView(R.id.enter_account_information_edit_text_container)
    LinearLayout textContainerLayout;

//    @BindView(R.id.ctx_help_tv)
//    TextView contextualHelpLabel;
//
//    @BindView(R.id.ctx_help_container)
//    FrameLayout ctxHelpButton;

    @BindView(R.id.enter_account_information_instruction_text_tv)
    TextView instructionsView;

    @BindView(R.id.enter_account_information_biller_name_tv)
    TextView billerNameView;

    @BindView(R.id.global_big_button)
    LinearLayout submitButtonContainer;

    @BindView(R.id.button_textview)
    TextView submitButton;

    private MainTabInterface listener;
    private ImageLoader imageManager = ImageLoader.getInstance();
    private EnterAccountInformationPresenter presenter;
    private DisplayImageOptions imageOptions;
    private String billerToken, currentErrorFieldName, currentErrorCondition;
    private EditText[] editTexts;
    private boolean isConfirmField;

    public EnterAccountInformationFragment() {
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle passedInBundle = getArguments();
        if (passedInBundle != null) {
            billerToken = passedInBundle.getString("billerId");
        }
        presenter = new EnterAccountInformationPresenter(this, new EnterAccountInformationRepository(), new BillerByIdService());
        presenter.getSpecificBiller(billerToken);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_enter_account_information, container, false);
        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.NONE)
                .build();
        ButterKnife.bind(this, rootview);
        presenter.getViewElements();
        if(PayItHereApplication.isIsFromConfirmAcc()){
            presenter.getSpecificBiller(billerToken);
            PayItHereApplication.setIsFromConfirmAcc(false);
        }
        return rootview;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            listener = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainTabInterface");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.displayTitle();
        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        editTexts = null;
        listener = null;
    }

    @Override
    public void setupUi() {
        presenter.displayTitle();

        presenter.displayInstructionsText();
        presenter.displaySubmitButton();
        submitButtonContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int statusCode = validateForm();
                if(statusCode == 200) {
                    presenter.displayConfirmAccountDetailsView();
                } else {
                    presenter.showErrorMessage(Integer.toString(statusCode));
//                    displayErrorAlert(statusCode+"");
                }
            }
        });
    }

    @Override
    public void displayConfirmAccountDetailsView(PaymentPost paymentPost) {
        if (getActivity() != null) {
            ConfirmAccountDetailsFragment fragment = new ConfirmAccountDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("payment", paymentPost);
            bundle.putInt("anFormat", presenter.getANFormat());
            fragment.setArguments(bundle);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.create_payment_slip_root, fragment, "Confirm Account");
            transaction.addToBackStack("Confirm Account");
            transaction.commit();
        }
    }

    @Override
    public void generateConfirmAccountBundle() {
        JsonObject jsonObject = new JsonObject();
        int index = 0;
        for (Form field: presenter.getFormFields()) {
            EditText currentField = editTexts[index];
            currentField.setPadding(20,25,0,40);
            jsonObject.addProperty(field.getName(), currentField.getText().toString());
            if (ActivityUtils.checkNull(field.getRequiresConfirmation()).equals("true")) {
                index ++;
            }
            index ++;
        }
        presenter.getPaymentPost(presenter.getBillerId(), jsonObject);

    }

    public void displayErrorDialog(String errorCode) {
        if (getActivity() != null) {
            displayErrorAlert(errorCode);
        }
    }

    @Override
    public void displayAccountValidationError(String error) {
        displayAlert(error);
    }

    public void displayFormErrorDialog(String errorCode) {
        displayErrorAlert(errorCode, currentErrorFieldName, currentErrorCondition);
    }

    public int validateForm() {
        int index = 0;
        int statusCode = 200;
        currentErrorFieldName = "";
        for (Form expectedField: presenter.getFormFields()) {
            EditText currentField = editTexts[index];
            if (expectedField.getRequired().equals("true") && ActivityUtils.checkNull(currentField.getText().toString()).equals("")) {
                currentErrorFieldName = expectedField.getLabel();
                currentErrorCondition = "";
                return 20308;
            }
//            Incorrect Field Length
            if (Integer.parseInt(expectedField.getMinLen()) > currentField.getText().length()) {
                currentErrorFieldName = expectedField.getLabel();
                currentErrorCondition = "Minimum length: " + expectedField.getMinLen();
                return 20313;
            }
            if (Integer.parseInt(expectedField.getMaxLen()) < currentField.getText().length()) {
                currentErrorFieldName = expectedField.getLabel();
                currentErrorCondition = "Maximum length: " + expectedField.getMinLen();
                return 20313;
            }
//            Fields don't match
            if (ActivityUtils.checkNull(expectedField.getRequiresConfirmation()).equals("true")) {
                if (!ActivityUtils.checkNull(currentField.getText().toString()).equals(ActivityUtils.checkNull(editTexts[index+1].getText().toString()))) {
                    currentErrorFieldName = expectedField.getLabel();
                    currentErrorCondition = "";
                    return 20314;
                }
                index ++;
            }
            index ++;
        }
        return statusCode;
    }

    @Override
    public void setViewFeatures() {
//        if (presenter.displayContextualHelp() != null) {
//            contextualHelpLabel.setVisibility(View.VISIBLE);
//            contextualHelpLabel.setText(presenter.displayContextualHelp().getTitle());
//            contextualHelpLabel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    displayContextualHelp();
//                }
//            });
//        } else {
//            contextualHelpLabel.setVisibility(View.GONE);
//        }
    }

    @Override
    public void displayContextualHelp(String text) {
        FeatureContextualHelp featureContextualHelp = presenter.displayModifiedContextualHelp();
        featureContextualHelp.getHelpViewAndroid().setBodyText(presenter.displayContextualHelp().getHelpViewAndroid().getBodyText());
        featureContextualHelp.getHelpViewAndroid().setTitle(text);
        listener.showContextHelper(getActivity(), featureContextualHelp);
    }

    @Override
    public void displayTitle(String title) {
        listener.onSetToolbarTitle(title);
    }

    public void displayForm(List<Form> formFields) {
        List<EditText> editTextList = new ArrayList<>();
        if (getActivity() == null) {
            return;
        }
        for (Form form: formFields) {
            final EditText editText = generateFormField(form);
            isConfirmField = false;
            editTextList.add(editText);
            if (ActivityUtils.checkNull(form.getRequiresConfirmation()).equals("true")) {
                isConfirmField = true;
                final EditText editTextConfirmation = generateFormField(form);
                editTextConfirmation.setHint("Confirm " + form.getLabel());
                editTextList.add(editTextConfirmation);
            }
        }
        editTexts = new EditText[editTextList.size()];
        editTexts = editTextList.toArray(editTexts);
        presenter.displayBiller();
    }

    public EditText generateFormField(Form form) {
        RelativeLayout layout = new RelativeLayout(getActivity());
        final EditText editText = new EditText(getActivity());
        RelativeLayout.LayoutParams editParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 134);
        editParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        editParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        final String label = form.getLabel();
        editText.setHint(label);
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(Integer.parseInt(form.getMaxLen()));
        editText.setFilters(filters);
        editText.setMaxLines(1);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTypeface(Typeface.SANS_SERIF);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        editText.setPadding(20,25,0,40);

        layout.addView(editText, editParams);

        if (!isConfirmField) {
            ImageView icon = new ImageView(getActivity());
            String text = ActivityUtils.checkNull(presenter.displayContextualHelp().getHelpViewAndroid().getHelpImage());
            imageManager.displayImage(text, icon, imageOptions);
            RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            iconParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            iconParams.addRule(RelativeLayout.CENTER_VERTICAL);
            icon.setPadding(0,0,0,40);
            icon.setColorFilter(Color.parseColor(ColorSchemeManager.getInstance().getThemePrimary()));
            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displayContextualHelp(label);
                }
            });
            layout.addView(icon, iconParams);
        }
        textContainerLayout.addView(layout);
        return editText;
    }

    @Override
    public void displayBillerName(String name) {
        billerNameView.setText(name);
        billerNameView.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
        billerNameView.setBackgroundColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    public void displayInstructions(String instructions) {
        instructionsView.setText(instructions);
    }

    @Override
    public void displaySubmitButton(String buttonText) {
        submitButton.setText(buttonText);
        submitButton.setTextColor(Color.WHITE);
        submitButtonContainer.setBackgroundColor(Color.parseColor(ColorSchemeManager.getInstance().getPositiveButton()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}