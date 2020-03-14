package com.example.bookdonation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class UploadBook extends AppCompatActivity {

    private Button mChooseButton;
    private Button mUploadButton;
    private EditText mImageName,mmob;
    private ProgressBar progressBar;
    private ImageView mImage;
    //private ProgressBar mProgressBar;
    public static Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mdbRef;
    private FirebaseAuth uploadAuth;
    private String mobi_num;
    String uid,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_book);

        mChooseButton = (Button)findViewById(R.id.chooseButton);
        mUploadButton = (Button) findViewById(R.id.uploadButton);
        mImageName =(EditText)findViewById(R.id.filename);
        mmob=findViewById(R.id.mob_num);
        mImage = (ImageView)findViewById(R.id.image);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        uid=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        progressBar=findViewById(R.id.progressBar);
        mChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });


        mUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadfile();
            }
        });
    }
    private String getExtension(Uri uri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private  void disableAll(){
        mChooseButton.setEnabled(false);
        mUploadButton.setEnabled(false);
        mImageName.setEnabled(false);
    }
    private  void enableAll(){
        mChooseButton.setEnabled(true);
        mUploadButton.setEnabled(true);
        mImageName.setEnabled(true);

    }
    private void uploadfile() {
        mobi_num=mmob.getText().toString();
        title=mImageName.getText().toString();
        try {
            Log.d("uploadfile", "just start");
            if (mImageUri != null) {

                progressBar.setVisibility(View.VISIBLE);
                disableAll();
                final StorageReference riversRef = mStorageRef.child("images/"+ UUID.randomUUID().toString());
                riversRef.putFile(mImageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                //   Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                Toast
                                        .makeText(UploadBook.this,
                                                "Image Uploaded!!",
                                                Toast.LENGTH_SHORT)
                                        .show();
                                String uid=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                                String dbref= FirebaseDatabase.getInstance().getReference("images").push().getKey();
                                Upload up_obj=new Upload(title,riversRef.toString(),uid,mobi_num);
                                FirebaseDatabase.getInstance().getReference("images").child(dbref).setValue(up_obj);
                                progressBar.setVisibility(View.INVISIBLE);
                                enableAll();
                                startActivity(new Intent(UploadBook.this,MainActivity.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                // ...
                                Log.d("exception inside", exception.toString());
                                progressBar.setVisibility(View.INVISIBLE);
                                enableAll();
                            }
                        });

            }
        }catch (Exception e){
            Log.d("uploadfile","atend"+e.toString());
        }
    }


    private void chooseImage()
    {
        Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,1);
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode==1 && resultCode==RESULT_OK){
            mImageUri=data.getData();
            mImage.setImageURI(mImageUri);
        }
    }




}
