package me.scoretwo.simplelibrarymanager.bungee

import me.scoretwo.simplelibrarymanager.bootstrap.SLMBootstrap
import me.scoretwo.simplelibrarymanager.commons.bootstrap.SLMMain
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File
import java.util.jar.JarFile

class SLMBungee: Plugin(), SLMMain {

    override fun onLoad() {
        SLMBootstrap(this)
    }

    override fun getPluginClassLoader(): ClassLoader {
        return javaClass.classLoader
    }

    override fun getPluginFiles(): MutableList<File> {
        val addressFile = File("address.yml")

        // support NewPaper's plugin load mode.
        if (addressFile.exists()) {
            val config = ConfigurationProvider.getProvider(YamlConfiguration::class.java).load(addressFile)!!

            if (config.contains("plugins")) {
                val files = mutableListOf<File>()
                for (path in config.getStringList("plugins")) {
                    files.addAll(File(path).listFiles()!!)
                }

                return files
            }
        }

        return File("plugins").listFiles()!!.toMutableList()
    }

}