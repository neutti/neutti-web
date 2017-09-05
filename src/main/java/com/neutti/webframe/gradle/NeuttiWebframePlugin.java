package com.neutti.webframe.gradle;

import groovy.lang.Closure;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskContainer;

public class NeuttiWebframePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        TaskContainer container = project.getTasks();
        Task task = container.getByName("neuttiWebAppRun");
        container.create("neuttiWebAppRun5", (Closure) task);
    }
}