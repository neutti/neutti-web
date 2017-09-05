package com.neutti.webframe.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;

public class NeuttiWebframePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().create("neuttiWebAppRunTask", RunTask.class);
    }
}