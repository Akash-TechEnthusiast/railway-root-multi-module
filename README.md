To clean build the project, run the following command in your terminal:

from the root directory of the project:

gradle clean build

This command will remove any previous build artifacts and compile the project from scratch, ensuring that you have a fresh build.

To run the project after building, use the command:

gradle :railway-web:bootRun


steps to add new module in the project

create new module as same as railway-utils  example railway-payment

then include it in settings.gradle  (root project) like below

include ':railway-payment'

then add dependency in railway-web/build.gradle like below

implementation project(":railway-payment")



Example:

railway-root/
├── build.gradle              ← root project (manages versions)
├── settings.gradle
│
├── railway-core/             ← core module (library)
│   └── build.gradle
│
└── railway-web/              ← main Spring Boot app
└── build.gradle

🚫 Limitations of other modules (non-main / library modules)
⚠️ 1. Cannot apply the Spring Boot plugin
plugins {
id 'org.springframework.boot'
}


❌ Don’t do this in railway-core or any shared module.
✅ Only apply it in the main module (like railway-web).

Reason:
The Spring Boot plugin tries to create an executable JAR (bootJar) — but library modules don’t have a main() or @SpringBootApplication.
That’s why you’d get:

> Main class name has not been configured and it could not be resolved from classpath

⚠️ 2. Cannot run independently

You can’t run the core module with:

./gradlew :railway-core:bootRun


It doesn’t have a main class.
It only contains code (entities, repositories, services) reused by the main web module.

✅ Correct usage:

implementation project(":railway-core")


in the web module’s build.gradle.

⚠️ 3. Limited access to configuration files

Only the main module (railway-web) loads application.yml, application-dev.yml, etc.

The core module cannot have its own application.yml that gets automatically read.

✅ Workaround:
If the core module needs some config, expose it via Spring’s @ConfigurationProperties in the main module or use environment properties passed from the main app.

⚠️ 4. No embedded server

Non-main modules cannot start an embedded Tomcat, Jetty, or Undertow.
The main SpringBootApplication (in the web module) starts and manages the web server.

⚠️ 5. Classpath restrictions

Library modules depend only on what is declared in their own build.gradle — they don’t inherit dependencies from other modules unless explicitly declared in the root or dependency management.

✅ Declare needed dependencies explicitly:

// railway-core/build.gradle
dependencies {
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
}

⚠️ 6. No direct static resources or templates served

If your library module has files like /templates/*.html or /static/js/app.js, they will not be automatically served.
Only the main web module (spring-boot-starter-web + Boot plugin) serves static and template resources.

✅ Keep web-related files only inside the web module.

⚠️ 7. Can’t override Boot auto-configuration

Spring Boot scans packages relative to your @SpringBootApplication class.

That means if your main class is:

com.india.railway.IndianRailwayApplication


Spring scans only com.india.railway.* packages.

✅ So, ensure your core code (entities, repositories) also lives under this base package (e.g., com.india.railway.core).

Otherwise, you’d need:

@SpringBootApplication(scanBasePackages = "com.india")

⚠️ 8. Dependency cycle caution

Avoid mutual dependencies between modules:

railway-core → railway-web ❌
railway-web → railway-core ✅


Cyclic dependencies cause Gradle build errors and runtime classpath conflicts.

⚠️ 9. No profile-specific beans in library modules

Since only the main module activates Spring profiles (dev, prod), beans annotated like this:

@Profile("dev")


inside library modules will only work if those profiles are activated by the main app.
They can’t control profile activation themselves.

⚠️ 10. Testing limitation

You can run tests per module:

./gradlew :railway-core:test


✅ Works fine.
But only the main module’s tests can start the full Spring Boot context.


