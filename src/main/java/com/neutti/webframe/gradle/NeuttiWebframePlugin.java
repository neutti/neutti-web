package com.neutti.webframe.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.JavaExec;
import org.gradle.api.tasks.SourceSet;

import java.util.concurrent.Callable;

public class NeuttiWebframePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        JavaExec run = project.getTasks().create("neuttiWebAppRunTask", JavaExec.class);
        run.setDescription("Runs this project as a JVM application");
        run.setGroup("application");

        run.setMain("com.neutti.webframe.start.NeuttiWebApplication");
        JavaPluginConvention javaPluginConvention = project.getConvention().getPlugin(JavaPluginConvention.class);
        run.setClasspath(javaPluginConvention.getSourceSets().getByName(SourceSet.MAIN_SOURCE_SET_NAME).getRuntimeClasspath());
    }
}