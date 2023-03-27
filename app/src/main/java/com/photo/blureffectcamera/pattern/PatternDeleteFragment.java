package com.photo.blureffectcamera.pattern;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.photo.blureffectcamera.R;

import java.io.File;
import java.util.ArrayList;

public class PatternDeleteFragment extends Fragment {
    public static final String FRAGMENT_TAG = "PatternDeleteFragment";
    static final String TAG = "PatternDeleteFragment";
    Context context;
    private ArrayList<String> data;
    private RecyclerView.LayoutManager layoutManager;
    private PatternDeleteListAdapter myAdapter;
    private RecyclerView recyclerView;

    /* renamed from: com.lyrebirdstudio.pattern.PatternDeleteFragment.2 */
    class C07542 implements OnClickListener {
        C07542() {
        }

        public void onClick(View v) {
            PatternDeleteFragment.this.getActivity().onBackPressed();
        }
    }

    /* renamed from: com.lyrebirdstudio.pattern.PatternDeleteFragment.3 */
    class C07553 implements DialogInterface.OnClickListener {
        C07553() {
        }

        public void onClick(DialogInterface dialog, int id) {
        }
    }

    /* renamed from: com.lyrebirdstudio.pattern.PatternDeleteFragment.4 */
    class C07564 implements DialogInterface.OnClickListener {
        final /* synthetic */ String val$item;
        final /* synthetic */ int val$position;

        C07564(String str, int i) {
            this.val$item = str;
            this.val$position = i;
        }

        public void onClick(DialogInterface dialog, int id) {
            File f = new File(this.val$item);
            if (f.exists() && PatternDeleteFragment.deleteRecursive(f)) {
                PatternHelper.sdIdList.remove(this.val$position);
                PatternDeleteFragment.this.createData();
                PatternDeleteFragment.this.myAdapter.setData(PatternDeleteFragment.this.data);
                PatternDeleteFragment.this.myAdapter.notifyDataSetChanged();
            }
        }
    }

    /* renamed from: com.lyrebirdstudio.pattern.PatternDeleteFragment.1 */
    class C13401 implements PatternDeleteListAdapter.ItemSelectedListener {
        C13401() {
        }

        public void onItemSelected(int index) {
            int position = index;
            PatternDeleteFragment.this.showSelectedArticle((String) PatternDeleteFragment.this.myAdapter.data.get(position), position);
        }
    }

    public PatternDeleteFragment() {
        this.data = new ArrayList();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pattern_delete, container, false);
        this.context = view.getContext();
        this.recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this.context);
        this.recyclerView.setLayoutManager(this.layoutManager);
        createData();
        this.myAdapter = new PatternDeleteListAdapter(this.context, this.data);
        this.myAdapter.setItemSelectedListener(new C13401());
        this.recyclerView.setAdapter(this.myAdapter);
        view.findViewById(R.id.button_pattern_online_back).setOnClickListener(new C07542());
        return view;
    }

    void createData() {
        this.data.clear();
        if (PatternHelper.sdIdList != null && PatternHelper.sdIdList.size() > 0) {
            for (int i = 0; i < PatternHelper.sdIdList.size(); i++) {
                this.data.add(PatternDetailFragment.getDirectory(getActivity(), "") + "/" + ((String) PatternHelper.sdIdList.get(i)));
            }
        }
    }

    @SuppressLint("ResourceType")
    private void showSelectedArticle(String item, int position) {
        new AlertDialog.Builder(this.context).setMessage(getString(R.string.save_image_lib_save_image_message)).setCancelable(true).setPositiveButton(getString(17039379), new C07564(item, position)).setNegativeButton(getString(17039360), new C07553()).create().show();
    }

    public static boolean deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            for (File child : fileOrDirectory.listFiles()) {
                deleteRecursive(child);
            }
        }
        return fileOrDirectory.delete();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            PatternHelper.sdIdList = savedInstanceState.getStringArrayList("sdList");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("sdList", PatternHelper.sdIdList);
    }
}
