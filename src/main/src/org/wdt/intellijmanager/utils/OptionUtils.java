package org.wdt.intellijmanager.utils;

import org.apache.commons.cli.Option;

public class OptionUtils {
    public static Option getOption(String command, String longCommand) {
        return getOption(command, longCommand, false);
    }

    public static Option getOption(String command, String longCommand, boolean hasArgs) {
        return Option.builder(command).longOpt(longCommand).hasArg(hasArgs).required(false).build();
    }
}
