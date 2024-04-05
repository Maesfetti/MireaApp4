package com.example.myapp4;
import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FirstFragment extends Fragment {
    private final String ID = "channelID";
    private final int notificationId = 100;
    public FirstFragment() {
        super(R.layout.fragment_first);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Канал";
            String description = "Описание";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager)requireContext()
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        view.findViewById(R.id.toListFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String result = "Данные, переданные из FirstFragment в ListFragment";
                bundle.putString("listKey", result);
                Navigation.findNavController(v).navigate(R.id.action_FirstFrag_to_listFragment1, bundle);
            }
        });

        view.findViewById(R.id.toRecyclerFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String result = "Данные, переданные из StartFragment в RecyclerFragment";
                bundle.putString("recyclerKey", result);
                Navigation.findNavController(v).navigate(R.id.action_FirstFrag_to_recyclerFragment, bundle);
            }
        });

        view.findViewById(R.id.notificationButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("BusinessAnalysis")
                        .setContentText("There is new information about companies")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
                if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
                    return;
                Log.i("needed button", "gotovo");
                notificationManager.notify(notificationId, builder.build());

            }
        });

        return view;
    }
}