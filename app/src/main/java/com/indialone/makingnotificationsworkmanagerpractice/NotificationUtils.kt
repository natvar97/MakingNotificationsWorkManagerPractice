package com.indialone.makingnotificationsworkmanagerpractice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class NotificationUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    fun createInboxStyleNotificationChannel(context: Context): String {
        val channelId: String = InboxStyleNotificationMockData.mChannelId

        // The user-visible name of the channel.
        val channelName: CharSequence = InboxStyleNotificationMockData.mChannelName
        // The user-visible description of the channel.
        val channelDescription: String = InboxStyleNotificationMockData.mChannelDescription
        val channelImportance: Int = InboxStyleNotificationMockData.mChannelImportance
        val channelEnableVibrate: Boolean = InboxStyleNotificationMockData.mChannelEnableVibrate
        val channelLockscreenVisibility: Int =
            InboxStyleNotificationMockData.mChannelLockscreenVisibility

        val notificationChannel = NotificationChannel(channelId, channelName, channelImportance)
        notificationChannel.description = InboxStyleNotificationMockData.mChannelDescription
        notificationChannel.enableVibration(channelEnableVibrate)
        notificationChannel.lockscreenVisibility = channelLockscreenVisibility

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(notificationChannel)
        return channelId
    }

    fun createBigPictureStyleNotificationChannel(context: Context): String {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId: String = BigPictureStyleMockData.mChannelId

            val channelName: CharSequence = BigPictureStyleMockData.mChannelName
            val channelDescription: String = BigPictureStyleMockData.mChannelDescription
            val channelImportance: Int = BigPictureStyleMockData.mChannelImportance
            val channelEnableVibrate: Boolean = BigPictureStyleMockData.mChannelEnableVibrate
            val channelLockscreenVisibility: Int =
                BigPictureStyleMockData.mChannelLockscreenVisibility

            val notificationChannel = NotificationChannel(channelId, channelName, channelImportance)
            notificationChannel.description = channelDescription
            notificationChannel.enableVibration(channelEnableVibrate)
            notificationChannel.lockscreenVisibility = channelLockscreenVisibility

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
            return channelId
        } else {
            return ""
        }
    }

}