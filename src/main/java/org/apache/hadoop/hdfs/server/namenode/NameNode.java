/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hdfs.server.namenode;

import java.util.concurrent.atomic.AtomicBoolean;

public class NameNode {

  private FSNameSystem nameSystem;
  private AtomicBoolean started = new AtomicBoolean(true);

  public NameNode() throws Exception {
    initialize();
  }

  public static NameNode createNameNode() throws Exception {
    return new NameNode();
  }

  /**
   * NameNode process entrance.
   */
  public static void main(String[] args) throws Exception {
    NameNode nameNode = createNameNode();
    nameNode.join();
  }

  /**
   * Stop name node server.
   */
  public void stop() {
    started.set(false);
  }

  /**
   * Initialize nameNode env.
   */
  private void initialize() throws Exception {
    loadNameSystem();
    startCommonServices();
  }

  private void loadNameSystem() throws Exception {
    this.nameSystem = FSNameSystem.loadFromDisk();
  }

  /**
   * Start nameNode's common services.
   * @throws Exception
   */
  private void startCommonServices() throws Exception {
    this.nameSystem.startCommonServices();
  }

  /**
   * Keep name node alive.
   * @throws Exception
   */
  private synchronized void join() throws Exception {
    while (started.get()) {
      wait();
    }
  }

}
