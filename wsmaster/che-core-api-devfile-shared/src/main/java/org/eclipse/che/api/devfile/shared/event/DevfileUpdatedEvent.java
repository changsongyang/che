/*
 * Copyright (c) 2012-2018 Red Hat, Inc.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package org.eclipse.che.api.devfile.shared.event;

import org.eclipse.che.api.core.model.workspace.devfile.PersistentDevfile;
import org.eclipse.che.api.core.notification.EventOrigin;

/** Informs about persisted devfile update. */
@EventOrigin("devfile")
public class DevfileUpdatedEvent {
  private final PersistentDevfile persistentDevfile;

  public DevfileUpdatedEvent(PersistentDevfile persistentDevfile) {
    this.persistentDevfile = persistentDevfile;
  }

  public PersistentDevfile getPersistentDevfile() {
    return persistentDevfile;
  }
}