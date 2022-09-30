package me.einfachbeez.utility.jda.util;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.util.List;

/**
 * @author EinfachBeez | https://github.com/EinfachBeez
 */
public class AutoComplete {

    /**
     * Filter all possible choises based on the user's current input
     *
     * <p><b>Example</b><br>
     * <pre>
     * {@code  return event.replyChoises(AutoComplete.filterChoises(event, choises));}
     * </pre>
     * @param event {@link CommandAutoCompleteInteractionEvent}
     * @param choices List of {@link Command.Choice}
     * @return Filtered List of {@link Command.Choice}
     */
    public static List<Command.Choice> filterChoices (CommandAutoCompleteInteractionEvent event, List<Command.Choice> choices) {
        return AutoComplete.filterChoices(event.getFocusedOption().getValue().toLowerCase(), choices);
    }

    /**
     * Filters specific filter string on the user's current input<br>
     *
     * <p><b>Example</b><br>
     * <pre>
     * {@code return event.replyChoises(AutoComplete.filterChoises("test", choises));}
     * </pre>
     * @param filter String to filter
     * @param choices List of {@link Command.Choice}
     * @return Filtered List of {@link  Command.Choice}
     */
    public static List<Command.Choice> filterChoices(String filter, List<Command.Choice> choices) {
        choices.removeIf(choice -> !choice.getName().toLowerCase().contains(filter.toLowerCase()));
        return choices;
    }
}
