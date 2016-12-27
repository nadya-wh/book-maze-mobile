package com.kolyadko_polovtseva.book_maze;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kolyadko_polovtseva.book_maze.entity.User;
import com.kolyadko_polovtseva.book_maze.util.CloudinaryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;


public class ProfileActivity extends BaseActivity {

    private ImageView imageView;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = new User();
        SharedPreferences myprefs = getApplicationContext().getSharedPreferences("user", MODE_APPEND);
        user.setLogin(myprefs.getString("login", ""));
        user.setImageUrl(myprefs.getString("imageUrl", ""));
        user.setFirstName(myprefs.getString("firstName", ""));
        user.setLastName(myprefs.getString("lastName", ""));

        TextView firstNameTextView = (TextView) findViewById(R.id.first_name_text_view);
        TextView lastNameTextView = (TextView) findViewById(R.id.last_name_text_view);
        imageView = (ImageView) findViewById(R.id.user_image);

        firstNameTextView.setText("First name: " + user.getFirstName());
        lastNameTextView.setText("Last name: " + user.getLastName());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ReservedBooksActivity.class));
            }
        });
        if (user.getImageUrl() != null) {
            DownloadUserImageTask downloadPictureTask = new DownloadUserImageTask();
            downloadPictureTask.execute(CloudinaryUtil.getInstance().generateImageUrl(user.getImageUrl(), 100, 150));
        }
    }

    class DownloadUserImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap image = null;
            try {
                java.net.URL url = new java.net.URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                image = BitmapFactory.decodeStream(input);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

}
