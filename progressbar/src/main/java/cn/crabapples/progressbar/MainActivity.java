package cn.crabapples.progressbar;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar progressBar1 = findViewById(R.id.progress1);
        final ProgressBar progressBar2 = findViewById(R.id.progress2);
        final SeekBar seekBar1 = findViewById(R.id.progress3);

        addProgressBar1(progressBar1).start();
        addProgressBar2(progressBar2).start();
        addProgressBar3(seekBar1).start();
    }

    Thread addProgressBar1(final ProgressBar progressBar) {
        return new Thread(() -> {
            try {
                while (progressBar.getProgress() < progressBar.getMax()) {
                    progressBar.incrementProgressBy(2);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    Thread addProgressBar2(final ProgressBar progressBar) {
        return new Thread(() -> {
            try {
                for (int progress = progressBar.getProgress(); progress <= progressBar.getMax(); progress++) {
                    progressBar.setProgress(progress);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    Thread addProgressBar3(final SeekBar seekBar) {
        return new Thread(() -> {
            try {
                while (seekBar.getProgress() < seekBar.getMax()) {
                    seekBar.incrementProgressBy(3);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        /**
         * @param seekBar  触发事件的对象
         * @param progress 进度
         * @param fromUser 是否是用户手动触发
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }
    }
}
