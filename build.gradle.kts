import org.jetbrains.gradle.ext.settings
import org.jetbrains.gradle.ext.taskTriggers

plugins {
    alias(libs.plugins.moddevgradle) apply false
    alias(libs.plugins.loom) apply false
    alias(libs.plugins.forgegradle) apply false

    alias(libs.plugins.minotaur) apply false
    alias(libs.plugins.curseforgegradle) apply false

    id("project-setup") apply false
}