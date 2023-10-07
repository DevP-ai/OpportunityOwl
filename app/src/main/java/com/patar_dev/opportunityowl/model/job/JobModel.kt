package com.patar_dev.opportunityowl.model.job

data class JobModel(
    val uid:String?="",
    val jobTitle:String?="",
    val companyName:String?="",
    val location:String?="",
    val salary:String?="",
    val description:String?="",
    val jobImage:String?="",
    val hrName:String?="",
    val hrProfession:String?="",
    val hrImage:String?=""
)