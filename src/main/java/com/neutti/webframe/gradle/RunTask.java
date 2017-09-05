package com.neutti.webframe.gradle;

import org.gradle.api.tasks.JavaExec;

public class RunTask extends JavaExec {
    @Override
    public void exec() {
        setGroup("application");
        setMain("com.neutti.webframe.start.NeuttiWebApplication");
        super.exec();
    }
}
