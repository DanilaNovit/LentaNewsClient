package com.novitsky.lentanewsclient.navigation

import com.novitsky.lentanewsclient.activities.ActivityConfigurationModel

interface ConfigHandler {
    fun configure(config: ActivityConfigurationModel)
}