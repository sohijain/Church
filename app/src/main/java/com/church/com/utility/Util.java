package com.church.com.utility;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.church.com.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class Util {
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd";
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    static Locale myLocale;
    private static ProgressDialog ProgressBar;

    public static int MAX_CAPACITY_OF_FORWARD = 6;


    public static void p(String source , String message)
    {
        Log.e(source+" ","--"+message+"--");
    }

    public static void ShowToast(Context context, String Message) {
        Toast.makeText(context, "" + Message, Toast.LENGTH_SHORT).show();

    }

    public static void initializeProgressBar(Activity parent) {
        if (ProgressBar!=null && ProgressBar.isShowing()) {
            ProgressBar.dismiss();
       /*     final ProgressDialog progressDialog = new ProgressDialog(parent);
            progressDialog.setMessage(parent.getString(R.string.labelLoading));
            progressDialog.setCancelable(false);
            ProgressBar = progressDialog;
            try {
                ProgressBar.show();
            }
            catch (WindowManager.BadTokenException e){
                e.printStackTrace();
            }*/
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(parent);
            progressDialog.setMessage(parent.getString(R.string.labelLoading));
            progressDialog.setCancelable(false);
            ProgressBar = progressDialog;
            try {
                if(ProgressBar != null)
                {
                    ProgressBar.show();
                }
            }
            catch (WindowManager.BadTokenException e){
                e.printStackTrace();
            }
        }


    }
    public static void initializeProgressBar(Activity parent, String message) {
        if (ProgressBar!=null && ProgressBar.isShowing()) {
            ProgressBar.dismiss();
        } else {
            final ProgressDialog progressDialog = new ProgressDialog(parent);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            ProgressBar = progressDialog;
            try {
                    ProgressBar.show();
            }
            catch (WindowManager.BadTokenException e){
                e.printStackTrace();
            }
        }

    }

    public static void closeProgressBar() {
        if (ProgressBar!=null && ProgressBar.isShowing()){
        ProgressBar.hide();
        ProgressBar.dismiss();
        }
    }

    public static void showInternetErrorToast(Context context) {
        Toast.makeText(context, context.getString(R.string.error_internet_connection), Toast.LENGTH_SHORT).show();

    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0|| str.equals("null") ;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean validateEditText(EditText[] fields) {
        for (int i = 0; i < fields.length; i++) {
            EditText currentField = fields[i];
            if (currentField.getText().toString().length() != 0) {
                return true;
            }
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), color), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static String getDateFromMillis(long yourmilliseconds)
    {
        String result = "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date resultdate = new Date(yourmilliseconds);
        result = sdf.format(resultdate)+"";

        Calendar nowTime = Calendar.getInstance();
        Calendar neededTime = Calendar.getInstance();
        neededTime.setTimeInMillis(yourmilliseconds);

        if ((neededTime.get(Calendar.YEAR) == nowTime.get(Calendar.YEAR)))
        {
            if ((neededTime.get(Calendar.MONTH) == nowTime.get(Calendar.MONTH)))
            {
                if (neededTime.get(Calendar.DATE) - nowTime.get(Calendar.DATE) == 1)
                {
                    //here return like "Tomorrow at 12:00"
                    return "Tomorrow";

                } else if (nowTime.get(Calendar.DATE) == neededTime.get(Calendar.DATE)) {
                    //here return like "Today at 12:00"
                    return "Today";

                } else if (nowTime.get(Calendar.DATE) - neededTime.get(Calendar.DATE) == 1) {
                    //here return like "Yesterday at 12:00"
                    return "Yesterday";

                } else {
                    return result;
                }
            } else {
                return result;
            }

        } else {
            return result;
        }

    }


    public static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_2);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }
    /*public static boolean isValidUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            return URLUtil.isValidUrl(url) && Patterns.WEB_URL.matcher(url).matches();
        } catch (MalformedURLException e) {

        }

        return false;
    }*/
    public static void hasSoftKeyBackgroundColorChange(Activity mContext) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = mContext.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(mContext.getResources().getColor(R.color.black));
            window.setNavigationBarColor(mContext.getResources().getColor(R.color.black));
        }
    }
    public static void setLocale(Context context, String localeName) {
        // if (!localeName.equals("en")) {
        myLocale = new Locale(localeName);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
          /*  Intent refresh = new Intent(this, MainActivity.class);
            refresh.putExtra(currentLang, localeName);
            startActivity(refresh);*/
       /* } else {
           // Toast.makeText(MainActivity.this, "Language already selected!", Toast.LENGTH_SHORT).show();
        }*/
    }
    public static void tintViewDrawable(Context context, TextView view, int color) {
        Drawable[] drawables = view.getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setColorFilter(context.getResources().getColor(color), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }
    public static void shareIntentCall(Context context, String message){
        String shareBody = message+".\n" +
                "";
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Arctik Circle");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent,"Share"));
    }

}
