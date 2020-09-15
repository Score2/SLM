package me.scoretwo.simplelibrarymanager.commons.bootstrap

import java.io.File

interface SLMMain {

    fun getDataFolder(): File

    fun getPluginClassLoader(): ClassLoader

    fun getPluginFiles(): MutableList<File>

}