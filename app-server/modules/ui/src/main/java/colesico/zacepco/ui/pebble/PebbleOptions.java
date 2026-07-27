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

import colesico.framework.config.Config;
import colesico.framework.pebble.PebbleOptionsPrototype;
import io.pebbletemplates.pebble.PebbleEngine;
import io.pebbletemplates.pebble.extension.AbstractExtension;
import io.pebbletemplates.pebble.extension.Extension;
import io.pebbletemplates.pebble.extension.Filter;
import io.pebbletemplates.pebble.extension.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * Pebble conf
 */
@Config
public class PebbleOptions extends PebbleOptionsPrototype {

    @Override
    public void configure(PebbleEngine.Builder builder) {

        Extension extension = new AbstractExtension() {
            @Override
            public Map<String, Filter> getFilters() {
                Map<String, Filter> flts = new HashMap<>();
                flts.put(LocalDateFormatFilter.FILTER_NAME, new LocalDateFormatFilter());
                flts.put(LocalDateTimeFormatFilter.FILTER_NAME, new LocalDateTimeFormatFilter());
                return flts;
            }

            @Override
            public Map<String, Function> getFunctions() {
                Map<String, Function> funcs = new HashMap<>();
                funcs.put(BriefTextFunc.FUNCTION_NAME, new BriefTextFunc());
                return funcs;
            }
        };

        builder.extension(extension);
    }

}
