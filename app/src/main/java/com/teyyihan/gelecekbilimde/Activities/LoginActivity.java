package com.teyyihan.gelecekbilimde.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.TwitterAuthProvider;
import com.teyyihan.gelecekbilimde.Models.CurrentUserInfo;
import com.teyyihan.gelecekbilimde.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Teooo";
    private static final int RC_SIGN_IN = 9001;
    private static final int TWITTER_SIGN_IN = 123;
    private boolean isNotificationOpened = false;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private ProgressBar progressBar;
    private DatabaseReference mRef;
    TwitterLoginButton twitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(getString(R.string.twitter_consumer_key),getString(R.string.twitter_consumer_secret));
        TwitterConfig twitterConfig = new TwitterConfig.Builder(this).twitterAuthConfig(authConfig).build();

        Twitter.initialize(twitterConfig);

        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.login_progress);
        progressBar.setVisibility(View.GONE);
        mRef = FirebaseDatabase.getInstance().getReference("Users");

        twitterLoginButton = findViewById(R.id.twitter_login_btn);

        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                signToFirebaseWithTwitterSession(result.data);
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });


        //Google sign in
        findViewById(R.id.google_sign_in_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);
                progressBar.setVisibility(View.VISIBLE);
                signIn();
            }
        });



        mAuth = FirebaseAuth.getInstance();
    }

    private void signToFirebaseWithTwitterSession(TwitterSession data) {

        AuthCredential twitterAuthCredential = TwitterAuthProvider.getCredential(data.getAuthToken().token,data.getAuthToken().secret);

        mAuth.signInWithCredential(twitterAuthCredential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                saveUserToFirebaseDB();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext()," "+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        if(getIntent().hasExtra("url") && !isNotificationOpened){


            findViewById(R.id.twitter_login_btn).setVisibility(View.GONE);
            findViewById(R.id.google_sign_in_btn).setVisibility(View.GONE);
            findViewById(R.id.textView).setVisibility(View.GONE);

            isNotificationOpened = true;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setData(Uri.parse((String) getIntent().getExtras().get("url")));
            startActivity(intent);
        }else{
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {

                Map<String,String> newUser = new HashMap<>();
                newUser.put("photoURL",String.valueOf(currentUser.getPhotoUrl()));
                newUser.put("email",currentUser.getEmail());
                newUser.put("displayName",currentUser.getDisplayName());
                newUser.put("providerID",currentUser.getProviderId());
                saveUserToStaticVars(newUser);

                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // Result returned from GOOGLE
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);


            } catch (ApiException e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Google hesabına giriş yapılırken bir hata oluştu" + e.getLocalizedMessage() , Toast.LENGTH_LONG).show();
            }
        } else {
            twitterLoginButton.onActivityResult(requestCode,resultCode,data);

        }

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct){


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            saveUserToFirebaseDB();


                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Bir hata oluştu",Toast.LENGTH_LONG).show();
                        }

                    }
                    //Firebase login failed
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Bir hata oluştu"+e.getMessage()+"   "+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUserToStaticVars(Map<String,String> newUser) {
        CurrentUserInfo.userInfo = newUser;
    }


    // for google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    void saveUserToFirebaseDB() {
        FirebaseUser user = mAuth.getCurrentUser();

        Map<String,String> newUser = new HashMap<>();
        newUser.put("photoURL",String.valueOf(user.getPhotoUrl()));
        newUser.put("email",user.getEmail());
        newUser.put("displayName",user.getDisplayName());
        newUser.put("providerID",user.getProviderId());

        // save user to db
        mRef.child(user.getUid()).setValue(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                newUser.put("userUID",user.getUid());
                saveUserToStaticVars(newUser);
                progressBar.setVisibility(View.GONE);
                System.out.println("teooo buradaaaaaa");
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Bir hata oluştu",Toast.LENGTH_LONG).show();
            }
        });
    }

}
