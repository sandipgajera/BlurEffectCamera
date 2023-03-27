package com.photo.blureffectcamera.pattern;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.photo.blureffectcamera.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class PatternDetailFragment extends Fragment {
    public static final String FRAGMENT_TAG = "PatternDetailFragment";
    static final String TAG = "PatternDetailAdapter";
    static final String folderName = "/pattern/";
    Context context;
    View downloadButton;
    boolean downloadMode;
    String imageUrl;
    private RecyclerView.LayoutManager layoutManager;
    PatternDetailGridAdapter myAdapter;
    OnClickListener myOnClickListener;
    String name;
    ProgressBar progressBar;
    View progressBarContainer;
    private RecyclerView recyclerView;
    TextView textView;
    String[] urlList;
    String zipUrl;

    /* renamed from: com.lyrebirdstudio.pattern.PatternDetailFragment.1 */
    class C07571 implements OnClickListener {
        C07571() {
        }

        public void onClick(View v) {
            int id = v.getId();
         if (id == R.id.button_pattern_detail_back) {
                PatternDetailFragment.this.getActivity().onBackPressed();
            }
        }
    }

    /* renamed from: com.lyrebirdstudio.pattern.PatternDetailFragment.3 */
    static class C07583 implements Comparator<File> {
        C07583() {
        }

        public int compare(File o1, File o2) {
            if (o1.lastModified() < o2.lastModified()) {
                return 1;
            }
            if (o1.lastModified() > o2.lastModified()) {
                return -1;
            }
            return 0;
        }
    }


    public PatternDetailFragment() {
        this.name = "";
        this.downloadMode = true;
        this.myOnClickListener = new C07571();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern_detail, container, false);
        this.context = view.getContext();
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_pattern_detail);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new GridLayoutManager(this.context, 4);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.myAdapter = new PatternDetailGridAdapter(this.context, this.urlList);
        this.recyclerView.setAdapter(this.myAdapter);
        if (this.imageUrl != null) {
//            Picasso.with(this.context).load(Uri.parse(this.imageUrl)).into((ImageView) view.findViewById(R.id.image_view_pattern_detail));
            ((TextView) view.findViewById(R.id.text_view_name_pattern_detail)).setText(this.name);
        }
        this.textView = (TextView) view.findViewById(R.id.text_view_pattern_download);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_bar_pattern_download);
        this.downloadButton.setOnClickListener(this.myOnClickListener);
        this.progressBarContainer = view.findViewById(R.id.pattern_detail_progress_container);
        view.findViewById(R.id.button_pattern_detail_back).setOnClickListener(this.myOnClickListener);
        return view;
    }

    void removeSelf() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.executePendingTransactions();
        int size = fm.getBackStackEntryCount();
        if (size > 2) {
            size = 2;
        }
        for (int i = 0; i < size; i++) {
            fm.popBackStack();
        }
    }

    public void zipDownload(Context context, String url, String folderId) {
        this.downloadButton.setVisibility(View.INVISIBLE);
        this.progressBarContainer.setVisibility(View.VISIBLE);
        if (context != null) {
            String fileName = url.substring(url.lastIndexOf(47) + 1, url.length());
            File f = getFilePath(fileName, context, folderId);
            if (f != null && f.getParentFile().isDirectory()) {
            }
        }
    }

    private String unpackZip(String path, String zipName) {
        String iconPath = null;
        try {
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(path + zipName)));
            byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
            while (true) {
                ZipEntry ze = zis.getNextEntry();
                if (ze != null) {
                    String filename = ze.getName();
                    if (filename.contains("icon")) {
                        iconPath = path + filename;
                    }
                    if (ze.isDirectory()) {
                        new File(path + filename).mkdirs();
                    } else {
                        FileOutputStream fout = new FileOutputStream(path + filename);
                        while (true) {
                            int count = zis.read(buffer);
                            if (count == -1) {
                                break;
                            }
                            fout.write(buffer, 0, count);
                        }
                        fout.close();
                        zis.closeEntry();
                    }
                } else {
                    zis.close();
                    return iconPath;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getFilePath(String filename, Context context, String folderId) {
        if (context == null) {
            return null;
        }
        File newfile = getDirectory(context, folderId);
        if (newfile != null) {
            return new File(newfile, filename);
        }
        return null;
    }

    public static File getDirectory(Context context, String folderId) {
        if (context == null) {
            return null;
        }
        File filesDir = context.getFilesDir();
        if (filesDir == null) {
            String dataDir = getDataDir(context);
            if (dataDir == null) {
                return null;
            }
            File newfile = new File(dataDir + folderName + folderId);
            newfile.mkdir();
            return newfile;
        }
        File newfile = new File(filesDir.getAbsolutePath() + folderName + folderId);
        newfile.mkdir();
        return newfile;
    }

    public static String getDataDir(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.dataDir + "/files";
        } catch (Exception e) {
            return null;
        }
    }

    public static void sortFiles(File[] files) {
        Arrays.sort(files, new C07583());
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            this.zipUrl = savedInstanceState.getString("zipUrl");
            this.name = savedInstanceState.getString("name");
            this.urlList = savedInstanceState.getStringArray("urlList");
            this.imageUrl = savedInstanceState.getString("imageUrl");
            if (this.myAdapter != null && this.urlList != null) {
                this.myAdapter.setData(this.urlList);
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("zipUrl", this.zipUrl);
        outState.putString("name", this.name);
        outState.putStringArray("urlList", this.urlList);
        outState.putString("imageUrl", this.imageUrl);
    }
}
