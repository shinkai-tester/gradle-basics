package com.example

import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.nio.file.Files

/**
 * Task that counts the number of JAR files specified in `allJars`
 * and writes the result to the file defined in `countFile`.
 */


// @CacheableTask
abstract class JarCount : DefaultTask() {
    // ConfigurableFileCollection <- @InputFiles
    // RegularFileProperty <- @InputFile
    // DirectoryProperty <- @InputDirectory

    @get:InputFiles
    abstract val allJars: ConfigurableFileCollection

    @get:OutputFile
    abstract val countFile: RegularFileProperty

    @TaskAction
    fun doCount() {
        logger.lifecycle("Executing JarCount task to count JAR files")

        val jarFiles: Set<File> = allJars.files
        val count: Int = jarFiles.size
        val out: File = countFile.get().asFile

        if (jarFiles.isEmpty()) {
            logger.warn("No JAR files found!")
        }

        Files.write(out.toPath(), listOf("$count"))

        logger.lifecycle("Counted $count JAR files. Result saved to ${out.absolutePath}")
    }
}
