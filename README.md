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

### Interaction

#### Modal

Modals are a way to create fancy pop-up forms where specific users can submit data to the discord bot.
The `Modal` Class in the jda-manager makes it easy to create modals & gives you the possibility to 
easily access the data.

#### Create Modal & Register Modal

An example of how to create a new modal. The `super` call is used to pass the title and the modal id, 
which can be used later to access the modal again. With `addComponent` or `addComponents` you can add the TextInput 
objects to your modal. With `onSubmit` you can get the member, the list of values that were sent and the event 
(command / button) to thank the user for filling out the form for example.

```java
public class TestModal extends Modal {

    public TestModal() {
        super("Test Modal", "test");

        // Single Component
        TextInput name = TextInput.create("name", "Your name?", TextInputStyle.SHORT).setMaxLength(10).build();
        
        addComponent(member -> name);
        
        // Multiple Components
        TextInput skills = TextInput.create("skills", "Your skills?", TextInputStyle.PARAGRAPH).build();
        TextInput story = TextInput.create("story", "Your story?", TextInputStyle.PARAGRAPH).build();

        addComponents(skills, story);

        onSubmit((member, mappings, event) -> event.reply("Thanks " + mappings.get(0).getAsString() + "!").queue());
    }
}
```

```java
public class TestModalCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("testmodal")) return;
        TestModal modal = new TestModal();
        modal.open(event.getMember(), event);
    }
}
```

For example, to implement this example with /testmodal, after initializing JDA, the command 
must be registered & the `TestModalCommand` must be added as a listener.

```java
api.upsertCommand("testmodal", "This is a Test Modal Command").queue();
api.addEventListener(new TestModalCommand());
```