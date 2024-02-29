import com.google.firebase.FirebaseApp;

public class MyApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    FirebaseApp.initializeApp(this);
  }
}

//Here is an example of how to write data to the database:

import com.google.firebase.database.FirebaseDatabase;

public class MyActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FirebaseDatabase.getInstance().getReference("mydata")
      .setValue("Hello, Firebase!");
  }
}

//You can listen for changes in the database using a ValueEventListener:

import com.google.firebase.database.ValueEventListener;

public class MyActivity extends AppCompatActivity {
  private ValueEventListener mValueEventListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    FirebaseDatabase.getInstance().getReference("mydata")
      .addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          String value = dataSnapshot.getValue(String.class);
          Log.d("MyActivity", "Value is: " + value);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          Log.w("MyActivity", "Listen failed: " + databaseError.toException());
        }
      });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (mValueEventListener != null) {
      FirebaseDatabase.getInstance().getReference("mydata")
        .removeEventListener(mValueEventListener);
    }
  }
}