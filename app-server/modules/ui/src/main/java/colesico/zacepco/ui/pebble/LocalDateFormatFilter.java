/*
 * Copyright © 2018-2020 Colesico
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package colesico.zacepco.ui.pebble;

import io.pebbletemplates.pebble.error.PebbleException;
import io.pebbletemplates.pebble.extension.Filter;
import io.pebbletemplates.pebble.template.EvaluationContext;
import io.pebbletemplates.pebble.template.PebbleTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class LocalDateFormatFilter implements Filter {

    public static final String FILTER_NAME = "localDateFormat";

    @Override
    public Object apply(Object input, Map<String, Object> args, PebbleTemplate pebbleTemplate, EvaluationContext evaluationContext, int lineNumber) throws PebbleException {
        if (input==null){
            return "";
        }
        LocalDate ld = (LocalDate) input;
        String pattern = (String) args.get("0");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String result = ld.format(formatter);
        return result;
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
