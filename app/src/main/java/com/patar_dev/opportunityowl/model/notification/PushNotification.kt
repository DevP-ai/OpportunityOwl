package com.patar_dev.opportunityowl.model.notification



data class PushNotification(
    val data : NotificationData,
    val to : String
)
