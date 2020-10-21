package com.fewwind.plugin

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

public class PluginTransImpl implements Plugin<Project> {
    void apply(Project project) {
        def android = project.extensions.getByType(AppExtension)
        LifeCycleTransform transform = new LifeCycleTransform()
        android.registerTransform(transform)
        project.task('FewwindTask') {
            println "Hello gradle plugin"
        }
    }
}