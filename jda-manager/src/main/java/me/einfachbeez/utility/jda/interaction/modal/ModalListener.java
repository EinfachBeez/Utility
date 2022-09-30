package me.einfachbeez.utility.jda.interaction.modal;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * @author EinfachBeez | https://github.com/EinfachBeez
 */
public class ModalListener extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        for (Map.Entry<Member, Modal> modalEntry : ModalManager.getModals()) {
            if (!modalEntry.getValue().getId().equals(event.getModalId())) return;
            if (event.getMember() == null) return;
            if (!modalEntry.getKey().getId().equals(event.getMember().getId())) return;

            List<ModalMapping> modalMappings = event.getValues();
            modalEntry.getValue().getOnSubmit().accept(event.getMember(), modalMappings, event);
        }
    }
}
