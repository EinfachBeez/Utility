package me.einfachbeez.utility.jda;

import me.einfachbeez.utility.jda.interaction.modal.ModalListener;
import net.dv8tion.jda.api.JDA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author EinfachBeez | https://github.com/EinfachBeez
 */
public class JDAManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDAManager.class);
    private final JDA api;

    public JDAManager(JDA api) {
        this.api = api;

        api.addEventListener(new ModalListener());
    }

    public JDA getApi() {
        return api;
    }

}
