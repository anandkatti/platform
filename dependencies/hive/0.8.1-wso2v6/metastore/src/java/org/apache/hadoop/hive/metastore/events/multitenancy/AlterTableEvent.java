/**
 * Licensed to the Apache Software Foundation (ASF) under one

 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hive.metastore.events.multitenancy;

import org.apache.hadoop.hive.metastore.MultitenantMetaStoreHandler;
import org.apache.hadoop.hive.metastore.api.Table;

public class AlterTableEvent extends ListenerEvent {

  private final Table newTable;
  private final Table oldTable;
  public AlterTableEvent(Table oldTable, Table newTable, boolean status,
                         MultitenantMetaStoreHandler handler) {

    super (status, handler);
    this.oldTable = oldTable;
    this.newTable = newTable;
  }

  /**
   * @return the old table
   */
  public Table getOldTable() {
    return oldTable;
  }

  /**
   * @return the new table
   */
  public Table getNewTable() {
    return newTable;
  }
}