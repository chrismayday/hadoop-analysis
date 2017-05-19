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

import java.io.Closeable;
import java.io.IOException;

/**
 * This is the class for routine NameNode management.
 */
public class FSNameSystem implements NameSystem, Closeable {

  private boolean fsRunning = true;

  private static FSNameSystem nameSystem;

  /**
   * Load fs name system from disk files.
   * @return fs name system.
   * @throws Exception
   */
  static FSNameSystem loadFromDisk() throws Exception {
    if (nameSystem == null) {
      nameSystem = new FSNameSystem();
    }
    return nameSystem;
  }

  FSNameSystem() throws Exception {

  }

  /**
   * Check whether fs name system is running.
   * @return true if fs name system is running.
   */
  public boolean isRunning() {
    return fsRunning;
  }

  /**
   * Close fs name system.
   * @throws IOException
   */
  public void close() throws IOException {
    fsRunning = false;
    try {
      stopCommonServices();
    } catch (Exception ignored) {}

  }

  /**
   * Start all daemons for FS name system.
   * @throws Exception
   */
  public void startCommonServices() throws Exception {

  }

  /**
   * Stop all daemons for FS name system.
   * @throws Exception
   */
  public void stopCommonServices() throws Exception {

  }
}
