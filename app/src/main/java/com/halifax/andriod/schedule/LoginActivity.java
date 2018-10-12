package com.halifax.andriod.schedule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;

    //TextView variables
    private LinearLayout mProfileLayout;
    private TextView mTextViewEmail;
    private TextView mTextViewUserName;
    private ImageView mImageViewProfilePic;
    private SignInButton mSignInButton;

    //Google Account Sign in Configuration
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Bind UI widgets
        mTextViewEmail = (TextView) findViewById(R.id.email_text_view);
        mTextViewUserName = (TextView) findViewById(R.id.username_text_view);
        mImageViewProfilePic = (ImageView) findViewById(R.id.profile_picture_image_view);
        mProfileLayout = findViewById(R.id.Profile_Layout);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);


        ////Bind and set the dimensions of the sign-in button
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_button);
        mSignInButton.setSize(SignInButton.SIZE_STANDARD);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signIn();
            }
        });


    }

    private void signIn() {
        //Choose an account to log in
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        //Account is not null then user is logged in
        if (account != null) {
//            buttonLogout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    signOut();
//                }
//            });
//          buttonLogout.setVisibility(View.VISIBLE);

            mSignInButton.setVisibility(View.GONE);
            mTextViewEmail.setText(account.getEmail());
            mTextViewUserName.setText(account.getDisplayName());

            Picasso.get().load(account.getPhotoUrl()).fit().into(mImageViewProfilePic);
            mProfileLayout.setVisibility(View.VISIBLE);
            mSignInButton.setVisibility(View.GONE);

        }

        else {
            //user is not logged in
            // Set the dimensions of the sign-in button.
            mSignInButton.setSize(SignInButton.SIZE_WIDE);
            mSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });
            mSignInButton.setVisibility(View.VISIBLE);
            mProfileLayout.setVisibility(View.GONE);
            //buttonLogout.setVisibility(View.GONE);
        }

    }

}
