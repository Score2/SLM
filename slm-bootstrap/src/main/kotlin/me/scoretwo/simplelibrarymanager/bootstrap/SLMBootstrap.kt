package me.scoretwo.simplelibrarymanager.bootstrap

import me.scoretwo.simplelibrarymanager.commons.SLM
import me.scoretwo.simplelibrarymanager.commons.bootstrap.SLMMain
import me.scoretwo.simplelibrarymanager.commons.libraries.LibraryManager

class SLMBootstrap() {

    constructor(slmPlugin: SLMMain) {
        SLM.bootstrap = slmPlugin
        init()
    }

    fun init() {
        LibraryManager.loadLibraries()
        LibraryManager.downloadLibraries()
        LibraryManager.includeLibraries()
    }

}