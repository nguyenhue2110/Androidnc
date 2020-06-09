package hue.com.mob201_ps08729;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import hue.com.mob201_ps08729.model.User;

public class DangNhapActivity extends AppCompatActivity {
EditText edtUser,edtPass;
Button btnLogin,btndky;
LoginButton btnface;
    CallbackManager callbackManager;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtUser= findViewById(R.id.edtUser);
        edtPass= findViewById(R.id.edtPass);
        btndky= findViewById(R.id.btndky);
        btnLogin= findViewById(R.id.btnLogin);
        btnface= findViewById(R.id.btnface);

        callbackManager = CallbackManager.Factory.create();

       btnface.setReadPermissions(Arrays.asList("public_profile","email","user_birthday","user_friends"));
       btnface.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
           @Override
           public void onSuccess(LoginResult loginResult) {

               String accesstoken= loginResult.getAccessToken().getToken();

               String chuoi= loginResult.getAccessToken().getUserId();
//               Toast.makeText(DangNhapActivity.this, chuoi, Toast.LENGTH_SHORT).show();
               Intent intent= new Intent(DangNhapActivity.this,MainActivity.class);
               startActivity(intent);
               GraphRequest request= GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                   @Override
                   public void onCompleted(JSONObject object, GraphResponse response) {

//                    Bundle facebook= getFacebookdata(object);
//                       Intent intent= new Intent(DangNhapActivity.this,MainActivity.class);
//                       startActivity(intent);
                   }
               });
           }

           @Override
           public void onCancel() {

           }

           @Override
           public void onError(FacebookException error) {

           }
       });
        FirebaseDatabase mdatabase= FirebaseDatabase.getInstance();
        final DatabaseReference table_user= mdatabase.getReference("User");
        btndky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DangNhapActivity.this,DangKyActivity.class);
                startActivity(intent);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog= new ProgressDialog(DangNhapActivity.this);
                mDialog.setMessage("Please waiting");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(edtUser.getText().toString().isEmpty()|| edtPass.getText().toString().isEmpty()){
                            mDialog.dismiss();
                            Toast.makeText(DangNhapActivity.this, "không bỏ trống", Toast.LENGTH_SHORT).show();
                        }else {
                            //check user not exits
                            if(dataSnapshot.child(edtUser.getText().toString()).exists()) {

                                mDialog.dismiss();
                                User user = dataSnapshot.child(edtUser.getText().toString()).getValue(User.class);

                                if (user.getPassWord().equals(edtPass.getText().toString())) {

                                    Intent intent= new Intent(DangNhapActivity.this, MainActivity.class);
                                    intent.putExtra("tendn",edtUser.getText().toString());
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(DangNhapActivity.this, "faild", Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                mDialog.dismiss();
                                Toast.makeText(DangNhapActivity.this, "User not exits", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
