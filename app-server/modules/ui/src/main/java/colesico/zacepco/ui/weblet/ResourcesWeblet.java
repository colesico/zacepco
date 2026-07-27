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
import colesico.framework.weblet.Weblet;
import colesico.framework.webstatic.StaticResource;


@Weblet
@Route("/resources/ui")
public class ResourcesWeblet {

    private final StaticResource staticResource;

    public ResourcesWeblet(StaticResource.Builder srBuilder) {
        staticResource = srBuilder.resourcesRoot("$ui/webpub").build();
    }

    @Route("*")
    public void get(String routeSuffix) {
        staticResource.send(routeSuffix);
    }

    @Route("/favicon.ico")
    public void favico() {
        staticResource.send("favicon.ico", false);
    }
}
