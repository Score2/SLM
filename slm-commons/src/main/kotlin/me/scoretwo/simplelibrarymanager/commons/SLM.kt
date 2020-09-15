package me.scoretwo.simplelibrarymanager.commons

import me.scoretwo.simplelibrarymanager.commons.bootstrap.SLMMain
import me.scoretwo.simplelibrarymanager.commons.utils.FileUtils
import java.net.URL
import java.util.jar.JarFile

object SLM {

    lateinit var bootstrap: SLMMain

    val libraies = mutableListOf<URL>()


}