package cn.crabapples.main.activity.demo;

import android.os.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import cn.crabapples.main.R;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class DownloadDemoActivity extends AppCompatActivity {
    private final String TAG = "DownloadDemoActivity";
    private final String DOWNLOAD_URL = "http://z6stkux-www-photoshop-com.4pbxyvfbton.fgongbi01.cn" +
            "/5b14839c4fd2ddb4576d3bf8efac639a.RedAlert2_setup.rar" +
            "?ssig=0081e846ba8330aba92364247ba8420f7f851057&time_stamp=1621277920&fn=bd4ae0d1ae367bbbade5f5d8e8a27568";
    EditText urlView;
    EditText countView;
    LinearLayout progressBarGroupView;
    CountDownLatch countDownLatch;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_demo);
        urlView = findViewById(R.id.url);
        countView = findViewById(R.id.threadCount);
        progressBarGroupView = findViewById(R.id.progressBarGroup);
        handler = new Handler(getMainLooper(), (message) -> {
            showToast("下载完成");
            return false;
        });
    }


    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void start(View view) {
        progressBarGroupView.removeAllViews();
        String url = urlView.getText().toString();
        int threadCount = Integer.parseInt(countView.getText().toString());
        countDownLatch = new CountDownLatch(threadCount);
        Log.i(TAG, "线程数:" + threadCount);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < threadCount; i++) {
            Log.v(TAG, "创建view:" + i);
            layoutInflater.inflate(R.layout.module_progressbar, progressBarGroupView);
        }
        download(threadCount);
        listenDownload();
    }

    public void listenDownload() {
        new Thread(() -> {
            try {
                countDownLatch.await();
                Message message = new Message();
                handler.sendMessage(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void back(View view) {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void download(int threadCount) {
        new Thread(() -> {
            try {
                HttpURLConnection getInfoConnection = (HttpURLConnection) new URL(DOWNLOAD_URL).openConnection();
                getInfoConnection.setConnectTimeout(100000);
                long fileSize = getInfoConnection.getContentLengthLong();
                String fileName = getFileName(DOWNLOAD_URL);
                String path = getCacheDir().getAbsolutePath() + "/" + fileName;
                long blockSize = fileSize / threadCount;
                Log.v(TAG, "文件大小:" + fileSize);
                for (int i = 0; i < threadCount; i++) {
                    int finalI = i;
                    new Thread(() -> {
                        try {
                            long startIndex = finalI * blockSize;
                            long endIndex = (finalI + 1) * blockSize;
                            if (finalI == threadCount - 1) {
                                endIndex = fileSize;
                            }
                            String tempPath = getCacheDir().getPath() + "/" + Thread.currentThread().getName() + ".log";
                            File logFile = new File(tempPath);
                            if (logFile.exists() && logFile.length() > 0) {
                                // 读取下载记录文件将上一次下载的记录设置为本次下载开始位置
                                FileInputStream fileInputStream = new FileInputStream(logFile);
                                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                startIndex = Integer.parseInt(bufferedReader.readLine());
                                bufferedReader.close();
                            }
                            HttpURLConnection connection = (HttpURLConnection) new URL(DOWNLOAD_URL).openConnection();
                            connection.setRequestProperty("Range", String.format("bytes=%d-%d", startIndex, endIndex));
                            connection.setConnectTimeout(100000);
                            if (206 == connection.getResponseCode()) {
                                readStream(path, connection.getInputStream(), startIndex, finalI, blockSize);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void readStream(String path, InputStream inputStream, long startIndex, int threadNum, long blockSize) throws IOException {
        Log.v(TAG, "开始下载文件");
        String tempPath = getCacheDir().getPath() + "/" + Thread.currentThread().getName() + ".log";
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        file.seek(startIndex);
        byte[] data = new byte[1024 * 500];
        long count = 0;
        ProgressBar progressBar = (ProgressBar) progressBarGroupView.getChildAt(threadNum);
        progressBar.setMax((int) blockSize);
        progressBar.setProgress((int) (startIndex - threadNum * blockSize));


        for (int i = inputStream.read(data); i != -1; i = inputStream.read(data)) {
            file.write(data, 0, i);
            RandomAccessFile temp = new RandomAccessFile(tempPath, "rwd");
            count += i;
            progressBar.setProgress((int) (progressBar.getProgress() + count));
            temp.write(String.valueOf(count + startIndex).getBytes(StandardCharsets.UTF_8));
            temp.close();
            Log.d(TAG, Thread.currentThread().getName() + "下载:" + count);
        }
        Log.d(TAG, Thread.currentThread().getName() + "下载完成");
        inputStream.close();
        file.close();
        countDownLatch.countDown();
    }

    /**
     * 根据Url获取要下载的文件名
     *
     * @param url 下载地址
     * @return 文件名
     */
    private String getFileName(String url) {
        int start = url.lastIndexOf("/");
        int end = url.indexOf("?");
        // 解析url中最后一个/到?之间的内容作为文件名
        if (start != -1 && end != -1) {
            return url.substring(start + 1, end);
        }
        // 如果文件名解析失败则返回默认文件名
        return "file";
    }
}
