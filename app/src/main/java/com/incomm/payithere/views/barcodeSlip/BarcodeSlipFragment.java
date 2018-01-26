package com.incomm.payithere.views.barcodeSlip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.incomm.payithere.R;
import com.incomm.payithere.factories.DialogFactory;
import com.incomm.payithere.managers.ColorSchemeManager;
import com.incomm.payithere.models.cms.FeatureContextualHelp;
import com.incomm.payithere.models.services.response.PaymentWithEreceipt;
import com.incomm.payithere.repositories.BarcodeSlipCMSRepository;
import com.incomm.payithere.services.PaymentDetailService;
import com.incomm.payithere.tasks.BarcodeGeneratorTask;
import com.incomm.payithere.tasks.OnTaskCompleted;
import com.incomm.payithere.utils.ActivityUtils;
import com.incomm.payithere.utils.GoogleAnalyticsUtils;
import com.incomm.payithere.utils.TemporaryDataManager;
import com.incomm.payithere.views.MainTabInterface;
import com.incomm.payithere.views.ViewFragment;
import com.incomm.payithere.views.locations.LocationsFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BarcodeSlipFragment extends ViewFragment implements BarcodeSlipMVP.View, OnTaskCompleted {

    @BindView(R.id.payment_amount_label_tv)
    TextView paymentAmountLabelTV;
    @BindView(R.id.final_total_label_tv)
    TextView paymentSlipLabelTV;
    @BindView(R.id.payment_amount_tv)
    TextView paymentAmountTV;
    @BindView(R.id.payment_fee_label_tv)
    TextView paymentFeeLabelTV;
    @BindView(R.id.payment_fee_tv)
    TextView paymentFeeTV;
    @BindView(R.id.payment_total_label_tv)
    TextView paymentTotalLabelTV;
    @BindView(R.id.payment_total_tv)
    TextView paymentTotalTV;
    @BindView(R.id.slip_expires_label_tv)
    TextView slipExpiresLabel;
    @BindView(R.id.slip_expires_date_tv)
    TextView slipExpiresDate;
    @BindView(R.id.final_total_tv)
    TextView finalTotalTV;
    @BindView(R.id.terms_tv)
    TextView termsTV;
    @BindView(R.id.payment_locations_button)
    TextView paymentLocationButton;
    @BindView(R.id.email_slip_button)
    TextView emailSlipButton;
    @BindView(R.id.ctx_help_container)
    LinearLayout ctxHelpContainer;
    @BindView(R.id.ctx_help_tv)
    TextView ctxHelpTV;
    @BindView(R.id.ctx_help_icon)
    ImageView ctxHelpIV;
    @BindView(R.id.save_for_later_link_tv)
    TextView saveLaterButton;
    @BindView(R.id.app_logo_iv)
    ImageView appLogoIV;
    @BindView(R.id.barcode_iv)
    ImageView barcodeIV;
    @BindView(R.id.barcode_tv)
    TextView barcodeTV;
    @BindView(R.id.instruction_sv)
    ScrollView scrollView;
    @BindView(R.id.limit_instruction_tv)
    TextView limitInstruction;
    @BindView(R.id.buttons_container)
    LinearLayout buttonsContainer;
    @BindView(R.id.save_divider_vw)
    View saveDivider;
    @BindView(R.id.fee_disclaimer_tv)
    TextView feeDisclaimer;
    @BindView(R.id.instructions_container_ll)
    LinearLayout instructionsContainer;

    private MainTabInterface mListener;
    private BarcodeSlipPresenter presenter;
    private DisplayImageOptions imageOptions;
    private String paymentId, fee, amount;
    private PaymentWithEreceipt payment;
    private NumberFormat numberFormat;
    private int currentFragment;
    private ImageLoader imageManager = ImageLoader.getInstance();
    private Bitmap barcode;
    private BarcodeGeneratorTask barcodeGeneratorTask;
//    private View saveDivider;// the divider should be removed if save is not shown

    public BarcodeSlipFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle passedInBundle = getArguments();
        if (passedInBundle != null) {
            paymentId = passedInBundle.getString("paymentId");
            currentFragment = getArguments().getInt("current_fragment");
        }
        numberFormat = NumberFormat.getCurrencyInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //TODO: Create a copy from the below xml, rename appropriately
        View rootView = inflater.inflate(R.layout.fragment_barcode_slip, container, false);
        ButterKnife.bind(this, rootView);
//        saveDivider = rootView.findViewById(R.id.save_divider_vw);
        imageOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(false)
                .imageScaleType(ImageScaleType.NONE)
                .build();
        presenter = new BarcodeSlipPresenter(this, new BarcodeSlipCMSRepository(),new PaymentDetailService());
        presenter.getViewElements();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainTabInterface) {
            mListener = (MainTabInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainTabInterface");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Disable the back button for this fragment
        presenter.setTitle();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if(currentFragment == R.id.create_payment_slip_root){
                        return true;
                    }else {}
                }
                return false;
            }
        });

        GoogleAnalyticsUtils.getInstance().sendData(getActivity(), presenter.getAnalyticsId());
    }

    @OnClick(R.id.payment_locations_button)
    public void OnLocationButtonClick(){
        LocationsFragment fragment = new LocationsFragment();
        presenter.setIsFromTab(false);
        showFragmentWithOutBundle(fragment,currentFragment,"locations",getFragmentManager());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        barcodeGeneratorTask.cancel(true);
        super.onPause();
    }

    @Override
    public void setupUi() {
        presenter.getPaymentDetails(paymentId);
        presenter.setTitle();
        presenter.getPaymentSlipLabel();
        presenter.getAccountLabel();
        presenter.getSaveLaterBtn();
        presenter.getPaymentAmountLabel();
        presenter.getFeeLabel();
        presenter.getTotalLabel();
        presenter.getBillDuelLabel();
        presenter.getSlipExipresLabel();
        presenter.getScanBarCodeText();
        presenter.getTermsAndConditions();
        presenter.getContextualHelp();
        presenter.getPaymentLocationsBtn();
        presenter.getEmailPaymentSlipBtn();
        presenter.getVanillaLogo();
        presenter.getContextualHelpIcon();
        presenter.getInstructionFooter();
        displayContextualHelpLabel();

    }

    @OnClick(R.id.save_for_later_link_tv)
    public void saveBtnClicked(){
        TabLayout tabhost = (TabLayout) getActivity().findViewById(R.id.tabs);
        tabhost.getTabAt(0).select();
    }

    @Override
    public void displayContextualHelpLabel() {
        ctxHelpTV.setText(presenter.getContextualHelp().getTitle());
        ctxHelpTV.setTextColor(Color.parseColor(ColorSchemeManager.getInstance().getThemePrimary()));
    }

    @Override
    public void displayContextualHelpIcon(String text) {
        ctxHelpIV.setColorFilter(Color.parseColor(ColorSchemeManager.getInstance().getThemePrimary()));
//        imageManager.displayImage(text,ctxHelpIV, imageOptions);
    }

    private void displayBarCode(Bitmap bitmap) {
        if (isAdded()) {
            Drawable drawable = new BitmapDrawable(getResources(), bitmap);
            barcodeIV.setImageDrawable(drawable);
        }
    }

    private void displayBarcodeNumber(String number) {
        barcodeTV.setText(number);
        barcodeTV.setTextColor(Color.BLACK);
    }

    private void displayTotal(String total) {
        finalTotalTV.setText(total);
        finalTotalTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
        paymentTotalTV.setText(total);
        paymentTotalTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    private void displayAmount(String amount) {
        paymentAmountTV.setText(amount);
        paymentAmountTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    private void displayBillerName(String name) {
//        billerNameTV.setText(name);
//        billerNameTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

//    private void displayAccountNumber(String number) {
//        accountNumberTV.setText(number);
//        accountNumberTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
//    }

    private void displayFee(String fee) {
        paymentFeeTV.setText(fee);
        paymentFeeTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void setViewFeatures() {

    }

    @Override
    public void displayPaymentSlip(String text) {
        paymentSlipLabelTV.setText(text);
        paymentSlipLabelTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayTitle(String title, String subtitle) {
        mListener.onSetToolbarTitle(title, subtitle);
    }

    @Override
    public void displaySaveLaterBtn(String text) {
        saveLaterButton.setText(text);
        saveLaterButton.setTextColor(Color.parseColor("#FFFFFF"));
        saveLaterButton.setBackgroundColor(Color.parseColor("#4E4E4E"));
        if(currentFragment == R.id.create_payment_slip_root){
            saveLaterButton.setVisibility(View.VISIBLE);
            saveDivider.setVisibility(View.VISIBLE);
        }else {
            saveDivider.setVisibility(View.GONE);
            saveLaterButton.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.ctx_help_container)
    public void ctxBtnClick(){
        mListener.showContextHelper(getActivity(), presenter.getContextualHelp());
    }

    @Override
    public void displayPaymentAmountLabel(String text) {
        paymentAmountLabelTV.setText(text);
        paymentAmountLabelTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayFeeLabel(String text) {
        paymentFeeLabelTV.setText(text);
        paymentFeeLabelTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));

    }

    @Override
    public void displayTotalLabel(String text) {
        paymentTotalLabelTV.setText(text);
        paymentTotalLabelTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayBillDuelLabel(String text) {
    }

    @Override
    public void displaySlipExipresLabel(String text) {
        slipExpiresLabel.setText(text);
        slipExpiresLabel.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displaySlipExpiresDate(String text) {
        slipExpiresDate.setText(ActivityUtils.determineExpirationDateBarcodeSlip(text));
        slipExpiresDate.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayScanBarCodeText(String text) {
//        scanBarcodeTV.setText(text);
//        scanBarcodeTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayTermsAndConditions(String text) {
        termsTV.setText(text);
        termsTV.setTextColor(Color.parseColor(presenter.getGeneralTextColor()));
    }

    @Override
    public void displayContextualHelp(FeatureContextualHelp feature) {
    }

    @Override
    public void displayPaymentLocationsBtn(String text) {
        paymentLocationButton.setText(text);
        paymentLocationButton.setTextColor(Color.parseColor("#FFFFFF"));
        paymentLocationButton.setBackgroundColor(Color.parseColor("#4E4E4E"));
    }

    @Override
    public void displayEmailPaymentSlipBtn(String text) {
        emailSlipButton.setText(text);
        emailSlipButton.setTextColor(Color.parseColor("#FFFFFF"));
        emailSlipButton.setBackgroundColor(Color.parseColor("#4E4E4E"));

        emailSlipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.emailPaymentSlip(payment);
            }
        });
    }

    @Override
    public void displayVanillaLogo(String text) {
        imageManager.displayImage(text,appLogoIV, imageOptions);
    }

    @Override
    public void displayViewElements(PaymentWithEreceipt payment) {
        this.payment = payment;
        presenter.setTitle();
        displayFee(payment.getEreceipt().getFee());
        fee = payment.getEreceipt().getFee();
//        displayAccountNumber(payment.getUserData().get("account_number"));
        displayBillerName(payment.getBiller().getName());
        displayAmount(payment.getEreceipt().getAmount());
        amount = payment.getEreceipt().getAmount();
        displayTotal(payment.getEreceipt().getTotal());
        displaySlipExpiresDate(payment.getEreceipt().getTimestamp());

        // fee and amount can't be null when this is called
        presenter.getInstructions();
        presenter.getFeeDisclaimer();

        // generating barcode from task and callback to fragment
        barcodeTV.setText(payment.getBarcode());
        barcodeGeneratorTask = new BarcodeGeneratorTask(barcodeIV.getWidth(), barcodeIV.getHeight(), this);
        Log.e("BARCODE HEIGHT", String.valueOf(barcodeIV.getHeight()));
        barcodeGeneratorTask.execute(payment.getBarcode());
        presenter.getEmailPaymentSlipBtn();
    }
/*
    @Override
    public void displayInstructions(String text) {
        String newText = modifyInstructions(text);
//        instructionsTV.setText(Html.fromHtml(newText));
        InternalStyleSheet css = new InternalStyleSheet();
        css.addRule("p","color:"+ColorSchemeManager.getInstance().getGeneralText());
        css.addRule("p","size:"+"1");
        css.addRule("font-family: sans-serif-light");
//        css.addRule("p", "font-size: 10.5px");
//        css.addRule("ol", "font-size: 10.5px");
//        css.addRule("ul", "font-size: 10.5px");
        css.addRule("p", "font-size: 18px");
        css.addRule("ol", "font-size: 18px");
        css.addRule("ul", "font-size: 18px");
        instructionsTV.setBackgroundColor(Color.parseColor("#F5F5F5"));
        instructionsTV.addStyleSheet(css);
        instructionsTV.loadMarkdown(newText);
    } */

//    @Override
    public void displayInstructions(List<String> texts) {
        for (int i = 0; i < texts.size(); i++) {
            String newText = ActivityUtils.checkNull(modifyInstructions(texts.get(i)));
            SpannableStringBuilder str = new SpannableStringBuilder(newText);

            // if statement to bold the dollar amount if in the string
            if (newText.contains(amount)) {
                final Pattern p = Pattern.compile(amount.substring(1));
                final Matcher matcher = p.matcher(newText);
                while (matcher.find()) {
                    str.setSpan(new StyleSpan(Typeface.BOLD), matcher.start()-1, matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setGravity(Gravity.CENTER_HORIZONTAL);

            TextView instructionIterator = new TextView(getContext());
            LinearLayout.LayoutParams iteratorParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            iteratorParams.setMarginStart(48);
            iteratorParams.setMarginEnd(16);
            instructionIterator.setTextSize(16);
            String iterator = i + 1 + ".";
            instructionIterator.setText(iterator);
            instructionIterator.setLayoutParams(iteratorParams);
            layout.addView(instructionIterator);

            TextView instructionText = new TextView(getContext());
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            instructionText.setTextSize(16);
//            instructionText.setGravity(Gravity.CENTER_HORIZONTAL);
//            instructionText.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            instructionText.setPadding(0, 0, 0, 24);
            instructionText.setText(str);
            instructionText.setLayoutParams(textParams);
            layout.addView(instructionText);

            instructionsContainer.addView(layout);
        }
    }

    public String modifyInstructions(String text) {
        String newText = text;
        newText = newText.replace("$DOLLAR_AMOUNT", ActivityUtils.checkNull(amount + "*"));
        return newText;
    }

    public void displayFeeDisclaimer(String text) {
        String newString = modifyFee(text);
        feeDisclaimer.setText(newString);
    }

    public String modifyFee(String text) {
        String newText = text;
        newText = newText.replace("$FEE_AMOUNT", ActivityUtils.checkNull(fee));
        return newText;
    }

    @Override
    public void displayEmailSuccessfullySentToast(String message, String buttonText) {
        String email = TemporaryDataManager.getInstance().getEmail();
        String formatedMessage = message.replace("$", email);
        DialogFactory.createOkAlertDialog(getContext(), "", formatedMessage, buttonText).show();
    }

    @Override
    public void showError(String error) {
        displayErrorAlert(error);
    }

    @Override
    public void onTaskCompleted(Bitmap bitmap) {
        displayBarCode(bitmap);
    }

    @Override
    public void displayInstructionFooterInformation(String text) {
        limitInstruction.setText(text);
    }
}
