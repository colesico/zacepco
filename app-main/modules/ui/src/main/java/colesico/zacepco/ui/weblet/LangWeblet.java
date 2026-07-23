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

package colesico.zacepco.ui.weblet;


import colesico.framework.httprouter.Route;
import colesico.framework.profile.ProfileManager;
import colesico.framework.telehttp.response.RedirectResponse;
import colesico.framework.weblet.Weblet;

import java.util.Locale;

/**
 * @author Colesico
 */
@Weblet
@Route("/lang")
public class LangWeblet {

    private final ProfileManager profileManager;

    public LangWeblet(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    private void setLocale(String lang, String country) {
        var profile = profileManager.resolve();
        profile.setLocale(Locale.of(lang, country));
        profileManager.commit(profile);
    }

    public RedirectResponse ru(String redirect) {
        setLocale("ru", "RU");
        return RedirectResponse.of(redirect);
    }

    public RedirectResponse en(String redirect) {
        setLocale("en", "US");
        return RedirectResponse.of(redirect);
    }

}
