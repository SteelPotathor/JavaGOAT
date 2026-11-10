
# Second Year Uni Java Project : Matrimonial Agency

Team for the project : [L0rest](https://github.com/L0rest), [OG-mihawk](https://github.com/OG-Mihawk) and [SteelPotathor](https://github.com/SteelPotathor).  
Thanks to Mr. Panzoli (PHD) for guiding us through this project.

## Purpose :gem:

The main goal was to put in practice our knowledge of datastructures and algorithms.  
We wanted to create a simple and easy to use application where everything is pretty much guided.

### Mantainance 🛠

The project was revived in 2026 (branch `revival`): build fixed, bugs fixed, tests added.

### Getting Started :beginner:

Requirements: a **JDK 19 or newer** (tested with OpenJDK 20 on Windows 11) and an internet access for the
first build (Maven and the libraries are downloaded automatically). Nothing else has to be installed.

Run every command **from the project root** (the data files are read relative to it).

Windows (PowerShell):

```powershell
$env:JAVA_HOME = "C:\path\to\jdk-20"   # e.g. "$HOME\.jdks\openjdk-20" for a JDK downloaded by IntelliJ
.\mvnw.cmd clean javafx:run            # build and start the application
.\mvnw.cmd test                        # unit tests
```

Linux / macOS / Git Bash: same commands with `./mvnw`.

`JAVA_HOME` is required by the Maven wrapper. With an older JDK the build stops with
"JavaGOAT needs JDK 19 or newer".

In IntelliJ IDEA: open the folder as a Maven project, select a JDK ≥ 19, then run the Maven goal `javafx:run`.
If you prefer running `ApplicationStart` directly, add the VM option
`--add-exports=javafx.base/com.sun.javafx.event=org.controlsfx.controls` (needed by the search auto-completion)
and keep the project root as working directory.

Data: the profiles are stored in `src/main/java/com/example/javagoat/Model/storage/Profiles.dat`
(Java serialization), saved when quitting (exit button or window close). Another directory can be used with
the JVM option `-Djavagoat.dataDir=<directory>` (e.g. as a VM option of an IntelliJ run configuration). The distance table is recomputed at start-up; `Distances.dat` is no longer read.

The video shows almost every functionnalities : Youtube video coming soon!

### Graphics :art:

Special thanks to [OG-mihawk](https://github.com/OG-Mihawk) for the amazing UI.

### Disclaimer :x:

The calendar do not save and the stat box about "today's events" in the dashboard is factitious.
