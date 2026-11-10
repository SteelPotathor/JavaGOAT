
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

### Data and matching :busts_in_silhouette:

The profiles and the calendar are stored in `data/profiles.json` and `data/calendar.json` (readable JSON,
saved when quitting with the exit button or the window close button). These working files are not versioned:
on the first start they are created from the data shipped in `data/seed/`, and deleting one resets it.
The shipped profiles are 12 hand-written **showcase profiles** that demonstrate the algorithm, followed by
488 profiles produced by a reproducible generator (`.\mvnw.cmd compile exec:java`). The formats, the
generator and the settings are described in [data/README.md](data/README.md). Another data directory can be used with the JVM option
`-Djavagoat.dataDir=<directory>` (e.g. as a VM option of an IntelliJ run configuration).

Matching: each person states, for every criterion (age and size ranges, IQ, smoker, alcohol, sport, diet,
religion, body build, hair, ethnicity), the accepted values and an importance: *Indifferent*,
*Nice to have*, *Important* or *Required* (eliminatory). The sexes looked for are always eliminatory.
For a candidate, each criterion gives a satisfaction between 0 and 1 (partial outside a range or for a
neighbouring value such as "occasionally" instead of "never"); common passions are added. The
**compatibility** is the geometric mean of the satisfaction of both people, so a suggestion has to suit
both of them. The matching page shows the 5 best compatible people not matched yet (blue arrow: how well
the candidate fits the selected person, red arrow: the other way round). Weights and tolerances are in
`matching.properties` and can be overridden in `data/matching.properties`.

Quick demo (shipped data): open the matching page of **Camille Moreau** (first row of the search page): Thomas comes first;
Hugo (smoker) and Julien (44) are never suggested; see the `note` of each showcase profile.

The video shows almost every functionnalities : Youtube video coming soon!

### Graphics :art:

Special thanks to [OG-mihawk](https://github.com/OG-Mihawk) for the amazing UI.

### Disclaimer :x:

The notifications of the dashboard are kept for the current session only.

The dashboard statistics are computed from the saved data: today's matches (match dates), today's new
profiles (creation date, recorded for the profiles created in the application) and today's events
(calendar, recurring events included).
