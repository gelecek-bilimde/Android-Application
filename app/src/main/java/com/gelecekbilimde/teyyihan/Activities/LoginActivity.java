package com.gelecekbilimde.teyyihan.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gelecekbilimde.teyyihan.Models.CurrentUserInfo;
import com.gelecekbilimde.teyyihan.R;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private ProgressBar progressBar;
    private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.login_progress);
        progressBar.setVisibility(View.GONE);
        mRef = FirebaseDatabase.getInstance().getReference("Users");


        findViewById(R.id.google_sign_in_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                signIn();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {

            Map<String,String> newUser = new HashMap<>();
            newUser.put("photoURL",String.valueOf(currentUser.getPhotoUrl()));
            newUser.put("email",currentUser.getEmail());
            newUser.put("displayName",currentUser.getDisplayName());
            saveUserToStaticVars(newUser);

            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

                Toast.makeText(getApplicationContext(),"Google'a giriş yapıldı",Toast.LENGTH_LONG).show();

            } catch (ApiException e) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Google hesabına giriş yapılırken bir hata oluştu",Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(),"Firebase'e giriş yapıldı",Toast.LENGTH_LONG).show();

                            FirebaseUser user = mAuth.getCurrentUser();

                            Map<String,String> newUser = new HashMap<>();
                            newUser.put("photoURL",String.valueOf(user.getPhotoUrl()));
                            newUser.put("email",user.getEmail());
                            newUser.put("displayName",user.getDisplayName());

                            //is user new
                            if (user.getMetadata().getCreationTimestamp() == user.getMetadata().getLastSignInTimestamp()) {

                                // save user to db
                                mRef.child(user.getUid()).setValue(newUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        newUser.put("userUID",user.getUid());
                                        saveUserToStaticVars(newUser);
                                        progressBar.setVisibility(View.GONE);
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                                    }
                                });


                            //is user old
                            } else {
                                newUser.put("userUID",user.getUid());
                                saveUserToStaticVars(newUser);
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                        }

                    }
                    //Firebase login failed
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Firebase'e giriş yapılırken bir hata oluştu"+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUserToStaticVars(Map<String,String> newUser) {
        CurrentUserInfo.userInfo = newUser;
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }




}
