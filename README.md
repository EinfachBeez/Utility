# Utility

Java Utilities for known API's

## Dependencies

### Maven

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>me.einfachbeez.Utility</groupId>
        <artifactId>ARTIFACT</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
```

### Gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'me.einfachbeez.utility:ARTIFACT:VERSION'
}
```

## JDA-Manager

### Init

```java
JDA api = JDABuilder.createDefault(TOKEN),
        Arrays.asList(GatewayIntent.values()))
        .build();

JDAManager manager = new JDAManager(api);
```

