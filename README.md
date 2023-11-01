

# **DiceRoller**
Roll Dice I think
*1.8 -> 1.20*

Maven!!!!!!!!!!!!!!!!!!!

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.s-wendel</groupId>
    <artifactId>DiceRolla</artifactId>
    <version>THE_VERSION</version>
</dependency>
```

Gradle!!!!!!!!!!!
```kotlin
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
  implementation 'com.github.s-wendel:DiceRolla:THE_VERSION'
}
```
