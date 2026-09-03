/*
 * Copyright (c) 2026 Vladlen V. Larionov.
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may obtain a copy of the License at http://apache.org
 */

package colesico.zacepco.investigation.srv.dao;

import colesico.framework.jdbirec.RecordKit;
import colesico.framework.jdbirec.RecordKitApi;
import colesico.zacepco.investigation.srv.model.Detective;

@RecordKit
public interface DetectiveRK extends RecordKitApi<Detective> {
}
