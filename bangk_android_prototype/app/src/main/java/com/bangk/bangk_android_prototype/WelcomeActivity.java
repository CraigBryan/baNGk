package com.bangk.bangk_android_prototype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bangk.bangk_android_prototype.NavDrawer.NavDrawerActivity;

public class WelcomeActivity extends AppCompatActivity {

    private enum SigninStatus {
        GOOD, CN_ONLY, PWD_ONLY, NEITHER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        View signinButton = findViewById(R.id.welcome_signin_button);
        signinButton.setOnClickListener(new WelcomeClickListener("sign_in"));

        View forgotPwdLink = findViewById(R.id.welcome_help_link);
        forgotPwdLink.setOnClickListener(
            new WelcomeClickListener("password_help")
        );

        View registerButton = findViewById(R.id.welcome_register_button);
        registerButton.setOnClickListener(
            new WelcomeClickListener("register_help")
        );
    }

    private void displayHelpToast(int stringId, int length) {
        Toast toast = Toast.makeText(
            this, getString(stringId), length
        );
        toast.show();
    }

    private void signIn() {
        EditText cardNumInput = (EditText) findViewById(R.id.cn_input);
        EditText passwordInput = (EditText) findViewById(R.id.pw_input);
        String cn = cardNumInput.getText().toString();
        String pwd = passwordInput.getText().toString();

        switch (getSigninStatus(cn, pwd)) {
            case GOOD:
                goToSignedInActivity();
                break;
            case CN_ONLY:
                displayHelpToast(
                    R.string.welcome_cn_no_pwd, Toast.LENGTH_SHORT
                );
                passwordInput.requestFocus();
                break;
            case PWD_ONLY:
                displayHelpToast(
                    R.string.welcome_pwd_no_cn, Toast.LENGTH_SHORT
                );
                passwordInput.setText("");
                cardNumInput.requestFocus();
                break;
            case NEITHER:
                displayHelpToast(
                    R.string.welcome_no_cn_no_pwd, Toast.LENGTH_SHORT);
                cardNumInput.requestFocus();
                break;
        }
    }

    private SigninStatus getSigninStatus(String cardNum, String password) {
        if (cardNum.equals("") && password.equals("")) {
            return SigninStatus.NEITHER;
        } else if (cardNum.equals("") && !password.equals("")) {
            return SigninStatus.PWD_ONLY;
        } else if (!cardNum.equals("") && password.equals("")) {
            return SigninStatus.CN_ONLY;
        } else {
            return SigninStatus.GOOD;
        }
    }

    private void goToSignedInActivity() {
        Intent intent = new Intent(this, NavDrawerActivity.class);
        intent.putExtra(
            NavDrawerActivity.FRAGMENT_TITLE_STRING,
            getResources().getString(R.string.view_accounts_title)
        );
        finish();
        startActivity(intent);
    }

    private class WelcomeClickListener implements View.OnClickListener {
        private String action;

        private WelcomeClickListener(String action) {
            this.action = action;
        }

        @Override
        public void onClick(View v) {
            switch(action) {
                case "sign_in":
                    signIn();
                    break;
                case "password_help":
                    displayHelpToast(
                        R.string.welcome_pwd_help, Toast.LENGTH_LONG
                    );
                    break;
                case "register_help":
                    displayHelpToast(
                        R.string.welcome_signup_help, Toast.LENGTH_LONG
                    );
                    break;
                default:
                    Log.e(
                        "baNGk ERROR",
                        "Unknown nav drawer action done: " + action
                    );
            }
        }
    }
}
