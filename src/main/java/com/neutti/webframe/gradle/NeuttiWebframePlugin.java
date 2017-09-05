package com.neutti.webframe.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class NeuttiWebframePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().create("neuttiWebAppRun");
    }
}