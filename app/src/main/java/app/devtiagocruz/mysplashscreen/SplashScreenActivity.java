package app.devtiagocruz.mysplashscreen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    boolean finishSplashScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen.installSplashScreen(this);

        // Set up an OnPreDrawListener to the root view.
        final View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        // Check if the initial data is ready.
                        if (finishSplashScreen) {
                            // The content is ready; start drawing.
                            content.getViewTreeObserver().removeOnPreDrawListener(this);

                            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                            finish();

                            return true;
                        } else {
                            // The content is not ready; suspend.
                            return false;
                        }
                    }
                });

        // 5 seconds timeout to hide splash screen
        new android.os.Handler(Looper.getMainLooper()).postDelayed(() -> finishSplashScreen = true, 5000);
    }
}