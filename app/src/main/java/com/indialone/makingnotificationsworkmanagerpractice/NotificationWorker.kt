package com.indialone.makingnotificationsworkmanagerpractice

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.from
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    private val NOTIFICATION_ID = 888
    private lateinit var mNotificationManagerCompat: NotificationManagerCompat

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork(): Result {
        generateBigPictureStyleNotification()
//        generateCreateInboxTypeNotification()
        return Result.success()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateCreateInboxTypeNotification() {
        val notificationChannelId =
            NotificationUtils().createInboxStyleNotificationChannel(applicationContext)

        val inboxStyle = NotificationCompat.InboxStyle()
            .setBigContentTitle(InboxStyleNotificationMockData.mBigContentTitle)
            .setSummaryText(InboxStyleNotificationMockData.mSummaryText)

        for (summary in InboxStyleNotificationMockData.mIndividualEmailSummary()) {
            inboxStyle.addLine(summary)
        }

        val mainIntent = Intent(applicationContext, MainActivity::class.java)
        val mainPendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationCompatBuilder = NotificationCompat
            .Builder(applicationContext, notificationChannelId)

        notificationCompatBuilder.setStyle(inboxStyle)
            .setContentTitle(InboxStyleNotificationMockData.mContentTitle)
            .setContentText(InboxStyleNotificationMockData.mContentText)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.ic_launcher_background
                )
            )
            .setColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.purple_500
                )
            )
            .setSubText(InboxStyleNotificationMockData.mNumberOfNewEmails.toString())
            .setCategory(Notification.CATEGORY_EMAIL)
            .setPriority(InboxStyleNotificationMockData.mPriority)
            .setVisibility(InboxStyleNotificationMockData.mChannelLockscreenVisibility)


        for (name in InboxStyleNotificationMockData.mParticipants()) {
            notificationCompatBuilder.addPerson(name)
        }

        val notification = notificationCompatBuilder.build()
        from(applicationContext).notify(NOTIFICATION_ID, notification)

    }

    private fun generateBigPictureStyleNotification() {

        val notificationChannelId: String =
            NotificationUtils().createBigPictureStyleNotificationChannel(applicationContext)

        val bigPictureStyle =
            NotificationCompat.BigPictureStyle()
                .bigPicture(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        BigPictureStyleMockData.mBigImage
                    )
                )
                .setBigContentTitle(BigPictureStyleMockData.mBigContentTitle)
                .setSummaryText(BigPictureStyleMockData.mSummaryText)


        val mainIntent = Intent(applicationContext, MainActivity::class.java)

        val mainPendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationCompatBuilder = NotificationCompat.Builder(
            applicationContext, notificationChannelId
        )

        notificationCompatBuilder
            .setStyle(bigPictureStyle)
            .setContentTitle(BigPictureStyleMockData.mContentTitle)
            .setContentText(BigPictureStyleMockData.mContentText)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    applicationContext.resources,
                    R.drawable.ic_launcher_foreground
                )
            )
            .setContentIntent(mainPendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.purple_500
                )
            )
            .setSubText(1.toString())
            .setCategory(Notification.CATEGORY_SOCIAL)
            .setPriority(BigPictureStyleMockData.mPriority)
            .setVisibility(BigPictureStyleMockData.mChannelLockscreenVisibility)

        for (name in BigPictureStyleMockData.mParticipants()) {
            notificationCompatBuilder.addPerson(name)
        }

        val notification = notificationCompatBuilder.build()

        from(applicationContext).notify(NOTIFICATION_ID, notification)
    }


}