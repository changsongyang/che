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
package org.eclipse.che.api.devfile.server.model.impl;

import org.eclipse.che.api.core.model.workspace.devfile.Command;
import org.eclipse.che.api.core.model.workspace.devfile.Component;
import org.eclipse.che.api.core.model.workspace.devfile.Devfile;
import org.eclipse.che.api.core.model.workspace.devfile.Metadata;
import org.eclipse.che.api.core.model.workspace.devfile.PersistentDevfile;
import org.eclipse.che.api.core.model.workspace.devfile.Project;
import org.eclipse.che.api.workspace.server.model.impl.devfile.DevfileImpl;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity(name = "PersistentDevfile")
@NamedQueries({
  @NamedQuery(name = "PersistentDevfile.getAll", query = "SELECT d FROM PersistentDevfile d"),
  @NamedQuery(
      name = "PersistentDevfile.getAllCount",
      query = "SELECT COUNT(d) FROM PersistentDevfile d"),
})
public class PersistentDevfileImpl implements PersistentDevfile {

  @Id
  @Column(name = "id")
  private String id;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "devfile_id")
  private DevfileImpl devfile;

  public PersistentDevfileImpl() {}

  public PersistentDevfileImpl(String id, Devfile devfile) {
    this.devfile = new DevfileImpl(devfile);
    this.id = id;
  }

  public PersistentDevfileImpl(PersistentDevfileImpl devfile) {
    this.devfile = devfile.devfile;
    this.id = devfile.getId();
  }

  @Override
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getApiVersion() {
    return devfile.getApiVersion();
  }

  @Override
  public List<? extends Project> getProjects() {
    return devfile.getProjects();
  }

  @Override
  public List<? extends Component> getComponents() {
    return devfile.getComponents();
  }

  @Override
  public List<? extends Command> getCommands() {
    return devfile.getCommands();
  }

  @Override
  public Map<String, String> getAttributes() {
    return devfile.getAttributes();
  }

  @Override
  public Metadata getMetadata() {
    return devfile.getMetadata();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersistentDevfileImpl devfile1 = (PersistentDevfileImpl) o;
    return id.equals(devfile1.id) &&
            devfile.equals(devfile1.devfile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, devfile);
  }

  @Override
  public String toString() {
    return "PersistentDevfileImpl{" +
            "id='" + id + '\'' +
            ", devfile=" + devfile +
            '}';
  }
}
