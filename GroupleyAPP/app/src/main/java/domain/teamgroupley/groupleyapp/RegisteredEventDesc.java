package domain.teamgroupley.groupleyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisteredEventDesc extends AppCompatActivity {

    private static final String TAG = "RegisteredEventDesc";
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    FirebaseUser user = mAuth.getCurrentUser();
    private String UserID = user.getUid();;
    private DatabaseReference myRef;

    public static int desnum;
    private int Eventtie = Registered_Events.regEventTitle;
    String Event = "Event";

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mTitle = mRootRef.child(UserID).child("RegisteredEvents").child(Event+Eventtie).child("Title");
    DatabaseReference mDesc = mRootRef.child(UserID).child("RegisteredEvents").child(Event+Eventtie).child("Description");
    DatabaseReference mCater = mRootRef.child(UserID).child("RegisteredEvents").child(Event+Eventtie).child("Category");
    DatabaseReference mDate = mRootRef.child(UserID).child("RegisteredEvents").child(Event+Eventtie).child("Date");
    DatabaseReference mTime = mRootRef.child(UserID).child("RegisteredEvents").child(Event+Eventtie).child("Time");
    DatabaseReference mAddress = mRootRef.child(UserID).child("RegisteredEvents").child(Event+Eventtie).child("Address");
    DatabaseReference mMax = mRootRef.child(UserID).child("RegisteredEvents").child(Event+Eventtie).child("Max_People");

    private EditText Titl;
    private EditText Descrip;
    private EditText Cater;
    private EditText Dat;
    private EditText Timy;
    private EditText Add;
    private EditText Maxppl;

    private Button Display;
    private Button Peoplechanging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_registered_event_desc);
        Descrip = (EditText)findViewById(R.id.des_txt_des_reg_des);
        Cater = (EditText)findViewById(R.id.Catergory_txt_reg_des);
        Dat = (EditText)findViewById(R.id.Date_txt_reg_des);
        Timy = (EditText)findViewById(R.id.time_txt_reg_des);
        Add = (EditText)findViewById(R.id.address_txt_reg_des);
        Maxppl = (EditText)findViewById(R.id.max_people_txt_reg_des);
        Titl = (EditText)findViewById(R.id.Title_txt_reg_des);
        Display = (Button)findViewById(R.id.DISplay_btn);
        Peoplechanging = (Button) findViewById(R.id.Attending);
        Display.setFocusable(true);
        Display.setFocusableInTouchMode(true);///add this line
        Display.requestFocus();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        Peoplechanging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desnum = Eventtie;
                startActivity(new Intent(RegisteredEventDesc.this, PeopleAttending.class));
            }
        });

        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

        mTitle.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                Titl.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDesc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                Descrip.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mCater.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                Cater.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                Dat.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mTime.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                Timy.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAddress.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                Add.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mMax.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String temp = dataSnapshot.getValue(String.class);
                Maxppl.setText(temp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}

