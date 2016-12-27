package com.kolyadko_polovtseva.book_maze;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kolyadko_polovtseva.book_maze.entity.Book;
import com.kolyadko_polovtseva.book_maze.entity.RegisterRecord;
import com.kolyadko_polovtseva.book_maze.entity.Response;
import com.kolyadko_polovtseva.book_maze.network.WebServiceCall;
import com.kolyadko_polovtseva.book_maze.util.AuthorUtil;
import com.kolyadko_polovtseva.book_maze.util.CloudinaryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;

import javax.net.ssl.HttpsURLConnection;


public class RegisterRecordActivity extends BaseActivity {

    private RegisterRecord registerRecord;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registerRecord = getIntent().getParcelableExtra("registerRecord");

        TextView nameTextView = (TextView) findViewById(R.id.name_text_view);
        TextView authorTextView = (TextView) findViewById(R.id.author_text_view);
        imageView = (ImageView) findViewById(R.id.book_image);
        TextView returnDeadLineTextView = (TextView) findViewById(R.id.return_deadline_text_view);
        TextView reserveDateTextView = (TextView) findViewById(R.id.reserve_date_text_view);
        CheckBox wasReturned = (CheckBox) findViewById(R.id.was_returned_checkbox);


        if (registerRecord != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Book book = registerRecord.getLibraryBook().getBook();
            nameTextView.setText("Name: " + book.getName());
            authorTextView.setText("Authors: " + AuthorUtil.toString(book.getAuthors()));
            wasReturned.setChecked(registerRecord.getWasReturned());
            String date = dateFormat.format(registerRecord.getReserveDate());
            reserveDateTextView.setText("Reserve date: " + date);
            returnDeadLineTextView.setText("Return deadline: " + dateFormat.format(registerRecord.getReturnDeadline()));
            DownloadPictureTask downloadPictureTask = new DownloadPictureTask();
            downloadPictureTask.execute(CloudinaryUtil.getInstance().generateImageUrl(book.getImageUrl(), 100, 150));
        }
    }


    class DownloadPictureTask extends AsyncTask<String, Void, Bitmap> {

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


    class ReserveBookTask extends AsyncTask<Void, Void, Boolean> {

        private String token;

        public ReserveBookTask(String token) {
            this.token = token;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            Book book = registerRecord.getLibraryBook().getBook();
            String urlParameters = "book=" + book.getIdBook() + "&userId=" + token;
            Response response = WebServiceCall.postTo("/user/books/reserved", urlParameters,
                    WebServiceCall.GET_METHOD_NAME);
            return response.getResponseCode() == HttpsURLConnection.HTTP_OK;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            String message = result ? "The book was reserved." : "The book was not reserved.";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}
