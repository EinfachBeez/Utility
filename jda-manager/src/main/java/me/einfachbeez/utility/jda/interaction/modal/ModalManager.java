package me.einfachbeez.utility.jda.interaction.modal;

import me.einfachbeez.utility.jda.interaction.modal.Modal;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author EinfachBeez | https://github.com/EinfachBeez
 */
public class ModalManager {

    private static final ArrayList<Map.Entry<Member, Modal>> modals = new ArrayList<>();

    public static void openModal(Modal modal, Member member, Interaction interaction) {
        LinkedList<ItemComponent> components = new LinkedList<>();
        modal.getComponents().forEach(component -> components.add(component.apply(member)));

        net.dv8tion.jda.api.interactions.components.Modal.Builder modalBuilder =
                net.dv8tion.jda.api.interactions.components.Modal.create(modal.getId(),
                modal.getTitle());

        components.forEach(component -> modalBuilder.addActionRows(ActionRow.of(component)));

        if (interaction instanceof SlashCommandInteractionEvent commandEvent) {
            commandEvent.replyModal(modalBuilder.build()).queue();
        } else if (interaction instanceof ButtonInteraction buttonInteraction) {
            buttonInteraction.replyModal(modalBuilder.build()).queue();
        } else {
            throw new IllegalStateException("Invalid interaction event to open the modal");
        }

        modals.add(Map.entry(member, modal));
    }

    public static ArrayList<Map.Entry<Member, Modal>> getModals() {
        return modals;
    }
}
