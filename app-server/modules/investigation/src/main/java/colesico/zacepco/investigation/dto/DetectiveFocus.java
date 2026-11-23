package colesico.zacepco.investigation.dto;

import colesico.zacepco.script.model.scriptdoc.TId;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record DetectiveFocus(
        Set<TId> ids,
        Set<TId.Type> types
) {

    public boolean isEmpty() {
        return ids.isEmpty() && types.isEmpty();
    }

    public int size() {
        return ids.size() + types.size();
    }

    @Override
    public String toString() {
        var idStream = (ids == null) ? Stream.<String>empty() : ids.stream().map(Object::toString);
        var typeStream = (types == null) ? Stream.<String>empty() : types.stream().map(type -> type.code + "?");

        return Stream.concat(idStream, typeStream)
                .collect(Collectors.joining(", "));
    }

    private static String[] splitTokens(String input) {
        if (input == null) {
            return new String[0];
        }
        String cleaned = input.replaceAll("^[,\\s]+|[,\\s]+$", "");
        if (cleaned.isEmpty()) {
            return new String[0];
        }
        return cleaned.split("[,\\s]+");
    }

    public static DetectiveFocus parse(String focus) {
        var tokens = splitTokens(focus);
        if (tokens.length == 0) {
            return new DetectiveFocus(Set.of(), Set.of());
        }
        var ids = new LinkedHashSet<TId>();
        var types = new LinkedHashSet<TId.Type>();
        for (var token : tokens) {
            try {
                if (token.length() == 1) {
                    token = token + '?';
                }
                if (token.charAt(1) == '?') {
                    types.add(TId.Type.of(token.charAt(0)));
                } else {
                    ids.add(TId.parse(token));
                }
            } catch (Exception e) {
                throw new DetectiveFocusParseException(token);
            }
        }
        return new DetectiveFocus(ids, types);
    }

    public static class DetectiveFocusParseException extends RuntimeException {
        public final String token;

        public DetectiveFocusParseException(String token) {
            this.token = token;
            super("Invalid token: '" + token + "'");
        }
    }
}
