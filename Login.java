package ie.freetime.reddwarf;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import ie.freetime.reddwarf.Models.User;

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    static GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    String TAG = "LoginActivity";
    TextView googleButtonText;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    ImageView loginLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginLoading = (ImageView) findViewById(R.id.loginLoading);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton)findViewById(R.id.sign_in_button);
        googleButtonText = (TextView)signInButton.getChildAt(0);
        googleButtonText.setText("Enter Dwarfer");
        googleButtonText.setTextSize(18);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    Log.v(TAG, "onAuthStateChanged:signed_in: " +user.getUid());
                }
                else {
                    Log.v(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        checkLoginStatus();
    }

    @Override
    public void onBackPressed() {
        loginLoading.setVisibility(View.GONE);
        finishAffinity();
    }

    @Override
    public void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if(mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                loginLoading.setVisibility(View.VISIBLE);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                loginLoading.setVisibility(View.GONE);
                // Google Sign In failed, update UI appropriately
                // ...
            }

        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct){

        Log.v("Login", "firebaseWithGoogle: "+acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.v("Login", "signInWithCredential:onComplete: "+task.isSuccessful());

                        Log.v(TAG, "fromInsideFirebaseAuthWithGoogle: "+acct.getDisplayName());
                        onAuthSuccess();

                        if(!task.isSuccessful()){

                            Log.v("Login", "signinWithCredential", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                            loginLoading.setVisibility(View.GONE);

                        }
                    }
                });
    }

    public void onAuthSuccess(){

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        myRef.child("users").child(user.getUid()).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()){
                            User newUser = new User(user.getDisplayName(), user.getEmail());
                            myRef.child("users").child(user.getUid()).setValue(newUser);
                            Toast.makeText(getApplicationContext(), "Welcome aboard "+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Welcome back "+user.getDisplayName(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage() ,Toast.LENGTH_SHORT).show();
                        loginLoading.setVisibility(View.GONE);
                    }
                }
        );

        Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToMain);
    }

    private void checkLoginStatus() {


        //mAuth.addAuthStateListener(mAuthListener);
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {

            Log.v("Login", "onAuthStateChanged:signed_in: " + user.getUid());
            Intent goToHome = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(goToHome);

            Toast.makeText(getApplicationContext(), "Welcome back "+user.getDisplayName(), Toast.LENGTH_SHORT).show();
        } else {
        }
    }

    public static void logout(){

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {

                    }
                }
        );
        FirebaseAuth.getInstance().signOut();
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
