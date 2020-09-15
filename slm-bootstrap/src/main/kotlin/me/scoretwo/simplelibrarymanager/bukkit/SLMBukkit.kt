package me.scoretwo.simplelibrarymanager.bukkit

import me.scoretwo.simplelibrarymanager.bootstrap.SLMBootstrap
import me.scoretwo.simplelibrarymanager.commons.bootstrap.SLMMain
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class SLMBukkit: JavaPlugin(), SLMMain {

    override fun onLoad() {
        SLMBootstrap(this)
    }

    override fun getPluginClassLoader(): ClassLoader {
        return super.getClassLoader()
    }

    override fun getPluginFiles(): MutableList<File> {
        val addressFile = File("address.yml")

        // support NewPaper's plugin load mode.
        if (addressFile.exists()) {
            val config = YamlConfiguration.loadConfiguration(addressFile)

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