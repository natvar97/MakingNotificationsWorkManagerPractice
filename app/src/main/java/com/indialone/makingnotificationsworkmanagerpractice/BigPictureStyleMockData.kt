package com.indialone.makingnotificationsworkmanagerpractice

import android.app.NotificationManager
import androidx.core.app.NotificationCompat

object BigPictureStyleMockData {

    const val mContentTitle = "Bob's Post"
    const val mContentText = "[Picture] Like my shot of Earth?"
    const val mPriority = NotificationCompat.PRIORITY_HIGH

    // Style notification values:
    const val mBigImage = R.drawable.ic_launcher_background
    const val mBigContentTitle = "Bob's Post"
    const val mSummaryText = "Like my shot of Earth?"

    // This would be possible responses based on the contents of the post.
    val mPossiblePostResponses = arrayOf<CharSequence?>("Yes", "No", "Maybe?")

    fun mParticipants(): ArrayList<String> {
        // If the phone is in "Do not disturb mode, the user will still be notified if
        // the user(s) is starred as a favorite.
        val list = ArrayList<String>()

        list.add("Bob Smith")

        return list
    }


    // Notification channel values (for devices targeting 26 and above):
    const val mChannelId = "channel_social_1"

    // The user-visible name of the channel.
    const val mChannelName = "Sample Social"

    // The user-visible description of the channel.
    const val mChannelDescription = "Sample Social Notifications"
    const val mChannelImportance = NotificationManager.IMPORTANCE_HIGH
    const val mChannelEnableVibrate = true
    const val mChannelLockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE

}