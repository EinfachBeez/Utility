package me.einfachbeez.utility.jda.interaction.modal;

import me.einfachbeez.utility.jda.util.TriConsumer;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * @author EinfachBeez | https://github.com/EinfachBeez
 */
public class Modal {

    private final String title;
    private final String id;
    private final LinkedList<Function<Member, ItemComponent>> components;
    private TriConsumer<Member, List<ModalMapping>, ModalInteractionEvent> onSubmit;

    /**
     * Create a new Modal entity
     * @param title The title of the modal
     * @param id The id of the modal
     */
    public Modal(String title, String id) {
        this.title = title;
        this.id = id;
        components = new LinkedList<>();
    }

    /**
     * Add a new single component block to the modal for the user
     *
     * <p><b>Example</b><br>
     * <pre>
     * {@code  addComponent(member -> new TextInput#create(...));}
     * </pre>
     */
    public void addComponent(Function<Member, ItemComponent> component) {
        this.components.add(component);
    }

    /**
     * Add multiple component blocks to the modal for the user
     *
     * <p><b>Example</b><br>
     * <pre>
     * {@code  addComponents(member -> n);}
     * </pre>
     */
    public void addComponents(ItemComponent... itemComponents) {
        for (ItemComponent itemComponent : itemComponents) {
            addComponent(member -> itemComponent);
        }
    }

    /**
     * The method is called when the user press the submit button
     *
     * @param onSubmit Consumer for the member, the list of modal inputs from the user and the subsequent event, e.g.
     *                to send a success message.
     */
    public void onSubmit(TriConsumer<Member, List<ModalMapping>, ModalInteractionEvent> onSubmit) {
        this.onSubmit = onSubmit;
    }

    /**
     * Opens the modal for a specific member based on a command interaction
     */
    public void open(Member member, SlashCommandInteractionEvent event) {
        ModalManager.openModal(this, member, event);
    }

    /**
     * Opens the modal for a specific member based on a button interaction
     */
    public void open(Member member, ButtonInteractionEvent event) {
        ModalManager.openModal(this, member, event);
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public LinkedList<Function<Member, ItemComponent>> getComponents() {
        return components;
    }

    public TriConsumer<Member, List<ModalMapping>, ModalInteractionEvent> getOnSubmit() {
        return onSubmit;
    }
}
