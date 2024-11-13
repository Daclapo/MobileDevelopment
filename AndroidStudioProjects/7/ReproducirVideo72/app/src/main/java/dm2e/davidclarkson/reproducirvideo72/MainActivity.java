package dm2e.davidclarkson.reproducirvideo72;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView miVideoView = (VideoView) findViewById(R.id.videoView1);
        MediaController mController = new MediaController(this);
        miVideoView.setMediaController(mController);
        miVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() +"/"+R.raw.goodtime));
        miVideoView.requestFocus();
    }
}