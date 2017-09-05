package com.neutti.webframe.gradle;

import org.gradle.api.file.FileCollection;
import org.gradle.api.tasks.Classpath;
import org.gradle.api.tasks.JavaExec;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class RunTask extends JavaExec {

    @Override
    public void exec() {
        setGroup("application");
        setMain("com.neutti.webframe.start.NeuttiWebApplication");
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();
        List files = new ArrayList();
        for(URL url: urls){
            files.add(url.getFile());
        }
        setClasspath(getProject().fileTree(files));
     /*   Classpath = sourceSets.main.runtimeClasspath
        main = "com.neutti.webframe.start.NeuttiWebApplication"
        group = 'application'*/
        super.exec();
    }

}
