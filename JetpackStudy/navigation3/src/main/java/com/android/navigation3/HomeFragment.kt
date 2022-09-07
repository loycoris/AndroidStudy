package com.android.navigation3

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.Navigation

class HomeFragment : Fragment() {

    private var notificationId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val button = view?.findViewById<Button>(R.id.button)
        button?.setOnClickListener {
            sendNotification()
        }
    }

    private fun sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = activity?.let {
                NotificationChannel(
                    it.packageName,
                    "MyChannel",
                    NotificationManager.IMPORTANCE_DEFAULT
                ).also { it.description = "My NotificationChannel" }
            }
            activity?.let {
                if (channel != null) {
                    it.getSystemService(NotificationManager::class.java)
                        .createNotificationChannel(channel)
                }
            }
        }

        val notification = activity?.let {
            NotificationCompat.Builder(it, it.packageName)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Deep Link")
                .setContentText("点我试试...")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .build()
        }

        activity?.let {
            if (notification != null) {
                NotificationManagerCompat.from(it).notify(++notificationId, notification)
            }
        }
    }

    private fun getPendingIntent(): PendingIntent? {
        val args = HomeFragmentArgs.Builder()
            .setUserName("cais")
            .setAge(11)
            .build().toBundle()
        return activity?.let {
            Navigation.findNavController(it, R.id.button)
                .createDeepLink()
                .setGraph(R.navigation.my_nav_graph)
                .setDestination(R.id.detailFragment)
                .setArguments(args)
                .createPendingIntent()
        }
    }
}