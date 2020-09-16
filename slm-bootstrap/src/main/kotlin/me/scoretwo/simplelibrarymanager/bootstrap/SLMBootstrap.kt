package me.scoretwo.simplelibrarymanager.bootstrap

import me.scoretwo.simplelibrarymanager.commons.SLM
import me.scoretwo.simplelibrarymanager.commons.bootstrap.SLMMain
import me.scoretwo.simplelibrarymanager.commons.libraries.LibraryManager

class SLMBootstrap(slmPlugin: SLMMain) {

    init {
        SLM.bootstrap = slmPlugin
        LibraryManager.loadLibraries()
        LibraryManager.downloadLibraries()
        LibraryManager.includeLibraries()
    }

}