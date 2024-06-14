package ru.mirea.gupalodr.mireaproject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CollageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CollageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // ПЕРЕМЕННЫЕ
    private ActivityResultLauncher<Intent> cameraActivityResultLauncher;
    private ImageView currentImage;
    private Uri imageUri;
    private boolean isWork = false;
    private int REQUEST_CODE_PERMISSION = 100;
    private int REQUEST_IMAGE_CAPTURE = 1;

    public CollageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CollageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CollageFragment newInstance(String param1, String param2) {
        CollageFragment fragment = new CollageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // проверка на разрешения
        int cameraPermissionStatus = ContextCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.CAMERA);
        int storagePermissionStatus = ContextCompat.checkSelfPermission(view.getContext(), android.Manifest.permission.
                WRITE_EXTERNAL_STORAGE);

        if  (cameraPermissionStatus == PackageManager.PERMISSION_GRANTED && storagePermissionStatus
                == PackageManager.PERMISSION_GRANTED)  {
            isWork = true;
        }  else  {
            requestPermissions( new String[] { android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            },  REQUEST_CODE_PERMISSION);
        }

        // установка фотограйии в коллаж
        ActivityResultCallback<ActivityResult> callback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Log.d("KKK", "callback\nuri = " + imageUri); // Удалено обращение к imageUri, которое не используется
                    if (imageUri!= null) {
                        currentImage.setImageURI(imageUri);
                    } else {
                        Toast.makeText(requireContext(), "imageUri == null", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        cameraActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                callback);

        // Обработчик нажатия на компонент «imageView»
        ImageView image1 = view.findViewById(R.id.image1);
        ImageView image2 = view.findViewById(R.id.image2);
        ImageView image3 = view.findViewById(R.id.image3);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KKK", "listener on image1");
                getPhoto(v);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KKK", "listener on image2");
                getPhoto(v);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("KKK", "listener on image3");
                getPhoto(v);
            }
        });
    }


    public void getPhoto(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (isWork) {
            try {
                currentImage = (ImageView) view;
                File photoFile = createImageFile();
                String authorities = requireContext().getPackageName() + ".fileprovider";
                imageUri = FileProvider.getUriForFile(requireContext(), authorities, photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                cameraActivityResultLauncher.launch(cameraIntent);
                Log.d("KKK", "get photo\nuri = " + imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
//            Log.d("KKK", "callback\nuri = " + imageUri);
//            if (imageUri!= null) {
//                currentImage.setImageURI(imageUri);
//            } else {
//                Toast.makeText(requireContext(), "imageUri == null", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            isWork = grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

}
