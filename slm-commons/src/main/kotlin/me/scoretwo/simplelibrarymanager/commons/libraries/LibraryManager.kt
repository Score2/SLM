package me.scoretwo.simplelibrarymanager.commons.libraries

import me.scoretwo.simplelibrarymanager.commons.SLM
import me.scoretwo.simplelibrarymanager.commons.utils.FileUtils
import java.io.File
import java.net.URL
import java.net.URLClassLoader
import java.util.jar.JarFile

object LibraryManager {

    fun loadLibraries() {
        SLM.libraies.clear()
        for (file in SLM.bootstrap.getPluginFiles()) {
            val jarFile = JarFile(file)
            val entry = jarFile.getJarEntry("libraries.txt") ?: continue
            val urls = FileUtils.readString(jarFile.getInputStream(entry))

            urls.forEach { SLM.libraies.add(URL(it)) }
        }
    }

    fun downloadLibraries() {
        for (url in SLM.libraies) {
            downloadLibrary(url)
        }
    }

    fun includeLibraries() {
        for (file in File("${SLM.bootstrap.getDataFolder().name}/libs").listFiles()!!) {
            includeLibrary(file)
        }
    }

    @Synchronized
    fun includeLibrary(file: File) {
        try {
            val url = file.toURI().toURL()
            val method = URLClassLoader::class.java.getDeclaredMethod("addURL", URL::class.java)
            method.isAccessible = true
            method.invoke(SLM.bootstrap.getPluginClassLoader(), url)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun downloadLibrary(
        url: URL, file: File = File(
            "${SLM.bootstrap.getDataFolder().name}/libs", url.toString().substring(
                url.toString().lastIndexOf(
                    "/"
                ) + 1
            )
        )
    ) {
        try {
            println("[SLM | Download] Downloading library ${file.name}...")
            val connection = url.openConnection()
            connection.connectTimeout = 5 * 1000
            connection.readTimeout = 15 * 1000

            if (!file.exists()) {
                file.parentFile.mkdirs()
            }
            FileUtils.save(file, connection.getInputStream())
        } catch (e: Exception) {
            e.printStackTrace()
            println("[SLM | Download] Failed to download library ${file.name}!")
        }
    }

}